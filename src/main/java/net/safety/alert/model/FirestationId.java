package net.safety.alert.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirestationId implements Serializable {
	private static final long serialVersionUID = 2L;
	private String address;
	private String station;
}
