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
import net.safety.alert.dto.PersonByAddressDTO;
import net.safety.alert.dto.PersonByStationDTO;
import net.safety.alert.dto.PersonGroupByAddressByListStationDTO;
import net.safety.alert.dto.PersonsByAddressDTO;
import net.safety.alert.dto.PersonsByStationDTO;
import net.safety.alert.dto.PersonsGroupByAddressByListStationDTO;
import net.safety.alert.dto.PhonesByStationDTO;
import net.safety.alert.repository.IGeneralRepository;

@Service
public class GeneralService implements IGeneralService {

	@Autowired
	IGeneralRepository generalRepository;

	@Override
	public PersonsByStationDTO findPersonsByStationDTO(String station) {
		// Persons
		List<PersonByStationDTO> persons = generalRepository.findPersonsByStationDTO(station);

		// Number of children
		LocalDate now = LocalDate.now();
		long childrenCount = persons.stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0)
				.count();

		// Number of adults
		long adultCount = persons.stream().filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).count();

		return PersonsByStationDTO.toPersonsByStationDTO(persons, childrenCount, adultCount);
	}

	@Override
	public ChildrensByAddressDTO findChildrensByAddressDTO(String address) {

		List<ChildrenByAddressDTO> children = generalRepository.findChildrenByAddressDTO(address);
		List<AdultByAddressDTO> adults = generalRepository.findAdultsByAddressDTO(address);

		return ChildrensByAddressDTO.toChildrensByAddressDTO(children, adults);
	}

	@Override
	public PhonesByStationDTO findPhonesByStationDTO(String station) {
		List<String> phones = generalRepository.findPhonesByStationDTO(station);
		return PhonesByStationDTO.toPhonesByStationDTO(phones);
	}

	@Override
	public PersonsByAddressDTO findPersonsByAddressDTO(String address) { // Iterable<IPersonsByAddress>
		List<PersonByAddressDTO> personsDTO = generalRepository.findPersonsByAddressDTO(address);
		return PersonsByAddressDTO.toPersonsByAddressDTO(personsDTO);
	}

	@Override
	public PersonsGroupByAddressByListStationDTO findPersonsGroupByAddressByListStationDTO(List<String> stations) {
		Map<String, List<PersonGroupByAddressByListStationDTO>> personsMap = generalRepository
				.findPersonsGroupByAddressByListStationDTO(stations);
		return PersonsGroupByAddressByListStationDTO.toPersonsGroupByAddressByListStationDTO(personsMap);
	}
}
