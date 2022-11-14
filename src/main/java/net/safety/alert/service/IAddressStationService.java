package net.safety.alert.service;

import net.safety.alert.model.Address;

public interface IAddressStationService {

	Address createMappingAddressStation(Address address);

	Address updateMappingAddressStation(Address address);

	void deleteMappingAddressStation(Address address);

	Address getPersistent(Address address);
}