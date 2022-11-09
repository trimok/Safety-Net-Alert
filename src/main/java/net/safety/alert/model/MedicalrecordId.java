package net.safety.alert.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalrecordId implements Serializable {
	private static final long serialVersionUID = 3L;
	private String firstName;
	private String lastName;
}
