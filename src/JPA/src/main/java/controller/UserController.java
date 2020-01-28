package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.UserManager;
import model.Utente;

/**
 * Servlet implementation class UserManager
 */
@WebServlet("/uc")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cause = request.getParameter("action");
		Utente u = UserManager.getUser(request.getParameter("username"));
		if (cause.contentEquals("search")) {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/JSON");
			response.getWriter().append(om.writeValueAsString(u));
		} else if (cause.contentEquals("delete")) {
			UserManager.removeUser(request.getParameter("username"));
		}
	}

}
