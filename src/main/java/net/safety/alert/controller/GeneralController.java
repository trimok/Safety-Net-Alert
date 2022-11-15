package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;
import net.safety.alert.service.IGeneralService;

@RestController
public class GeneralController {

	@Autowired
	IGeneralService generalService;

	@GetMapping("/firestation")
	public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name = "stationNumber") String station) {
		return generalService.findPersonsByStationDTO(station);
	}

	@GetMapping("/childAlert")
	public ChildrensByAddressDTO findChildrensByAddressDTO(@RequestParam(name = "address") String address) {
		return generalService.findChildrensByAddressDTO(address);
	}

	@GetMapping("/phoneAlert")
	public PhonesByStationDTO findPhonesByStationDTO(@RequestParam(name = "firestation") String station) {
		return generalService.findPhonesByStationDTO(station);
	}

	@GetMapping("/fire")
	public PersonsByAddressDTO findPersonsByAddressDTO(@RequestParam(name = "address") String address) {
		return generalService.findPersonsByAddressDTO(address);
	}

}
