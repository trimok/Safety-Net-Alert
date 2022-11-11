package net.safety.alert.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class MedicalRecordDTO {
	private String firstName;
	private String lastName;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

	public Map<String, String> getMapMedications() {
		Map<String, String> mapMedications = new HashMap<>();
		medications.forEach(m -> {
			String[] infos = m.split(":");
			mapMedications.put(infos[0], infos[1]);
		});
		return mapMedications;
	}
}
