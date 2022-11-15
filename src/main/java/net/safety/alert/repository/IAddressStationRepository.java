package net.safety.alert.repository;

import net.safety.alert.model.Address;

public interface IAddressStationRepository {

	Address getPersistent(Address address);

	void deleteByFireStation(Address address);

	void deleteByAddress(Address address);

	Address save(Address address);
}