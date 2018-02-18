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
 * Servlet implementation class Editor
 */
@WebServlet("/Me/editor")
public class Editor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> msgs;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		msgs = new ArrayList<String>();
		updateEmail(request);
		String operation = request.getParameter("op");
		if (operation != null) {
			if (operation.equals("update")) {
				String param_key = request.getParameter("account_key");
				try {
					int key = Integer.parseInt(param_key);
					AccountDAO dao = new AccountDAO();
					AccountDTO account = dao.get(key);
					request.setAttribute("account_key", Integer.toString(account.getKey()));
					request.setAttribute("account_title", account.getTitle());
					request.setAttribute("account_url", account.getUrl());
					request.setAttribute("account_id", account.getId());
					request.setAttribute("account_password", account.getPassword());
					request.setAttribute("account_email", account.getEmail());
					request.setAttribute("account_memo", account.getMemo());
					request.setAttribute("op", "update");
					request.setAttribute("msgs", msgs);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Me/editor.jsp");
					dispatcher.forward(request, response);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("op", "insert");
				request.setAttribute("account_key", "");
				request.setAttribute("account_title", "");
				request.setAttribute("account_url", "");
				request.setAttribute("account_id", "");
				request.setAttribute("account_password", "");
				request.setAttribute("account_email", "");
				request.setAttribute("account_memo", "");
				request.setAttribute("msgs", msgs);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Me/editor.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("op", "insert");
			request.setAttribute("account_key", "");
			request.setAttribute("account_title", "");
			request.setAttribute("account_url", "");
			request.setAttribute("account_id", "");
			request.setAttribute("account_password", "");
			request.setAttribute("account_email", "");
			request.setAttribute("account_memo", "");
			request.setAttribute("msgs", msgs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Me/editor.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void updateEmail(HttpServletRequest request) throws IOException {
		EmailDAO dao = new EmailDAO();

		String operation = request.getParameter("op_email");
		if (operation != null) {
			if (operation.equals("delete")) {
				String key = request.getParameter("email_key");
				try {
					EmailDTO email = dao.get(Integer.parseInt(key));
					dao.delete(email);
					msgs.add(MessageUtil.getDeleteMessage(email.getName()));
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (operation.equals("insert")) {
				String name = request.getParameter("email_name");
				String address = request.getParameter("email_address");
				String memo = request.getParameter("email_memo");
				if ((name != null) && (address != null) && (memo != null)) {
					try {
						dao.insert(name, address, memo);
						msgs.add(MessageUtil.getInsertMessage(name));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}

		try {
			request.setAttribute("emails", dao.get());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
