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
	 * @return
	 */
	PersonsByStationDTO findPersonsByStationDTO(String station);

	/**
	 * @param address
	 * @return
	 */
	ChildrensByAddressDTO findChildrensByAddressDTO(String address);

	/**
	 * @param station
	 * @return
	 */
	PhonesByStationDTO findPhonesByStationDTO(String station);

	/**
	 * @param address
	 * @return
	 */
	PersonsByAddressDTO findPersonsByAddressDTO(String address);

	/**
	 * @param stations
	 * @return
	 */
	PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(List<String> stations);

	/**
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(String firstName, String lastName);

	/**
	 * @param city
	 * @return
	 */
	EmailsByCityDTO findEmailsByCityDTO(String city);
}
