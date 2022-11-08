package net.safety.alert.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Data;

@Data
@Entity
@IdClass(FirestationId.class)
public class Firestation {
	@Id
	private String address;

	@Id
	private String station;
}
