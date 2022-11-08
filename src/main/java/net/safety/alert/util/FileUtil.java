package net.safety.alert.util;

import java.io.InputStream;

import lombok.Getter;

@Getter
public class FileUtil {

	public static InputStream getStreamFromFilename(Object context, String fileName) {
		return context.getClass().getClassLoader().getResourceAsStream(fileName);
	}
}
