package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.service.IFireStationService;
import net.safety.alert.service.IPersonService;

@RestController
public class FireStationController {
	@Autowired
	IFireStationService fireStationService;

	@Autowired
	IPersonService personService;

	@PostMapping("/firestation")
	public FireStationDTO createFireStation(@RequestBody FireStationDTO fireStation) {
		return fireStationService.createFireStation(fireStation);
	}

	@PutMapping("/firestation")
	public FireStationDTO UpdateFireStation(@RequestBody FireStationDTO fireStation) {
		return fireStationService.createFireStation(fireStation);
	}

	@DeleteMapping("/firestation")
	public void deleteFirestation(@RequestBody FireStationDTO fireStation) {
		fireStationService.deleteFireStation(fireStation);
	}
}
