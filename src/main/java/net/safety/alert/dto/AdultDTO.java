package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdultDTO {
	private String firstName;
	private String lastName;

	public static AdultDTO toAdultDTO(Person person) {

		AdultDTO adultDTO = new AdultDTO();
		adultDTO.setFirstName(person.getFirstName());
		adultDTO.setLastName(person.getLastName());
		return adultDTO;
	}
}
