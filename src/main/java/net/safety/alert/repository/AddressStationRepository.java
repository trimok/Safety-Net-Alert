package net.safety.alert.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;

@Repository
public class AddressStationRepository implements IAddressStationRepository {
	@Autowired
	private Database database;

	@Override
	public Address save(Address address) {
		FireStation fireStationDatabase = database.getFireStationsMap().get(address.getFireStation().getId());
		Address addressDatabase = database.getAddressesMap().get(address.getName());

		// Updating the fireStation Map
		if (fireStationDatabase == null) {
			fireStationDatabase = address.getFireStation();
			database.getFireStationsMap().put(address.getFireStation().getId(), fireStationDatabase);
		}

		// Updating the addressMap Map
		if (addressDatabase == null) {
			addressDatabase = address;
			database.getAddressesMap().put(address.getName(), addressDatabase);
		}

		// Link address / station
		addressDatabase.setFireStation(fireStationDatabase);

		return addressDatabase;
	}

	@Override
	public void delete(Address address) {
		database.getAddressesMap().values().forEach(a -> {
			if (a.getFireStation() != null && a.getFireStation().equals(address.getFireStation())) {
				a.setFireStation(null);
			}
		});
	}

	@Override
	public Address getPersistent(Address address) {
		Address addressDatabase = database.getAddressesMap().get(address.getName());

		return addressDatabase;
	}
}
