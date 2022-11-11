package net.safety.alert.repository;

import net.safety.alert.model.Person;

public interface IPersonRepository {

	Person getPersistent(Person person);

	void delete(Person person);

	Person update(Person person);

	Person save(Person person);
}
