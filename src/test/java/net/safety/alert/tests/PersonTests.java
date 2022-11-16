package net.safety.alert.tests;

import static org.junit.Assert.assertNull;

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

import net.safety.alert.database.Database;
import net.safety.alert.dto.PersonDTO;
import net.safety.alert.model.Person;
import net.safety.alert.tests.util.JsonUtil;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class PersonTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Database database;

	@BeforeAll
	public void razDatabase() {
		database.raz();
		database.init();
	}

	private final static PersonDTO NEW_PERSON = new PersonDTO("Tristan", "Mokobodzki", "Paris", "75001", "0600000000",
			"tristan@paris.com", "rue de Rivoli");

	private final static PersonDTO UPDATE_PERSON = new PersonDTO("Roger", "Boyd", "Paris", "75001", "0600000000",
			"tristan@paris.com", "rue de Rivoli");

	private final static PersonDTO PATCH_PERSON = new PersonDTO("Jacob", "Boyd", "Paris", "", "", "", "");

	private final static PersonDTO DELETE_PERSON = new PersonDTO("Sophia", "Zemicks", "", "", "", "", "");

	/********************** TEST POST /person PersonController.createPerson **********/

	public static Stream<Arguments> whenPersonIsGiven_ShouldCreatePersonProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(NEW_PERSON));
	}

	@DisplayName("POST /person : ")
	@ParameterizedTest(name = "when person is {0}, should create the person in the database.")
	@MethodSource("whenPersonIsGiven_ShouldCreatePersonProvider")
	public void whenPersonIsGiven_ShouldCreatePerson(PersonDTO personDTO) throws Exception {

		// WHEN
		PersonDTO personResultDTO = JsonUtil.dtoFromUrl(false, MockMvcRequestBuilders::post, mockMvc, "/person",
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
		PersonDTO personResultDTO = JsonUtil.dtoFromUrl(false, MockMvcRequestBuilders::put, mockMvc, "/person",
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
		PersonDTO personResultDTO = JsonUtil.dtoFromUrl(false, MockMvcRequestBuilders::patch, mockMvc, "/person",
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
		JsonUtil.dtoFromDeleteUrl(mockMvc, "/person", personDTO);

		// THEN
		Person personDatabase = database.getPersonsMap().get(personDTO.getPersonId());
		assertNull(personDatabase);
	}
}
