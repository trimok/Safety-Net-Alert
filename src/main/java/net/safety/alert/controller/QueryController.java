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

/**
 * @author trimok
 *
 *         Controller which is responsible for all the queries (GET /xxx)
 *
 */
@RestController
public class QueryController {

	/**
	 * 
	 */
	@Autowired
	IQueryService queryService;

	/**
	 * Getting the persons by station, with a count of adults and children
	 * 
	 * @param station
	 *            : the station
	 * @return : a PersonsByStationDTO object
	 */
	@GetMapping("/firestation")
	public PersonsByStationDTO findPersonsByStationDTO(@RequestParam(name = "stationNumber") String station) {
		return queryService.findPersonsByStationDTO(station);
	}

	/**
	 * Getting the list of children (with age) (and adults) by address
	 * 
	 * @param address
	 *            : the address
	 * @return : a ChildrensByAddressDTO Object
	 */
	@GetMapping("/childAlert")
	public ChildrensByAddressDTO findChildrensByAddressDTO(@RequestParam(name = "address") String address) {
		return queryService.findChildrensByAddressDTO(address);
	}

	/**
	 * Getting the phones by station
	 * 
	 * @param station
	 *            : a station
	 * @return : a PhonesByStationDTO Object
	 */
	@GetMapping("/phoneAlert")
	public PhonesByStationDTO findPhonesByStationDTO(@RequestParam(name = "firestation") String station) {
		return queryService.findPhonesByStationDTO(station);
	}

	/**
	 * Getting the list of persons (and firestation) by address
	 * 
	 * @param address
	 *            : an address
	 * @return : a PersonsByAddressDTO Object
	 */
	@GetMapping("/fire")
	public PersonsByAddressDTO findPersonsByAddressDTO(@RequestParam(name = "address") String address) {
		return queryService.findPersonsByAddressDTO(address);
	}

	/**
	 * Getting the persons, group by address, from a list of stations
	 * 
	 * @param stationListAsString
	 *            : the list of stations
	 * @return : a PersonsGroupByAddressByListStationDTO Object
	 */
	@GetMapping("/flood/stations")
	public PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(
			@RequestParam(name = "stations") String stationListAsString) {
		List<String> stations = Arrays.asList(stationListAsString.split(COMMA_SEPARATOR));
		return queryService.findPersonsGroupByAddressByListStationDTO(stations);
	}

	/**
	 * Getting information of a Person from its first name and last name
	 * 
	 * @param firstName
	 *            : the first name
	 * @param lastName
	 *            : the last name
	 * @return : a PersonsByFirstNameLastNameDTO Object
	 */
	@GetMapping("/personInfo")
	public PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(
			@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
		return queryService.findPersonsByFirstNameLastNameDTO(firstName, lastName);
	}

	/**
	 * Getting the list of emails by city
	 * 
	 * @param city
	 *            : the city
	 * @return : a EmailsByCityDTO Object
	 */
	@GetMapping("/communityEmail")
	public EmailsByCityDTO findEmailsByCityDTO(@RequestParam(name = "city") String city) {
		EmailsByCityDTO emailsByCityDTO = queryService.findEmailsByCityDTO(city);
		return emailsByCityDTO;
	}
}
