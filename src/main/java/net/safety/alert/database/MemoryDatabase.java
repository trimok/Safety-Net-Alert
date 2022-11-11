package net.safety.alert.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import net.safety.alert.dto.JsonDTO;
import net.safety.alert.model.Person;

@Data
public class MemoryDatabase {
	private List<Person> persons;
	private Map<String, String> fireStationsMap = new HashMap<>();
	public static MemoryDatabase instance = new MemoryDatabase();

	public static MemoryDatabase getInstance() {
		return instance;
	}
	public void init(JsonDTO jsonDTO) {
		final JsonDTO finalJsonDTO = jsonDTO;

		// Build the map address / stations
		jsonDTO.getFirestations().forEach(f -> fireStationsMap.put(f.getAddress(), f.getStation()));

		// Update persons with allergies and medications
		this.persons = jsonDTO.getPersons();
		this.persons.forEach(p -> finalJsonDTO.getMedicalrecords().stream()
				.filter(m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName()))
				.forEach(m -> {
					p.setAllergies(m.getAllergies());
					p.setMedications(m.getMapMedications());
					p.setBirthdate(m.getBirthdate());
				}));

		// Synchronize the station attribute for the persons
		synchronizeGlobalAddressStationDatabase();
	}

	public void synchronizeGlobalAddressStationDatabase() {
		persons.forEach(p -> p.updateStation(fireStationsMap.getOrDefault(p.getAddress(), null)));
	}

	public void synchronizeAllAddressStationDatabase(List<Person> personList) {
		personList.forEach(p -> p.updateStation(fireStationsMap.getOrDefault(p.getAddress(), null)));
	}

	public void synchronizeAddressStationDatabase(Person person) {
		person.updateStation(fireStationsMap.getOrDefault(person.getAddress(), null));
	}

}
