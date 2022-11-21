package net.safety.alert.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.model.Person;
import net.safety.alert.util.DateUtil;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonGroupByAddressByListStationDTO {
	/**
	 * 
	 */
	private String fireStation;
	/**
	 * 
	 */
	private String lastName;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private Long age;
	/**
	 * 
	 */
	@JsonIgnore
	private String address;
	/**
	 * 
	 */
	List<String> allergies = new ArrayList<>();
	/**
	 * 
	 */
	Map<String, String> medications = new HashMap<>();

	/**
	 * @param person
	 *            a Person object
	 * @return : a PersonGroupByAddressByListStationDTO object
	 */
	public static PersonGroupByAddressByListStationDTO toPersonGroupByAddressByListStationDTO(Person person) {
		PersonGroupByAddressByListStationDTO personGroupByAddressByListStationDTO = new PersonGroupByAddressByListStationDTO();

		Address personAddress = person.getAddress();
		if (personAddress != null) {
			personGroupByAddressByListStationDTO.setAddress(personAddress.getName());

			FireStation personFireStation = personAddress.getFireStation();
			if (personFireStation != null) {
				personGroupByAddressByListStationDTO.setFireStation(personFireStation.getId());
			}
		}

		personGroupByAddressByListStationDTO.setLastName(person.getLastName());
		personGroupByAddressByListStationDTO.setPhone(person.getPhone());
		personGroupByAddressByListStationDTO.setAge(DateUtil.getAge(person.getBirthdate()));
		person.getAllergies()
				.forEach(a -> personGroupByAddressByListStationDTO.getAllergies().add(a == null ? "" : a.getName()));
		person.getMedications().values()
				.forEach(m -> personGroupByAddressByListStationDTO.getMedications().put(m.getName(), m.getQuantity()));

		return personGroupByAddressByListStationDTO;
	}
}
