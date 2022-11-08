package net.safety.alert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.model.Firestation;
import net.safety.alert.model.FirestationId;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, FirestationId> {
}
