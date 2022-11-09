package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.Firestation;
import net.safety.alert.service.IFirestationService;

@RestController
public class FirestationController {
	@Autowired
	IFirestationService firestationService;

	@PostMapping("/firestation")
	public Firestation createFirestation(@RequestBody Firestation firestation) {
		return firestationService.createFirestation(firestation);
	}

	@DeleteMapping("/firestation")
	public void deleteFirestation(@RequestBody Firestation firestation) {
		firestationService.deleteFirestation(firestation);
	}
}
