package net.safety.alert.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.safety.alert.util.StringsUtil;

/**
 * @author trimok
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private FireStation fireStation;

	/**
	 * @return true if fireStation is not empty
	 */
	@JsonIgnore
	public boolean isValid() {
		if (fireStation == null || !StringsUtil.isValid(fireStation.getId())) {
			return false;
		}
		if (!StringsUtil.isValid(name)) {
			return false;
		}
		return true;
	}
}
