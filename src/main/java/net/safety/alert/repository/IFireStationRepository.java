package net.safety.alert.repository;

import net.safety.alert.dto.FireStationDTO;

public interface IFireStationRepository {

	FireStationDTO getPersistent(FireStationDTO fireStation);

	void delete(FireStationDTO fireStation);

	FireStationDTO save(FireStationDTO fireStation);

}