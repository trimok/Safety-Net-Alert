package net.safety.alert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;

@Repository
public interface PersonRepository extends CrudRepository<Person, PersonId> {
}
