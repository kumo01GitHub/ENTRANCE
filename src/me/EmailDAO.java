package me;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class EmailDAO extends DAO {

	public EmailDAO() throws IOException {
		super();
	}

	public ArrayList<EmailDTO> get() throws ClassNotFoundException, SQLException {
		ArrayList<EmailDTO> list = new ArrayList<EmailDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, name, address, memo FROM email");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new EmailDTO(
					rs.getInt("pkey"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("memo")));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public EmailDTO get(int key) throws ClassNotFoundException, SQLException {
		EmailDTO dto = null;

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, name, address, memo FROM email WHERE pkey = ?");
		stmt.setInt(1, key);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			dto = new EmailDTO(
					rs.getInt("pkey"),
					rs.getString("name"),
					rs.getString("address"),
					rs.getString("memo"));
		}
		rs.close();
		stmt.close();
		close();

		return dto;
	}

	public void insert(String name, String address, String memo) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("INSERT INTO email (name, address, memo) VALUES (?, ?, ?)");
		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, memo);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void insert(EmailDTO dto) throws ClassNotFoundException, SQLException {
		insert(dto.getName(), dto.getAddress(), dto.getMemo());
	}

	public void delete(int key) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM email WHERE pkey = ?");
		stmt.setInt(1, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void delete(EmailDTO dto) throws ClassNotFoundException, SQLException {
		delete(dto.getKey());
	}

	public void update(EmailDTO dto) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("UPDATE email SET name = ?, address = ?, memo = ? WHERE pkey = ?");
		stmt.setString(1, dto.getName());
		stmt.setString(2, dto.getAddress());
		stmt.setString(3, dto.getMemo());
		stmt.setInt(4, dto.getKey());
		stmt.executeUpdate();
		stmt.close();
		close();
	}

}
