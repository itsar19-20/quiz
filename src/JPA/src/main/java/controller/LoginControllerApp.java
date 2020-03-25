package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AuthenticationManagerApp;
import business.UtenteAttivoManager;
import model.Utente;

/**
 * Servlet implementation class LoginControllerApp
 */
@WebServlet("/loginApp")
public class LoginControllerApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginControllerApp() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthenticationManagerApp man = new AuthenticationManagerApp();
		UtenteAttivoManager uam=new UtenteAttivoManager();
		uam.utenteAttivo(request.getParameter("username"));
		System.out.println("verificatocontroller");
		Utente u = man.login(request.getParameter("username"), request.getParameter("password"));
		System.out.println(request.getParameter("username"));
		System.out.println(u);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(u));
	}

}