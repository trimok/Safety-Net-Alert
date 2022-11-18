package net.safety.alert.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SafetyNetException extends RuntimeException {
	protected static final long serialVersionUID = 7L;
	/**
	 * 
	 */
	public String operation;
	/**
	 * 
	 */
	public String errorMessage;
	/**
	 * 
	 */
	public Object payload;
}
