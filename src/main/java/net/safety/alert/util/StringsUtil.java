package net.safety.alert.util;

/**
 * @author trimok
 *
 */
public class StringsUtil {
	/**
	 * @param info
	 *            : the string to be tested
	 * @return : the validity of the string (string not null and string not blank, not empty)
	 */
	public static boolean isValid(String info) {
		return info != null && !info.isBlank();
	}
}
