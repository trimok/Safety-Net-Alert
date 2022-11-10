package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.model.FireStation;
import net.safety.alert.service.IFireStationService;
import net.safety.alert.service.IPersonService;
import net.safety.alert.service.IQueryService;
import net.safety.alert.view.PersonsByStationDTO;

@RestController
public class FireStationController {
	@Autowired
	IFireStationService fireStationService;

	@Autowired
	IQueryService queryService;

	@Autowired
	IPersonService personService;

	@PostMapping("/firestation")
	public FireStation createFirestation(@RequestBody FireStation fireStation) {
		return fireStationService.createFirestation(fireStation);
	}

	@DeleteMapping("/firestation")
	public void deleteFirestation(@RequestBody FireStation fireStation) {
		fireStationService.deleteFirestation(fireStation);
	}

	@GetMapping("/firestation")
	public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name = "stationNumber") String station) {
		return queryService.findPersonsByStationDTO(station);
	}
}
