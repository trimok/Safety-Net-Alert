package net.safety.alert.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IChildrenByAddress {
	String getFirstName();
	String getLastName();
	default Long getAge() {
		return ChronoUnit.YEARS.between(getBirthdate(), LocalDate.now());
	}
	@JsonIgnore
	LocalDate getBirthdate();

	void setAge(Long age);
}