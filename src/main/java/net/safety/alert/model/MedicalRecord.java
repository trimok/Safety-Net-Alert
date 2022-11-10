package net.safety.alert.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "MEDICAL_RECORD")
@IdClass(MedicalRecordId.class)
public class MedicalRecord {

	@Id
	@Column(name = "first_name")
	private String firstName;

	@Id
	@Column(name = "last_name")
	private String lastName;

	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate birthdate;

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
	private Set<String> allergies = new HashSet<>();

	public void setMedications(final List<Medication> medications) {
		for (Medication medication : medications) {
			this.medications.put(medication.getName(), medication.getQuantity());
		}
	}

	@JsonIgnore
	public MedicalRecordId getMedicalRecordId() {
		return new MedicalRecordId(this.firstName, this.lastName);
	}

	public void patchBy(MedicalRecord medicalRecord) {
		if (medicalRecord != null) {

			LocalDate birthdate = medicalRecord.getBirthdate();
			if (birthdate != null) {
				this.setBirthdate(birthdate);
			}
			// Allergies
			Set<String> allergies = medicalRecord.getAllergies();
			if (allergies != null && !allergies.isEmpty()) {
				this.allergies.addAll(allergies);
			}

			// Medication
			Map<String, String> medications = medicalRecord.getMedications();
			if (medications != null && !medications.isEmpty()) {
				this.medications.putAll(medications);
			}
		}
	}

	public boolean isValid() {
		return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty();
	}
}
