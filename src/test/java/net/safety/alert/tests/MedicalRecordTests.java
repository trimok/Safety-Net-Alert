package net.safety.alert.tests;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.MEDICAL_RECORD_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_MEDICAL_RECORD_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_MEDICAL_RECORD_OPERATION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.function.Function;
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
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import net.safety.alert.config.Mapper;
import net.safety.alert.database.Database;
import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.exception.ApiError;
import net.safety.alert.model.Person;
import net.safety.alert.tests.util.TestsUtil;

@SpringBootTest(classes = net.safety.alert.config.SafetyNetAlertApplication.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class MedicalRecordTests {
	private static final String URL_MEDICAL_RECORD = "/medicalRecord";

	private static Mapper objectMapper;

	@Autowired
	public void setMapper(Mapper objectMapper) {
		MedicalRecordTests.objectMapper = objectMapper;
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Database database;

	@BeforeAll
	public void razDatabase() {
		database.reset();
	}

	private static final MedicalRecordDTO CREATE_MEDICAL_RECORD = new MedicalRecordDTO("Cedric", "Nomedic");
	static {
		CREATE_MEDICAL_RECORD.getAllergies().add("xilliathal");
		CREATE_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
		CREATE_MEDICAL_RECORD.getMedications().add("dodoxadin:30mg");
		CREATE_MEDICAL_RECORD.setBirthdate(LocalDate.now().minusYears(20));
	}
	private final static MedicalRecordDTO CREATE_MEDICAL_RECORD_NOT_VALID = new MedicalRecordDTO("", "");
	private final static ApiError ERROR_CREATE_MEDICAL_RECORD_NOT_VALID = new ApiError(URL_MEDICAL_RECORD,
			MEDICAL_RECORD_NOT_VALID, CREATE_MEDICAL_RECORD_OPERATION, null);

	private static final MedicalRecordDTO UPDATE_MEDICAL_RECORD = new MedicalRecordDTO("Allison", "Boyd");
	static {
		UPDATE_MEDICAL_RECORD.getAllergies().add("xilliathal");
		UPDATE_MEDICAL_RECORD.getAllergies().add("pollen");
		UPDATE_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
		UPDATE_MEDICAL_RECORD.getMedications().add("dodoxadin:30mg");
		UPDATE_MEDICAL_RECORD.setBirthdate(LocalDate.now().minusYears(20));
	}
	private final static MedicalRecordDTO UPDATE_MEDICAL_RECORD_NOT_VALID = new MedicalRecordDTO("", "");
	private final static ApiError ERROR_UPDATE_MEDICAL_RECORD_NOT_VALID = new ApiError(URL_MEDICAL_RECORD,
			MEDICAL_RECORD_NOT_VALID, UPDATE_MEDICAL_RECORD_OPERATION, null);

	private static final MedicalRecordDTO PATCH_MEDICAL_RECORD = new MedicalRecordDTO("Reginold", "Walker");
	static {
		PATCH_MEDICAL_RECORD.getAllergies().add("xilliathal");
		PATCH_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
	}
	private final static MedicalRecordDTO PATCH_MEDICAL_RECORD_NOT_VALID = new MedicalRecordDTO("", "");
	private final static ApiError ERROR_PATCH_MEDICAL_RECORD_NOT_VALID = new ApiError(URL_MEDICAL_RECORD,
			MEDICAL_RECORD_NOT_VALID, PATCH_MEDICAL_RECORD_OPERATION, null);

	private static final MedicalRecordDTO DELETE_MEDICAL_RECORD = new MedicalRecordDTO("Sophia", "Zemicks");
	private final static MedicalRecordDTO DELETE_MEDICAL_RECORD_NOT_VALID = new MedicalRecordDTO("", "");
	private final static ApiError ERROR_DELETE_MEDICAL_RECORD_NOT_VALID = new ApiError(URL_MEDICAL_RECORD,
			MEDICAL_RECORD_NOT_VALID, DELETE_MEDICAL_RECORD_OPERATION, null);

	/********************** TEST ERROR MEDICAL_RECORD NOT VALID /person **********/

	public static Stream<Arguments> whenNotValidMedicalRecordIsGiven_ShouldRaiseExceptionProvider() {
		// GIVEN
		return Stream.of(
				Arguments.arguments(CREATE_MEDICAL_RECORD_NOT_VALID, ERROR_CREATE_MEDICAL_RECORD_NOT_VALID,
						HttpStatus.BAD_REQUEST, TestsUtil.HTTP_POST, "POST"),
				Arguments.arguments(UPDATE_MEDICAL_RECORD_NOT_VALID, ERROR_UPDATE_MEDICAL_RECORD_NOT_VALID,
						HttpStatus.BAD_REQUEST, TestsUtil.HTTP_PUT, "PUT"),
				Arguments.arguments(PATCH_MEDICAL_RECORD_NOT_VALID, ERROR_PATCH_MEDICAL_RECORD_NOT_VALID,
						HttpStatus.BAD_REQUEST, TestsUtil.HTTP_PATCH, "PATCH"),
				Arguments.arguments(DELETE_MEDICAL_RECORD_NOT_VALID, ERROR_DELETE_MEDICAL_RECORD_NOT_VALID,
						HttpStatus.BAD_REQUEST, TestsUtil.HTTP_DELETE, "DELETE"));
	}

	@DisplayName("ERROR MEDICAL_RECORD NOT VALID, CRUD  /medicalRecord : ")
	@ParameterizedTest(name = "{4} : when medicalRecord {0} is not valid, should raise an exception {1}, with status {2}")
	@MethodSource("whenNotValidMedicalRecordIsGiven_ShouldRaiseExceptionProvider")
	public void whenNotValidMedicalRecordIsGiven_ShouldRaiseException(MedicalRecordDTO personDTO, ApiError apiError,
			HttpStatus status, Function<String, MockHttpServletRequestBuilder> operation, String operationName)
			throws Exception {

		ApiError error = TestsUtil.errorFromUrl(objectMapper, operation, mockMvc, URL_MEDICAL_RECORD, ApiError.class,
				personDTO, status);

		// THEN
		assert (error.equalsMetadata(apiError));
	}

	/********************** TEST POST /medicalRecord MedicalRecordController.createMedicalRecord **********/

	public static Stream<Arguments> whenMedicalRecordIsGiven_ShouldCreateMedicalRecordProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(CREATE_MEDICAL_RECORD));
	}

	@DisplayName("POST /medicalRecord : ")
	@ParameterizedTest(name = "when medical Record is {0}, should create the medical record in the database.")
	@MethodSource("whenMedicalRecordIsGiven_ShouldCreateMedicalRecordProvider")
	public void whenMedicalRecordIsGiven_ShouldCreateMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception {

		Person person = database.getPersonsMap().get(medicalRecordDTO.getPersonId());

		assertThat(person.getAllergies().size()).isEqualTo(0);
		assertThat(person.getMedications().size()).isEqualTo(0);

		// WHEN
		MedicalRecordDTO medicalRecordResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_POST,
				mockMvc, URL_MEDICAL_RECORD, MedicalRecordDTO.class, medicalRecordDTO);

		// THEN
		MedicalRecordDTO medicalRecordDatabase = MedicalRecordDTO
				.toMedicalRecordDTO(database.getPersonsMap().get(medicalRecordDTO.getPersonId()));
		assert (medicalRecordDatabase.equals(medicalRecordDTO));
		assert (medicalRecordResultDTO.equals(medicalRecordDTO));
	}

	/********************** TEST PUT /medicalRecord MedicalRecordController.updateMedicalRecord **********/

	public static Stream<Arguments> whenMedicalRecordIsGiven_ShouldUpdateMedicalRecordProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(UPDATE_MEDICAL_RECORD));
	}

	@DisplayName("PUT /medicalRecord : ")
	@ParameterizedTest(name = "when medical Record is {0}, should update the medical record in the database.")
	@MethodSource("whenMedicalRecordIsGiven_ShouldUpdateMedicalRecordProvider")
	public void whenMedicalRecordIsGiven_ShouldUpdateMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception {

		Person person = database.getPersonsMap().get(medicalRecordDTO.getPersonId());

		assertThat(person.getAllergies().size()).isEqualTo(1);
		assertThat(person.getMedications().size()).isEqualTo(1);

		// WHEN
		MedicalRecordDTO medicalRecordResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_PUT, mockMvc,
				URL_MEDICAL_RECORD, MedicalRecordDTO.class, medicalRecordDTO);

		// THEN
		MedicalRecordDTO medicalRecordDatabase = MedicalRecordDTO
				.toMedicalRecordDTO(database.getPersonsMap().get(medicalRecordDTO.getPersonId()));
		assertThat(medicalRecordDatabase.getAllergies().size()).isEqualTo(2);
		assertThat(medicalRecordDatabase.getMedications().size()).isEqualTo(2);
		assert (medicalRecordDatabase.equals(medicalRecordDTO));
		assert (medicalRecordResultDTO.equals(medicalRecordDTO));
	}

	/********************** TEST PATCH /medicalRecord MedicalRecordController.patchMedicalRecord **********/

	public static Stream<Arguments> whenMedicalRecordIsGiven_ShouldPatchMedicalRecordProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(PATCH_MEDICAL_RECORD));
	}

	@DisplayName("PATCH /medicalRecord : ")
	@ParameterizedTest(name = "when medical Record is {0}, should patch the medical record in the database.")
	@MethodSource("whenMedicalRecordIsGiven_ShouldPatchMedicalRecordProvider")
	public void whenMedicalRecordIsGiven_ShouldPatchMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception {

		Person person = database.getPersonsMap().get(medicalRecordDTO.getPersonId());

		assertThat(person.getAllergies().size()).isEqualTo(1);
		assertThat(person.getMedications().size()).isEqualTo(1);

		// WHEN
		MedicalRecordDTO medicalRecordResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_PATCH,
				mockMvc, URL_MEDICAL_RECORD, MedicalRecordDTO.class, medicalRecordDTO);

		// THEN
		MedicalRecordDTO medicalRecordDatabase = MedicalRecordDTO
				.toMedicalRecordDTO(database.getPersonsMap().get(medicalRecordDTO.getPersonId()));
		assertThat(medicalRecordDatabase.getAllergies().size()).isEqualTo(2);
		assertThat(medicalRecordDatabase.getMedications().size()).isEqualTo(2);
		assert (medicalRecordResultDTO.equals(medicalRecordDatabase));
	}

	/********************** TEST DELETE /medicalRecord MedicalRecordController.deleteMedicalRecord **********/

	public static Stream<Arguments> whenMedicalRecordIsGiven_ShouldDeleteMedicalRecordProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(DELETE_MEDICAL_RECORD));
	}

	@DisplayName("DELETE /medicalRecord : ")
	@ParameterizedTest(name = "when medical Record is {0}, should delete the medical record in the database.")
	@MethodSource("whenMedicalRecordIsGiven_ShouldDeleteMedicalRecordProvider")
	public void whenMedicalRecordIsGiven_ShouldDeleteMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception {

		Person person = database.getPersonsMap().get(medicalRecordDTO.getPersonId());

		assertThat(person.getAllergies().size()).isEqualTo(3);
		assertThat(person.getMedications().size()).isEqualTo(4);
		assertNotNull(person.getBirthdate());

		// WHEN
		TestsUtil.dtoFromDeleteUrl(objectMapper, mockMvc, URL_MEDICAL_RECORD, medicalRecordDTO);

		// THEN
		Person personAfterDelete = database.getPersonsMap().get(medicalRecordDTO.getPersonId());
		assertThat(personAfterDelete.getAllergies().size()).isEqualTo(0);
		assertThat(personAfterDelete.getMedications().size()).isEqualTo(0);
		assertNull(personAfterDelete.getBirthdate());
	}

	/**************************************************************************************************************/
}
