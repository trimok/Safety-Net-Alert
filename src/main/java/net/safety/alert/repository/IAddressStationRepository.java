package net.safety.alert.repository;

import net.safety.alert.model.Address;

/**
 * @author trimok
 *
 */
public interface IAddressStationRepository {

	/**
	 * @param address
	 *            : an Address object
	 * @return : an Address object
	 */
	Address getPersistent(Address address);

	/**
	 * @param address
	 *            : an Address object
	 */
	void deleteByFireStation(Address address);

	/**
	 * @param address
	 *            : an Address object
	 */
	void deleteByAddress(Address address);

	/**
	 * @param address
	 *            : an Address object
	 * @return : an Address object
	 */
	Address save(Address address);
}