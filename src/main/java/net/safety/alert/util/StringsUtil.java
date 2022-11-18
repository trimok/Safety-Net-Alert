package net.safety.alert.util;

/**
 * @author trimok
 *
 */
public class StringsUtil {
	/**
	 * @param info
	 * @return
	 */
	public static boolean isValid(String info) {
		return info != null && !info.isBlank();
	}
}
