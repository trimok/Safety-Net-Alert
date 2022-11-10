package net.safety.alert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.model.MedicalRecord;
import net.safety.alert.model.MedicalRecordId;

@Repository
public interface MedicalRecordRepository extends CrudRepository<MedicalRecord, MedicalRecordId> {
}
