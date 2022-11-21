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
public class PersonRepository implements IPersonRepository {
	/**
	 * The database
	 */
	@Autowired
	private Database database;

	/**
	 * Getting a persistent Person from a key
	 */
	@Override
	public Person getPersistent(Person person) {

		Person personDatabase = database.getPerson(person.getPersonId());
		return personDatabase;
	}

	/**
	 * Saving (or updating) a person
	 */
	@Override
	public Person save(Person person) {
		database.savePerson(person.getPersonId(), person);
		return person;
	}

	/**
	 * Deleting a person
	 */
	@Override
	public void delete(Person person) {
		database.deletePerson(person.getPersonId());
	}
}
