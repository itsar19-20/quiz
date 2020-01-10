package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticationManagerWeb am = new AuthenticationManagerWeb();
		UtenteWeb u = am.login(request.getParameter("username"), request.getParameter("password"));
		if (u == null) {
			request.getRequestDispatcher("/").forward(request, response);
		} else {
			request.getRequestDispatcher("/home.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
