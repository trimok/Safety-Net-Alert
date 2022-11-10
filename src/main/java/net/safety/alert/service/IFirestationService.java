package net.safety.alert.service;

import net.safety.alert.model.FireStation;

public interface IFirestationService {

	FireStation createFirestation(FireStation fireStation);

	void deleteFirestation(FireStation fireStation);

	FireStation getPersistent(FireStation fireStation);

}