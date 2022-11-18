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

		Person personDatabase = database.getPersonsMap().get(person.getPersonId());
		return personDatabase;
	}

	/**
	 * Saving a person
	 */
	@Override
	public Person save(Person person) {
		database.getPersonsMap().put(person.getPersonId(), person);
		database.triggerAddressForPerson(person);
		return person;
	}

	/**
	 * Deleting a person
	 */
	@Override
	public void delete(Person person) {
		database.getPersonsMap().remove(person.getPersonId());
	}
}
