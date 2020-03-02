package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import business.WebUserManager;
import model.UtenteWeb;

/**
 * Servlet implementation class WebUserController
 */
@WebServlet("/webus")
public class WebUserSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebUserSearchController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUserManager wum = new WebUserManager();
		List<UtenteWeb> modList = wum.getUtenteWebList();
		ObjectMapper om = new ObjectMapper();
		response.setContentType("/application/JSON");
		response.getWriter().append(om.writeValueAsString(modList));
	}

}
