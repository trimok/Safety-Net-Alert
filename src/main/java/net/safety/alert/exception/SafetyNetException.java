package net.safety.alert.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 *         The base class for all the applicative exception
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SafetyNetException extends RuntimeException {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 7L;
	/**
	 * The functional operation (CREATE_PERSON, etc...)
	 */
	public String operation;
	/**
	 * An error message
	 */
	public String errorMessage;
	/**
	 * The object transfered in the message
	 */
	public Object payload;
}
