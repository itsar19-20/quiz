package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.AmiciziaManager;
import business.UserManager;
import model.Utente;

/**
 * Servlet implementation class DeleteFriendController
 */
@WebServlet("/deletefriend")
public class DeleteFriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteFriendController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		AmiciziaManager am = new AmiciziaManager();
		Utente u = um.getUser(request.getParameter("username"));
		Utente u2 = um.getUser(request.getParameter("usernameFriend"));
		Boolean check = false;
		if(u != null && u2 != null) {
			am.cancellaAmicizia(u, u2);
			check = true;
		}
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/JSON");
		response.getWriter().append(om.writeValueAsString(check));
	}

}
