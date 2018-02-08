package it.tirocirapid.profilo.studente;

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

import it.tirocirapid.classes.manager.AbstractCurriculumManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire il caricamento dei dati di uno studente
 */
@WebServlet("/profilo_studente")
public class CaricaProfiloStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaricaProfiloStudente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		String referer = request.getHeader("Referer");
		HttpSession session = request.getSession();
		HashMap<String, String> userTypes =  (HashMap<String, String>) getServletContext().getAttribute("userTypes");
		UserLoggato user = (UserLoggato) session.getAttribute("user");
		final String replacement = "";
		if (user.getTipo().equals(userTypes.get("Stud")))
		{
			try 
			{
				Studente studente = caricaDatiStudente(user.getId());
				request.setAttribute("studente", studente);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "Lo studente cercato non &egrave; registrato alla piattaforma");
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/studente_profilo.jsp"); //Alla schermata del profilo dello studente
			dispatcher.forward(request, response);
		}
		else if (!replaceIfMissing(request.getParameter("username"), replacement).equals(replacement))
		{
			String username = request.getParameter("username");
			try 
			{
				Studente studente = caricaDatiStudente(username);
				request.setAttribute("studente", studente);
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "Lo studente cercato non &egrave; registrato alla piattaforma");
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/visualizza_curriculum.jsp"); //Alla schermata del profilo dello studente
			dispatcher.forward(request, response);
		}
		else
		{
//			response.sendRedirect(referer);
			request.setAttribute("errore", "Non hai selezionato alcun studente");
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata del profilo dello studente
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	protected Studente caricaDatiStudente(String username) throws SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractStudenteManager managerStudente = factory.createStudenteManager();
		Studente studente = managerStudente.read(username);
		AbstractCurriculumManager managerCurriculum = factory.createCurriculumManager();
		Curriculum curriculum = managerCurriculum.read(username);
		studente.setCurriculum(curriculum);
		return studente;
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
