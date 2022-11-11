package net.safety.alert.repository;

import org.springframework.stereotype.Repository;

import net.safety.alert.database.MemoryDatabase;
import net.safety.alert.dto.FireStationDTO;

@Repository
public class FireStationRepository implements IFireStationRepository {
	private static MemoryDatabase instance = MemoryDatabase.getInstance();

	@Override
	public FireStationDTO save(FireStationDTO fireStation) {
		instance.getFireStationsMap().put(fireStation.getAddress(), fireStation.getStation());
		instance.synchronizeGlobalAddressStationDatabase();
		return fireStation;
	}

	@Override
	public void delete(FireStationDTO fireStation) {
		instance.getFireStationsMap().remove(fireStation.getAddress());
		instance.synchronizeGlobalAddressStationDatabase();
	}

	@Override
	public FireStationDTO getPersistent(FireStationDTO fireStation) {
		String station = instance.getFireStationsMap().getOrDefault(fireStation.getAddress(), null);

		if (station == null || !station.equals(fireStation.getStation())) {
			return null;
		} else {
			return fireStation;
		}
	}
}
