package net.safety.alert.repository;

import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
public interface IPersonRepository {

	/**
	 * @param person
	 *            : a Person object (with the key PersonId)
	 * @return : a Person object : a Person object : a Person object
	 */
	Person getPersistent(Person person);

	/**
	 * @param person
	 *            : a Person object
	 */
	void delete(Person person);

	/**
	 * @param person
	 *            : a Person object
	 * @return : a Person object : a Person object : a Person object
	 */
	Person save(Person person);
}
