package net.safety.alert.service;

import net.safety.alert.model.Medicalrecord;

public interface IMedicalrecordService {

	Medicalrecord createMedicalRecord(Medicalrecord medicalRecord);

	Medicalrecord updateMedicalRecord(Medicalrecord medicalRecord);

	void deleteMedicalRecord(Medicalrecord medicalRecord);

	Medicalrecord getPersistent(Medicalrecord medicalRecord);

	Medicalrecord patchMedicalRecord(Medicalrecord medicalRecord);
}