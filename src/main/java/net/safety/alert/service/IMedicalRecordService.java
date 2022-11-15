package net.safety.alert.service;

import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.model.Person;

public interface IMedicalRecordService {

	MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecord);

	MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecord);

	void deleteMedicalRecord(MedicalRecordDTO medicalRecord);

	Person getPersistent(Person medicalRecord);

	MedicalRecordDTO patchMedicalRecord(MedicalRecordDTO medicalRecord);

}