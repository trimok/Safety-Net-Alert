package net.safety.alert.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.safety.alert.constants.FilenameConstants;
import net.safety.alert.database.MemoryDatabase;
import net.safety.alert.util.FileUtil;
import net.safety.alert.util.JsonDTO;

@Service
public class JsonService {
	public void initDatabase(Object context) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		InputStream is = null;
		JsonDTO jsonDTO = null;

		try {
			// Reading the json data
			is = FileUtil.getStreamFromFilename(context, FilenameConstants.JSON_DATA_FILE);
			jsonDTO = objectMapper.readValue(is, new TypeReference<JsonDTO>() {
			});
			final JsonDTO finalJsonDTO = jsonDTO;

			// Update persons with stations
			jsonDTO.getPersons().forEach(p -> finalJsonDTO.getFirestations().stream()
					.filter(f -> f.getAddress().equals(p.getAddress())).forEach(f -> p.setStation(f.getStation())));

			// Update persons with allergies and medications
			jsonDTO.getPersons()
					.forEach(p -> finalJsonDTO.getMedicalrecords().stream().filter(
							m -> m.getFirstName().equals(p.getFirstName()) && m.getLastName().equals(p.getLastName()))
							.forEach(m -> {
								p.setAllergies(m.getAllergies());
								p.setMedications(m.getMapMedications());
								p.setBirthdate(m.getBirthdate());
							}));
			// Database Initialisation
			MemoryDatabase.getInstance().setPersons(jsonDTO.getPersons());

			System.out.println("Loading data OK.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
