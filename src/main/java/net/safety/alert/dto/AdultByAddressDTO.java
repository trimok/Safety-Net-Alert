package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultByAddressDTO {
	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String lastName;

	/**
	 * @param person
	 *            : a Person object
	 * @return : a AdultByAddressDTO object
	 */
	public static AdultByAddressDTO toAdultDTO(Person person) {

		AdultByAddressDTO adultByAddressDTO = new AdultByAddressDTO();
		adultByAddressDTO.setFirstName(person.getFirstName());
		adultByAddressDTO.setLastName(person.getLastName());
		return adultByAddressDTO;
	}
}
