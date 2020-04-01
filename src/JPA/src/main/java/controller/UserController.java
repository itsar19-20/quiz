package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.ChallengeManager;
import business.UserManager;
import model.Challenge;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	UserManager um = new UserManager();
		Utente u = um.getUser(req.getParameter("username"));
		ObjectMapper om = new ObjectMapper();
		resp.setContentType("application/JSON");
		resp.getWriter().append(om.writeValueAsString(u));
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cause = request.getParameter("action");
		UserManager um = new UserManager();
		List<Utente> u = um.userSearch();
		if (cause.contentEquals("search")) {
			ObjectMapper om = new ObjectMapper();
			response.setContentType("application/JSON");
			response.getWriter().append(om.writeValueAsString(u));
		} else if (cause.contentEquals("delete")) {
			Utente user = um.getUser(request.getParameter("username"));
			List<Challenge> ch = user.getChallengers();
			if (ch!=null) {
				ChallengeManager cm = new ChallengeManager();
				for(int x=0; x<ch.size(); x++) {
					cm.challengeRemover(ch.get(x).getTitolo());
				}
			}
			um.removeUser(request.getParameter("username"));
		}
	}

}
