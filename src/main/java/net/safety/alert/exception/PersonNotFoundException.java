package net.safety.alert.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonNotFoundException extends SafetyNetException {
	private static final long serialVersionUID = 5L;
	public PersonNotFoundException(String operation, String message, Object payload) {
		super(operation, message, payload);
	}
}
