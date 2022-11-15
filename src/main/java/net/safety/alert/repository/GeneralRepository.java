package net.safety.alert.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.safety.alert.database.Database;
import net.safety.alert.dto.AdultDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByStationDTO;

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
	public List<AdultDTO> findAdultsByAddressDTO(String address) {
		LocalDate now = LocalDate.now();
		return database
				.getPersonsMap().values().stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0
						&& p.getAddress() != null && address.equals(p.getAddress().getName()))
				.map(p -> AdultDTO.toAdultDTO(p)).toList();
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
}
