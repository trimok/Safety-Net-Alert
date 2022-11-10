package net.safety.alert.service;

import org.springframework.stereotype.Service;

import net.safety.alert.model.Person;

@Service
public interface IPersonService {

	Person getPersistent(Person person);

	void deletePerson(Person person);

	Person updatePerson(Person person);

	Person createPerson(Person person);

	Person patchPerson(Person person);
}