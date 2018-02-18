package me;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class AccountDAO extends DAO {

	public AccountDAO() throws IOException {
		super();
	}

	public ArrayList<AccountDTO> get() throws ClassNotFoundException, SQLException {
		ArrayList<AccountDTO> list = new ArrayList<AccountDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, title, url, id, password, email, memo FROM account");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new AccountDTO(
					rs.getInt("pkey"),
					rs.getString("title"),
					rs.getString("url"),
					rs.getString("id"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("memo")));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public AccountDTO get(int key) throws ClassNotFoundException, SQLException {
		AccountDTO dto = null;

		connect();
		PreparedStatement stmt = prepareStatement(
				"SELECT pkey, title, url, id, password, email, memo FROM account WHERE pkey = ?");
		stmt.setInt(1, key);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			dto = new AccountDTO(
					rs.getInt("pkey"),
					rs.getString("title"),
					rs.getString("url"),
					rs.getString("id"),
					rs.getString("password"),
					rs.getString("email"),
					rs.getString("memo"));
		}
		rs.close();
		stmt.close();
		close();

		return dto;
	}

	public void insert(String title, String url, String id, String password, String email, String memo)
			throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"INSERT INTO account (title, url, id, password, email, memo) VALUES (?, ?, ?, ?, ?, ?)");
		stmt.setString(1, title);
		stmt.setString(2, url);
		stmt.setString(3, id);
		stmt.setString(4, password);
		stmt.setString(5, email);
		stmt.setString(6, memo);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void insert(AccountDTO dto) throws ClassNotFoundException, SQLException {
		insert(dto.getTitle(), dto.getUrl(), dto.getId(), dto.getPassword(), dto.getEmail(), dto.getMemo());
	}

	public void delete(int key) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM account WHERE pkey = ?");
		stmt.setInt(1, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void delete(AccountDTO dto) throws ClassNotFoundException, SQLException {
		delete(dto.getKey());
	}

	public void update(AccountDTO dto) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"UPDATE account SET title = ?, url = ?, id = ?, password = ?, email = ?, memo = ? WHERE pkey = ?");
		stmt.setString(1, dto.getTitle());
		stmt.setString(2, dto.getUrl());
		stmt.setString(3, dto.getId());
		stmt.setString(4, dto.getPassword());
		stmt.setString(5, dto.getEmail());
		stmt.setString(6, dto.getMemo());
		stmt.setInt(7, dto.getKey());
		stmt.executeUpdate();
		stmt.close();
		close();
	}

}
