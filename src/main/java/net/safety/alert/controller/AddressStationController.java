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

/**
 * @author trimok
 *
 */
@RestController
public class AddressStationController {
	/**
	 * 
	 */
	@Autowired
	IAddressStationService addressStationService;

	/**
	 * @param mappingAddressStationDTO
	 *            : a MappingAddressStationDTO object
	 * @return : a MappingAddressStationDTO object
	 */
	@PostMapping("/firestation")
	public MappingAddressStationDTO createMappingAddressStation(
			@RequestBody MappingAddressStationDTO mappingAddressStationDTO) {
		return addressStationService.createMappingAddressStation(mappingAddressStationDTO);
	}

	/**
	 * @param mappingAddressStationDTO
	 *            : a MappingAddressStationDTO object
	 * @return : a MappingAddressStationDTO object
	 */
	@PutMapping("/firestation")
	public MappingAddressStationDTO updateMappingAddressStation(
			@RequestBody MappingAddressStationDTO mappingAddressStationDTO) {
		return addressStationService.updateMappingAddressStation(mappingAddressStationDTO);
	}

	/**
	 * @param fireStationDTO
	 *            : a fireStationDTO object
	 */
	@DeleteMapping("/firestation/byFireStation")
	public void deleteMappingAddressStationByFireStation(@RequestBody FireStationDTO fireStationDTO) {
		addressStationService.deleteMappingAddressStationByFireStation(fireStationDTO);
	}

	/**
	 * @param addressDTO
	 *            : a addressDTO Object
	 */
	@DeleteMapping("/firestation/byAddress")
	public void deleteMappingAddressStationByAddress(@RequestBody AddressDTO addressDTO) {
		addressStationService.deleteMappingAddressStationByAddress(addressDTO);
	}
}
