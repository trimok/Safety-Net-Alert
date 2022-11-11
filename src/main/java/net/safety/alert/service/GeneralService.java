package net.safety.alert.service;

import org.springframework.stereotype.Service;

@Service
public class GeneralService implements IGeneralService {
	/*
	 * @Autowired GeneralRepository generalRepository;
	 * 
	 * @Override public PersonsByStationDTO findPersonsByStationDTO(String station) { Iterable<IPersonsByStation>
	 * persons = generalRepository.findPersonsByStation(station); LocalDate now = LocalDate.now(); long childrenCount =
	 * StreamSupport.stream(persons.spliterator(), false) .filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) <
	 * 18.0).count(); long adultCount = StreamSupport.stream(persons.spliterator(), false) .filter(p ->
	 * ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).count();
	 * 
	 * return new PersonsByStationDTO(persons, childrenCount, adultCount); }
	 * 
	 * @Override public ChildrenByAddressDTO findChildrenByAddressDTO(String address) { Iterable<IChildrenByAddress>
	 * persons = generalRepository.findPersonsByAddress(address);
	 * 
	 * LocalDate now = LocalDate.now(); Iterable<IChildrenByAddress> children =
	 * StreamSupport.stream(persons.spliterator(), false) .filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) <
	 * 18.0).toList(); Iterable<IChildrenByAddress> adults = StreamSupport.stream(persons.spliterator(), false)
	 * .filter(p -> ChronoUnit.YEARS.between(p.getBirthdate(), now) >= 18.0).toList();
	 * 
	 * List<AdultDTO> adultsDTO = new ArrayList<>(); if (StreamSupport.stream(children.spliterator(), false).count() >
	 * 0) { adults.forEach(a -> adultsDTO.add(new AdultDTO(a.getFirstName(), a.getLastName()))); }
	 * 
	 * return new ChildrenByAddressDTO(children, adultsDTO); }
	 * 
	 * @Override public PhoneByStationDTO findPhonesByStationDTO(String station) { Iterable<IPhoneByStation> phones =
	 * generalRepository.findPhonesByStation(station); return new PhoneByStationDTO(phones); }
	 * 
	 * @Override public PersonByAddressDTO findPersonsByAddressDTO(String address) { // Iterable<IPersonsByAddress>
	 * persons = generalRepository.findPersonsByAddressDTO(address); Iterable<IPersonsByAddress> persons = null; return
	 * new PersonByAddressDTO(persons); }
	 */
}
