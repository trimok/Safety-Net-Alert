package net.safety.alert.service;

import net.safety.alert.dto.AddressDTO;
import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.dto.MappingAddressStationDTO;
import net.safety.alert.model.Address;

public interface IAddressStationService {

	MappingAddressStationDTO createMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO);

	MappingAddressStationDTO updateMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO);

	void deleteMappingAddressStationByFireStation(FireStationDTO fireStationDTO);

	void deleteMappingAddressStationByAddress(AddressDTO addressDTO);

	Address getPersistent(Address address);
}