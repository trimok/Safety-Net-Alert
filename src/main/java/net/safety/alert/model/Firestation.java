package net.safety.alert.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@IdClass(FirestationId.class)
public class Firestation {
	@Id
	private String address;

	@Id
	private String station;

	@JsonIgnore
	public FirestationId getFireStationId() {
		return new FirestationId(this.address, this.station);
	}
}
