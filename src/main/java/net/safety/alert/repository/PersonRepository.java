package net.safety.alert.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import net.safety.alert.database.MemoryDatabase;
import net.safety.alert.model.Person;

@Component
public class PersonRepository implements IPersonRepository {
	private static MemoryDatabase instance = MemoryDatabase.getInstance();

	@Override
	public Person getPersistent(Person person) {

		Optional<Person> personOptional = instance.getPersons().stream().filter(
				p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
				.findFirst();
		return personOptional.orElse(null);
	}

	@Override
	public Person save(Person person) {
		instance.getPersons().add(person);
		instance.synchronizeAddressStationDatabase(person);
		return person;
	}

	@Override
	public Person update(Person person) {
		instance.getPersons().stream().filter(
				p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName()))
				.map(p -> person);
		instance.synchronizeAddressStationDatabase(person);
		return person;
	}

	@Override
	public void delete(Person person) {
		instance.getPersons().remove(person);
	}
}
