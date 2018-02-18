package board;

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
 * Servlet implementation class Show
 */
@WebServlet("/Board/show")
public class Show extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> msgs;
	private PanelDAO dao;

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
		dao = new PanelDAO();

		try {
			String operation = request.getParameter("op");

			if (operation != null) {
				if (operation.equals("delete")) {
					deletePanel(request);
				} else if (operation.equals("insert")) {
					insertPanel(request);
				} else if (operation.equals("update")) {
					updatePanel(request);
				}
			}

			request.setAttribute("msgs", msgs);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			ShowTable showTable = new ShowTable(dao.get());
			request.setAttribute("showTable", showTable);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Board/show.jsp");
		dispatcher.forward(request, response);
	}

	private void deletePanel(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		String[] keys = request.getParameterValues("panel_key");
		if (keys != null) {
			for (int i = 0; i < keys.length; i++) {
				dao.delete(Integer.parseInt(keys[i]));
				msgs.add(MessageUtil.getDeleteMessage(keys[i]));
			}
		}
	}

	private void insertPanel(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
		String posting = request.getParameter("panel_posting");
		if (posting != null) {
			if (!posting.equals("")) {
				dao.insert(posting, request.getParameter("panel_size").charAt(0));
				msgs.add(MessageUtil.getInsertMessage(""));
			}
		}

	}

	private void updatePanel(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		String key = request.getParameter("panel_key");
		if (key != null) {
			dao.update(Integer.parseInt(key), request.getParameter("panel_posting"),
					request.getParameter("panel_size").charAt(0));
			msgs.add(MessageUtil.getUpdateMessage(key));
		}
	}

}
