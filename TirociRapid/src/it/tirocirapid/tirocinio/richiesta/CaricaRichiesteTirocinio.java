package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet implementation class CaricaRichiesteStudente
 */
@WebServlet("/richieste")
public class CaricaRichiesteTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaRichiesteTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserLoggato user = (UserLoggato) request.getSession().getAttribute("user");
		String id = user.getId();
		String tipo = user.getTipo();
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
		if (userTypes.get("Stud").equals(tipo))
		{
			try
			{
				ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readAllRichiesteStudente(id);
				request.setAttribute("richieste", richieste);
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_richieste.jsp"); //Alla schermata delle RichiesteStudente
			dispatcher.forward(request, response);
		}
		else if (userTypes.get("RespAz").equals(tipo))
		{
			try
			{
				ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readRichiesteAzienda(id);
				request.setAttribute("richieste", richieste);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_richieste.jsp"); //Alla schermata delle RichiesteAzienda
			dispatcher.forward(request, response);
		}
		else if (userTypes.get("Prof").equals(tipo))
		{
			try
			{
				ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readRichiesteTutor(id);
				request.setAttribute("richieste", richieste);
			}
			catch (SQLException | TuplaNotFoundException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/professore.jsp"); //Alla schermata delle RichiesteProfessore
			dispatcher.forward(request, response);
		}
		else if (userTypes.get("RespAppr").equals(tipo))
		{
			String action = request.getParameter("action");
			final String replacement = "";
			if (!replaceIfMissing(action, replacement).equals(replacement))
			{
				if (action.equals("tutor"))
				{
					try
					{
						ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readRichiesteTutor(id);
						request.setAttribute("richieste", richieste);
					}
					catch (SQLException | TuplaNotFoundException e)
					{
						e.printStackTrace();
						request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
						
					}
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/responsabile_approvazioni_richieste_di_tutorato.jsp"); //Alla schermata delle RichiesteTutorRespAppr
					dispatcher.forward(request, response);
				}
				else if (action.equals("all"))
				{
					try
					{
						ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readAllRichiesteInAttesaResponsabileApprovazione();
						request.setAttribute("richieste", richieste);
					}
					catch (SQLException e)
					{
						e.printStackTrace();
						request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
						
					}
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/responsabile_approvazioni_conferma_finale.jsp"); //Alla schermata delle RichiesteTutorRespAppr
					dispatcher.forward(request, response);
				}
				else
				{
					request.setAttribute("errore", "L'azione di caricamento Richieste Tirocinio scelta non &egrave; corretta");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/responsabile_approvazioni_richieste_di_tutorato.jsp"); //Alla schermata delle RichiesteTutorRespAppr
					dispatcher.forward(request, response);
				}
			}
			else
			{
				request.setAttribute("errore", "Non hai scelto l'azione di caricamento delle Richieste Tirocinio");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/responsabile_approvazioni_richieste_di_tutorato.jsp"); //Alla schermata delle RichiesteTutorRespAppr
				dispatcher.forward(request, response);
			}
		}
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
