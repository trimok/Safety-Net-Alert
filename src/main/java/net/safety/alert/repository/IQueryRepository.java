package net.safety.alert.repository;

import java.util.List;
import java.util.Map;

import net.safety.alert.dto.AdultByAddressDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;

public interface IQueryRepository {

	List<PersonByStationDTO> findPersonsByStationDTO(String station);

	List<ChildrenByAddressDTO> findChildrenByAddressDTO(String address);

	List<AdultByAddressDTO> findAdultsByAddressDTO(String address);

	List<String> findPhonesByStationDTO(String station);

	List<PersonByAddressDTO> findPersonsByAddressDTO(String address);

	Map<String, List<PersonGroupByAddressByListStationDTO>> findPersonsGroupByAddressByListStationDTO(
			List<String> stations);

	List<PersonByFirstNameLastNameDTO> findPersonsByFirstNameLastNameDTO(String firstName, String lastName);

	List<String> findEmailsByCityDTO(String city);
}
