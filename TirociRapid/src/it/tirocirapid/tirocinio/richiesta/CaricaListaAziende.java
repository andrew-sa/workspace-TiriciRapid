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
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire il carimento delle aziende
 */
@WebServlet("/lista_aziende")
public class CaricaListaAziende extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaListaAziende() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		HashMap<String, String> userTypes =  (HashMap<String, String>) getServletContext().getAttribute("userTypes");
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		
		try 
		{
			ArrayList<Azienda> aziende = new ArrayList<>();
			if (user.getTipo().equals(userTypes.get("Stud"))) 
			{
				aziende = managerAzienda.readDisponibili();
			}
			else //user.getTipo().equals(userTypes.get("RespAppr"))
			{
				aziende = managerAzienda.readAll();
			}
			
			JSONArray aziendeJSON = new JSONArray();
			for (Azienda azienda: aziende)
			{
				JSONObject aziendaJSON = new JSONObject();
				aziendaJSON.put("partitaIVA", azienda.getPartitaIVA());
				aziendaJSON.put("nome", azienda.getNome());
				aziendaJSON.put("ambito", azienda.getDescrizioneAmbito());
				aziendaJSON.put("sede", azienda.getSede());
				aziendaJSON.put("email", azienda.getEmail());
				aziendaJSON.put("telefono", azienda.getNumeroTelefono());
				aziendaJSON.put("stato", azienda.getStato());
				
				aziendeJSON.put(aziendaJSON);
			}
			response.getWriter().write(aziendeJSON.toString());
//			request.setAttribute("aziende", aziende);	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			response.getWriter().write("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
//			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
		}
		
//		if (user.getTipo().equals(userTypes.get("Stud"))) 
//		{
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente_aziende.jsp"); //Alla schermata delle aziende visualizzate dallo studente
//			dispatcher.forward(request, response);
//		}
//		else 
//		{
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/responsabile_approvazioni_aziende.jsp"); //Alla schermata delle aziende visualizzate dal responsabile approvazioni
//			dispatcher.forward(request, response);
//		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
