package net.safety.alert.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.safety.alert.constants.FilenameConstants;
import net.safety.alert.repository.FirestationRepository;
import net.safety.alert.repository.MedicalrecordRepository;
import net.safety.alert.repository.PersonRepository;
import net.safety.alert.util.FileUtil;
import net.safety.alert.util.JsonWrapper;

@Service
public class JsonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private FirestationRepository firestationRepository;

	@Autowired
	private MedicalrecordRepository medicalrecordRepository;

	public void initDatabase(Object context) {

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		InputStream is = null;

		// Read json data
		try {
			JsonWrapper wrapper = null;
			is = FileUtil.getStreamFromFilename(context, FilenameConstants.JSON_DATA_FILE);
			wrapper = objectMapper.readValue(is, new TypeReference<JsonWrapper>() {
			});

			personRepository.saveAll(wrapper.getPersons());
			firestationRepository.saveAll(wrapper.getFirestations());
			medicalrecordRepository.saveAll(wrapper.getMedicalrecords());

			System.out.println("Persons saved : " + wrapper.getPersons().size());
			System.out.println("Firestation saved : " + wrapper.getFirestations().size());
			System.out.println("Medical records saved : " + wrapper.getMedicalrecords().size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
