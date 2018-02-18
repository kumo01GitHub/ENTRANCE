package common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public abstract class DAO {
	private String url;
	private String user;
	private String password;

	private Connection connection;

	protected DAO() throws IOException {
		InputStream in = DAO.class.getClassLoader().getResourceAsStream("database.properties");
		Properties properties = new Properties();
		properties.load(in);
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
	}

	protected void connect() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, password);
	}

	protected void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	protected PreparedStatement prepareStatement(String sql) throws SQLException {
		if (connection != null) {
			return connection.prepareStatement(sql);
		} else {
			return null;
		}
	}

}
