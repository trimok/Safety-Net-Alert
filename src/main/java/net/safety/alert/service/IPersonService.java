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
	 * @return
	 */
	Person getPersistent(Person person);

	/**
	 * @param person
	 */
	void deletePerson(PersonDTO person);

	/**
	 * @param person
	 * @return
	 */
	PersonDTO updatePerson(PersonDTO person);

	/**
	 * @param person
	 * @return
	 */
	PersonDTO createPerson(PersonDTO person);

	/**
	 * @param person
	 * @return
	 */
	PersonDTO patchPerson(PersonDTO person);
}