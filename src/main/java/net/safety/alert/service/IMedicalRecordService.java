package net.safety.alert.service;

import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
public interface IMedicalRecordService {

	/**
	 * @param medicalRecord
	 * @return
	 */
	MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 * @return
	 */
	MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 */
	void deleteMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 * @return
	 */
	Person getPersistent(Person medicalRecord);

	/**
	 * @param medicalRecord
	 * @return
	 */
	MedicalRecordDTO patchMedicalRecord(MedicalRecordDTO medicalRecord);

}