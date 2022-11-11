package net.safety.alert.service;

import org.springframework.stereotype.Service;;

@Service
public class FireStationService implements IFireStationService {
	/*
	 * @Autowired FireStationRepository fireStationRepository;
	 * 
	 * 
	 * 
	 * @Override public FireStation createFirestation(FireStation fireStation) { if (!fireStation.isValid()) { throw new
	 * PersonNotValidException(CREATE_PERSON, FIRESTATION_NOT_VALID, fireStation); }
	 * 
	 * FireStation firestationDatabase = getPersistent(fireStation);
	 * 
	 * if (firestationDatabase == null) { return fireStationRepository.save(fireStation); } else { throw new
	 * FireStationAlreadyCreatedException(CREATE_FIRESTATION, FIRESTATION_ALREADY_CREATED, fireStation); } }
	 * 
	 * @Override public void deleteFirestation(FireStation fireStation) { if (!fireStation.isValid()) { throw new
	 * PersonNotValidException(CREATE_PERSON, FIRESTATION_NOT_VALID, fireStation); }
	 * 
	 * FireStation firestationDatabase = getPersistent(fireStation);
	 * 
	 * if (firestationDatabase != null) { fireStationRepository.delete(fireStation); } else { throw new
	 * FireStationNotFoundException(DELETE_FIRESTATION, FIRESTATION_NOT_FOUND, fireStation); } }
	 * 
	 * @Override public FireStation getPersistent(FireStation fireStation) { Optional<FireStation> fireStationOptional =
	 * fireStationRepository.findById(fireStation.getFireStationId()); if (fireStationOptional.isPresent()) { return
	 * fireStationOptional.get(); } else { return null; } }
	 */
}
