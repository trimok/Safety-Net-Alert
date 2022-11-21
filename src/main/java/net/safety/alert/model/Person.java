package net.safety.alert.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private List<Medication> medications = new ArrayList<>();
	/**
	 * 
	 */
	private List<Allergie> allergies = new ArrayList<>();

	/**
	 * @return : the validity of the Person (valid key)
	 */
	@JsonIgnore
	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}

	/**
	 * Updating a (database) Person with a (new) Person object
	 * 
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
	 * Constructor
	 * 
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
	public Person(String firstName, String lastName, LocalDate birthdate, List<Medication> medications,
			List<Allergie> allergies) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}

	/**
	 * Patching a database Person with a new Person
	 * 
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
	 * Updating of the medical record data of a Person
	 * 
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
	 * Patching of the medical record data of a Person
	 * 
	 * @param person
	 *            : the Person object which is patching the Medical Record Data
	 * @return : the Person object which is patched with the Medical Record Data
	 */
	public Person patchMedicalRecordBy(Person person) {
		List<Allergie> allergies = person.getAllergies();
		List<Medication> medications = person.getMedications();

		if (allergies != null) {
			// If the allergies name is not in the list, the allergie is added
			List<Allergie> allergiesToAdd = allergies.stream()
					.filter(a -> this.allergies.stream().noneMatch(b -> b.getName().equals(a.getName()))).toList();

			if (allergiesToAdd != null && !allergiesToAdd.isEmpty()) {
				this.allergies.addAll(allergiesToAdd);
			}
		}

		if (medications != null) {
			// it the medications name is not in the list, the medication is added
			List<Medication> medicationsToAdd = medications.stream()
					.filter(a -> this.medications.stream().noneMatch(b -> b.getName().equals(a.getName()))).toList();

			if (medicationsToAdd != null && !medicationsToAdd.isEmpty()) {
				this.medications.addAll(medicationsToAdd);
			}

			// if the medications name is in the list, this.medication is updated medication
			this.medications = this.medications.stream().map(m -> {
				Optional<Medication> optional = medications.stream()
						.filter(n -> n.getName().equals(m.getName()) && !n.getQuantity().equals(m.getQuantity()))
						.findFirst();
				if (optional.isPresent()) {
					return optional.get();
				} else {
					return m;
				}
			}).toList();
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
		this.allergies = new ArrayList<>();
		this.medications = new ArrayList<>();
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
