package me;

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
 * Servlet implementation class View
 */
@WebServlet("/Me/view")
public class View extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> msgs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		msgs = new ArrayList<String>();

		String operation = request.getParameter("op");
		if (operation != null) {
			try {
				if (operation.equals("delete")) {
					delete(request);
				} else if (operation.equals("insert")) {
					insert(request);
				} else if (operation.equals("update")) {
					upeate(request);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		AccountDAO dao = new AccountDAO();
		try {
			request.setAttribute("accounts", dao.get());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute("msgs", msgs);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Me/view.jsp");
		dispatcher.forward(request, response);
	}

	private void delete(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		AccountDAO dao = new AccountDAO();
		String[] key = request.getParameterValues("account_key");
		if (key != null) {
			for (int i = 0; i < key.length; i++) {
				AccountDTO account = dao.get(Integer.parseInt(key[i]));
				if (account != null) {
					dao.delete(account);
					msgs.add(MessageUtil.getDeleteMessage(account.getTitle()));
				}
			}
		}
	}

	private void insert(HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
		AccountDAO dao = new AccountDAO();
		dao.insert(
				request.getParameter("account_title"),
				request.getParameter("account_url"),
				request.getParameter("account_id"),
				request.getParameter("account_password"),
				request.getParameter("account_email"),
				request.getParameter("account_memo"));
		msgs.add(MessageUtil.getInsertMessage(request.getParameter("account_title")));
	}

	private void upeate(HttpServletRequest request)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException {
		AccountDAO dao = new AccountDAO();
		if ((request.getParameter("account_key") != null) && (request.getParameter("account_title") != null)) {
			dao.update(new AccountDTO(
					Integer.parseInt(request.getParameter("account_key")),
					request.getParameter("account_title"),
					request.getParameter("account_url"),
					request.getParameter("account_id"),
					request.getParameter("account_password"),
					request.getParameter("account_email"),
					request.getParameter("account_memo")));
			msgs.add(MessageUtil.getUpdateMessage(request.getParameter("account_title")));
		}
	}

}
