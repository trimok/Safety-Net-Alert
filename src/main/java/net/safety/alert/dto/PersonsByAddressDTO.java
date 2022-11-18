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
public class PersonsByAddressDTO {
	/**
	 * 
	 */
	List<PersonByAddressDTO> persons;

	/**
	 * @param persons
	 * @return
	 */
	public static PersonsByAddressDTO toPersonsByAddressDTO(List<PersonByAddressDTO> persons) {
		return new PersonsByAddressDTO(persons);
	}
}
