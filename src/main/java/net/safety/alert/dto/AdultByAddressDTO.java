package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultByAddressDTO {
	private String firstName;
	private String lastName;

	public static AdultByAddressDTO toAdultDTO(Person person) {

		AdultByAddressDTO adultByAddressDTO = new AdultByAddressDTO();
		adultByAddressDTO.setFirstName(person.getFirstName());
		adultByAddressDTO.setLastName(person.getLastName());
		return adultByAddressDTO;
	}
}
