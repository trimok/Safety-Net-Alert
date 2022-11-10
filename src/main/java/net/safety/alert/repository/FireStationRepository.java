package net.safety.alert.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.safety.alert.model.FireStation;
import net.safety.alert.model.FireStationId;

@Repository
public interface FireStationRepository extends CrudRepository<FireStation, FireStationId> {

}
