package net.safety.alert.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IPersonsByAddress {
	String getLastName();
	String getPhone();
	default Long getAge() {
		return ChronoUnit.YEARS.between(getBirthdate(), LocalDate.now());
	}
	@JsonIgnore
	LocalDate getBirthdate();
	Set<String> getAllergies();
	Map<String, String> getMedications();
}
