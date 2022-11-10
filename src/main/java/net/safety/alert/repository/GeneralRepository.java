package net.safety.alert.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import net.safety.alert.model.Person;
import net.safety.alert.model.PersonId;
import net.safety.alert.view.IChildrenByAddress;
import net.safety.alert.view.IPersonsByStation;
import net.safety.alert.view.IPhoneByStation;

public interface GeneralRepository extends CrudRepository<Person, PersonId> {
	@Query("Select distinct p.firstName as firstName, p.lastName as lastName, p.address as address, p.phone as phone , "
			+ "m.birthdate as birthdate from MedicalRecord m, Person p  join FireStation f on (p.address = f.address) "
			+ " left join MedicalRecord m on (p.firstName = m.firstName and p.lastName = m.lastName) where f.station=?1 ")
	Iterable<IPersonsByStation> findPersonsByStation(String station);

	@Query("Select distinct p.firstName as firstName, p.lastName as lastName, "
			+ " m.birthdate as birthdate from MedicalRecord m, Person p "
			+ " left join MedicalRecord m on (p.firstName = m.firstName and p.lastName = m.lastName) where p.address=?1 ")
	Iterable<IChildrenByAddress> findPersonByAddress(String address);

	@Query("Select distinct p.phone as phone from Person p " + "  join FireStation f on (p.address = f.address) "
			+ " where f.station=?1 ")
	Iterable<IPhoneByStation> findPhoneByStation(String station);
}
