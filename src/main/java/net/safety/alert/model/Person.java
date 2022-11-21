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
	 * @return : the validity of the Person (valid key)
	 */
	@JsonIgnore
	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}

	/**
	 * @param person
	 *            : the Person object which is updating
	 * @return : the Person object which is updated
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
	 *            : the first name
	 * @param lastName
	 *            : the last name
	 * @param birthdate
	 *            : the birthdate
	 * @param medications
	 *            : the medications
	 * @param allergies
	 *            : the allergies
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
	 *            : the Person object which is patching
	 * @return : the Person object which is patched
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
	 *            : the Person object which is updating
	 * @return : the Person object which is updated
	 */
	public Person updateMedicalRecords(Person person) {
		this.setBirthdate(person.getBirthdate());
		this.setAllergies(person.getAllergies());
		this.setMedications(person.getMedications());
		return this;
	}

	/**
	 * @param person
	 *            : the Person object which is patching the Medical Record Data
	 * @return : the Person object which is patched with the Medical Record Data
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
	 * @return : true if the medical record data do not exis, false elsewhere
	 */
	public boolean isMedicalRecordEmpty() {
		return birthdate == null && (allergies == null || allergies.isEmpty())
				&& (medications == null || medications.isEmpty());
	}

	/**
	 * @return : a Person object
	 */
	public Person emptyMedicalRecord() {
		this.allergies = new HashMap<>();
		this.medications = new HashMap<>();
		this.birthdate = null;

		return this;
	}

	/**
	 * @return : a PersonId object (the key of the Person object)
	 */
	@JsonIgnore
	public PersonId getPersonId() {
		return new PersonId(firstName, lastName);
	}
}
