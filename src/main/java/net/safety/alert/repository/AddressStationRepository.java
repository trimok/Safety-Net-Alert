package net.safety.alert.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;

/**
 * @author trimok
 *
 */
@Repository
public class AddressStationRepository implements IAddressStationRepository {
	/**
	 * The database
	 */
	@Autowired
	private Database database;

	/*
	 * Saving (or updating) the address
	 */
	@Override
	public Address save(Address address) {
		FireStation fireStationDatabase = database.getFireStation(address.getFireStation().getId());
		Address addressDatabase = database.getAddress(address.getName());

		// Updating the fireStation Map
		if (fireStationDatabase == null) {
			fireStationDatabase = address.getFireStation();
			database.saveFireStation(address.getFireStation().getId(), fireStationDatabase);
		}

		// Updating the addressMap Map
		if (addressDatabase == null) {
			addressDatabase = address;
			database.saveAddress(address.getName(), addressDatabase);
		}

		// Link address / station
		addressDatabase.setFireStation(fireStationDatabase);

		return addressDatabase;
	}

	/**
	 * Deleting the map address-station by address
	 */
	@Override
	public void deleteByFireStation(Address address) {
		database.getAddresses().forEach(a -> {
			if (a.getFireStation() != null && a.getFireStation().equals(address.getFireStation())) {
				a.setFireStation(null);
			}
		});
	}

	/**
	 * Deleting the map address- station by firestation
	 */
	@Override
	public void deleteByAddress(Address address) {
		database.getAddresses().forEach(a -> {
			if (a.getName().equals(address.getName())) {
				a.setFireStation(null);
			}
		});
	}

	/**
	 * Retrieving persistent address
	 */
	@Override
	public Address getPersistent(Address address) {
		Address addressDatabase = database.getAddress(address.getName());

		return addressDatabase;
	}
}
