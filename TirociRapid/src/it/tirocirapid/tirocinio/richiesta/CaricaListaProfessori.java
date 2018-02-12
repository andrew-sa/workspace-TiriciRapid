package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che gestisce il caricamento della lista dei professori che uno studente può scegliere come tutor interno per la propria richiesta di tirocinio
 */
@WebServlet("/lista_professori")
public class CaricaListaProfessori extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaListaProfessori() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractProfessoreManager managerProfessore = factory.createProfessoreManager();
//		try 
//		{
//			ArrayList<Professore> professori = managerProfessore.readAll();
//			request.setAttribute("professori", professori);	
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
//		}
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente_professori.jsp"); //Alla schermata della ListaProfessori
//		dispatcher.forward(request, response);
		try
		{
			ArrayList<Professore> professori = managerProfessore.readAll();
			JSONArray professoriJSON = new JSONArray();
			for (Professore professore: professori)
			{
				JSONObject professoreJSON = new JSONObject();
				professoreJSON.put("username", professore.getUsername());
				professoreJSON.put("nome", professore.getNome());
				professoreJSON.put("cognome", professore.getCognome());
				professoreJSON.put("ambito", professore.getAmbito());
				professoreJSON.put("emailIstituzionale", professore.getEmailIstituzionale());
				professoreJSON.put("email", professore.getEmail());
				professoreJSON.put("telefono", professore.getTelefono());
				
				professoriJSON.put(professoreJSON);
			}
			response.getWriter().write(professoriJSON.toString());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
