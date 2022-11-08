package net.safety.alert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.model.Medicalrecord;
import net.safety.alert.model.MedicalrecordId;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, MedicalrecordId> {
}
