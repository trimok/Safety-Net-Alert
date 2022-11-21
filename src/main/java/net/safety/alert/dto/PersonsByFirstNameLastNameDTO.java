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
	 *            : a list of PersonByFirstNameLastNameDTO object
	 * @return : a PersonsByFirstNameLastNameDTO object
	 */
	public static PersonsByFirstNameLastNameDTO toPersonsByFirstNameLastNameDTO(
			List<PersonByFirstNameLastNameDTO> persons) {
		return new PersonsByFirstNameLastNameDTO(persons);
	}
}
