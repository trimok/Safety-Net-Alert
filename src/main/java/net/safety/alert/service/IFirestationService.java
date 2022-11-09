package net.safety.alert.service;

import net.safety.alert.model.Firestation;

public interface IFirestationService {

	Firestation createFirestation(Firestation firestation);

	void deleteFirestation(Firestation firestation);

	Firestation getPersistent(Firestation firestation);

}