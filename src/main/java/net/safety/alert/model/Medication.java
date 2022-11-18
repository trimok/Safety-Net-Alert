package net.safety.alert.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Medication {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String quantity;
}
