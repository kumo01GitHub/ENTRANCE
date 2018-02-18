package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Rewrite
 */
@WebServlet("/Board/rewrite")
public class Rewrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			PanelDAO dao = new PanelDAO();
			PanelDTO dto = dao.get(Integer.parseInt(request.getParameter("panel_key")));

			request.setAttribute("panel_key", dto.getKey());
			request.setAttribute("panel_createdDay", dto.getCreatedDay());
			request.setAttribute("panel_lastUpdated", dto.getLastUpdated());
			request.setAttribute("panel_posting", dto.getPosting());
			request.setAttribute("panel_size", dto.getSize());

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Board/rewrite.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
