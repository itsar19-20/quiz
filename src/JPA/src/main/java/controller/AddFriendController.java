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
import business.exeception.BasicException;
import model.Utente;

/**
 * Servlet implementation class AddFriendController
 */
@WebServlet("/addfriend")
public class AddFriendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddFriendController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		Utente u = um.getUser(request.getParameter("username"));
		Utente u2 = um.getUser(request.getParameter("usernameFriend"));
		AmiciziaManager am = new AmiciziaManager();
		Boolean check = false;
		if(u!=null&&u2!=null) {
			try {
				am.aggiungiAmicizia(u, u2);
			} catch (BasicException e) {
				e.printStackTrace();
			}
			check = true;
		}
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/JSON");
		response.getWriter().append(om.writeValueAsString(check));
	}

}
