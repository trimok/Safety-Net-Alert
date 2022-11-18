package net.safety.alert.repository;

import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
public interface IMedicalRecordRepository {

	/**
	 * @param medicalRecord
	 * @return
	 */
	Person save(Person medicalRecord);

	/**
	 * @param medicalRecord
	 */
	void delete(Person medicalRecord);

	/**
	 * @param medicalRecord
	 * @return
	 */
	Person getPersistent(Person medicalRecord);
}