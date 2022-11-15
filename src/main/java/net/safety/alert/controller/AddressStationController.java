package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.AddressDTO;
import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.dto.MappingAddressStationDTO;
import net.safety.alert.service.IAddressStationService;

@RestController
public class AddressStationController {
	@Autowired
	IAddressStationService addressStationService;

	@PostMapping("/firestation")
	public MappingAddressStationDTO createMappingAddressStation(
			@RequestBody MappingAddressStationDTO mappingAddressStationDTO) {
		return addressStationService.createMappingAddressStation(mappingAddressStationDTO);
	}

	@PutMapping("/firestation")
	public MappingAddressStationDTO updateMappingAddressStation(
			@RequestBody MappingAddressStationDTO mappingAddressStationDTO) {
		return addressStationService.updateMappingAddressStation(mappingAddressStationDTO);
	}

	@DeleteMapping("/firestation/byFireStation")
	public void deleteMappingAddressStationByFireStation(@RequestBody FireStationDTO fireStationDTO) {
		addressStationService.deleteMappingAddressStationByFireStation(fireStationDTO);
	}

	@DeleteMapping("/firestation/byAddress")
	public void deleteMappingAddressStationByAddress(@RequestBody AddressDTO addressDTO) {
		addressStationService.deleteMappingAddressStationByAddress(addressDTO);
	}
}
