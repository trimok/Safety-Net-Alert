package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.service.IMedicalRecordService;

/**
 * @author trimok
 *
 */
@RestController
public class MedicalRecordController {

	/**
	 * 
	 */
	@Autowired
	IMedicalRecordService medicalRecordService;

	/**
	 * @param medicalRecordDTO
	 *            : a medicalRecordDTO Object
	 * @return : a medicalRecordDTO Object
	 */
	@PostMapping("/medicalRecord")
	public MedicalRecordDTO createMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return medicalRecordService.createMedicalRecord(medicalRecordDTO);
	}

	/**
	 * @param medicalRecordDTO
	 *            : a medicalRecordDTO Object
	 * @return : a medicalRecordDTO Object
	 */
	@PutMapping("/medicalRecord")
	public MedicalRecordDTO updateMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return medicalRecordService.updateMedicalRecord(medicalRecordDTO);
	}

	/**
	 * @param medicalRecordDTO
	 *            : a medicalRecordDTO Object
	 * @return : a medicalRecordDTO Object
	 */
	@PatchMapping("/medicalRecord")
	public MedicalRecordDTO patchMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return medicalRecordService.patchMedicalRecord(medicalRecordDTO);
	}

	/**
	 * @param medicalRecordDTO
	 *            : : a medicalRecordDTO Object
	 */
	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		medicalRecordService.deleteMedicalRecord(medicalRecordDTO);
	}
}