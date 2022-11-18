package net.safety.alert.repository;

import java.util.List;
import java.util.Map;

import net.safety.alert.dto.AdultByAddressDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;

/**
 * @author trimok
 *
 */
public interface IQueryRepository {

	/**
	 * @param station
	 *            : a station
	 * @return : a List of PersonByStationDTO
	 */
	List<PersonByStationDTO> findPersonsByStationDTO(String station);

	/**
	 * @param address
	 *            : an address
	 * @return : a List of ChildrenByAddressDTO
	 */
	List<ChildrenByAddressDTO> findChildrenByAddressDTO(String address);

	/**
	 * @param address
	 *            : an address
	 * @return : a List of AdultByAddressDTO
	 */
	List<AdultByAddressDTO> findAdultsByAddressDTO(String address);

	/**
	 * @param station
	 *            : a station
	 * @return : a List of String (the list of phones)
	 */
	List<String> findPhonesByStationDTO(String station);

	/**
	 * @param address
	 *            : an address
	 * @return : a List of PersonByAddressDTO
	 */
	List<PersonByAddressDTO> findPersonsByAddressDTO(String address);

	/**
	 * @param stations
	 *            : the list of stations
	 * @return : a Map of List of PersonGroupByAddressByListStationDTO group by address
	 */
	Map<String, List<PersonGroupByAddressByListStationDTO>> findPersonsGroupByAddressByListStationDTO(
			List<String> stations);

	/**
	 * @param firstName
	 *            : firstName
	 * @param lastName
	 *            : lastName
	 * @return : a List of PersonByFirstNameLastNameDTO
	 */
	List<PersonByFirstNameLastNameDTO> findPersonsByFirstNameLastNameDTO(String firstName, String lastName);

	/**
	 * @param city
	 *            : the city
	 * @return : The list of emails
	 */
	List<String> findEmailsByCityDTO(String city);
}
