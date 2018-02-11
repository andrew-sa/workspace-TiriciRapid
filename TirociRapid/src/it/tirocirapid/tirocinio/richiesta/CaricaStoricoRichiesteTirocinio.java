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

import org.json.JSONArray;
import org.json.JSONObject;

import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet implementation class CaricaStoricoRichiesteTirocinio
 */
@WebServlet("/storico_richieste")
public class CaricaStoricoRichiesteTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaStoricoRichiesteTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		UserLoggato user = (UserLoggato) request.getSession().getAttribute("user");
		String id = user.getId();
		String tipo = user.getTipo();
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
		if (userTypes.get("RespAz").equals(tipo))
		{
			try
			{
				ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readStoricoRichiesteAzienda(id);
				JSONArray richiesteJSON = new JSONArray();
				for (RichiestaTirocinio reqTir: richieste)
				{
					JSONObject reqTirJSON = new JSONObject();
					reqTirJSON.put("usernameStudente", reqTir.getStudente().getUsername());
					reqTirJSON.put("partitaIVAAzienda", reqTir.getTirocinio().getPartitaIVAAzienda());
					reqTirJSON.put("nomeTirocinio", reqTir.getTirocinio().getNome());
					reqTirJSON.put("stato", reqTir.getStato());
					
					richiesteJSON.put(reqTirJSON);
				}
//				request.setAttribute("richieste", richieste);
				response.getWriter().write(richiesteJSON.toString());
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				response.getWriter().write("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
//				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
//			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/storico_richieste.jsp"); //Alla schermata delle StoricoRichiesteAzienda
//			dispatcher.forward(request, response);
		}
		else if (userTypes.get("Prof").equals(tipo) || userTypes.get("RespAppr").equals(tipo))
		{
			try
			{
				ArrayList<RichiestaTirocinio> richieste = managerRichiestaTirocinio.readStoricoRichiesteTutor(id);
				JSONArray richiesteJSON = new JSONArray();
				for (RichiestaTirocinio reqTir: richieste)
				{
					JSONObject reqTirJSON = new JSONObject();
					reqTirJSON.put("usernameStudente", reqTir.getStudente().getUsername());
					reqTirJSON.put("partitaIVAAzienda", reqTir.getTirocinio().getPartitaIVAAzienda());
					reqTirJSON.put("nomeTirocinio", reqTir.getTirocinio().getNome());
					reqTirJSON.put("stato", reqTir.getStato());
					
					richiesteJSON.put(reqTirJSON);
				}	
//				request.setAttribute("richieste", richieste);
			}
			catch (SQLException | TuplaNotFoundException e)
			{
				e.printStackTrace();
				response.getWriter().write("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
//				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
//			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/storico_richieste.jsp"); //Alla schermata delle StoricoRichiesteProfessore
//			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
