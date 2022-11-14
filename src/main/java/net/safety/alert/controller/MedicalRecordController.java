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

@RestController
public class MedicalRecordController {

	@Autowired
	IMedicalRecordService medicalRecordService;

	@PostMapping("/medicalRecord")
	public MedicalRecordDTO createMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return MedicalRecordDTO
				.toMedicalRecordDTO(medicalRecordService.createMedicalRecord(medicalRecordDTO.toPerson()));
	}

	@PutMapping("/medicalRecord")
	public MedicalRecordDTO updateMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return MedicalRecordDTO
				.toMedicalRecordDTO(medicalRecordService.updateMedicalRecord(medicalRecordDTO.toPerson()));
	}

	@PatchMapping("/medicalRecord")
	public MedicalRecordDTO patchMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		return MedicalRecordDTO
				.toMedicalRecordDTO(medicalRecordService.patchMedicalRecord(medicalRecordDTO.toPerson()));
	}

	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestBody MedicalRecordDTO medicalRecordDTO) {
		medicalRecordService.deleteMedicalRecord(medicalRecordDTO.toPerson());
	}
}