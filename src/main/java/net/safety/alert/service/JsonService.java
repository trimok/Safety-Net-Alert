package net.safety.alert.service;

import org.springframework.stereotype.Service;

@Service
public class JsonService {
	public void initDatabase(Object context) {
		/*
		 * 
		 * ObjectMapper objectMapper = new ObjectMapper(); objectMapper.registerModule(new JavaTimeModule());
		 * InputStream is = null; JsonDTO jsonDTO = null;
		 * 
		 * try { // Reading the json data is = FileUtil.getStreamFromFilename(context,
		 * FilenameConstants.JSON_DATA_FILE); jsonDTO = objectMapper.readValue(is, new TypeReference<JsonDTO>() { });
		 * 
		 * // Database Initialisation Database.getInstance().init(jsonDTO);
		 * 
		 * System.out.println("Loading data OK.");
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */
	}
}
