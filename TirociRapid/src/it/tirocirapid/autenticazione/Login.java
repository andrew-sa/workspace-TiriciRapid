package it.tirocirapid.autenticazione;

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
import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire il login degli utenti del sistema 
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		if ("studente".equalsIgnoreCase(request.getParameter("type")))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (replaceIfMissing(username, "").equals(""))
			{
				request.setAttribute("errore", "Il campo username &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Studente
				dispatcher.forward(request, response);
			}
			else if (replaceIfMissing(password, "").equals(""))
			{
				request.setAttribute("errore", "Il campo password &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Studente
				dispatcher.forward(request, response);
			}
			else
			{
				try
				{
					if (loginStudente(username, password))
					{
						UserLoggato user = new UserLoggato();
						user.setId(username);
						user.setTipo(userTypes.get("Stud"));
						session.setAttribute("user", user);
						response.sendRedirect(getServletContext().getContextPath() + "/"); //Alla home Studente
					}
					else
					{
						request.setAttribute("errore", "L'username e la password non corrispondono");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Studente
						dispatcher.forward(request, response);
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "Lo studente non esiste");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Studente
					dispatcher.forward(request, response);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Studente
					dispatcher.forward(request, response);	
				}
			}
		}
		else if ("professore".equalsIgnoreCase(request.getParameter("type")))
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (replaceIfMissing(username, "").equals(""))
			{
				request.setAttribute("errore", "Il campo username &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Professore
				dispatcher.forward(request, response);
			}
			else if (replaceIfMissing(password, "").equals(""))
			{
				request.setAttribute("errore", "Il campo password &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Professore
				dispatcher.forward(request, response);
			}
			else
			{
				try
				{
					if (loginProfessore(username, password) == 1)
					{
						UserLoggato user = new UserLoggato();
						user.setId(username);
						user.setTipo(userTypes.get("Prof"));
						session.setAttribute("user", user);
						response.sendRedirect(getServletContext().getContextPath() + "/"); //Alla home Professore
					}
					else if (loginProfessore(username, password) == 2)
					{
						UserLoggato user = new UserLoggato();
						user.setId(username);
						user.setTipo(userTypes.get("RespAppr"));
						session.setAttribute("user", user);
						response.sendRedirect(getServletContext().getContextPath() + "/"); //Alla home RespnsabileApprovazioni
					}
					else
					{
						request.setAttribute("errore", "L'username e la password non corrispondono");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Professore
						dispatcher.forward(request, response);
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "Il professore non esiste");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Professore
					dispatcher.forward(request, response);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login Professore
					dispatcher.forward(request, response);	
				}
			}

		}
		else if ("azienda".equalsIgnoreCase(request.getParameter("type")))
		{
			String partitaIVA = request.getParameter("partitaIVA");
			String password = request.getParameter("password");
			if (replaceIfMissing(partitaIVA, "").equals(""))
			{
				request.setAttribute("errore", "Il campo partitaIVA &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login ResponsabileAzienda
				dispatcher.forward(request, response);
			}
			else if (replaceIfMissing(password, "").equals(""))
			{
				request.setAttribute("errore", "Il campo password &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login ResponsabileAzienda
				dispatcher.forward(request, response);
			}
			else
			{
				try
				{
					if (loginResponsabileAzienda(partitaIVA, password))
					{
						UserLoggato user = new UserLoggato();
						user.setId(partitaIVA);
						user.setTipo(userTypes.get("RespAz"));
						session.setAttribute("user", user);
						response.sendRedirect(getServletContext().getContextPath() + "/"); //Alla home ResponsabileAzienda
					}
					else
					{
						request.setAttribute("errore", "La partitaIVA e la password non corrispondono");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login ResponsabileAzienda
						dispatcher.forward(request, response);
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "L'azienda non esiste");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login ResponsabileAzienda
					dispatcher.forward(request, response);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di login ResponsabileAzienda
					dispatcher.forward(request, response);	
				}
			}
		}
		else
		{
			//Do nothing
			if (referer != null)
				response.sendRedirect(referer);
			else
				response.sendRedirect(getServletContext().getContextPath());
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
	 * @param username username dello studente da caricare
	 * @param password password dello studente da caricare
	 * @return true se lo studente è stato caricato conn successo
	 * @return false se l'username e la pasword non corrispondono
	 * @throws TuplaNotFoundException se lo studente non è presente nel database
	 * @throws SQLException 
	 */
	private boolean loginStudente(String username, String password) throws SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractStudenteManager managerStudente = factory.createStudenteManager();
		return managerStudente.search(username, password);
	}
	/**
	 * @param partitaIVA PartitaIVA dell'azienda da caricare
	 * @param password Password dell'azienda da caricare
	 * @return true se l'azienda è stata caricata con successo
	 * @return false se la partitaIVA e la pasword non corrispondono
	 * @throws TuplaNotFoundException se l'azienda non è presente nel database
	 * @throws SQLException 
	 */
	private boolean loginResponsabileAzienda(String partitaIVA, String password) throws SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		return managerAzienda.search(partitaIVA, password);
	}
	/**
	 * 
	 * @param username username del professore da caricare
	 * @param password password del professore da caricare
	 * @return 0 se l'username e la pasword non corrispondono
	 * @return 1 se il professore è presente nel database
	 * @return 2 se il professore è presente nel database ed è anche ResponsabileApprovazioni
	 * @throws TuplaNotFoundException se il professore non è presente nel database
	 * @throws SQLException 
	 */
	private int loginProfessore(String username, String password) throws SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractProfessoreManager managerProfessore = factory.createProfessoreManager();
		return managerProfessore.search(username, password);
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
