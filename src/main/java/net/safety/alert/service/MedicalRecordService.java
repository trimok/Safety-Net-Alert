package net.safety.alert.service;

import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService implements IMedicalRecordService {
	/*
	 * @Autowired MedicalRecordRepository medicalRecordRepository;
	 * 
	 * @Override public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) { if (!medicalRecord.isValid()) {
	 * throw new PersonNotValidException(CREATE_MEDICAL_RECORD, MEDICAL_RECORD_NOT_VALID, medicalRecord); }
	 * 
	 * MedicalRecord medicalRecordDatabase = getPersistent(medicalRecord);
	 * 
	 * if (medicalRecordDatabase == null) { return medicalRecordRepository.save(medicalRecord); } else { throw new
	 * MedicalRecordAlreadyCreatedException(CREATE_MEDICAL_RECORD, MEDICAL_RECORD_ALREADY_CREATED,
	 * medicalRecordDatabase); } }
	 * 
	 * @Override public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
	 * 
	 * if (!medicalRecord.isValid()) { throw new PersonNotValidException(UPDATE_MEDICAL_RECORD,
	 * MEDICAL_RECORD_NOT_VALID, medicalRecord); }
	 * 
	 * MedicalRecord medicalRecordDatabase = getPersistent(medicalRecord);
	 * 
	 * if (medicalRecordDatabase != null) { return medicalRecordRepository.save(medicalRecord); } else { throw new
	 * MedicalRecordNotFoundException(UPDATE_MEDICAL_RECORD, MEDICAL_RECORD_NOT_FOUND, medicalRecord); } }
	 * 
	 * @Override public MedicalRecord patchMedicalRecord(MedicalRecord medicalRecord) { if (!medicalRecord.isValid()) {
	 * throw new PersonNotValidException(PATCH_MEDICAL_RECORD, MEDICAL_RECORD_NOT_VALID, medicalRecord); }
	 * 
	 * MedicalRecord medicalRecordDatabase = getPersistent(medicalRecord);
	 * 
	 * if (medicalRecordDatabase != null) { medicalRecordDatabase.patchBy(medicalRecord); return
	 * medicalRecordRepository.save(medicalRecordDatabase); } else { throw new
	 * MedicalRecordNotFoundException(PATCH_MEDICAL_RECORD, MEDICAL_RECORD_NOT_FOUND, medicalRecord); } }
	 * 
	 * @Override public void deleteMedicalRecord(MedicalRecord medicalRecord) { if (!medicalRecord.isValid()) { throw
	 * new PersonNotValidException(DELETE_MEDICAL_RECORD, MEDICAL_RECORD_NOT_VALID, medicalRecord); }
	 * 
	 * MedicalRecord medicalRecordDatabase = getPersistent(medicalRecord);
	 * 
	 * if (medicalRecordDatabase != null) { medicalRecordRepository.delete(medicalRecordDatabase); } else { throw new
	 * MedicalRecordNotFoundException(DELETE_MEDICAL_RECORD, MEDICAL_RECORD_NOT_FOUND, medicalRecord); } }
	 * 
	 * @Override public MedicalRecord getPersistent(MedicalRecord medicalRecord) { Optional<MedicalRecord>
	 * medicalRecordOptional = medicalRecordRepository .findById(medicalRecord.getMedicalRecordId()); if
	 * (medicalRecordOptional.isPresent()) { return medicalRecordOptional.get(); } else { return null; } }
	 * 
	 */
}
