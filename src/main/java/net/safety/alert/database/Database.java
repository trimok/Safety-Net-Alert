package net.safety.alert.database;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.Data;
import net.safety.alert.constants.FilenameConstants;
import net.safety.alert.dto.JsonDTO;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;
import net.safety.alert.util.FileUtil;
import net.safety.alert.util.StringsUtil;

@Data
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Database {

	// Global structure for persons
	private Map<PersonId, Person> personsMap = new HashMap<>();
	// Global structure for addresses
	private Map<String, Address> addressesMap = new HashMap<>();
	// Global structure for firestations
	private Map<String, FireStation> fireStationsMap = new HashMap<>();

	public void init() {

		// Step 1 : reading the data and putting this into a temporatyJsonData structure
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		InputStream is = null;
		JsonDTO jsonDTO = null;

		try {
			is = FileUtil.getStreamFromFilename(this, FilenameConstants.JSON_DATA_FILE);
			jsonDTO = objectMapper.readValue(is, new TypeReference<JsonDTO>() {
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Building the map fireStationsMap
		jsonDTO.getFirestations().forEach(f -> fireStationsMap.put(f.getStation(), new FireStation(f.getStation())));

		// Building the map addressesMap
		// The FireStation objects must be retrieving from the fireStationsMap
		jsonDTO.getFirestations().forEach(f -> addressesMap.put(f.getAddress(),
				new Address(f.getAddress(), fireStationsMap.get(f.getStation()))));

		// Building the persons Map
		jsonDTO.getPersons().forEach(p -> personsMap.put(p.getPersonId(), p.toPerson()));

		// Update the persons map with the (global structure) address
		triggerAddressDatabaseForAllPerson();

		// Update the persons map with allergies and medications
		final JsonDTO finalJsonDTO = jsonDTO;
		personsMap.values()
				.forEach(p -> finalJsonDTO.getMedicalrecords().stream().filter(
						m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName()))
						.forEach(m -> {
							p.setAllergies(m.getMapAllergies());
							p.setMedications(m.getMapMedications());
							p.setBirthdate(m.getBirthdate());
						}));

		System.out.println("stop");
	}

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

	public void triggerAddressDatabaseForAllPerson() {
		personsMap.values().forEach(p -> triggerAddressForPerson(p));
	}

	public void raz() {
		personsMap = new HashMap<>();
		addressesMap = new HashMap<>();
		fireStationsMap = new HashMap<>();
	}
}
