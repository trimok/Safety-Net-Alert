package net.safety.alert.repository;

import net.safety.alert.model.Address;

public interface IAddressStationRepository {

	Address getPersistent(Address address);

	void delete(Address address);

	Address save(Address address);

}