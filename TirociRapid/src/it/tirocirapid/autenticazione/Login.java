package it.tirocirapid.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param username username dello studente da caricare
	 * @param password password dello studente da caricare
	 * @return true se lo studente è stato caricato conn seuccesso
	 * @return false se lo studente non è presente nel database
	 */
	private boolean loginStudente(String username, String password)
	{
		return false;
	}
	/**
	 * 
	 * @param partitaIVA PartitaIVA dell'azienda da caricare
	 * @param password Password dell'azienda da caricare
	 * @return true se l'azienda è stata caricata con successo
	 * @return false se l'azieda non è presente nel database
	 */
	private boolean loginResponsabileAzienda(String partitaIVA, String password)
	{
		return false;
	}
	/**
	 * 
	 * @param username username del professore da caricare
	 * @param password password del professore da caricare
	 * @return true se il professore è stato caricato conn successo
	 * @return false se il professore non è presente nel database
	 */
	private boolean loginProfessore(String username, String password)
	{
		return false;
	}

}
