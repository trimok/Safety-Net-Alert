package net.safety.alert.service;

import org.springframework.stereotype.Service;

import net.safety.alert.dto.PersonDTO;
import net.safety.alert.model.Person;

/**
 * @author trimok
 *
 */
@Service
public interface IPersonService {

	/**
	 * @param person
	 *            : a Person object (with a PersonId key)
	 * @return : the Person object in the database
	 */
	Person getPersistent(Person person);

	/**
	 * @param person
	 *            : the PersonDTO object to be deleted
	 */
	void deletePerson(PersonDTO person);

	/**
	 * @param person
	 *            : the PersonDTO object to be updated
	 * @return : the PersonDTO object
	 */
	PersonDTO updatePerson(PersonDTO person);

	/**
	 * @param person
	 *            : the PersonDTO to be created
	 * @return : the PersonDTO object
	 */
	PersonDTO createPerson(PersonDTO person);

	/**
	 * @param person
	 *            : the PersonDTO object to be updated
	 * @return : the PersonDTO object
	 */
	PersonDTO patchPerson(PersonDTO person);
}