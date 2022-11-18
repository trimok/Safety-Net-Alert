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

/**
 * @author trimok
 *
 */
@RestController
public class PersonController {

	/**
	 * 
	 */
	@Autowired
	IPersonService personService;

	/**
	 * @param personDTO
	 *            : a personDTO Object
	 * @return : a personDTO Object
	 */
	@PostMapping("/person")
	public PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}

	/**
	 * @param personDTO
	 *            : a personDTO Object
	 * @return : a personDTO Object
	 */

	@PutMapping("/person")
	public PersonDTO updatePerson(@RequestBody PersonDTO personDTO) {
		return personService.updatePerson(personDTO);
	}

	/**
	 * @param personDTO
	 *            : a personDTO Object
	 * @return : a personDTO Object
	 */

	@PatchMapping("/person")
	public PersonDTO patchPerson(@RequestBody PersonDTO personDTO) {
		return personService.patchPerson(personDTO);
	}

	/**
	 * @param personDTO
	 *            : a personDTO Object
	 */
	@DeleteMapping("/person")
	public void deletePerson(@RequestBody PersonDTO personDTO) {
		personService.deletePerson(personDTO);
	}

}
