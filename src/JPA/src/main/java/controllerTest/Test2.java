package controllerTest;

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
import business.exeception.BasicException;
import model.Amicizia;
import model.Utente;

/**
 * Servlet implementation class Test2
 */
@WebServlet("/Test2")
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test2() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserManager um = new UserManager();
		Utente u = um.getUser(request.getParameter("username1"));
		Utente u2 = um.getUser(request.getParameter("username2"));
		AmiciziaManager am = new AmiciziaManager();
		try {
			am.aggiungiAmicizia(u, u2);
		} catch (BasicException e) {
			e.printStackTrace();
		}
		List<Amicizia> l = am.findAllAmiciza(u);
		ObjectMapper om = new ObjectMapper();
		response.setContentType("application/json");
		response.getWriter().append(om.writeValueAsString(l));
	}

}
