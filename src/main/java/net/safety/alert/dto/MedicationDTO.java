package net.safety.alert.dto;

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
public class MedicationDTO {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String quantity;
}
