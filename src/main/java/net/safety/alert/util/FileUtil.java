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
	 *            : the context (the current object)
	 * @param fileName
	 *            : the filename of the ressource
	 * @return : the resource stream
	 */
	public static InputStream getStreamFromFilename(Object context, String fileName) {
		return context.getClass().getClassLoader().getResourceAsStream(fileName);
	}
}
