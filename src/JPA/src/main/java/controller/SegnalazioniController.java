package controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.SegnalazioniManager;
import model.Segnalazione;


/**
 * Servlet implementation class SegnalazioniController
 */
@WebServlet("/sc")
public class SegnalazioniController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SegnalazioniController() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SegnalazioniManager sm = new SegnalazioniManager();	
		ObjectMapper om = new ObjectMapper();

		 List<Segnalazione>listSegnalazioni = sm.trovaSegnalazioni();	 	
	  	response.setContentType("application/JSON");
		response.getWriter().append(om.writeValueAsString(listSegnalazioni));
	
	}

}
