package net.safety.alert.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Address;
import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String lastName;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private String zip;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private String email;
	/**
	 * 
	 */
	private String address;

	/**
	 * @param firstName
	 *            : the first name
	 * @param lastName
	 *            : the last name
	 */
	public PersonDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * @return : a PersonId object (the key of the Person Object)
	 */
	@JsonIgnore
	public PersonId getPersonId() {
		return new PersonId(firstName, lastName);
	}

	/**
	 * @return : a Person object
	 */
	public Person toPerson() {
		Person person = new Person(firstName, lastName, city, zip, phone, email, null, new Address(address, null), null,
				null);
		return person;
	}

	/**
	 * @param person
	 *            a Person object
	 * @return : a PersonDTO object
	 */
	public static PersonDTO toPersonDTO(Person person) {
		return new PersonDTO(person.getFirstName(), person.getLastName(), person.getCity(), person.getZip(),
				person.getPhone(), person.getEmail(), person.getAddress() != null ? person.getAddress().getName() : "");
	}
}
