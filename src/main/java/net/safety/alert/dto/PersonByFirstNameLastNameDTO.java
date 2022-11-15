package net.safety.alert.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.model.Person;
import net.safety.alert.util.DateUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonByFirstNameLastNameDTO {
	private String lastName;
	private String address;
	private Long age;
	private String email;
	List<String> allergies = new ArrayList<>();
	Map<String, String> medications = new HashMap<>();

	public static PersonByFirstNameLastNameDTO toPersonByFirstNameLastNameDTO(Person person) {
		PersonByFirstNameLastNameDTO personByFirstNameLastNameDTO = new PersonByFirstNameLastNameDTO();

		personByFirstNameLastNameDTO.setLastName(person.getLastName());
		Address personAddress = person.getAddress();
		if (personAddress != null) {
			personByFirstNameLastNameDTO.setAddress(personAddress.getName());
		}
		personByFirstNameLastNameDTO.setAge(DateUtil.getAge(person.getBirthdate()));
		personByFirstNameLastNameDTO.setEmail(person.getEmail());
		person.getAllergies().values()
				.forEach(a -> personByFirstNameLastNameDTO.getAllergies().add(a == null ? "" : a.getName()));
		person.getMedications().values()
				.forEach(m -> personByFirstNameLastNameDTO.getMedications().put(m.getName(), m.getQuantity()));

		return personByFirstNameLastNameDTO;
	}
}
