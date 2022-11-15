package net.safety.alert.service;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;

public interface IGeneralService {
	PersonsByStationDTO findPersonsByStationDTO(String station);

	ChildrensByAddressDTO findChildrensByAddressDTO(String address);

	PhonesByStationDTO findPhonesByStationDTO(String station);

	PersonsByAddressDTO findPersonsByAddressDTO(String address);
}
