package net.safety.alert.controller;

import static net.safety.alert.constants.SeparatorConstants.COMMA_SEPARATOR;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.EmailsByCityDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PersonsGroupByAddressByListStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;
import net.safety.alert.service.IQueryService;

@RestController
public class QueryController {

	@Autowired
	IQueryService queryService;

	@GetMapping("/firestation")
	public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name = "stationNumber") String station) {
		return queryService.findPersonsByStationDTO(station);
	}

	@GetMapping("/childAlert")
	public ChildrensByAddressDTO findChildrensByAddressDTO(@RequestParam(name = "address") String address) {
		return queryService.findChildrensByAddressDTO(address);
	}

	@GetMapping("/phoneAlert")
	public PhonesByStationDTO findPhonesByStationDTO(@RequestParam(name = "firestation") String station) {
		return queryService.findPhonesByStationDTO(station);
	}

	@GetMapping("/fire")
	public PersonsByAddressDTO findPersonsByAddressDTO(@RequestParam(name = "address") String address) {
		return queryService.findPersonsByAddressDTO(address);
	}

	@GetMapping("/flood/stations")
	public PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(
			@RequestParam(name = "stations") String stationListAsString) {
		List<String> stations = Arrays.asList(stationListAsString.split(COMMA_SEPARATOR));
		return queryService.findPersonsGroupByAddressByListStationDTO(stations);
	}

	@GetMapping("/personInfo")
	public PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(
			@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
		return queryService.findPersonsByFirstNameLastNameDTO(firstName, lastName);
	}

	@GetMapping("/communityEmail")
	public EmailsByCityDTO findEmailsByCityDTO(@RequestParam(name = "city") String city) {
		EmailsByCityDTO emailsByCityDTO = queryService.findEmailsByCityDTO(city);
		return emailsByCityDTO;
	}
}
