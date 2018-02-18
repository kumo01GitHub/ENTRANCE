package board;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class PanelDAO extends DAO {

	public PanelDAO() throws IOException {
		super();
	}

	public ArrayList<PanelDTO> get() throws ClassNotFoundException, SQLException {
		ArrayList<PanelDTO> list = new ArrayList<PanelDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, created_day, last_updated, posting, size FROM panel");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new PanelDTO(
					rs.getInt("pkey"),
					rs.getDate("created_day"),
					rs.getDate("last_updated"),
					rs.getString("posting"),
					rs.getString("size").charAt(0)));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public PanelDTO get(int key) throws ClassNotFoundException, SQLException {
		PanelDTO dto = null;

		connect();
		PreparedStatement stmt = prepareStatement(
				"SELECT pkey, created_day, last_updated, posting, size FROM panel WHERE pkey = ?");
		stmt.setInt(1, key);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			dto = new PanelDTO(
					rs.getInt("pkey"),
					rs.getDate("created_day"),
					rs.getDate("last_updated"),
					rs.getString("posting"),
					rs.getString("size").charAt(0));
		}
		rs.close();
		stmt.close();
		close();

		return dto;
	}

	public void delete(PanelDTO dto) throws ClassNotFoundException, SQLException {
		delete(dto.getKey());
	}

	public void delete(int key) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM panel WHERE pkey = ?");
		stmt.setInt(1, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void insert(PanelDTO dto) throws ClassNotFoundException, SQLException {
		insert(dto.getPosting(), dto.getSize());
	}

	public void insert(String posting, char size) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"INSERT INTO panel (created_day, last_updated, posting, size) values (NOW(), NOW(), ?, ?)");
		stmt.setString(1, posting);
		stmt.setString(2, String.valueOf(size));
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void update(PanelDTO dto) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"UPDATE panel SET last_updated = NOW(), posting = ?, size = ? WHERE pkey = ?");
		stmt.setString(1, dto.getPosting());
		stmt.setString(2, String.valueOf(dto.getSize()));
		stmt.setInt(3, dto.getKey());
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void update(int key, String posting, char size) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"UPDATE panel SET last_updated = NOW(), posting = ?, size = ? WHERE pkey = ?");
		stmt.setString(1, posting);
		stmt.setString(2, String.valueOf(size));
		stmt.setInt(3, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

}
