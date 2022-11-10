package net.safety.alert.service;

import net.safety.alert.model.MedicalRecord;

public interface IMedicalRecordService {

	MedicalRecord createMedicalRecord(MedicalRecord medicalRecord);

	MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);

	void deleteMedicalRecord(MedicalRecord medicalRecord);

	MedicalRecord getPersistent(MedicalRecord medicalRecord);

	MedicalRecord patchMedicalRecord(MedicalRecord medicalRecord);
}