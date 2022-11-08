package net.safety.alert.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MedicalrecordId implements Serializable {
	private static final long serialVersionUID = 3L;
	private String firstName;
	private String lastName;
}
