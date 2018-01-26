package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.model.Azienda;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AbstractManagerFactory factory = new DAOFactory();
		AbstractProfessoreManager managerProfessore = factory.createProfessoreManager();
		try 
		{
			ArrayList<Professore> professori = managerProfessore.readAll();
			request.setAttribute("professori", professori);	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata della ListaProfessori
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
