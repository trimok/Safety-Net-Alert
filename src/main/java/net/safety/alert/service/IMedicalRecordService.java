package net.safety.alert.service;

import net.safety.alert.model.Person;

public interface IMedicalRecordService {

	Person createMedicalRecord(Person medicalRecord);

	Person updateMedicalRecord(Person medicalRecord);

	void deleteMedicalRecord(Person medicalRecord);

	Person getPersistent(Person medicalRecord);

	Person patchMedicalRecord(Person medicalRecord);

}