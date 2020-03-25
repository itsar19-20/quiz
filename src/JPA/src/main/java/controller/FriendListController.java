package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class FriendListController
 */
@WebServlet("/friendlist")
public class FriendListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FriendListController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserManager um = new UserManager();
		Utente u = um.getUser(username);
		AmiciziaManager am = new AmiciziaManager();
		List<Utente> friendlist = am.findAllAmiciza(u);
		System.out.println("PASS");
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(friendlist));
	}

	

}
