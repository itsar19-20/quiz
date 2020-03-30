package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.ChallengeManager;



@WebServlet("/cs")
public class ChallengeSolution extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChallengeSolution() {
        super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChallengeManager ch=new ChallengeManager();
		Boolean sol=ch.challengeSolution(request.getParameter("flag"),request.getParameter("titolo"));
		ObjectMapper o=new ObjectMapper();
		response.setContentType("application/JSON");
		response.getWriter().append(o.writeValueAsString(sol));
		
		
	
	}

}
