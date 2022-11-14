package net.safety.alert.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.model.Person;

@Repository
public class MedicalRecordRepository implements IMedicalRecordRepository {
	@Autowired
	private Database database;

	@Override
	public Person save(Person medicalRecord) {
		database.getPersonsMap().put(medicalRecord.getPersonId(), medicalRecord);
		return medicalRecord;
	}

	@Override
	public void delete(Person medicalRecord) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person getPersistent(Person medicalRecord) {
		Person personDatabase = database.getPersonsMap().get(medicalRecord.getPersonId());

		return personDatabase;
	}
}
