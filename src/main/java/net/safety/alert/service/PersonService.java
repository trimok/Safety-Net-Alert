package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_PERSON;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_PERSON;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.PersonDTO;
import net.safety.alert.exception.PersonAlreadyCreatedException;
import net.safety.alert.exception.PersonNotFoundException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.model.Person;
import net.safety.alert.repository.PersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	PersonRepository personRepository;

	@Override
	public Person createPerson(Person person) {
		if (!person.isValid()) {
			throw new PersonNotValidException(CREATE_PERSON, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase == null) {
			return personRepository.save(person);
		} else {
			throw new PersonAlreadyCreatedException(CREATE_PERSON, PERSON_ALREADY_CREATED,
					PersonDTO.toPersonDTO(personDatabase));
		}
	}

	@Override
	public Person updatePerson(Person person) {
		if (!person.isValid()) {
			throw new PersonNotValidException(UPDATE_PERSON, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			return personRepository.save(personDatabase.updatePerson(person));
		} else {
			throw new PersonAlreadyCreatedException(UPDATE_PERSON, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public Person patchPerson(Person person) {
		if (!person.isValid()) {
			throw new PersonNotValidException(PATCH_PERSON, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			return personRepository.save(personDatabase.patchPerson(person));
		} else {
			throw new PersonAlreadyCreatedException(PATCH_PERSON, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public void deletePerson(Person person) {
		if (!person.isValid()) {
			throw new PersonNotValidException(DELETE_PERSON, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			personRepository.delete(personDatabase);
		} else {
			throw new PersonNotFoundException(DELETE_PERSON, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public Person getPersistent(Person person) {
		return personRepository.getPersistent(person);
	}
}
