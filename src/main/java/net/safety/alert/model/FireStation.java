package net.safety.alert.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "FIRE_STATION")
@IdClass(FireStationId.class)
public class FireStation {
	@Id
	private String address;

	@Id
	private String station;

	@JsonIgnore
	public FireStationId getFireStationId() {
		return new FireStationId(this.address, this.station);
	}

	public boolean isValid() {
		return address != null && !address.isEmpty() && station != null && !station.isEmpty();
	}
}
