package net.safety.alert.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MedicalRecordAlreadyCreatedException extends SafetyNetException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	/**
	 * @param operation
	 *            : the functional operation (CREATE_PERSON, etc...)
	 * @param message
	 *            : the error message
	 * @param payload
	 *            : the payload
	 */
	public MedicalRecordAlreadyCreatedException(String operation, String message, Object payload) {
		super(operation, message, payload);
	}
}
