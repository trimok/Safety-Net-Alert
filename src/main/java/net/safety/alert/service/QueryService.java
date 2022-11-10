package net.safety.alert.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.safety.alert.repository.QueryRepository;
import net.safety.alert.view.IPersonsByStation;
import net.safety.alert.view.PersonsByStationDTO;

@Service
public class QueryService implements IQueryService {
	@Autowired
	QueryRepository queryRepository;

	@Override
	public Iterable<IPersonsByStation> findPersonsByStation(String station) {
		return queryRepository.findPersonsByStation(station);
	}

	@Override
	public PersonsByStationDTO findPersonsByStationDTO(String station) {
		Iterable<IPersonsByStation> persons = findPersonsByStation(station);
		LocalDate now = LocalDate.now();
		long childrenCount = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) < 18.0).count();
		long adultCount = StreamSupport.stream(persons.spliterator(), false)
				.filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).count();

		return new PersonsByStationDTO(persons, childrenCount, adultCount);
	}
}
