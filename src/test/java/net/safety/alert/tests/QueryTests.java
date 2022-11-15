package net.safety.alert.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.safety.alert.dto.ChildrensByAddressDTO;
import net.safety.alert.dto.PersonsByStationDTO;

@SpringBootTest
@AutoConfigureMockMvc
class QueryTests {
	@Autowired
	private MockMvc mockMvc;

	// Constants
	private final static String FIRESTATION_3 = "3";
	private final static String FIRESTATION_4 = "4";
	private final static String ADDRESS_CULVER = "1509 Culver St";

	public static Stream<Arguments> parametersProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(1, 2), Arguments.arguments(3, 4));
	}

	/********************** TEST GET /firestation?stationNumber=? QueryController.findPersonsByStationDTO **********/
	public static Stream<Arguments> whenStationIsChoosen_ShouldReturnPersonsAdultsChildrenProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(FIRESTATION_3, 11, 8, 3), Arguments.arguments(FIRESTATION_4, 1, 1, 0));
	}

	@DisplayName("GET /firestation?stationNumber=? : ")
	@ParameterizedTest(name = "when Station is {0}, should return {1} persons, {2} adults, and  {3} children")
	@MethodSource("whenStationIsChoosen_ShouldReturnPersonsAdultsChildrenProvider")
	public void whenStationIsChoosen_ShouldReturnPersonsAdultsChildren(String station, long personsCount,
			long adultCount, long childrenCount) throws Exception {
		// WHEN
		MvcResult result = mockMvc.perform(get("/firestation?stationNumber=" + station)).andReturn();

		ObjectMapper mapper = new ObjectMapper();

		PersonsByStationDTO personsByStationDTO = mapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<PersonsByStationDTO>() {
				});

		// THEN
		assertThat(personsByStationDTO.getPersons().size()).isEqualTo(personsCount);
		assertThat(personsByStationDTO.getAdultCount()).isEqualTo(adultCount);
		assertThat(personsByStationDTO.getChildrenCount()).isEqualTo(childrenCount);
		assert (!personsByStationDTO.getPersons().get(0).getFirstName().isEmpty());
		assert (!personsByStationDTO.getPersons().get(0).getLastName().isEmpty());
	}
	/********************** TEST GET /childAlert?address=? QueryController.findChildrensByAddressDTO **********/
	public static Stream<Arguments> whenAddressIsChoosen_ShouldReturnChildrenProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(ADDRESS_CULVER, 3, 2));
	}

	@DisplayName("GET /childAlert?address=? : ")
	@ParameterizedTest(name = "when Address is {0},  should return {2} adults, and  {3} children")
	@MethodSource("whenAddressIsChoosen_ShouldReturnChildrenProvider")
	public void whenAddressIsChoosen_ShouldReturnChildren(String address, long adultCount, long childrenCount)
			throws Exception {
		// WHEN
		MvcResult result = mockMvc.perform(get("/childAlert?address=" + address)).andReturn();

		ObjectMapper mapper = new ObjectMapper();

		ChildrensByAddressDTO childrensByAddressDTO = mapper.readValue(result.getResponse().getContentAsString(),
				new TypeReference<ChildrensByAddressDTO>() {
				});

		// THEN
		assertThat(childrensByAddressDTO.getAdults().size()).isEqualTo(adultCount);
		assertThat(childrensByAddressDTO.getChildren().size()).isEqualTo(childrenCount);
		assertThat(childrensByAddressDTO.getChildren().get(0).getAge()).isGreaterThan(0);
		assert (!childrensByAddressDTO.getChildren().get(0).getFirstName().isEmpty());
		assert (!childrensByAddressDTO.getChildren().get(0).getLastName().isEmpty());
	}
}
