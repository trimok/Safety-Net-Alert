package net.safety.alert.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author trimok
 * 
 *         Date utilities
 *
 */
public class DateUtil {
	/**
	 * @param birthdate
	 *            : the birthdate
	 * @return : the age
	 */
	public static Long getAge(LocalDate birthdate) {
		if (birthdate == null) {
			return -1L;
		} else {
			return ChronoUnit.YEARS.between(birthdate, LocalDate.now());
		}
	}
}
