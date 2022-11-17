package net.safety.alert.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import net.safety.alert.config.Mapper;
import net.safety.alert.database.Database;
import net.safety.alert.dto.AddressDTO;
import net.safety.alert.dto.FireStationDTO;
import net.safety.alert.dto.MappingAddressStationDTO;
import net.safety.alert.model.Address;
import net.safety.alert.model.FireStation;
import net.safety.alert.tests.util.JsonUtil;

@SpringBootTest(classes = net.safety.alert.config.SafetyNetAlertApplication.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class AddressStationTests {
	private static Mapper objectMapper;

	@Autowired
	public void setMapper(Mapper objectMapper) {
		AddressStationTests.objectMapper = objectMapper;
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Database database;

	@BeforeAll
	public void razDatabase() {
		database.reset();
	}

	private final static MappingAddressStationDTO CREATE_MAPPING = new MappingAddressStationDTO("947 Rivoli Steet",
			"5");

	private final static MappingAddressStationDTO UPDATE_MAPPING = new MappingAddressStationDTO("489 Manchester St",
			"6");

	private final static FireStationDTO DELETE_MAPPING_BY_FIRESTATION = new FireStationDTO("8");

	private final static List<String> LIST_ADDRESS_DELETE_MAPPING_BY_FIRESTATION = Arrays.asList("959 LoneTree Rd",
			"960 LoneTree Rd");

	private final static AddressDTO DELETE_MAPPING_BY_ADDRESS = new AddressDTO("961 LoneTree Rd");
	private final static FireStationDTO DELETE_MAPPING_BY_ADDRESS_FIRESTATION = new FireStationDTO("9");

	/********************** TEST POST /firestation AddressRecordController.createMappingAddressStation **********/

	public static Stream<Arguments> whenMappingAddressStationIsGiven_ShouldCreateMappingProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(CREATE_MAPPING));
	}

	@DisplayName("POST /firestation : ")
	@ParameterizedTest(name = "when mapping address/station is {0}, should create the mapping.")
	@MethodSource("whenMappingAddressStationIsGiven_ShouldCreateMappingProvider")
	public void whenMappingAddressStationIsGiven_ShouldCreateMapping(MappingAddressStationDTO mappingAddressStationDTO)
			throws Exception {

		Address address = database.getAddressesMap().get(mappingAddressStationDTO.getAddress());
		assertNotNull(address);
		assertNull(address.getFireStation());

		// WHEN
		MappingAddressStationDTO mappingAddressStationDTOResult = JsonUtil.dtoFromUrl(objectMapper, false,
				MockMvcRequestBuilders::post, mockMvc, "/firestation", MappingAddressStationDTO.class,
				mappingAddressStationDTO);

		// THEN
		Address addressAfterMapping = database.getAddressesMap().get(mappingAddressStationDTO.getAddress());
		assertNotNull(addressAfterMapping);
		assertNotNull(addressAfterMapping.getFireStation());
		assert (!addressAfterMapping.getFireStation().getId().isEmpty());
		assert (MappingAddressStationDTO.toMappingAddressStationDTO(addressAfterMapping)
				.equals(mappingAddressStationDTO));
		assert (mappingAddressStationDTOResult.equals(mappingAddressStationDTO));
	}

	/********************** TEST PUT /firestation AddressRecordController.updateMappingAddressStation **********/

	public static Stream<Arguments> whenMappingAddressStationIsGiven_ShouldUpdateMappingProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(UPDATE_MAPPING));
	}

	@DisplayName("PUT /firestation : ")
	@ParameterizedTest(name = "when mapping address/station is {0} , should update the mapping.")
	@MethodSource("whenMappingAddressStationIsGiven_ShouldUpdateMappingProvider")
	public void whenMappingAddressStationIsGiven_ShouldUpdateMapping(MappingAddressStationDTO mappingAddressStationDTO)
			throws Exception {

		Address address = database.getAddressesMap().get(mappingAddressStationDTO.getAddress());
		assertNotNull(address);
		assertNotNull(address.getFireStation());
		FireStation oldFireStation = address.getFireStation();

		// WHEN
		MappingAddressStationDTO mappingAddressStationDTOResult = JsonUtil.dtoFromUrl(objectMapper, false,
				MockMvcRequestBuilders::put, mockMvc, "/firestation", MappingAddressStationDTO.class,
				mappingAddressStationDTO);

		// THEN
		Address addressAfterMapping = database.getAddressesMap().get(mappingAddressStationDTO.getAddress());
		assertNotNull(addressAfterMapping);
		FireStation newFireStation = addressAfterMapping.getFireStation();
		assertNotNull(newFireStation);
		assert (!newFireStation.equals(oldFireStation));
		assert (newFireStation.getId().equals(mappingAddressStationDTO.getStation()));
		assert (mappingAddressStationDTOResult.equals(mappingAddressStationDTO));
	}

	/**
	 * TEST DELETE /firestation/byFireStation AddressRecordController.deleteMappingAddressStationByFireStation
	 **/

	public static Stream<Arguments> whenMappingAddressStationIsGiven_ShouldDeleteMappingByFireStationProvider() {
		// GIVEN
		return Stream
				.of(Arguments.arguments(DELETE_MAPPING_BY_FIRESTATION, LIST_ADDRESS_DELETE_MAPPING_BY_FIRESTATION));
	}

	@DisplayName("DELETE /firestation/byFireStation : ")
	@ParameterizedTest(name = "when station is {0} , should delete the mapping with the associated adresses {1}.")
	@MethodSource("whenMappingAddressStationIsGiven_ShouldDeleteMappingByFireStationProvider")
	public void whenMappingAddressStationIsGiven_ShouldDeleteMappingByFireStation(FireStationDTO fireStationDTO,
			List<String> addresses) throws Exception {

		List<String> addressesOld = database.getAddressesMap().values().stream().filter(
				a -> a.getFireStation() != null && a.getFireStation().getId().equals(fireStationDTO.getFireStation()))
				.map(a -> a.getName()).toList();
		assertThat(addressesOld.size()).isEqualTo(2);

		// WHEN
		JsonUtil.dtoFromDeleteUrl(objectMapper, mockMvc, "/firestation/byFireStation", fireStationDTO);

		// THEN
		List<String> addressesNew = database.getAddressesMap().values().stream().filter(
				a -> a.getFireStation() != null && a.getFireStation().getId().equals(fireStationDTO.getFireStation()))
				.map(a -> a.getName()).toList();

		assertThat(addressesNew.size()).isEqualTo(0);
	}

	/**
	 * TEST DELETE /firestation/byFireStation AddressRecordController.deleteMappingAddressStationByAddress
	 **/

	public static Stream<Arguments> whenMappingAddressStationIsGiven_ShouldDeleteMappingByAddressProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(DELETE_MAPPING_BY_ADDRESS, DELETE_MAPPING_BY_ADDRESS_FIRESTATION));
	}

	@DisplayName("DELETE /firestation/byAddress : ")
	@ParameterizedTest(name = "when address is {0} , should delete the mapping with the firestation {1}.")
	@MethodSource("whenMappingAddressStationIsGiven_ShouldDeleteMappingByAddressProvider")
	public void whenMappingAddressStationIsGiven_ShouldDeleteMappingByAddress(AddressDTO addressDTO,
			FireStationDTO fireStationDTO) throws Exception {

		Address address = database.getAddressesMap().get(addressDTO.getAddress());
		FireStation fireStation = address.getFireStation();
		assertNotNull(fireStation);
		assertFalse(fireStation.getId().isEmpty());

		// WHEN
		JsonUtil.dtoFromDeleteUrl(objectMapper, mockMvc, "/firestation/byAddress", addressDTO);

		// THEN
		FireStation fireStationDatabase = address.getFireStation();
		assertNull(fireStationDatabase);
	}

	/**********************************************************************************************************/
}
