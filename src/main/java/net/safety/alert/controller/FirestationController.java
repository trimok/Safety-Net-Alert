package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.FireStation;
import net.safety.alert.service.IFirestationService;

@RestController
public class FirestationController {
	@Autowired
	IFirestationService firestationService;

	@PostMapping("/firestation")
	public FireStation createFirestation(@RequestBody FireStation fireStation) {
		return firestationService.createFirestation(fireStation);
	}

	@DeleteMapping("/firestation")
	public void deleteFirestation(@RequestBody FireStation fireStation) {
		firestationService.deleteFirestation(fireStation);
	}
}
