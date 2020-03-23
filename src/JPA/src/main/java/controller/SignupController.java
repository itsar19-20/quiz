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
 * Servlet implementation class SignupController
 */
@WebServlet("/signUp")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		String username = request.getParameter("username");
		Utente u = um.getUser(username);
		if(u==null) {
			um.addUser(username, request.getParameter("email"), request.getParameter("password"));
			u = um.getUser(username);
			System.out.println(u);
		} else {
			u=null;
		}
		ObjectMapper om = new ObjectMapper();
		response.setContentType("/application/json");
		response.getWriter().append(om.writeValueAsString(u));
	}

}
