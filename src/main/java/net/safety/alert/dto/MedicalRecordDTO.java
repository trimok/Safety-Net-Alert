package net.safety.alert.dto;

import static net.safety.alert.constants.SeparatorConstants.COLONS_SEPARATOR;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.model.Allergie;
import net.safety.alert.model.Medication;
import net.safety.alert.model.Person;
import net.safety.alert.util.StringsUtil;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordDTO {
	private String firstName;
	private String lastName;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate birthdate;
	private List<String> medications = new ArrayList<>();
	private List<String> allergies = new ArrayList<>();

	@JsonIgnore
	public Map<String, Medication> getMapMedications() {
		Map<String, Medication> mapMedications = new HashMap<>();
		medications.forEach(m -> {
			String[] infos = m.split(COLONS_SEPARATOR);
			mapMedications.put(infos[0], new Medication(infos[0], infos[1]));
		});
		return mapMedications;
	}

	@JsonIgnore
	public Map<String, Allergie> getMapAllergies() {
		Map<String, Allergie> mapAllergies = new HashMap<>();
		allergies.forEach(m -> {
			mapAllergies.put(m, new Allergie(m));
		});
		return mapAllergies;
	}

	public MedicalRecordDTO(Person person) {
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.birthdate = person.getBirthdate();
		person.getAllergies().values().forEach(a -> this.allergies.add(a.getName()));
		person.getMedications().values().forEach(a -> this.medications.add(a.getName() + ":" + a.getQuantity()));
	}

	public static MedicalRecordDTO toMedicalRecordDTO(Person person) {
		return new MedicalRecordDTO(person);
	}

	public Person toPerson() {
		Person person = new Person(this.getFirstName(), this.getLastName(), this.getBirthdate(),
				this.getMapMedications(), this.getMapAllergies());

		return person;
	}

	@JsonIgnore
	public boolean isValid() {
		return StringsUtil.isValid(firstName) && StringsUtil.isValid(lastName);
	}
}
