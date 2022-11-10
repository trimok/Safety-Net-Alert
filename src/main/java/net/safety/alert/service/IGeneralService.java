package net.safety.alert.service;

import net.safety.alert.view.ChildrenByAddressDTO;
import net.safety.alert.view.PersonsByStationDTO;

public interface IGeneralService {

	PersonsByStationDTO findPersonsByStationDTO(String station);

	ChildrenByAddressDTO findChildrenByAddressDTO(String address);
}
