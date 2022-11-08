package net.safety.alert.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@IdClass(MedicalrecordId.class)
public class Medicalrecord {

	@Id
	@Column(name = "first_name")
	private String firstName;

	@Id
	@Column(name = "last_name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "MM/dd/YYYY")
	private Date birthdate;

	@ElementCollection
	@CollectionTable(name = "medication", joinColumns = {
			@JoinColumn(name = "first_name", referencedColumnName = "first_name"),
			@JoinColumn(name = "last_name", referencedColumnName = "last_name")})
	@MapKeyColumn(name = "name", nullable = false)
	@Column(name = "quantity", nullable = false)
	private Map<String, String> medications = new HashMap<>();

	@ElementCollection
	@Column(name = "name", nullable = false)
	@CollectionTable(name = "allergie", joinColumns = {
			@JoinColumn(name = "first_name", referencedColumnName = "first_name"),
			@JoinColumn(name = "last_name", referencedColumnName = "last_name")})
	private List<String> allergies;

	public void setMedications(final List<Medication> medications) {
		for (Medication medication : medications) {
			this.medications.put(medication.getName(), medication.getQuantity());
		}
	}
}
