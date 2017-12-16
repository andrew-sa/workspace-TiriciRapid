package it.tirocirapid.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa di gestire la registrazionwe di una nuova azienda
 */
@WebServlet("/registrazione_azienda")
public class RegistrazioneAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneAzienda() {
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
	 * @param partitaIVA rappresenta la partita IVA dell'azienda che si sta per registrare 
	 * @return true se la partitaIVA rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaPartitaIVA(String partitaIVA)
	{
		return false;
	}
	
	/**
	 * 
	 * @param email rappresenta l'email da validare
	 * @return true se l'email rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaEmail(String email)
	{
		return false;
	}
	
	/**
	 * 
	 * @param numero rappresenta il numero da validare
	 * @return true se il numero rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaNumeroTelefono(String numero)
	{
		return false;
	}
	
	/**
	 * 
	 * @param passw rappresenta la password da validare
	 * @return true se passw è formattata in modo corretto
	 * @return false se passw non è formattata in modo corretto
	 */
	private boolean validaFormatoPassword(String passw)
	{
		return false;
	}
	
	/**
	 * 
	 * @param password rappresenta la password 
	 * @param confPassword rappresenta la password da confermare
	 * @return true se password è uguale a confPassword
	 * @return false se password non è uguale a confPassword
	 */
	private boolean validaConfermaPassword(String password, String confPassword)
	{
		return false;
	}
	
	/**
	 * 
	 * @param str rappresenta il contenuto del campo  
	 * @return true se str è vouta 
	 * @return false se str è piena
	 */
	private boolean isEmptyField(String str)
	{
		return false;
	}
	
}
