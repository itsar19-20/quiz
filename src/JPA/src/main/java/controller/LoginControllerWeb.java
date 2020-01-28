package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AuthenticationManagerWeb;
import model.UtenteWeb;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginControllerWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControllerWeb() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticationManagerWeb am = new AuthenticationManagerWeb();
		UtenteWeb u = am.login(request.getParameter("username"), request.getParameter("password"));
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(u));
	}
}
