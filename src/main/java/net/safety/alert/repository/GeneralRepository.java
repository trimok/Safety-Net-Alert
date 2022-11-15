package net.safety.alert.repository;

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
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;

@Repository
public class GeneralRepository implements IGeneralRepository {
	@Autowired
	private Database database;

	@Override
	public List<PersonByStationDTO> findPersonsByStationDTO(String station) {
		return database.getPersonsMap().values().stream()
				.filter(p -> p.getAddress() != null && p.getAddress().getFireStation() != null
						&& station.equals(p.getAddress().getFireStation().getId()))
				.map(p -> PersonByStationDTO.toPersonByStationDTO(p)).toList();
	}

	@Override
	public List<ChildrenByAddressDTO> findChildrenByAddressDTO(String address) {
		LocalDate now = LocalDate.now();
		return database.getPersonsMap().values().stream()
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0 && p.getAddress() != null
						&& address.equals(p.getAddress().getName()))
				.map(p -> ChildrenByAddressDTO.toChildrenByAddressDTO(p)).toList();
	}

	@Override
	public List<AdultByAddressDTO> findAdultsByAddressDTO(String address) {
		LocalDate now = LocalDate.now();
		return database
				.getPersonsMap().values().stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0
						&& p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> AdultByAddressDTO.toAdultDTO(p)).toList();
	}

	@Override
	public List<String> findPhonesByStationDTO(String station) {
		return database.getPersonsMap().values().stream().filter(p -> p.getAddress() != null
				&& p.getAddress().getFireStation() != null && station.equals(p.getAddress().getFireStation().getId()))
				.map(p -> p.getPhone()).toList();
	}

	@Override
	public List<PersonByAddressDTO> findPersonsByAddressDTO(String address) {
		return database.getPersonsMap().values().stream()
				.filter(p -> p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> PersonByAddressDTO.toPersonByAddressDTO(p)).toList();
	}

	@Override
	public Map<String, List<PersonGroupByAddressByListStationDTO>> findPersonsGroupByAddressByListStationDTO(
			List<String> stations) {

		return database.getPersonsMap().values().stream()
				.filter(p -> stations.stream()
						.anyMatch(station -> p.getAddress() != null && p.getAddress().getFireStation() != null
								&& station.equals(p.getAddress().getFireStation().getId())))
				.map(p -> PersonGroupByAddressByListStationDTO.toPersonGroupByAddressByListStationDTO(p))
				.collect(Collectors.groupingBy(p -> p.getAddress()));
	}
}
