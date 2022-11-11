package net.safety.alert.repository;

public interface GeneralRepository {
	/*
	 * @Query("Select distinct p.firstName as firstName, p.lastName as lastName, p.address as address, p.phone as phone , "
	 * + "m.birthdate as birthdate from MedicalRecord m, Person p  join FireStation f on (p.address = f.address) " +
	 * " left join MedicalRecord m on (p.firstName = m.firstName and p.lastName = m.lastName) where f.station=?1 ")
	 * Iterable<IPersonsByStation> findPersonsByStation(String station);
	 * 
	 * @Query("Select distinct p.firstName as firstName, p.lastName as lastName, " +
	 * " m.birthdate as birthdate from MedicalRecord m, Person p " +
	 * " left join MedicalRecord m on (p.firstName = m.firstName and p.lastName = m.lastName) where p.address=?1 ")
	 * Iterable<IChildrenByAddress> findPersonsByAddress(String address);
	 * 
	 * @Query("Select distinct p.phone as phone from Person p " + "  join FireStation f on (p.address = f.address) " +
	 * " where f.station=?1 ") Iterable<IPhoneByStation> findPhonesByStation(String station);
	 */

	/*
	 * @Query("Select distinct p.lastName as lastName, p.phone as phone, m.medications as medications, m.allergies as allergies "
	 * + " from MedicalRecord m, Person p " +
	 * " left join MedicalRecord m on (p.firstName = m.firstName and p.lastName = m.lastName) " +
	 * " where p.address=?1 ") Iterable<IPersonsByAddress> findPersonsByAddressDTO(String address);
	 */

	/*
	 * @Query(nativeQuery = true, value =
	 * "Select distinct m.birthdate as birthdate, medication.name as name, medication.quantity as quantity " +
	 * " from MedicalRecord m, Medication medication " +
	 * " inner join Medication  on (m.firstName = medication.firstName and m.lastName = medication.lastName) ")
	 * Iterable<IPersonsByAddress> findPersonsByAddressDTO(String address);
	 */
}
