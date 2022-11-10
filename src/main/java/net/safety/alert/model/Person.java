package net.safety.alert.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@IdClass(PersonId.class)
public class Person {

	@Id
	@Column(name = "first_name")
	private String firstName;

	@Id
	@Column(name = "last_name")
	private String lastName;

	private String address;
	private String city;
	private String zip;
	private String phone;
	private String email;

	@JsonIgnore
	public PersonId getPersonId() {
		return new PersonId(this.firstName, this.lastName);
	}

	public void patchBy(Person person) {
		if (person != null) {
			// Address
			String address = person.getAddress();
			if (address != null && !address.isEmpty()) {
				this.setAddress(address);
			}
			// City
			String city = person.getCity();
			if (city != null && !city.isEmpty()) {
				this.setCity(city);
			}
			// Email
			String email = person.getEmail();
			if (email != null && !email.isEmpty()) {
				this.setEmail(email);
			}
			// Phone
			String phone = person.getPhone();
			if (phone != null && !phone.isEmpty()) {
				this.setPhone(phone);
			}
			// Zip
			String zip = person.getZip();
			if (zip != null && !zip.isEmpty()) {
				this.setZip(zip);
			}
		}
	}

	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}
}
