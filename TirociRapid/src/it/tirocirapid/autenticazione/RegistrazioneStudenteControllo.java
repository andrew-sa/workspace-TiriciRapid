package it.tirocirapid.autenticazione;

import java.io.IOException;
import java.sql.SQLException;

import javax.management.InvalidAttributeValueException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractCurriculumManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa della registrazione dello studente
 */
@WebServlet("/registrazione_studente")
public class RegistrazioneStudenteControllo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneStudenteControllo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		final String replacement = "";
		if (replaceIfMissing(username, replacement).equals(replacement))
		{
			request.setAttribute("errore", "Il campo username &egrave obbligatorio");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di registrazioneStudente
			dispatcher.forward(request, response);	
		}
		else if (replaceIfMissing(password, replacement).equals(replacement))
		{
			request.setAttribute("errore", "Il campo password &egrave obbligatorio");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di registrazioneStudente
			dispatcher.forward(request, response);	
		}
		else
		{
			try
			{
				if (isNewStudente(username, password))
				{
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di aggiuntaCurriculum
					dispatcher.forward(request, response);	
				}
				else
				{
					request.setAttribute("errore", "Lo studente " + username + " &egrave; gi&agrave; iscritto");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di registrazioneStudente
					dispatcher.forward(request, response);	
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di registrazioneStudente
				dispatcher.forward(request, response);	
			}
			catch (TuplaNotFoundException | InvalidAttributeValueException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di registrazioneStudente
				dispatcher.forward(request, response);	
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	/**
	 * @param password rappresenta l'password dello studente
	 * @param username rappresenta l'username dello studente 
	 * @return true  
	 * @return false 
	 * @throws TuplaNotFoundException 
	 * @throws SQLException 
	 * @throws InvalidAttributeValueException 
	 */
	private boolean isNewStudente(String username, String password) throws SQLException, TuplaNotFoundException, InvalidAttributeValueException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractStudenteManager managerStudente = factory.createStudenteManager();
		if(managerStudente.search(username, password))
		{
			AbstractCurriculumManager managerCurriculum = factory.createCurriculumManager();
			if (!managerCurriculum.search(username))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			throw new InvalidAttributeValueException("L'username e la password non corrispondono");
		}
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
