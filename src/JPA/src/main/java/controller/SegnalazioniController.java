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
	 * @see HttpServlet#doGets(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		SegnalazioniManager sm = new SegnalazioniManager();	
		ObjectMapper om = new ObjectMapper();
		
		 List<Segnalazione> listSegnalazioni = sm.trovaSegnalazioni();	
		 System.out.println("DIO CANE " + listSegnalazioni);
	  	response.setContentType("application/JSON");
		response.getWriter().append(om.writeValueAsString(listSegnalazioni));
	
	}
  
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 	 */
   
	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {
		SegnalazioniManager sm = new SegnalazioniManager();	
		
		
		String flag =request.getParameter("azione");
		
		if ( flag.equals("nascondi")) {
	   Boolean stato =Boolean.parseBoolean(request.getParameter("stato")); 
	   Integer segnId=Integer.parseInt(request.getParameter("segn"));
       sm.SegnaLavorazione(segnId, stato);};	   
 	
	   if(flag.equals("rspoiler")) {
		    System.out.print("è arrivata una rispsota");
	    	int segn= Integer.parseInt(request.getParameter("segn"));	
			String user = request.getParameter("user");
			Boolean spoiler=Boolean.parseBoolean(request.getParameter("spoiler"));
			//System.out.print("idSpoieler:"+spoiler +" risolutore:"+user+" correggere:"+segn );
			sm.risolviSpoiler(segn, user,spoiler);
	   }
		   
	  if(flag.equals("gconsegna")) {
		 int segnId=Integer.parseInt(request.getParameter("segn"));
		 String risolutore =  request.getParameter("user");
		 sm.consegna(segnId,risolutore);
	  }
	   
	  
	  if (flag.equals("rgenrica")) {
		  int segnId=Integer.parseInt(request.getParameter("segn"));
		  String risolutore =  request.getParameter("user");
		  sm.risolviSegnalazione(segnId, risolutore);
	  }
       
       System.out.println(flag);
       
   }

}
