package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsByFirstNameLastNameDTO {
	/**
	 * 
	 */
	private List<PersonByFirstNameLastNameDTO> persons;

	/**
	 * @param persons
	 * @return
	 */
	public static PersonsByFirstNameLastNameDTO toPersonsByFirstNameLastNameDTO(
			List<PersonByFirstNameLastNameDTO> persons) {
		return new PersonsByFirstNameLastNameDTO(persons);
	}
}
