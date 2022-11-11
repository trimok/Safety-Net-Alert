package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.Person;
import net.safety.alert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
	IPersonService personService;

	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		return personService.createPerson(person);
	}

	@PutMapping("/person")
	public Person updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
	}

	@PatchMapping("/person")
	public Person patchPerson(@RequestBody Person person) {
		return personService.patchPerson(person);
	}

	@DeleteMapping("/person")
	public void deletePerson(@RequestBody Person person) {
		personService.deletePerson(person);
	}

}
