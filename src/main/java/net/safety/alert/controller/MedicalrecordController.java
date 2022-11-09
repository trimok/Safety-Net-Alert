package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.Medicalrecord;
import net.safety.alert.service.IMedicalrecordService;

@RestController
public class MedicalrecordController {
	@Autowired
	IMedicalrecordService medicalRecordService;

	@PostMapping("/medicalRecord")
	public Medicalrecord createMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		return medicalRecordService.createMedicalRecord(medicalRecord);
	}

	@PutMapping("/medicalRecord")
	public Medicalrecord updateMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		return medicalRecordService.updateMedicalRecord(medicalRecord);
	}

	@PatchMapping("/medicalRecord")
	public Medicalrecord patchMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		return medicalRecordService.patchMedicalRecord(medicalRecord);
	}

	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestBody Medicalrecord medicalRecord) {
		medicalRecordService.deleteMedicalRecord(medicalRecord);
	}
}