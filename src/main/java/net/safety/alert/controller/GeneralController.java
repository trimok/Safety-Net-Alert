package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.service.IGeneralService;
import net.safety.alert.view.ChildrenByAddressDTO;
import net.safety.alert.view.PersonsByStationDTO;
import net.safety.alert.view.PhoneByStationDTO;

@RestController
public class GeneralController {

	@Autowired
	IGeneralService generalService;

	@GetMapping("/firestation")
	public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name = "stationNumber") String station) {
		return generalService.findPersonsByStationDTO(station);
	}

	@GetMapping("/childAlert")
	public ChildrenByAddressDTO findChildrenByAddressDTO(@RequestParam(name = "address") String address) {
		return generalService.findChildrenByAddressDTO(address);
	}

	@GetMapping("/phoneAlert")
	public PhoneByStationDTO findPhoneByStationDTO(@RequestParam(name = "firestation") String station) {
		return generalService.findPhoneByStationDTO(station);
	}
}
