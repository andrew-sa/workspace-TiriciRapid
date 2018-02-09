package it.tirocirapid.tirocinio.proposta;

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

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet implementation class CaricaProposteTirocinio
 */
@WebServlet("/carica_proposte_tirocinio")
public class CaricaProposteTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaProposteTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		HashMap<String, String> userTypes =  (HashMap<String, String>) getServletContext().getAttribute("userTypes");
		
		
		AbstractManagerFactory factory = new DAOFactory();
		AbstractTirocinioManager managerTirocini = factory.createTirocinioManager();
		try 
		{
			ArrayList<Tirocinio> tirocini = managerTirocini.readAllTirociniInAttesaApprovazione();
			request.setAttribute("tirocini", tirocini);	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher
				("/responsabile_approvazioni_richieste_nuovi_tirocini.jsp"); //Alla schermata delle richieste dei nuovi tirocini del responsabile approvazioni
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
