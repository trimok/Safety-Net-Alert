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
	 *            : a MedicalRecordDTO object
	 * @return : a MedicalRecordDTO object
	 */
	MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 *            : a MedicalRecordDTO object
	 * @return : a MedicalRecordDTO object
	 */
	MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 *            : a MedicalRecordDTO object
	 */
	void deleteMedicalRecord(MedicalRecordDTO medicalRecord);

	/**
	 * @param medicalRecord
	 *            : a Person object (with the PersonId key) representing medical record data
	 * @return : the Person object in the database
	 */
	Person getPersistent(Person medicalRecord);

	/**
	 * @param medicalRecord
	 *            : a MedicalRecordDTO object
	 * @return : a MedicalRecordDTO object
	 */
	MedicalRecordDTO patchMedicalRecord(MedicalRecordDTO medicalRecord);

}