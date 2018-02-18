package access;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class BookmarkCategoryDAO extends DAO {

	public BookmarkCategoryDAO() throws IOException {
		super();
	}

	public ArrayList<BookmarkCategoryDTO> get() throws ClassNotFoundException, SQLException {
		ArrayList<BookmarkCategoryDTO> list = new ArrayList<BookmarkCategoryDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, name FROM bookmark_category");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new BookmarkCategoryDTO(
					rs.getInt("pkey"),
					rs.getString("name")));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public BookmarkCategoryDTO get(int key) throws ClassNotFoundException, SQLException {
		BookmarkCategoryDTO dto = null;

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, name FROM bookmark_category WHERE pkey = ?");
		stmt.setInt(1, key);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			dto = new BookmarkCategoryDTO(
					rs.getInt("pkey"), rs.getString("name"));
		}
		rs.close();
		stmt.close();
		close();

		return dto;
	}

	public void insert(BookmarkCategoryDTO dto) throws ClassNotFoundException, SQLException {
		insert(dto.getName());
	}

	public void insert(String name) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("INSERT INTO bookmark_category (name) VALUES (?)");
		stmt.setString(1, name);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void delete(BookmarkCategoryDTO dto) throws ClassNotFoundException, SQLException {
		delete(dto.getKey());
	}

	public void delete(int key) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM bookmark_category WHERE pkey = ?");
		stmt.setInt(1, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void update(BookmarkCategoryDTO dto) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("UPDATE bookmark_category SET name = ? WHERE pkey = ?");
		stmt.setString(1, dto.getName());
		stmt.setInt(2, dto.getKey());
		stmt.executeUpdate();
		stmt.close();
		close();
	}

}
