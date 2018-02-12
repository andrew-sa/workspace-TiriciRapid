package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire l'eliminazione di una richiesta di tirocinio
 */
@WebServlet("/elimina_richiesta_tirocinio")
public class EliminaRichiestaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaRichiestaTirocinio() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = ((UserLoggato) request.getSession().getAttribute("user")).getId();
		String partitaIVA = request.getParameter("partitaIVAAzienda");
		String nomeTirocinio = request.getParameter("nomeTirocinio");
		String stato = request.getParameter("stato");
		nomeTirocinio = nomeTirocinio.replace("+", " ");
//		final String replacement = "";
		HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
		if (!(states.get(-1).equals(stato) || states.get(-4).equals(stato)))
		{
			request.setAttribute("errore", "Impossibile eliminare la richiesta di tirocinio selezionata");
		}
		else
		{
			try
			{
				AbstractManagerFactory factory = new DAOFactory();
				AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			
				managerRichiestaTirocinio.delete(username, partitaIVA, nomeTirocinio);
				request.setAttribute("successo", "La richiesta di tirocinio selezionata &egrave; stata eliminata con successo");
			}
			catch (MySQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "La richiesta di tirocinio scelta non &egrave; valida");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				
			}
			catch (TuplaNotFoundException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/richieste"); //Alla schermata di RichiesteTirocinioStudente
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
//	/**
//	 * 
//	 * @param orig la stringa da controllare
//	 * @param replacement la stringa da restituire nel caso orig fosse vuota o nulla  
//	 * @return orig se orig non è ne nulla e ne vuota
//	 * @return replacement se orig è nulla o vuota
//	 */
//	private String replaceIfMissing(String orig, String replacement)
//	{
//		if (orig == null || orig.trim().equals(""))
//			return replacement;
//		else
//			return orig;
//	}

}
