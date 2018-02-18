package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageUtil {
	private static final String PROPERTIES_PATH = "message.properties";


	public static String getDeleteMessage(String target) throws IOException {
		InputStream in = DAO.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
		Properties properties = new Properties();
		properties.load(in);
		String message = properties.getProperty("delete");
		return message + target;
	}

	public static String getInsertMessage(String target) throws IOException {
		InputStream in = DAO.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
		Properties properties = new Properties();
		properties.load(in);
		String message = properties.getProperty("insert");
		return message + target;
	}

	public static String getUpdateMessage(String target) throws IOException {
		InputStream in = DAO.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
		Properties properties = new Properties();
		properties.load(in);
		String message = properties.getProperty("update");
		return message + target;
	}

}
