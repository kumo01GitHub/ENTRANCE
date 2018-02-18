package access;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MessageUtil;

/**
 * Servlet implementation class Marker
 */
@WebServlet("/Access/marker")
public class Marker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> msgs;
	private BookmarkDAO dao;
	private BookmarkCategoryDAO cdao;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		msgs = new ArrayList<String>();
		dao = new BookmarkDAO();
		cdao = new BookmarkCategoryDAO();

		try {
			String operation = request.getParameter("op_category");
			if (operation != null) {
				if (operation.equals("delete")) {
					deleteCategory(request);
				} else if (operation.equals("insert")) {
					insertCategory(request);
				}
			}

			operation = request.getParameter("op_bookmark");
			if (operation != null) {
				if (operation.equals("delete")) {
					deleteBookmark(request);
				} else if (operation.equals("insert")) {
					insertBookmark(request);
				}
			}
			request.setAttribute("msgs", msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ArrayList<BookmarkCategoryDTO> categories = cdao.get();
			request.setAttribute("categories", categories);
			for (int i = 0; i < categories.size(); i++) {
				BookmarkCategoryDTO category = categories.get(i);
				ArrayList<BookmarkDTO> bookmarks = dao.getByCategory(category);
				request.setAttribute("bookmarks" + category.getKey(), bookmarks);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Access/marker.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteCategory(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		String[] key = request.getParameterValues("category_key");
		if (key != null) {
			for (int i = 0; i < key.length; i++) {
				BookmarkCategoryDTO category = cdao.get(Integer.parseInt(key[i]));
				if (category != null) {
					ArrayList<BookmarkDTO> bookmarks = dao.getByCategory(category);
					dao.deleteByCategory(category.getKey());
					cdao.delete(category);
					for (int j = 0; j < bookmarks.size(); j++) {
						msgs.add(MessageUtil.getDeleteMessage(bookmarks.get(j).getTitle()));
					}
					msgs.add(MessageUtil.getDeleteMessage(category.getName()));
				}
			}
		}
	}

	private void insertCategory(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
		String name = request.getParameter("category_name");
		if (name != null) {
			if (!name.equals("")) {
				cdao.insert(name);
				msgs.add(MessageUtil.getInsertMessage(request.getParameter("category_name")));
			}
		}
	}

	private void deleteBookmark(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		String[] key = request.getParameterValues("bookmark_key");
		if (key != null) {
			for (int i = 0; i < key.length; i++) {
				BookmarkDTO bookmark = dao.get(Integer.parseInt(key[i]));
				if (bookmark != null) {
					dao.delete(bookmark);
					msgs.add(MessageUtil.getDeleteMessage(bookmark.getTitle()));
				}
			}
		}

	}

	private void insertBookmark(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		String title = request.getParameter("bookmark_title");
		if (title != null) {
			if (!title.equals("")) {
				dao.insert(request.getParameter("bookmark_title"),
						request.getParameter("bookmark_url"),
						Integer.parseInt(request.getParameter("bookmark_category")));
				msgs.add(MessageUtil.getInsertMessage(title));
			}
		}
	}

}
