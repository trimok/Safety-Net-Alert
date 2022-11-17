package net.safety.alert.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
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
import net.safety.alert.dto.MedicalRecordDTO;
import net.safety.alert.model.Person;
import net.safety.alert.tests.util.JsonUtil;

@SpringBootTest(classes = net.safety.alert.config.SafetyNetAlertApplication.class)
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class MedicalRecordTests {
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

	private static final MedicalRecordDTO NEW_MEDICAL_RECORD = new MedicalRecordDTO("Cedric", "Nomedic");
	private static final MedicalRecordDTO UPDATE_MEDICAL_RECORD = new MedicalRecordDTO("Allison", "Boyd");
	private static final MedicalRecordDTO PATCH_MEDICAL_RECORD = new MedicalRecordDTO("Reginold", "Walker");
	private static final MedicalRecordDTO DELETE_MEDICAL_RECORD = new MedicalRecordDTO("Sophia", "Zemicks");

	static {
		NEW_MEDICAL_RECORD.getAllergies().add("xilliathal");
		NEW_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
		NEW_MEDICAL_RECORD.getMedications().add("dodoxadin:30mg");
		NEW_MEDICAL_RECORD.setBirthdate(LocalDate.now().minusYears(20));

		UPDATE_MEDICAL_RECORD.getAllergies().add("xilliathal");
		UPDATE_MEDICAL_RECORD.getAllergies().add("pollen");
		UPDATE_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
		UPDATE_MEDICAL_RECORD.getMedications().add("dodoxadin:30mg");
		UPDATE_MEDICAL_RECORD.setBirthdate(LocalDate.now().minusYears(20));

		PATCH_MEDICAL_RECORD.getAllergies().add("xilliathal");
		PATCH_MEDICAL_RECORD.getMedications().add("hydrapermazol:300mg");
	}

	/********************** TEST POST /medicalRecord MedicalRecordController.createMedicalRecord **********/

	public static Stream<Arguments> whenMedicalRecordIsGiven_ShouldCreateMedicalRecordProvider() {
		// GIVEN
		return Stream.of(Arguments.arguments(NEW_MEDICAL_RECORD));
	}

	@DisplayName("POST /medicalRecord : ")
	@ParameterizedTest(name = "when medical Record is {0}, should create the medical record in the database.")
	@MethodSource("whenMedicalRecordIsGiven_ShouldCreateMedicalRecordProvider")
	public void whenMedicalRecordIsGiven_ShouldCreateMedicalRecord(MedicalRecordDTO medicalRecordDTO) throws Exception {

		Person person = database.getPersonsMap().get(medicalRecordDTO.getPersonId());

		assertThat(person.getAllergies().size()).isEqualTo(0);
		assertThat(person.getMedications().size()).isEqualTo(0);

		// WHEN
		MedicalRecordDTO medicalRecordResultDTO = JsonUtil.dtoFromUrl(objectMapper, false, MockMvcRequestBuilders::post,
				mockMvc, "/medicalRecord", MedicalRecordDTO.class, medicalRecordDTO);

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
		MedicalRecordDTO medicalRecordResultDTO = JsonUtil.dtoFromUrl(objectMapper, false, MockMvcRequestBuilders::put,
				mockMvc, "/medicalRecord", MedicalRecordDTO.class, medicalRecordDTO);

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
		MedicalRecordDTO medicalRecordResultDTO = JsonUtil.dtoFromUrl(objectMapper, false,
				MockMvcRequestBuilders::patch, mockMvc, "/medicalRecord", MedicalRecordDTO.class, medicalRecordDTO);

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
		JsonUtil.dtoFromDeleteUrl(objectMapper, mockMvc, "/medicalRecord", medicalRecordDTO);

		// THEN
		Person personAfterDelete = database.getPersonsMap().get(medicalRecordDTO.getPersonId());
		assertThat(personAfterDelete.getAllergies().size()).isEqualTo(0);
		assertThat(personAfterDelete.getMedications().size()).isEqualTo(0);
		assertNull(personAfterDelete.getBirthdate());
	}

	/**************************************************************************************************************/
}
