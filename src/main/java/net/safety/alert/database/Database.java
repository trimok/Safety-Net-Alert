package net.safety.alert.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.safety.alert.config.Mapper;
import net.safety.alert.constants.FilenameConstants;
import net.safety.alert.dto.JsonDTO;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;
import net.safety.alert.util.FileUtil;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Slf4j
@Getter
@Setter
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Database {

	/**
	 * 
	 */
	@Autowired
	private Mapper objectMapper;

	/**
	 * Global structure for persons
	 */
	private Map<PersonId, Person> personsMap = new HashMap<>();
	/**
	 * Global structure for addresses
	 */
	private Map<String, Address> addressesMap = new HashMap<>();
	/*
	 * Global structure for firestations
	 */
	private Map<String, FireStation> fireStationsMap = new HashMap<>();

	/**
	 * The initialisation of the database
	 */
	public void init() {

		// Step 1 : basic reading the data and putting this into a temporaty JsonData structure
		InputStream is = null;
		JsonDTO jsonDTO = null;

		try {
			is = FileUtil.getStreamFromFilename(this, FilenameConstants.JSON_DATA_FILE);
			jsonDTO = objectMapper.readValue(is, new TypeReference<JsonDTO>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}

		// Step 2 : Operations to build a coherent database
		try {
			// Building the map fireStationsMap
			jsonDTO.getFirestations()
					.forEach(f -> fireStationsMap.put(f.getStation(), new FireStation(f.getStation())));

			// Building the map addressesMap
			// The FireStation objects must be retrieving from the fireStationsMap
			jsonDTO.getFirestations().forEach(f -> addressesMap.put(f.getAddress(),
					new Address(f.getAddress(), fireStationsMap.get(f.getStation()))));

			// Building the persons Map
			jsonDTO.getPersons().forEach(p -> personsMap.put(p.getPersonId(), p.toPerson()));

			// Completing the personsMap with medical records if medicalRecord does not match to an existing person
			jsonDTO.getMedicalrecords().stream().forEach(m -> personsMap.putIfAbsent(m.getPersonId(), m.toPerson()));

			// Update the persons map with the (global structure) address
			triggerAddressDatabaseForAllPerson();

			// Update the persons map with allergies and medications
			final JsonDTO finalJsonDTO = jsonDTO;
			personsMap.values()
					.forEach(p -> finalJsonDTO.getMedicalrecords().stream().filter(
							m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName()))
							.forEach(m -> {
								p.setAllergies(m.getAllergiesObjects());
								p.setMedications(m.getMedicationsObjects());
								p.setBirthdate(m.getBirthdate());
							}));

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * At each operation on an object Person which is impacting the address field, we must synchronize with the global
	 * address structure
	 * 
	 * @param person
	 *            : a Person object
	 */
	public void triggerAddressForPerson(Person person) {
		// Is the adress already existing
		Address address = person.getAddress();
		if (address != null && StringsUtil.isValid(address.getName())) {
			Address addressDatabase = addressesMap.get(address.getName());
			if (addressDatabase == null) {
				// Creation of the new adress object
				addressesMap.put(address.getName(), address);

			} else {
				// We make the link with the already existing Adress object
				person.setAddress(addressDatabase);
			}
		}
	}

	/**
	 * Global synchronization between the Persons and the Addresses
	 */
	public void triggerAddressDatabaseForAllPerson() {
		personsMap.values().forEach(p -> triggerAddressForPerson(p));
	}

	/**
	 * 
	 * @return : the collection of the addresses
	 */
	public Collection<Address> getAddresses() {
		return addressesMap.values();
	}

	/**
	 * 
	 * @param key
	 *            : the (String) key/name of the address
	 * @param address
	 *            : the Address object
	 */
	public void saveAddress(String key, Address address) {
		addressesMap.put(key, address);
	}

	/**
	 * 
	 * @param key
	 *            : the key (name) of the address
	 * @return : the Address object
	 */
	public Address getAddress(String key) {
		return addressesMap.get(key);
	}

	/**
	 * 
	 * @return : the collection of the firestations
	 */
	public Collection<FireStation> getFireStations() {
		return fireStationsMap.values();
	}

	/**
	 * 
	 * @param key
	 *            : the key (name) of the firestation
	 * @return : the FireStation object
	 */
	public FireStation getFireStation(String key) {
		return fireStationsMap.get(key);
	}

	/**
	 * 
	 * @param key
	 *            : the (String) key/name of the firestation
	 * @param fireStation
	 *            : the FireStation object
	 */
	public void saveFireStation(String key, FireStation fireStation) {
		fireStationsMap.put(key, fireStation);
	}

	/**
	 * 
	 * @return : the collection of the persons
	 */
	public Collection<Person> getPersons() {
		return personsMap.values();
	}

	/**
	 * 
	 * @param personId
	 *            : the key (PersonId) of the Person
	 * @return : the Person object
	 */
	public Person getPerson(PersonId personId) {
		return personsMap.get(personId);
	}

	/**
	 * 
	 * @param personId
	 *            : the PersonId key (first Name + last Name) of the person
	 */
	public void savePerson(PersonId personId, Person person) {
		personsMap.put(personId, person);

		// Sychronisation with the address list
		triggerAddressForPerson(person);
	}

	/**
	 * 
	 * @param personId
	 *            : the key of the Person to be deleted
	 */
	public void deletePerson(PersonId personId) {

		personsMap.remove(personId);
	}

	/**
	 * Emptying the database
	 */
	public void empty() {
		personsMap = new HashMap<>();
		addressesMap = new HashMap<>();
		fireStationsMap = new HashMap<>();
	}

	/**
	 * Reset of the database
	 */
	public void reset() {
		empty();
		init();
		log.info("Database reset.");
	}
}
