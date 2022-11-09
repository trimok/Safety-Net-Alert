package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_PERSON;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.exception.PersonAlreadyCreatedException;
import net.safety.alert.exception.PersonNotFoundException;
import net.safety.alert.model.Person;
import net.safety.alert.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {
	@Autowired
	PersonRepository personRepository;

	@Override
	public Person createPerson(Person person) {
		Person personDatabase = getPersistent(person);

		if (personDatabase == null) {
			return personRepository.save(person);
		} else {
			throw new PersonAlreadyCreatedException(CREATE_PERSON, PERSON_ALREADY_CREATED, person);
		}
	}

	@Override
	public Person patchPerson(Person person) {
		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			personDatabase.patchBy(person);
			return personRepository.save(personDatabase);
		} else {
			throw new PersonAlreadyCreatedException(PATCH_PERSON, PERSON_NOT_FOUND, person);
		}
	}

	@Override
	public Person updatePerson(Person person) {
		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			return personRepository.save(person);
		} else {
			throw new PersonAlreadyCreatedException(UPDATE_PERSON, PERSON_NOT_FOUND, person);
		}
	}

	@Override
	public void deletePerson(Person person) {
		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			personRepository.delete(person);
		} else {
			throw new PersonNotFoundException(DELETE_PERSON, PERSON_NOT_FOUND, person);
		}
	}

	@Override
	public Person getPersistent(Person person) {
		Optional<Person> personOptional = personRepository.findById(person.getPersonId());
		if (personOptional.isPresent()) {
			return personOptional.get();
		} else {
			return null;
		}
	}
}
