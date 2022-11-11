package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_FIRESTATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_FIRESTATION;
import static net.safety.alert.constants.HttpMessageConstants.FIRESTATION_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.FIRESTATION_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.FIRESTATION_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_FIRESTATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.exception.FireStationAlreadyCreatedException;
import net.safety.alert.exception.FireStationNotFoundException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.repository.IFireStationRepository;

@Service
public class FireStationService implements IFireStationService {

	@Autowired
	IFireStationRepository fireStationRepository;

	@Override
	public FireStationDTO createFireStation(FireStationDTO fireStation) {
		if (!fireStation.isValid()) {
			throw new PersonNotValidException(CREATE_FIRESTATION, FIRESTATION_NOT_VALID, fireStation);
		}

		FireStationDTO firestationDatabase = getPersistent(fireStation);

		if (firestationDatabase == null) {
			return fireStationRepository.save(fireStation);
		} else {
			throw new FireStationAlreadyCreatedException(CREATE_FIRESTATION, FIRESTATION_ALREADY_CREATED, fireStation);
		}
	}

	@Override
	public FireStationDTO updateFireStation(FireStationDTO fireStation) {
		if (!fireStation.isValid()) {
			throw new PersonNotValidException(UPDATE_FIRESTATION, FIRESTATION_NOT_VALID, fireStation);
		}

		FireStationDTO firestationDatabase = getPersistent(fireStation);

		if (firestationDatabase == null) {
			return fireStationRepository.save(fireStation);
		} else {
			throw new FireStationAlreadyCreatedException(UPDATE_FIRESTATION, FIRESTATION_ALREADY_CREATED, fireStation);
		}
	}

	@Override
	public void deleteFireStation(FireStationDTO fireStation) {
		if (!fireStation.isValid()) {
			throw new PersonNotValidException(DELETE_FIRESTATION, FIRESTATION_NOT_VALID, fireStation);
		}

		FireStationDTO firestationDatabase = getPersistent(fireStation);

		if (firestationDatabase != null) {
			fireStationRepository.delete(fireStation);
		} else {
			throw new FireStationNotFoundException(DELETE_FIRESTATION, FIRESTATION_NOT_FOUND, fireStation);
		}
	}

	@Override
	public FireStationDTO getPersistent(FireStationDTO fireStation) {
		return fireStationRepository.getPersistent(fireStation);
	}
}
