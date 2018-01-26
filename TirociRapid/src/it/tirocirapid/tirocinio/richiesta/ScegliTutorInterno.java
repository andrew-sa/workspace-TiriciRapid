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

import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa della gestione della scelta del tutor interno
 */
@WebServlet("/scegli_tutor")
public class ScegliTutorInterno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScegliTutorInterno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = ((UserLoggato) request.getSession().getAttribute("user")).getId();
		String partitaIVA = request.getParameter("partitaIVAAzienda");
		String nomeTirocinio = request.getParameter("nomeTirocinio");
		String usernameProfessore = request.getParameter("professore");
		final String replacement = "";
		HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
		
		if (replaceIfMissing(partitaIVA, replacement).equals(replacement) || replaceIfMissing(nomeTirocinio, replacement).equals(replacement))
		{
			request.setAttribute("errore", "La richiesta di tirocinio scelta non &egrave; valida");
		}
		else if (replaceIfMissing(usernameProfessore, replacement).equals(replacement))
		{
			request.setAttribute("errore", "Devi scegliere un professore per il ruolo di tutor interno");
		}
		else
		{
			try
			{
				AbstractManagerFactory factory = new DAOFactory();
				AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
				RichiestaTirocinio reqTir = managerRichiestaTirocinio.read(username, partitaIVA, nomeTirocinio);
				reqTir.setStato(states.get(3));
				reqTir.setTutorInterno(new Professore(usernameProfessore));
				managerRichiestaTirocinio.update(reqTir);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException | InsertFailedException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di RichiesteTirocinioStudente
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param orig la stringa da controllare
	 * @param replacement la stringa da restituire nel caso orig fosse vuota o nulla  
	 * @return orig se orig non è ne nulla e ne vuota
	 * @return replacement se orig è nulla o vuota
	 */
	private String replaceIfMissing(String orig, String replacement)
	{
		if (orig == null || orig.trim().equals(""))
			return replacement;
		else
			return orig;
	}

}
