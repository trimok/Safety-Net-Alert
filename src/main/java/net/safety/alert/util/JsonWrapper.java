package net.safety.alert.util;

import java.util.List;

import lombok.Data;
import net.safety.alert.model.Firestation;
import net.safety.alert.model.Medicalrecord;
import net.safety.alert.model.Person;

@Data
public class JsonWrapper {
	private List<Person> persons;
	private List<Firestation> firestations;
	private List<Medicalrecord> medicalrecords;
}
