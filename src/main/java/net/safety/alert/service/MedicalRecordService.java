package net.safety.alert.service;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.MEDICAL_RECORD_ALREADY_CREATED;
import static net.safety.alert.constants.HttpMessageConstants.MEDICAL_RECORD_NOT_FOUND;
import static net.safety.alert.constants.HttpMessageConstants.MEDICAL_RECORD_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_MEDICAL_RECORD_OPERATION;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.exception.MedicalRecordAlreadyCreatedException;
import net.safety.alert.exception.MedicalRecordNotFoundException;
import net.safety.alert.exception.PersonNotValidException;
import net.safety.alert.model.Person;
import net.safety.alert.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordService implements IMedicalRecordService {

	@Autowired
	IMedicalRecordRepository medicalRecordRepository;

	@Override
	public MedicalRecordDTO createMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
		Person person = medicalRecordDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(CREATE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_VALID, person);
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase == null) {
			return MedicalRecordDTO.toMedicalRecordDTO(medicalRecordRepository.save(person));
		} else if (personDatabase.isMedicalRecordEmpty()) {
			return updateMedicalRecord(medicalRecordDTO);
		} else {
			throw new MedicalRecordAlreadyCreatedException(CREATE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_ALREADY_CREATED,
					personDatabase);
		}
	}

	@Override
	public MedicalRecordDTO updateMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
		Person person = medicalRecordDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(UPDATE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_VALID, person);
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {

			personDatabase.updateMedicalRecords(person);
			return MedicalRecordDTO.toMedicalRecordDTO(medicalRecordRepository.save(personDatabase));
		} else {
			throw new MedicalRecordNotFoundException(UPDATE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_FOUND, person);
		}
	}

	@Override
	public MedicalRecordDTO patchMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
		Person person = medicalRecordDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(PATCH_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_VALID, person);
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			personDatabase.patchMedicalRecordBy(person);
			return MedicalRecordDTO.toMedicalRecordDTO(medicalRecordRepository.save(personDatabase));
		} else {
			throw new MedicalRecordNotFoundException(PATCH_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_FOUND, person);
		}
	}

	@Override
	public void deleteMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
		Person person = medicalRecordDTO.toPerson();
		if (!person.isValid()) {
			throw new PersonNotValidException(DELETE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_VALID, person);
		}

		Person personDatabase = getPersistent(person);

		if (personDatabase != null) {
			medicalRecordRepository.delete(personDatabase.emptyMedicalRecord());
		} else {
			throw new MedicalRecordNotFoundException(DELETE_MEDICAL_RECORD_OPERATION, MEDICAL_RECORD_NOT_FOUND, person);
		}
	}

	@Override
	public Person getPersistent(Person medicalRecord) {
		return medicalRecordRepository.getPersistent(medicalRecord);
	}
}
