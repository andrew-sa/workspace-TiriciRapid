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
import it.tirocirapid.classes.model.Azienda;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		try 
		{
			ArrayList<Azienda> aziende = managerAzienda.readAll();
			request.setAttribute("aziende", aziende);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata della ListaAziende
			dispatcher.forward(request, response);	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			String referer = request.getHeader("Referer");
			response.sendRedirect(referer);
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
