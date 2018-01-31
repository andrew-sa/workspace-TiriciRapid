package it.tirocirapid.profilo.azienda;

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

import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di caricare i tiricini di un'azienda
 */
@WebServlet("/tirocini_azienda")
public class CaricaTirociniAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaTirociniAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
//		String referer = request.getHeader("Referer");
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		final String replacement = "";
		if (user.getTipo().equals(userTypes.get("RespAz")))
		{
			try 
			{
				ArrayList<Tirocinio> tirocini = caricaTirociniAzienda(user.getId());
				request.setAttribute("tirocini", tirocini);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "L'azienda " + user.getId() + " non offre tirocini");
			}
		}
		else if (!replaceIfMissing(request.getParameter("partitaIVA"), replacement).equals(replacement))
		{
			try 
			{
				ArrayList<Tirocinio> tirocini = caricaTirociniAzienda(request.getParameter("partitaIVA"));
				request.setAttribute("tirocini", tirocini);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "Non sono presenti tirocini di questa azienda");
			}
		}
		else
		{
//			response.sendRedirect(referer);
			request.setAttribute("errore", "Non hai selezionato alcuna azienda");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_tirocini_azienda.jsp"); //Schermata listaTirociniAzienda
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private ArrayList<Tirocinio> caricaTirociniAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractTirocinioManager managerTirocinio = factory.createTirocinioManager();
		return managerTirocinio.readAllTirociniAzienda(partitaIVA);
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
