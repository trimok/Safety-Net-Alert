package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_PERSON_OPERATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.PersonDTO;
import net.safety.alert.exception.PersonAlreadyCreatedException;
import net.safety.alert.exception.PersonNotFoundException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.model.Person;
import net.safety.alert.repository.IPersonRepository;

@Service
public class PersonService implements IPersonService {

	@Autowired
	IPersonRepository personRepository;

	@Override
	public PersonDTO createPerson(PersonDTO personDTO) {

		Person person = personDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(CREATE_PERSON_OPERATION, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase == null) {
			return PersonDTO.toPersonDTO(personRepository.save(person));
		} else {
			throw new PersonAlreadyCreatedException(CREATE_PERSON_OPERATION, PERSON_ALREADY_CREATED,
					PersonDTO.toPersonDTO(personDatabase));
		}
	}

	@Override
	public PersonDTO updatePerson(PersonDTO personDTO) {
		Person person = personDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(UPDATE_PERSON_OPERATION, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			return PersonDTO.toPersonDTO(personRepository.save(personDatabase.updatePerson(person)));
		} else {
			throw new PersonNotFoundException(UPDATE_PERSON_OPERATION, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public PersonDTO patchPerson(PersonDTO personDTO) {
		Person person = personDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(PATCH_PERSON_OPERATION, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			return PersonDTO.toPersonDTO(personRepository.save(personDatabase.patchPerson(person)));
		} else {
			throw new PersonNotFoundException(PATCH_PERSON_OPERATION, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public void deletePerson(PersonDTO personDTO) {
		Person person = personDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(DELETE_PERSON_OPERATION, PERSON_NOT_VALID, PersonDTO.toPersonDTO(person));
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			personRepository.delete(personDatabase);
		} else {
			throw new PersonNotFoundException(DELETE_PERSON_OPERATION, PERSON_NOT_FOUND, PersonDTO.toPersonDTO(person));
		}
	}

	@Override
	public Person getPersistent(Person person) {
		return personRepository.getPersistent(person);
	}
}
