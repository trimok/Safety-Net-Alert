package net.safety.alert.util;

import java.util.List;

import lombok.Data;
import net.safety.alert.model.Person;

@Data
public class JsonDTO {
	private List<Person> persons;
	private List<FireStationDTO> firestations;
	private List<MedicalRecordDTO> medicalrecords;
}
