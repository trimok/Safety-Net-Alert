package net.safety.alert.repository;

import net.safety.alert.model.Person;

public interface IMedicalRecordRepository {

	Person save(Person medicalRecord);

	void delete(Person medicalRecord);

	Person getPersistent(Person medicalRecord);
}