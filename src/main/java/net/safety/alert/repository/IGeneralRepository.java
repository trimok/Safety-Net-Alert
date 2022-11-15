package net.safety.alert.repository;

import java.util.List;

import net.safety.alert.dto.AdultDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByStationDTO;

public interface IGeneralRepository {
	List<PersonByStationDTO> findPersonsByStationDTO(String station);

	List<ChildrenByAddressDTO> findChildrenByAddressDTO(String address);

	List<AdultDTO> findAdultsByAddressDTO(String address);

	List<String> findPhonesByStationDTO(String station);

	List<PersonByAddressDTO> findPersonsByAddressDTO(String address);
}
