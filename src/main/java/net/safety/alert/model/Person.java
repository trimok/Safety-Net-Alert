package net.safety.alert.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

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
	private LocalDate birthdate;
	/**
	 * 
	 */
	private Address address;
	/**
	 * 
	 */
	private Map<String, Medication> medications = new HashMap<>();
	/**
	 * 
	 */
	private Map<String, Allergie> allergies = new HashMap<>();

	/**
	 * @return
	 */
	@JsonIgnore
	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}

	/**
	 * @param person
	 * @return
	 */
	public Person updatePerson(Person person) {
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setAddress(person.getAddress());

		return this;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param medications
	 * @param allergies
	 */
	public Person(String firstName, String lastName, LocalDate birthdate, Map<String, Medication> medications,
			Map<String, Allergie> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	/**
	 * @param person
	 * @return
	 */
	public Person patchPerson(Person person) {
		String city = person.getCity();
		if (StringsUtil.isValid(city)) {
			this.setCity(city);
		}
		String zip = person.getZip();
		if (StringsUtil.isValid(zip)) {
			this.setZip(zip);
		}
		String phone = person.getPhone();
		if (StringsUtil.isValid(phone)) {
			this.setPhone(phone);
		}
		String email = person.getEmail();
		if (StringsUtil.isValid(email)) {
			this.setEmail(email);
		}
		Address address = person.getAddress();
		if (StringsUtil.isValid(address.getName())) {
			this.setAddress(address);
		}
		return this;
	}

	/**
	 * @param person
	 * @return
	 */
	public Person updateMedicalRecords(Person person) {
		this.setBirthdate(person.getBirthdate());
		this.setAllergies(person.getAllergies());
		this.setMedications(person.getMedications());
		return this;
	}

	/**
	 * @param person
	 * @return
	 */
	public Person patchMedicalRecordBy(Person person) {
		Map<String, Allergie> allergies = person.getAllergies();
		Map<String, Medication> medications = person.getMedications();

		if (allergies != null) {
			this.allergies.putAll(allergies);
		}
		if (medications != null) {
			this.medications.putAll(medications);
		}

		if (birthdate != null) {
			this.birthdate = person.getBirthdate();
		}

		return this;
	}

	/**
	 * @return
	 */
	public boolean isMedicalRecordEmpty() {
		return birthdate == null && (allergies == null || allergies.isEmpty())
				&& (medications == null || medications.isEmpty());
	}

	/**
	 * @return
	 */
	public Person emptyMedicalRecord() {
		this.allergies = new HashMap<>();
		this.medications = new HashMap<>();
		this.birthdate = null;

		return this;
	}

	/**
	 * @return
	 */
	@JsonIgnore
	public PersonId getPersonId() {
		return new PersonId(firstName, lastName);
	}
}
