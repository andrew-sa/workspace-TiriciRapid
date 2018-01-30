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
import javax.servlet.http.HttpSession;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di caricare i dati di un'azienda
 */
@WebServlet("/dati_azienda")
public class CaricaDatiAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaDatiAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		Azienda azienda;
		
		if((user != null) && (request.getParameter("partitaIVA")!=null)) //Se l'utente non è un respoonsabile azienda 
		{
			try 
			{
				azienda = managerAzienda.read(request.getParameter("partitaIVA"));
				request.setAttribute("azienda", azienda);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "L'azienda cercata non &egrave; presente nel database");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata dell' azienda singola
			dispatcher.forward(request, response);
		}
		else if(user.getTipo().equals(userTypes.get("RespAz")))    //Se l'utente è un respoonsabile azienda 
		{
			try 
			{
				azienda = managerAzienda.read(user.getId());
				request.setAttribute("azienda", azienda);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "L'azienda cercata non &egrave; presente nel database");
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata del profilo dell' azienda
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
