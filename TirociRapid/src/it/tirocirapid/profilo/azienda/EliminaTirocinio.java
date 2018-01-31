package it.tirocirapid.profilo.azienda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa della eliminazione di un tirocinio da parte di un'azienda
 */
@WebServlet("/elimina_tirocinio")
public class EliminaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserLoggato user = (UserLoggato) request.getSession().getAttribute("user");
		String partitaIVAAzienda = user.getId();
		String nomeTirocinio = request.getParameter("nome");
		HashMap<Integer, String> statesPropTir = (HashMap<Integer, String>) request.getServletContext().getAttribute("statesPropTir");
		HashMap<String, String> statesAzienda = (HashMap<String, String>) getServletContext().getAttribute("statesAzienda");
		final String replacement = "";
		if (!replaceIfMissing(nomeTirocinio, replacement).equals(replacement))
		{
			try
			{
				AbstractManagerFactory factory = new DAOFactory();
				AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
				int count = managerRichiestaTirocinio.countByTirocinio(nomeTirocinio, partitaIVAAzienda);
				AbstractTirocinioManager managerTirocinio = factory.createTirocinioManager();
				if (count == 0)
				{
					
					managerTirocinio.delete(partitaIVAAzienda, nomeTirocinio);
				}
				else
				{
					managerTirocinio.update(partitaIVAAzienda, nomeTirocinio, statesPropTir.get(3));
				}
				request.setAttribute("successo", "Tirocinio " + nomeTirocinio + " eliminato con successo");
				count = managerTirocinio.countByAzienda(partitaIVAAzienda);
				if (count == 0)
				{
					AbstractAziendaManager managerAzienda = factory.createAziendaManager();
					managerAzienda.updateStato(partitaIVAAzienda, statesAzienda.get("NoNDisponibile"));
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (InsertFailedException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
			catch (TuplaNotFoundException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
		}
		else
		{
			request.setAttribute("errore", "Non hai selezionato alcuna proposta di tirocinio");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //ProfiloAzienda
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
