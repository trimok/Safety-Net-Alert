package net.safety.alert.repository;

import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
public interface IMedicalRecordRepository {

	/**
	 * @param medicalRecord
	 *            : a Person object representing medical record data
	 * @return : a Person object representing medical record data
	 */
	Person save(Person medicalRecord);

	/**
	 * 
	 * @param medicalRecord:
	 *            a Person object representing medical record data
	 */
	void delete(Person medicalRecord);

	/**
	 * @param medicalRecord
	 *            : a Person object (with the PersonId key) representing medical record data
	 * @return : the Person object in the database
	 */
	Person getPersistent(Person medicalRecord);
}