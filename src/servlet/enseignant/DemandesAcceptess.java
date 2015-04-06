package servlet.enseignant;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/enseignant/demandesAcceptees")
public class DemandesAcceptess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	getDemandeAcceptees(request);
	    	this.getServletContext().getRequestDispatcher("/WEB-INF/enseignant/demandes_acceptees.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	void getDemandeAcceptees(HttpServletRequest request){
	    
	}

}
