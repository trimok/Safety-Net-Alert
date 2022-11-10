package net.safety.alert.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.repository.GeneralRepository;
import net.safety.alert.view.AdultDTO;
import net.safety.alert.view.ChildrenByAddressDTO;
import net.safety.alert.view.IChildrenByAddress;
import net.safety.alert.view.IPersonsByStation;
import net.safety.alert.view.IPhoneByStation;
import net.safety.alert.view.PersonsByStationDTO;
import net.safety.alert.view.PhoneByStationDTO;

@Service
public class GeneralService implements IGeneralService {
	@Autowired
	GeneralRepository generalRepository;

	@Override
	public PersonsByStationDTO findPersonsByStationDTO(String station) {
		Iterable<IPersonsByStation> persons = generalRepository.findPersonsByStation(station);
		LocalDate now = LocalDate.now();
		long childrenCount = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0).count();
		long adultCount = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).count();

		return new PersonsByStationDTO(persons, childrenCount, adultCount);
	}

	@Override
	public ChildrenByAddressDTO findChildrenByAddressDTO(String address) {
		Iterable<IChildrenByAddress> persons = generalRepository.findPersonByAddress(address);

		LocalDate now = LocalDate.now();
		Iterable<IChildrenByAddress> children = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0).toList();
		Iterable<IChildrenByAddress> adults = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).toList();

		List<AdultDTO> adultsDTO = new ArrayList<>();
		if (StreamSupport.stream(children.spliterator(), false).count() > 0) {
			adults.forEach(a -> adultsDTO.add(new AdultDTO(a.getFirstName(), a.getLastName())));
		}

		return new ChildrenByAddressDTO(children, adultsDTO);
	}

	@Override
	public PhoneByStationDTO findPhoneByStationDTO(String station) {
		Iterable<IPhoneByStation> phones = generalRepository.findPhoneByStation(station);
		return new PhoneByStationDTO(phones);
	}
}
