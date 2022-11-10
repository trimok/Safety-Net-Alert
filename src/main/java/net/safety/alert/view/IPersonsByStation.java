package net.safety.alert.view;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IPersonsByStation {
	public String getFirstName();
	public String getLastName();
	public String getAddress();
	public String getPhone();
	@JsonIgnore
	public LocalDate getBirthdate();
}
