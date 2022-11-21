package net.safety.alert.dto;

import static net.safety.alert.constants.SeparatorConstants.COLONS_SEPARATOR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	 * Deserialization of a String with format "name :quantity" into a Medication object
	 * 
	 * @return : a List of Medication objects
	 */
	@JsonIgnore
	public List<Medication> getMedicationsObjects() {
		List<Medication> medicationsObjectList = new ArrayList<>();
		medications.forEach(m -> {
			String[] infos = m.split(COLONS_SEPARATOR);
			medicationsObjectList.add(new Medication(infos[0], infos[1]));
		});
		return medicationsObjectList;
	}

	/**
	 * Deserialization of a String with format "name" into a Allergie object
	 * 
	 * @return : a List of Allergie objects
	 */
	@JsonIgnore
	public List<Allergie> getAllergiesObjects() {
		List<Allergie> allergiesObjectList = new ArrayList<>();
		allergies.forEach(m -> {
			allergiesObjectList.add(new Allergie(m));
		});
		return allergiesObjectList;
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
		person.getMedications().forEach(a -> this.medications.add(a.getName() + ":" + a.getQuantity()));
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
				this.getMedicationsObjects(), this.getAllergiesObjects());

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
