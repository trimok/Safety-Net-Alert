package net.safety.alert.tests;

import static net.safety.alert.constants.HttpMessageConstants.CREATE_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.DELETE_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.PATCH_PERSON_OPERATION;
import static net.safety.alert.constants.HttpMessageConstants.PERSON_NOT_VALID;
import static net.safety.alert.constants.HttpMessageConstants.UPDATE_PERSON_OPERATION;
import static org.junit.Assert.assertNull;

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
import net.safety.alert.dto.PersonDTO;
import net.safety.alert.exception.ApiInfo;
import net.safety.alert.model.Person;
import net.safety.alert.tests.util.TestsUtil;

@SpringBootTest(classes = net.safety.alert.config.SafetyNetAlertApplication.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class PersonTests {

	private static Mapper objectMapper;

	@Autowired
	public void setMapper(Mapper objectMapper) {
		PersonTests.objectMapper = objectMapper;
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Database database;

	@BeforeAll
	public void razDatabase() {
		database.reset();
	}

	private final static PersonDTO CREATE_PERSON = new PersonDTO("Tristan", "Mokobodzki", "Paris", "75001", "0600000000",
			"tristan@paris.com", "rue de Rivoli");
	private final static PersonDTO CREATE_PERSON_NOT_VALID = new PersonDTO("", "");
	private final static ApiInfo ERROR_CREATE_PERSON_NOT_VALID = new ApiInfo("/person", PERSON_NOT_VALID,
			CREATE_PERSON_OPERATION, null);

	private final static PersonDTO UPDATE_PERSON = new PersonDTO("Roger", "Boyd", "Paris", "75001", "0600000000",
			"tristan@paris.com", "rue de Rivoli");
	private final static PersonDTO UPDATE_PERSON_NOT_VALID = new PersonDTO("", "");
	private final static ApiInfo ERROR_UPDATE_PERSON_NOT_VALID = new ApiInfo("/person", PERSON_NOT_VALID,
			UPDATE_PERSON_OPERATION, null);

	private final static PersonDTO PATCH_PERSON = new PersonDTO("Jacob", "Boyd", "Paris", "", "", "", "");
	private final static PersonDTO PATCH_PERSON_NOT_VALID = new PersonDTO("", "");
	private final static ApiInfo ERROR_PATCH_PERSON_NOT_VALID = new ApiInfo("/person", PERSON_NOT_VALID,
			PATCH_PERSON_OPERATION, null);

	private final static PersonDTO DELETE_PERSON = new PersonDTO("Sophia", "Zemicks", "", "", "", "", "");
	private final static PersonDTO DELETE_PERSON_NOT_VALID = new PersonDTO("", "");
	private final static ApiInfo ERROR_DELETE_PERSON_NOT_VALID = new ApiInfo("/person", PERSON_NOT_VALID,
			DELETE_PERSON_OPERATION, null);

	/********************** TEST ERROR PERSON NOT VALID /person **********/

	public static Stream<Arguments> whenNotValidPersonIsGiven_ShouldRaiseExceptionProvider() {
		// GIVEN
		return Stream.of(
				Arguments.arguments(CREATE_PERSON_NOT_VALID, ERROR_CREATE_PERSON_NOT_VALID, HttpStatus.BAD_REQUEST,
						TestsUtil.HTTP_POST, "POST"),
				Arguments.arguments(UPDATE_PERSON_NOT_VALID, ERROR_UPDATE_PERSON_NOT_VALID, HttpStatus.BAD_REQUEST,
						TestsUtil.HTTP_PUT, "PUT"),
				Arguments.arguments(PATCH_PERSON_NOT_VALID, ERROR_PATCH_PERSON_NOT_VALID, HttpStatus.BAD_REQUEST,
						TestsUtil.HTTP_PATCH, "PATCH"),
				Arguments.arguments(DELETE_PERSON_NOT_VALID, ERROR_DELETE_PERSON_NOT_VALID, HttpStatus.BAD_REQUEST,
						TestsUtil.HTTP_DELETE, "DELETE"));
	}

	@DisplayName("ERROR PERSON NOT VALID, CRUD  /person : ")
	@ParameterizedTest(name = "{4} : when person {0} is not valid, should raise an exception {1}, with status {2}")
	@MethodSource("whenNotValidPersonIsGiven_ShouldRaiseExceptionProvider")
	public void whenNotValidPersonIsGiven_ShouldRaiseException(PersonDTO personDTO, ApiInfo apiInfo, HttpStatus status,
			Function<String, MockHttpServletRequestBuilder> operation, String operationName) throws Exception {

		ApiInfo error = TestsUtil.errorFromUrl(objectMapper, operation, mockMvc, "/person", ApiInfo.class, personDTO,
				status);

		// THEN
		assert (error.equalsMetadata(apiInfo));
	}

	/********************** TEST POST /person PersonController.createPerson **********/

	public static Stream<Arguments> whenPersonIsGiven_ShouldCreatePersonProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(CREATE_PERSON));
	}

	@DisplayName("POST /person : ")
	@ParameterizedTest(name = "when person is {0}, should create the person in the database.")
	@MethodSource("whenPersonIsGiven_ShouldCreatePersonProvider")
	public void whenPersonIsGiven_ShouldCreatePerson(PersonDTO personDTO) throws Exception {

		// WHEN
		PersonDTO personResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_POST, mockMvc, "/person",
				PersonDTO.class, personDTO);

		// THEN
		assert (PersonDTO.toPersonDTO(database.getPersonsMap().get(personDTO.getPersonId())).equals(personDTO));
		assert (personResultDTO.equals(personDTO));
	}

	/********************** TEST PUT /person PersonController.updatePerson **********/

	public static Stream<Arguments> whenPersonIsGiven_ShouldUpdatePersonProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(UPDATE_PERSON));
	}

	@DisplayName("PUT /person : ")
	@ParameterizedTest(name = "when person is {0}, should update the person in the database.")
	@MethodSource("whenPersonIsGiven_ShouldUpdatePersonProvider")
	public void whenPersonIsGiven_ShouldUpdatePerson(PersonDTO personDTO) throws Exception {

		// WHEN
		PersonDTO personResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_PUT, mockMvc, "/person",
				PersonDTO.class, personDTO);

		// THEN
		assert (PersonDTO.toPersonDTO(database.getPersonsMap().get(personDTO.getPersonId())).equals(personDTO));
		assert (personResultDTO.equals(personDTO));
	}

	/********************** TEST PATCH /person PersonController.patchPerson **********/

	public static Stream<Arguments> whenPersonIsGiven_ShouldPatchPersonProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(PATCH_PERSON));
	}

	@DisplayName("PATCH /person : ")
	@ParameterizedTest(name = "when person is {0}, should patch the person in the database.")
	@MethodSource("whenPersonIsGiven_ShouldPatchPersonProvider")
	public void whenPersonIsGiven_ShouldPatchPerson(PersonDTO personDTO) throws Exception {

		PersonDTO personDTOPatch = PersonDTO.toPersonDTO(database.getPersonsMap().get(personDTO.getPersonId()));
		personDTOPatch.setCity(personDTO.getCity());

		// WHEN
		PersonDTO personResultDTO = TestsUtil.dtoFromUrl(objectMapper, false, TestsUtil.HTTP_PATCH, mockMvc, "/person",
				PersonDTO.class, personDTO);

		// THEN
		assert (PersonDTO.toPersonDTO(database.getPersonsMap().get(personDTO.getPersonId())).equals(personDTOPatch));
		assert (personResultDTO.equals(personDTOPatch));
	}

	/********************** TEST DELETE /person PersonController.deletePerson **********/
	public static Stream<Arguments> whenPersonIsGiven_ShouldDeletePersonProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(DELETE_PERSON));
	}

	@DisplayName("DELETE /person : ")
	@ParameterizedTest(name = "when person is {0}, should delete the person in the database.")
	@MethodSource("whenPersonIsGiven_ShouldDeletePersonProvider")
	public void whenPersonIsGiven_ShouldDeletePerson(PersonDTO personDTO) throws Exception {

		// WHEN
		TestsUtil.dtoFromDeleteUrl(objectMapper, mockMvc, "/person", personDTO);

		// THEN
		Person personDatabase = database.getPersonsMap().get(personDTO.getPersonId());
		assertNull(personDatabase);
	}
}
