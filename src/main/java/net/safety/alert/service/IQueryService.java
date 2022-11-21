package net.safety.alert.service;

import java.util.List;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.EmailsByCityDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PersonsGroupByAddressByListStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;

/**
 * @author trimok
 *
 */
public interface IQueryService {
	/**
	 * @param station
	 *            : the station
	 * @return a PersonsByStationDTO object
	 */
	PersonsByStationDTO findPersonsByStationDTO(String station);

	/**
	 * @param address
	 *            : the address
	 * @return : a ChildrensByAddressDTO object
	 */
	ChildrensByAddressDTO findChildrensByAddressDTO(String address);

	/**
	 * @param station
	 *            : the station
	 * @return : a PhonesByStationDTO object
	 */
	PhonesByStationDTO findPhonesByStationDTO(String station);

	/**
	 * @param address
	 *            : the address
	 * @return : a PersonsByAddressDTO object
	 */
	PersonsByAddressDTO findPersonsByAddressDTO(String address);

	/**
	 * @param stations
	 *            : the list of stations
	 * @return : a PersonsGroupByAddressByListStationDTO object
	 */
	PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(List<String> stations);

	/**
	 * @param firstName
	 *            : the first name
	 * @param lastName
	 *            : the last name
	 * @return : a PersonsByFirstNameLastNameDTO object
	 */
	PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(String firstName, String lastName);

	/**
	 * @param city
	 *            : the city
	 * @return : a EmailsByCityDTO object
	 */
	EmailsByCityDTO findEmailsByCityDTO(String city);
}
