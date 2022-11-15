package net.safety.alert.service;

import org.springframework.stereotype.Service;

import net.safety.alert.dto.PersonDTO;
import net.safety.alert.model.Person;

@Service
public interface IPersonService {

	Person getPersistent(Person person);

	void deletePerson(PersonDTO person);

	PersonDTO updatePerson(PersonDTO person);

	PersonDTO createPerson(PersonDTO person);

	PersonDTO patchPerson(PersonDTO person);
}