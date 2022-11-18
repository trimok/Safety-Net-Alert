package net.safety.alert.service;

import net.safety.alert.dto.AddressDTO;
import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.dto.MappingAddressStationDTO;
import net.safety.alert.model.Address;

/**
 * @author trimok
 *
 */
public interface IAddressStationService {

	/**
	 * @param mappingAddressStationDTO
	 *            : a MappingAddressStationDTO object
	 * @return : a MappingAddressStationDTO object
	 */
	MappingAddressStationDTO createMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO);

	/**
	 * @param mappingAddressStationDTO
	 *            : a MappingAddressStationDTO object
	 * @return : a MappingAddressStationDTO object
	 */
	MappingAddressStationDTO updateMappingAddressStation(MappingAddressStationDTO mappingAddressStationDTO);

	/**
	 * @param fireStationDTO
	 *            : a FireStationDTO object
	 */
	void deleteMappingAddressStationByFireStation(FireStationDTO fireStationDTO);

	/**
	 * @param addressDTO
	 *            : a AddressDTO object
	 */
	void deleteMappingAddressStationByAddress(AddressDTO addressDTO);

	/**
	 * @param address
	 *            : an Address object
	 * @return : an Address object
	 */
	Address getPersistent(Address address);
}