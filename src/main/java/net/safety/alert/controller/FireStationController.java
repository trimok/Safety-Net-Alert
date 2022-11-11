package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.service.IFireStationService;
import net.safety.alert.service.IPersonService;

@RestController
public class FireStationController {
	@Autowired
	IFireStationService fireStationService;

	@Autowired
	IPersonService personService;

	/*
	 * @PostMapping("/firestation") public FireStation createFirestation(@RequestBody FireStation fireStation) { return
	 * fireStationService.createFirestation(fireStation); }
	 * 
	 * @DeleteMapping("/firestation") public void deleteFirestation(@RequestBody FireStation fireStation) {
	 * fireStationService.deleteFirestation(fireStation); }
	 */

}
