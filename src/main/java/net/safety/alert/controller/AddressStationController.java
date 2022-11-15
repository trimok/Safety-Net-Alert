package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.service.IAddressStationService;

@RestController
public class AddressStationController {
	@Autowired
	IAddressStationService addressStationService;

	@PostMapping("/firestation")
	public FireStationDTO createMappingAddressStation(@RequestBody FireStationDTO fireStationDTO) {
		return addressStationService.createMappingAddressStation(fireStationDTO);
	}

	@PutMapping("/firestation")
	public FireStationDTO updateMappingAddressStation(@RequestBody FireStationDTO fireStationDTO) {
		return addressStationService.updateMappingAddressStation(fireStationDTO);
	}

	@DeleteMapping("/firestation")
	public void deleteMappingAddressStation(@RequestBody FireStationDTO fireStationDTO) {
		addressStationService.deleteMappingAddressStation(fireStationDTO);
	}
}
