package net.safety.alert.dto;

import java.util.List;

import lombok.Data;

@Data
public class JsonDTO {
	private List<PersonDTO> persons;
	private List<FireStationDTO> firestations;
	private List<MedicalRecordDTO> medicalrecords;
}
