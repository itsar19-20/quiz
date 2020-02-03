package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.WebUserEdit;

/**
 * Servlet implementation class WebUserEditController
 */
@WebServlet("/wuec")
public class WebUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebUserEditController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebUserEdit wue = new WebUserEdit();
		wue.edit(request.getParameter("username"), request.getParameter("password"), request.getParameter("admin"));
	}

}
