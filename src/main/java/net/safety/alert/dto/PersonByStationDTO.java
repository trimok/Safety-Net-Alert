package net.safety.alert.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Person;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonByStationDTO {
	private String firstName;
	private String lastName;
	private String phone;
	@JsonIgnore
	public LocalDate birthdate;
	private String address;

	public static PersonByStationDTO toPersonByStationDTO(Person person) {
		return new PersonByStationDTO(person.getFirstName(), person.getLastName(), person.getPhone(),
				person.getBirthdate(), (person.getAddress() != null ? person.getAddress().getName() : ""));
	}
}
