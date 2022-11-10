package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.MedicalRecord;
import net.safety.alert.service.IMedicalRecordService;

@RestController
public class MedicalRecordController {
	@Autowired
	IMedicalRecordService medicalRecordService;

	@PostMapping("/medicalRecord")
	public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.createMedicalRecord(medicalRecord);
	}

	@PutMapping("/medicalRecord")
	public MedicalRecord updateMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.updateMedicalRecord(medicalRecord);
	}

	@PatchMapping("/medicalRecord")
	public MedicalRecord patchMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		return medicalRecordService.patchMedicalRecord(medicalRecord);
	}

	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		medicalRecordService.deleteMedicalRecord(medicalRecord);
	}
}