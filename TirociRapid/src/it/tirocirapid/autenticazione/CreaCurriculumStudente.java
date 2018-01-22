package it.tirocirapid.autenticazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet che si occupa della creazione di un curriculum da parte dello studente
 */
@WebServlet("/inserisci_curriculum")
public class CreaCurriculumStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaCurriculumStudente() {
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
		
		
		
	}
	
	/**
	 * 
	 * @param fax Il fax da validare
	 * @return true se il fax rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaFax(String fax)
	{
		return false;
	}

	/**
	 * 
	 * @param str rappresenta il contenuto del campo descrittivo 
	 * @return true se str � vouta 
	 * @return false se str � piena
	 */
	private boolean isEmptyField(String str)
	{
		return false;
	}

	/**
	 * 
	 * @param str rappresenta il contenuto dei compi multi valore
	 * @return true se str � formattato in modo corretto
	 * @return false se str non � formattanto in modo corretto
	 */
	private boolean validaFormattazioneValoriMultipli(String str)
	{
		return false;
	}
	
}
