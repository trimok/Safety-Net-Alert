package net.safety.alert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 *         This class is representing the key of the Person class
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonId {
	/**
	 * 
	 */
	private String firstName;
	/**
	 * 
	 */
	private String lastName;
}
