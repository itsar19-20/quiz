package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AddPointOnUserProfile;

/**
 * Servlet implementation class PointAddController
 */
@WebServlet("/pac")
public class PointAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PointAddController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AddPointOnUserProfile add = new AddPointOnUserProfile();
		int punteggio = Integer.parseInt(request.getParameter("punteggio"));
		add.addPoint(request.getParameter("username"), punteggio);
	}

}
