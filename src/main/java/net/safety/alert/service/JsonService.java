package net.safety.alert.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import net.safety.alert.constants.FilenameConstants;
import net.safety.alert.database.MemoryDatabase;
import net.safety.alert.dto.JsonDTO;
import net.safety.alert.util.FileUtil;

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

			// Database Initialisation
			MemoryDatabase.getInstance().init(jsonDTO);

			System.out.println("Loading data OK.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
