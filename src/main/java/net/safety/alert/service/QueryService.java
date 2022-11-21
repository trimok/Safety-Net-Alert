package net.safety.alert.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.dto.AdultByAddressDTO;
import net.safety.alert.dto.ChildrenByAddressDTO;
import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.EmailsByCityDTO;
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByFirstNameLastNameDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PersonsGroupByAddressByListStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;
import net.safety.alert.repository.IQueryRepository;

/**
 * @author trimok
 *
 */
@Service
public class QueryService implements IQueryService {

	/**
	 * 
	 */
	@Autowired
	IQueryRepository queryRepository;

	/**
	 * Getting the persons by station, with a count of adults and children
	 */
	@Override
	public PersonsByStationDTO findPersonsByStationDTO(String station) {
		// Persons
		List<PersonByStationDTO> persons = queryRepository.findPersonsByStationDTO(station);

		// Number of children
		LocalDate now = LocalDate.now();
		long childrenCount = persons.stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0)
				.count();

		// Number of adults
		long adultCount = persons.stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).count();

		return PersonsByStationDTO.toPersonsByStationDTO(persons, childrenCount, adultCount);
	}

	/**
	 * Getting the list of children (with age) (and adults) by address
	 */
	@Override
	public ChildrensByAddressDTO findChildrensByAddressDTO(String address) {

		List<ChildrenByAddressDTO> children = queryRepository.findChildrenByAddressDTO(address);
		List<AdultByAddressDTO> adults = queryRepository.findAdultsByAddressDTO(address);

		return ChildrensByAddressDTO.toChildrensByAddressDTO(children, adults);
	}

	/**
	 * Getting the phones by station
	 */
	@Override
	public PhonesByStationDTO findPhonesByStationDTO(String station) {
		List<String> phones = queryRepository.findPhonesByStationDTO(station);
		return PhonesByStationDTO.toPhonesByStationDTO(phones);
	}

	/**
	 * Getting the list of persons (and firestation) by address
	 */
	@Override
	public PersonsByAddressDTO findPersonsByAddressDTO(String address) { // Iterable<IPersonsByAddress>
		List<PersonByAddressDTO> personsDTO = queryRepository.findPersonsByAddressDTO(address);
		return PersonsByAddressDTO.toPersonsByAddressDTO(personsDTO);
	}

	/**
	 * Getting the persons, group by address, from a list of stations
	 */
	@Override
	public PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(List<String> stations) {
		Map<String, List<PersonGroupByAddressByListStationDTO>> personsMap = queryRepository
				.findPersonsGroupByAddressByListStationDTO(stations);
		return PersonsGroupByAddressByListStationDTO.toPersonsGroupByAddressByListStationDTO(personsMap);
	}

	/**
	 * Getting information of a Person from its first name and last name
	 */
	@Override
	public PersonsByFirstNameLastNameDTO findPersonsByFirstNameLastNameDTO(String firstName, String lastName) {
		List<PersonByFirstNameLastNameDTO> persons = queryRepository.findPersonsByFirstNameLastNameDTO(firstName,
				lastName);
		return PersonsByFirstNameLastNameDTO.toPersonsByFirstNameLastNameDTO(persons);
	}

	/**
	 * Getting the list of emails by city
	 */
	@Override
	public EmailsByCityDTO findEmailsByCityDTO(String city) {
		List<String> emails = queryRepository.findEmailsByCityDTO(city);
		return EmailsByCityDTO.toEmailsByCityDTO(emails);
	}
}
