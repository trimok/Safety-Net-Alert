package net.safety.alert.dto;

import static net.safety.alert.constants.SeparatorConstants.COLONS_SEPARATOR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Allergie;
import net.safety.alert.model.Medication;
import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;
import net.safety.alert.util.StringsUtil;
/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {
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
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate birthdate;
	/**
	 * 
	 */
	private List<String> medications = new ArrayList<>();
	/**
	 * 
	 */
	private List<String> allergies = new ArrayList<>();

	/**
	 * @param firstName
	 *            : the first name
	 * @param lastName
	 *            : the last name
	 */
	public MedicalRecordDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.medications = new ArrayList<>();
		this.allergies = new ArrayList<>();
	}

	/**
	 * Deserialization of a String with format "name :quantity" into a map entry (string, Medication)
	 * 
	 * @return : a Map (String, Medication) representing medications
	 */
	@JsonIgnore
	public Map<String, Medication> getMedicationsMap() {
		Map<String, Medication> medicationsMap = new HashMap<>();
		medications.forEach(m -> {
			String[] infos = m.split(COLONS_SEPARATOR);
			medicationsMap.put(infos[0], new Medication(infos[0], infos[1]));
		});
		return medicationsMap;
	}

	/**
	 * Deserialization of a String with format "name" into a map entry (string, Allervie)
	 * 
	 * @return : a Map (String, Allergie) representing allergies
	 */
	@JsonIgnore
	public Set<Allergie> getAllergiesSet() {
		Set<Allergie> allergiesSet = new HashSet<>();
		allergies.forEach(m -> {
			allergiesSet.add(new Allergie(m));
		});
		return allergiesSet;
	}

	/**
	 * @param person
	 *            : the person
	 */
	public MedicalRecordDTO(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthdate = person.getBirthdate();
		person.getAllergies().forEach(a -> this.allergies.add(a.getName()));
		person.getMedications().values().forEach(a -> this.medications.add(a.getName() + ":" + a.getQuantity()));
	}

	/**
	 * @param person
	 *            : a Person object
	 * @return :a MedicalRecordDTO object
	 */
	public static MedicalRecordDTO toMedicalRecordDTO(Person person) {
		return new MedicalRecordDTO(person);
	}

	/**
	 * @return : a Person object
	 */
	public Person toPerson() {
		Person person = new Person(this.getFirstName(), this.getLastName(), this.getBirthdate(),
				this.getMedicationsMap(), this.getAllergiesSet());

		return person;
	}

	/**
	 * @return : the validity of the MedicalRecordDTO object
	 */
	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(firstName) && StringsUtil.isValid(lastName);
	}

	/**
	 * @return : the key of the Person (a PersonID object)
	 */
	@JsonIgnore
	public PersonId getPersonId() {
		return new PersonId(firstName, lastName);
	}
}
