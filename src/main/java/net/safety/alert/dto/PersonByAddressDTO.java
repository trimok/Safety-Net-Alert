package net.safety.alert.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PersonByAddressDTO {
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
	List<String> allergies = new ArrayList<>();
	/**
	 * 
	 */
	Map<String, String> medications = new HashMap<>();

	/**
	 * @param person
	 *            : a Person object
	 * @return : a PersonByAddressDTO object
	 */
	public static PersonByAddressDTO toPersonByAddressDTO(Person person) {
		PersonByAddressDTO personByAddressDTO = new PersonByAddressDTO();

		Address personAddress = person.getAddress();
		if (personAddress != null) {
			FireStation personFireStation = person.getAddress().getFireStation();
			if (personFireStation != null) {
				personByAddressDTO.setFireStation(personFireStation.getId());
			}
		}

		personByAddressDTO.setLastName(person.getLastName());
		personByAddressDTO.setPhone(person.getPhone());
		personByAddressDTO.setAge(DateUtil.getAge(person.getBirthdate()));
		person.getAllergies().forEach(a -> personByAddressDTO.getAllergies().add(a == null ? "" : a.getName()));
		person.getMedications().values()
				.forEach(m -> personByAddressDTO.getMedications().put(m.getName(), m.getQuantity()));

		return personByAddressDTO;
	}
}
