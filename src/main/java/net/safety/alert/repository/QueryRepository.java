package net.safety.alert.repository;

import static net.safety.alert.constants.NumberConstants.ADULT_LIMIT;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.dto.AdultByAddressDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Repository
public class QueryRepository implements IQueryRepository {
	/**
	 * 
	 */
	@Autowired
	private Database database;

	/**
	 * Getting the persons by station, with a count of adults and children
	 */
	@Override
	public List<PersonByStationDTO> findPersonsByStationDTO(String station) {
		return database.getPersons().stream()
				.filter(p -> p.getAddress() != null && p.getAddress().getFireStation() != null
						&& station.equals(p.getAddress().getFireStation().getId()))
				.map(p -> PersonByStationDTO.toPersonByStationDTO(p)).toList();
	}

	/**
	 * Getting the list of children (with age) by address
	 */
	@Override
	public List<ChildrenByAddressDTO> findChildrenByAddressDTO(String address) {
		LocalDate now = LocalDate.now();
		return database.getPersons().stream().filter(
				p -> ((p.getBirthdate() != null) && (ChronoUnit.YEARS.between(p.getBirthdate(), now) < ADULT_LIMIT))
						&& p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> ChildrenByAddressDTO.toChildrenByAddressDTO(p)).toList();
	}

	/**
	 * Getting the list of adults by address
	 */
	@Override
	public List<AdultByAddressDTO> findAdultsByAddressDTO(String address) {
		LocalDate now = LocalDate.now();
		return database.getPersons().stream().filter(
				p -> ((p.getBirthdate() == null) || (ChronoUnit.YEARS.between(p.getBirthdate(), now) >= ADULT_LIMIT))
						&& p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> AdultByAddressDTO.toAdultDTO(p)).toList();
	}

	/**
	 * Getting the phones by station
	 */
	@Override
	public List<String> findPhonesByStationDTO(String station) {
		return database.getPersons().stream().filter(p -> p.getAddress() != null
				&& p.getAddress().getFireStation() != null && station.equals(p.getAddress().getFireStation().getId()))
				.map(p -> p.getPhone()).toList();
	}

	/**
	 * Getting the list of persons (and firestation) by address
	 */
	@Override
	public List<PersonByAddressDTO> findPersonsByAddressDTO(String address) {
		return database.getPersons().stream()
				.filter(p -> p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> PersonByAddressDTO.toPersonByAddressDTO(p)).toList();
	}

	/**
	 * Getting the persons, group by address, from a list of stations
	 */
	@Override
	public Map<String, List<PersonGroupByAddressByListStationDTO>> findPersonsGroupByAddressByListStationDTO(
			List<String> stations) {

		return database.getPersons().stream()
				.filter(p -> stations.stream()
						.anyMatch(station -> p.getAddress() != null && p.getAddress().getFireStation() != null
								&& station.equals(p.getAddress().getFireStation().getId())))
				.map(p -> PersonGroupByAddressByListStationDTO.toPersonGroupByAddressByListStationDTO(p))
				.collect(Collectors.groupingBy(p -> p.getAddress()));
	}

	/**
	 * Getting information of a Person from its first name and last name
	 */
	@Override
	public List<PersonByFirstNameLastNameDTO> findPersonsByFirstNameLastNameDTO(String firstName, String lastName) {
		return database.getPersons().stream()
				.filter(p -> (!StringsUtil.isValid(firstName) || firstName.equals(p.getFirstName()))
						&& (!StringsUtil.isValid(lastName) || lastName.equals(p.getLastName())))
				.map(p -> PersonByFirstNameLastNameDTO.toPersonByFirstNameLastNameDTO(p)).toList();
	}

	/**
	 * Getting the list of emails by city
	 */
	@Override
	public List<String> findEmailsByCityDTO(String city) {
		return database.getPersons().stream().filter(p -> city.equals(p.getCity())).map(p -> p.getEmail()).toList();
	}
}
