package access;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.DAO;

public class BookmarkDAO extends DAO {

	public BookmarkDAO() throws IOException {
		super();
	}

	public ArrayList<BookmarkDTO> get() throws ClassNotFoundException, SQLException {
		ArrayList<BookmarkDTO> list = new ArrayList<BookmarkDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, title, url, category FROM bookmark");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new BookmarkDTO(
					rs.getInt("pkey"),
					rs.getString("title"),
					rs.getString("url"),
					rs.getInt("category")));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public BookmarkDTO get(int key) throws ClassNotFoundException, SQLException {
		BookmarkDTO dto = null;

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, title, url, category FROM bookmark WHERE pkey = ?");
		stmt.setInt(1, key);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			dto = new BookmarkDTO(
					rs.getInt("pkey"),
					rs.getString("title"),
					rs.getString("url"),
					rs.getInt("category"));
		}
		rs.close();
		stmt.close();
		close();

		return dto;
	}

	public ArrayList<BookmarkDTO> getByCategory(int category) throws ClassNotFoundException, SQLException {
		ArrayList<BookmarkDTO> list = new ArrayList<BookmarkDTO>();

		connect();
		PreparedStatement stmt = prepareStatement("SELECT pkey, title, url, category FROM bookmark WHERE category = ?");
		stmt.setInt(1, category);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			list.add(new BookmarkDTO(
					rs.getInt("pkey"),
					rs.getString("title"),
					rs.getString("url"),
					rs.getInt("category")));
		}
		rs.close();
		stmt.close();
		close();

		return list;
	}

	public ArrayList<BookmarkDTO> getByCategory(BookmarkCategoryDTO category)
			throws ClassNotFoundException, SQLException {
		return getByCategory(category.getKey());
	}

	public void insert(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
		insert(dto.getTitle(), dto.getUrl(), dto.getCategory());
	}

	public void insert(String title, String url, int category) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("INSERT INTO bookmark (title, url, category) VALUES (?, ?, ?)");
		stmt.setString(1, title);
		stmt.setString(2, url);
		stmt.setInt(3, category);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void delete(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
		delete(dto.getKey());
	}

	public void delete(int key) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM bookmark WHERE pkey = ?");
		stmt.setInt(1, key);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void deleteByCategory(int category) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement("DELETE FROM bookmark WHERE category = ?");
		stmt.setInt(1, category);
		stmt.executeUpdate();
		stmt.close();
		close();
	}

	public void delteByCategory(BookmarkCategoryDTO category) throws ClassNotFoundException, SQLException {
		deleteByCategory(category.getKey());
	}

	public void update(BookmarkDTO dto) throws ClassNotFoundException, SQLException {
		connect();
		PreparedStatement stmt = prepareStatement(
				"UPDATE bookmark SET title = ?, url = ?, category = ? WHERE pkey = ?");
		stmt.setString(1, dto.getTitle());
		stmt.setString(2, dto.getUrl());
		stmt.setInt(3, dto.getCategory());
		stmt.setInt(4, dto.getKey());
		stmt.executeUpdate();
		stmt.close();
		close();
	}
}
