package net.safety.alert.util;

import java.util.List;

import lombok.Data;
import net.safety.alert.model.FireStation;
import net.safety.alert.model.MedicalRecord;
import net.safety.alert.model.Person;

@Data
public class JsonWrapper {
	private List<Person> persons;
	private List<FireStation> firestations;
	private List<MedicalRecord> medicalrecords;
}
