package net.safety.alert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.service.IGeneralService;

@RestController
public class GeneralController {

	@Autowired
	IGeneralService generalService;

	/*
	 * @GetMapping("/firestation") public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name =
	 * "stationNumber") String station) { return generalService.findPersonsByStationDTO(station); }
	 * 
	 * @GetMapping("/childAlert") public ChildrenByAddressDTO findChildrenByAddressDTO(@RequestParam(name = "address")
	 * String address) { return generalService.findChildrenByAddressDTO(address); }
	 * 
	 * @GetMapping("/phoneAlert") public PhoneByStationDTO findPhonesByStationDTO(@RequestParam(name = "firestation")
	 * String station) { return generalService.findPhonesByStationDTO(station); }
	 * 
	 * @GetMapping("/fire") public PersonByAddressDTO findPersonsByAddressDTO(@RequestParam(name = "address") String
	 * address) { return generalService.findPersonsByAddressDTO(address); }
	 */
}
