package net.safety.alert.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Person;
import net.safety.alert.util.DateUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildrenByAddressDTO {
	private String firstName;
	private String lastName;
	private Long age;

	public static ChildrenByAddressDTO toChildrenByAddressDTO(Person person) {

		ChildrenByAddressDTO childrenByAddressDTO = new ChildrenByAddressDTO();
		childrenByAddressDTO.setFirstName(person.getFirstName());
		childrenByAddressDTO.setLastName(person.getLastName());
		childrenByAddressDTO.setAge(DateUtil.getAge(person.getBirthdate()));

		return childrenByAddressDTO;
	}
}