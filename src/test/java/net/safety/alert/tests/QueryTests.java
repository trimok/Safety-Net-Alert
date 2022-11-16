package net.safety.alert.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

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

@SpringBootTest
@AutoConfigureMockMvc
class QueryTests {
	private static final int FIRESTATION_3_PHONE_COUNT = 11;
	private static final int ADDRESS_CULVER_CHILD_COUNT = 2;
	private static final int ADDRESS_CULVER_ADULT_COUNT = 3;
	private static final int FIRESTATION_4_CHILDREN_COUNT = 0;
	private static final int FIRESTATION_4_ADULT_COUNT = 1;
	private static final int FIRESTATION_4_PERSONS_COUNT = 1;
	private static final int FIRESTATION_3_CHILDREN_COUNT = 3;
	private static final int FIRESTATION_3_ADULT_COUNT = 8;
	private static final int FIRESTATION_3_PERSONS_COUNT = 11;
	private static final int STATION_LIST_3_4_ADDRESS_COUNT = 5;

	// Constants
	private final static String FIRESTATION_3 = "3";
	private final static String FIRESTATION_4 = "4";
	private static final String STATION_LIST_3_4 = "3,4";

	private final static String ADDRESS_CULVER = "1509 Culver St";
	private static final int ADDRESS_CULVER_PERSONS_COUNT = 5;
	private final static String FELICIA = "Felicia";
	private final static String BOYD = "Boyd";
	private final static int BOYD_COUNT = 6;
	private final static String CULVER_CITY = "Culver";
	private final static int CULVER_CITY_PERSONS_COUNT = 23;

	@Autowired
	private MockMvc mockMvc;

	public final static ObjectMapper mapper = new ObjectMapper();

	// Utilitary Method
	public <T> T getDtoFromUrlGet(String url, Class<T> classT) {
		T tDTO = null;
		try {

			MvcResult result = mockMvc.perform(get(url)).andReturn();

			tDTO = mapper.readValue(result.getResponse().getContentAsString(),
					mapper.getTypeFactory().constructType(classT));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tDTO;
	}

	/********************** TEST GET /firestation?stationNumber= QueryController.findPersonsByStationDTO **********/

	public static Stream<Arguments> whenStationIsGiven_ShouldReturnPersonsAdultsChildrenProvider() {
		// GIVEN
		return Stream.of(
				Arguments.arguments(FIRESTATION_3, FIRESTATION_3_PERSONS_COUNT, FIRESTATION_3_ADULT_COUNT,
						FIRESTATION_3_CHILDREN_COUNT),
				Arguments.arguments(FIRESTATION_4, FIRESTATION_4_PERSONS_COUNT, FIRESTATION_4_ADULT_COUNT,
						FIRESTATION_4_CHILDREN_COUNT));
	}

	@DisplayName("GET /firestation?stationNumber= : ")
	@ParameterizedTest(name = "when Station is {0}, should return {1} persons, {2} adults, and  {3} children")
	@MethodSource("whenStationIsGiven_ShouldReturnPersonsAdultsChildrenProvider")
	public void whenStationIsGiven_ShouldReturnPersonsAdultsChildren(String station, long personsCount, long adultCount,
			long childrenCount) throws Exception {

		// WHEN
		PersonsByStationDTO personsByStationDTO = getDtoFromUrlGet("/firestation?stationNumber=" + station,
				PersonsByStationDTO.class);

		// THEN
		List<PersonByStationDTO> persons = personsByStationDTO.getPersons();
		PersonByStationDTO person = persons.get(0);

		assertThat(persons.size()).isEqualTo(personsCount);
		assertThat(personsByStationDTO.getAdultCount()).isEqualTo(adultCount);
		assertThat(personsByStationDTO.getChildrenCount()).isEqualTo(childrenCount);
		assert (!person.getFirstName().isEmpty());
		assert (!person.getLastName().isEmpty());
	}

	/********************** TEST GET /childAlert?address= QueryController.findChildrensByAddressDTO **********/

	public static Stream<Arguments> whenAddressIsGiven_ShouldReturnChildrenProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(ADDRESS_CULVER, ADDRESS_CULVER_ADULT_COUNT, ADDRESS_CULVER_CHILD_COUNT));
	}

	@DisplayName("GET /childAlert?address= : ")
	@ParameterizedTest(name = "when Address is {0},  should return {1} adults, and  {2} children")
	@MethodSource("whenAddressIsGiven_ShouldReturnChildrenProvider")
	public void whenAddressIsGiven_ShouldReturnChildren(String address, long adultCount, long childrenCount)
			throws Exception {

		// WHEN
		ChildrensByAddressDTO childrens = getDtoFromUrlGet("/childAlert?address=" + address,
				ChildrensByAddressDTO.class);

		// THEN
		List<ChildrenByAddressDTO> children = childrens.getChildren();
		ChildrenByAddressDTO child = children.get(0);
		List<AdultByAddressDTO> adults = childrens.getAdults();
		AdultByAddressDTO adult = adults.get(0);

		assertThat(adults.size()).isEqualTo(adultCount);
		assertThat(children.size()).isEqualTo(childrenCount);
		assertThat(child.getAge()).isGreaterThan(0);
		assert (!child.getFirstName().isEmpty());
		assert (!child.getLastName().isEmpty());
		assert (!adult.getFirstName().isEmpty());
		assert (!adult.getLastName().isEmpty());
	}

	/********************** TEST GET /phoneAlert?firestation= QueryController.findPhonesByStationDTO **********/

	public static Stream<Arguments> whenFireStationIsGiven_ShouldReturnPhoneListProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(FIRESTATION_3, FIRESTATION_3_PHONE_COUNT));
	}

	@DisplayName("GET /phoneAlert?firestation= : ")
	@ParameterizedTest(name = "when firestation is {0},  should return {1} phones")
	@MethodSource("whenFireStationIsGiven_ShouldReturnPhoneListProvider")
	public void whenFireStationIsGiven_ShouldReturnPhoneList(String fireStation, long phonesCount) throws Exception {

		// WHEN
		PhonesByStationDTO phonesDTO = getDtoFromUrlGet("/phoneAlert?firestation=" + fireStation,
				PhonesByStationDTO.class);

		// THEN
		List<String> phones = phonesDTO.getPhones();
		assertThat(phones.size()).isEqualTo(phonesCount);
	}

	/********************** TEST GET /fire?address= QueryController.findPersonsByAddressDTO **********/

	public static Stream<Arguments> whenAddressIsGiven_ShouldReturnPersonListProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(ADDRESS_CULVER, ADDRESS_CULVER_PERSONS_COUNT));
	}

	@DisplayName("GET /fire?address= : ")
	@ParameterizedTest(name = "when address is {0},  should return {1} persons with medical records")
	@MethodSource("whenAddressIsGiven_ShouldReturnPersonListProvider")
	public void whenAddressIsGiven_ShouldReturnPersonList(String address, long personsCount) throws Exception {

		// WHEN
		PersonsByAddressDTO personsDTO = getDtoFromUrlGet("/fire?address=" + address, PersonsByAddressDTO.class);

		// THEN
		List<PersonByAddressDTO> persons = personsDTO.getPersons();
		PersonByAddressDTO person = persons.get(1);
		Map<String, String> medications = person.getMedications();
		List<String> allergies = person.getAllergies();

		assertThat(persons.size()).isEqualTo(personsCount);
		assert (!person.getLastName().isEmpty());
		assert (!person.getPhone().isEmpty());
		assert (!person.getFireStation().isEmpty());
		assertThat(person.getAge()).isGreaterThan(0);
		assertThat(medications.size()).isGreaterThan(0);
		assertThat(allergies.size()).isGreaterThan(0);
	}

	/********* TEST GET /flood/stations?stations= QueryController.findPersonsGroupByAddressByListStationDTO *********/

	public static Stream<Arguments> whenListStationIsGiven_ShouldReturnPersonListGroupByAddressProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(STATION_LIST_3_4, STATION_LIST_3_4_ADDRESS_COUNT, ADDRESS_CULVER,
				ADDRESS_CULVER_PERSONS_COUNT));
	}

	@DisplayName("GET /flood/stations?stations= : ")
	@ParameterizedTest(name = "when listeStation is {0},  should return {1} address groups with {2} persons for the group at address {3}")
	@MethodSource("whenListStationIsGiven_ShouldReturnPersonListGroupByAddressProvider")
	public void whenListStationIsGiven_ShouldReturnPersonListGroupByAddress(String addressList, long groupCount,
			String groupName, long personsGroupCount) throws Exception {

		// WHEN
		PersonsGroupByAddressByListStationDTO mapPersonsDTO = getDtoFromUrlGet(
				"/flood/stations?stations=" + addressList, PersonsGroupByAddressByListStationDTO.class);

		// THEN
		Map<String, List<PersonGroupByAddressByListStationDTO>> mapPersons = mapPersonsDTO.getPersonsMap();
		List<PersonGroupByAddressByListStationDTO> personsGroup = mapPersons.get(groupName);
		PersonGroupByAddressByListStationDTO person = personsGroup.get(1);

		assertThat(mapPersons.size()).isEqualTo(groupCount);
		assertThat(mapPersons.size()).isEqualTo(personsGroupCount);

		Map<String, String> medications = person.getMedications();
		List<String> allergies = person.getAllergies();
		assert (!person.getLastName().isEmpty());
		assert (!person.getPhone().isEmpty());
		assert (!person.getFireStation().isEmpty());
		assertThat(person.getAge()).isGreaterThan(0);
		assertThat(medications.size()).isGreaterThan(0);
		assertThat(allergies.size()).isGreaterThan(0);
	}

	/********* TEST GET /personInfo?firstName=&lastName= QueryController.findPersonsByFirstNameLastNameDTO *********/

	public static Stream<Arguments> whenFirstNameAndOrLastNameIsGiven_ShouldReturnPersonPersonListProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(FELICIA, BOYD, 1), Arguments.arguments("", BOYD, BOYD_COUNT));
	}

	@DisplayName("GET /personInfo?firstName=&lastName= : ")
	@ParameterizedTest(name = "when firstName is {0}, and lastName is {1}  should return {2} person(s)")
	@MethodSource("whenFirstNameAndOrLastNameIsGiven_ShouldReturnPersonPersonListProvider")
	public void whenFirstNameAndOrLastNameIsGiven_ShouldReturnPersonPersonList(String firstName, String lastName,
			int personCount) throws Exception {

		// WHEN
		PersonsByFirstNameLastNameDTO personsDTO = getDtoFromUrlGet(
				"/personInfo?firstName=" + firstName + "&lastName=" + lastName, PersonsByFirstNameLastNameDTO.class);

		// THEN
		List<PersonByFirstNameLastNameDTO> persons = personsDTO.getPersons();
		PersonByFirstNameLastNameDTO person = persons.get(0);

		assertThat(persons.size()).isEqualTo(personCount);

		Map<String, String> medications = person.getMedications();
		List<String> allergies = person.getAllergies();
		assert (!person.getLastName().isEmpty());
		assert (!person.getAddress().isEmpty());
		assertThat(person.getAge()).isGreaterThan(0);
		if (personCount == 1) {
			assertTrue(medications.size() > 0 && allergies.size() > 0);
		} else {
			assertTrue(medications.size() > 0 || allergies.size() > 0);
		}
	}

	/********* TEST GET /communityEmail?city= QueryController.findEmailsByCityDTO *********/

	public static Stream<Arguments> whenCityIsGiven_ShouldReturnPersonEmailListProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(CULVER_CITY, CULVER_CITY_PERSONS_COUNT), Arguments.arguments("", 0));
	}

	@DisplayName("GET /communityEmail?city= : ")
	@ParameterizedTest(name = "when city is {0}  should return {1} emails(s)")
	@MethodSource("whenCityIsGiven_ShouldReturnPersonEmailListProvider")
	public void whenCityIsGiven_ShouldReturnPersonEmailList(String city, int personCount) throws Exception {

		// WHEN
		EmailsByCityDTO emailsDTO = getDtoFromUrlGet("/communityEmail?city=" + city, EmailsByCityDTO.class);

		// THEN
		List<String> emails = emailsDTO.getEmails();
		assertThat(emails.size()).isEqualTo(personCount);
	}
	/**************************************************************************************************************************/
}
