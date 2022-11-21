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
	 * Saving (or updating) the medical record data of a Person
	 */
	@Override
	public Person save(Person medicalRecord) {
		database.savePerson(medicalRecord.getPersonId(), medicalRecord);
		return medicalRecord;
	}

	/**
	 * 'Deleting' the medical record data of a Person
	 */
	@Override
	public void delete(Person medicalRecord) {
		database.savePerson(medicalRecord.getPersonId(), medicalRecord);
	}

	/**
	 * Getting Persistent person from medical record
	 */
	@Override
	public Person getPersistent(Person medicalRecord) {
		Person personDatabase = database.getPerson(medicalRecord.getPersonId());

		return personDatabase;
	}
}
