package net.safety.alert.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonsByAddressDTO {
	List<PersonByAddressDTO> persons;

	public static PersonsByAddressDTO toPersonsByAddressDTO(List<PersonByAddressDTO> persons) {
		return new PersonsByAddressDTO(persons);
	}
}
