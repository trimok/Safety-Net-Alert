package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.PersonDTO;
import net.safety.alert.service.IPersonService;

@RestController
public class PersonController {

	@Autowired
	IPersonService personService;

	@PostMapping("/person")
	public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
		return PersonDTO.toPersonDTO(personService.createPerson(personDTO.toPerson()));
	}

	@PutMapping("/person")
	public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
		return PersonDTO.toPersonDTO(personService.updatePerson(personDTO.toPerson()));
	}

	@PatchMapping("/person")
	public PersonDTO patchPerson(@RequestBody PersonDTO personDTO) {
		return PersonDTO.toPersonDTO(personService.patchPerson(personDTO.toPerson()));
	}

	@DeleteMapping("/person")
	public void deletePerson(@RequestBody PersonDTO personDTO) {
		personService.deletePerson(personDTO.toPerson());
	}

}
