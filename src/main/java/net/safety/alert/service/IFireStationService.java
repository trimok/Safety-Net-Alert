package net.safety.alert.service;

import net.safety.alert.dto.FireStationDTO;

public interface IFireStationService {

	FireStationDTO createFireStation(FireStationDTO fireStation);

	FireStationDTO updateFireStation(FireStationDTO fireStation);

	void deleteFireStation(FireStationDTO fireStation);

	FireStationDTO getPersistent(FireStationDTO fireStation);
}