package net.safety.alert.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class FirestationId implements Serializable {
	private static final long serialVersionUID = 2L;
	private String address;
	private String station;
}
