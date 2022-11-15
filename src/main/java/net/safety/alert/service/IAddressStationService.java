package net.safety.alert.service;

import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.model.Address;

public interface IAddressStationService {

	FireStationDTO createMappingAddressStation(FireStationDTO fireStationDTO);

	FireStationDTO updateMappingAddressStation(FireStationDTO fireStationDTO);

	void deleteMappingAddressStation(FireStationDTO fireStationDTO);

	Address getPersistent(Address address);
}