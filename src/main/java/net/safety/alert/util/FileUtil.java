package net.safety.alert.util;

import java.io.InputStream;

import lombok.Getter;

/**
 * @author trimok
 *
 */
@Getter
public class FileUtil {

	/**
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static InputStream getStreamFromFilename(Object context, String fileName) {
		return context.getClass().getClassLoader().getResourceAsStream(fileName);
	}
}
