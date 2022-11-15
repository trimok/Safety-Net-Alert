package net.safety.alert.service;

import java.util.List;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.EmailsByCityDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PersonsGroupByAddressByListStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;

public interface IQueryService {
	PersonsByStationDTO findPersonsByStationDTO(String station);

	ChildrensByAddressDTO findChildrensByAddressDTO(String address);

	PhonesByStationDTO findPhonesByStationDTO(String station);

	PersonsByAddressDTO findPersonsByAddressDTO(String address);

	PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(List<String> stations);

	PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(String firstName, String lastName);

	EmailsByCityDTO findEmailsByCityDTO(String city);
}
