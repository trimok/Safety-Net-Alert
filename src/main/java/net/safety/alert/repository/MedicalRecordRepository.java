package net.safety.alert.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
	/**
	 * The database
	 */
	@Autowired
	private Database database;

	/**
	 * Saving a person
	 */
	@Override
	public Person save(Person medicalRecord) {
		database.getPersonsMap().put(medicalRecord.getPersonId(), medicalRecord);
		return medicalRecord;
	}

	/**
	 * Deleting a person
	 */
	@Override
	public void delete(Person medicalRecord) {
		database.getPersonsMap().put(medicalRecord.getPersonId(), medicalRecord);
	}

	/**
	 * Getting Persistent person from medical record
	 */
	@Override
	public Person getPersistent(Person medicalRecord) {
		Person personDatabase = database.getPersonsMap().get(medicalRecord.getPersonId());

		return personDatabase;
	}
}
