package net.safety.alert.dto;

import java.util.List;

import lombok.Data;

/**
 * @author trimok
 *
 */
@Data
public class JsonDTO {
	/**
	 * 
	 */
	private List<PersonDTO> persons;
	/**
	 * 
	 */
	private List<MappingAddressStationDTO> firestations;
	/**
	 * 
	 */
	private List<MedicalRecordDTO> medicalrecords;
}
