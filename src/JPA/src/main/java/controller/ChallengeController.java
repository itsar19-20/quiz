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
import model.Challenge;

@WebServlet("/cc")
public class ChallengeController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ChallengeController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		ChallengeManager ch = new ChallengeManager();
		ObjectMapper o = new ObjectMapper();
		
		List<Challenge> ListChalleng = ch.findAllChallenger();
		
		response.setContentType("application/JSON");
		response.getWriter().append(o.writeValueAsString(ListChalleng));
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean check = false;
		ChallengeManager ch = new ChallengeManager();
		check = ch.addChallenge(request.getParameter("titolo"), request.getParameter("descrizione"), request.getParameter("categoria"), request.getParameter("creatore"), request.getParameter("flag"));
		ObjectMapper o = new ObjectMapper();
		response.setContentType("application/JSON");
		response.getWriter().append(o.writeValueAsString(check));
	}
	
	
}
