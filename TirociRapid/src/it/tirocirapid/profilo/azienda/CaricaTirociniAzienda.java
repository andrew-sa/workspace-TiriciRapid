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

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di caricare i tiricini di un'azienda
 */
@WebServlet("/tiricini_azienda")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		
		AbstractManagerFactory factory = new DAOFactory();
		AbstractTirocinioManager managerTirocinio = factory.createTirocinioManager();
		ArrayList<Tirocinio> tirocini;
		
		if((user != null) && (request.getParameter("partitaIVA")!=null)) //STUDENTE
		{
			try 
			{
				tirocini = managerTirocinio.readAllTirociniAzienda(request.getParameter("partitaIVA"));
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
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata dei tirocini di questa azienda scelta dallo studente
			dispatcher.forward(request, response);
		}
		else if(user.getTipo().equals(userTypes.get("RespAz")))
		{
			try 
			{
				tirocini = managerTirocinio.readAllTirociniAzienda(user.getId());
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
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di tutti i tirocini dell'azienda attualmente loggata
			dispatcher.forward(request, response);
		}
		else
		{
			
			response.sendRedirect(referer);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
