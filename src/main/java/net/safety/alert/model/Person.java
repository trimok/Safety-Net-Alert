package net.safety.alert.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;
import net.safety.alert.util.StringsUtil;

@Data
public class Person {

	private String firstName;
	private String lastName;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private String address;
	private String station;
	private String birthdate;
	private Map<String, String> medications = new HashMap<>();
	private List<String> allergies = new ArrayList<>();

	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}

	public Person updateStation(String station) {
		this.setStation(station);
		return this;
	}

	public Person updatePerson(Person person) {
		this.setCity(person.getCity());
		this.setZip(person.getZip());
		this.setPhone(person.getPhone());
		this.setEmail(person.getEmail());
		this.setAddress(person.getAddress());

		return this;
	}

	public Person patchPerson(Person person) {
		String city = person.getCity();
		if (StringsUtil.isValid(city)) {
			this.setCity(city);
		}
		String zip = person.getZip();
		if (StringsUtil.isValid(zip)) {
			this.setZip(zip);
		}
		String phone = person.getPhone();
		if (StringsUtil.isValid(phone)) {
			this.setPhone(phone);
		}
		String email = person.getEmail();
		if (StringsUtil.isValid(email)) {
			this.setEmail(email);
		}
		String address = person.getAddress();
		if (StringsUtil.isValid(address)) {
			this.setAddress(address);
		}
		return this;
	}
}
