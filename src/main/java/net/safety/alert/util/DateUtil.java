package net.safety.alert.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {
	public static Long getAge(LocalDate birthdate) {
		if (birthdate == null) {
			return 0L;
		} else {
			return ChronoUnit.YEARS.between(birthdate, LocalDate.now());
		}
	}
}
