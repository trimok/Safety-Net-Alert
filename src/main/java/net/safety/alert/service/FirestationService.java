package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_FIRESTATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_FIRESTATION;
import static net.safety.alert.constants.HttpMessageConstants.FIRESTATION_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.FIRESTATION_NOT_FOUND;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.exception.FireStationAlreadyCreatedException;
import net.safety.alert.exception.FireStationNotFoundException;
import net.safety.alert.model.Firestation;
import net.safety.alert.repository.FirestationRepository;;

@Service
public class FirestationService implements IFirestationService {
	@Autowired
	FirestationRepository fireStationRepository;

	@Override
	public Firestation createFirestation(Firestation firestation) {
		Firestation firestationDatabase = getPersistent(firestation);

		if (firestationDatabase == null) {
			return fireStationRepository.save(firestation);
		} else {
			throw new FireStationAlreadyCreatedException(CREATE_FIRESTATION, FIRESTATION_ALREADY_CREATED, firestation);
		}
	}

	@Override
	public void deleteFirestation(Firestation firestation) {
		Firestation firestationDatabase = getPersistent(firestation);

		if (firestationDatabase != null) {
			fireStationRepository.delete(firestation);
		} else {
			throw new FireStationNotFoundException(DELETE_FIRESTATION, FIRESTATION_NOT_FOUND, firestation);
		}
	}

	@Override
	public Firestation getPersistent(Firestation firestation) {
		Optional<Firestation> fireStationOptional = fireStationRepository.findById(firestation.getFireStationId());
		if (fireStationOptional.isPresent()) {
			return fireStationOptional.get();
		} else {
			return null;
		}
	}
}
