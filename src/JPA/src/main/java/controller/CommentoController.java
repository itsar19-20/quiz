package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import business.CommentoManager;
/**
 * Servlet implementation class CommentoController
 */
@WebServlet("/comSpoiler")
public class CommentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	CommentoManager  cm = new CommentoManager();
	int id = Integer.parseInt(request.getParameter("id"));
	
	cm.ChangeSpoiler(id);
	response.setContentType("application/json");
	response.getWriter().print("il commento è stato cambiato");
	
	
	}

}
