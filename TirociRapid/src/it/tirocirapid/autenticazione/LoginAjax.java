package it.tirocirapid.autenticazione;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

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
 * Servlet implementation class LoginAjax
 */
@WebServlet("/login")
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		HashMap<String, String> userTypes = (HashMap<String, String>) request.getServletContext().getAttribute("userTypes");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String tipo = request.getParameter("type");
		final String replacement = "";

		response.setContentType("text/xml");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE result [");
		out.println("<!ENTITY egrave \"è\">");
		out.println("]>");

		out.println("<result>");

		if (replaceIfMissing(tipo, replacement).equals(replacement))
		{
			out.println("<errore>");
			out.println("Il parametro tipo immesso non &egrave; corretto");
			out.println("</errore>");
		}
		else if (replaceIfMissing(id, replacement).equals(replacement))
		{
			out.println("<errore>");
			if ("studente".equalsIgnoreCase(tipo) || "professore".equalsIgnoreCase(tipo))
			{
				out.println("Il campo username &egrave; obbligatorio");
			}
			else if ("azienda".equalsIgnoreCase(tipo))
			{
				out.println("Il campo partitaIVA &egrave; obbligatorio");
			}
			else
			{
				out.println("Il parametro tipo immesso non &egrave; corretto");
			}
			out.println("</errore>");
		}
		else if (replaceIfMissing(password, replacement).equals(replacement))
		{
			out.println("<errore>");
			out.println("Il campo password &egrave; obbligatorio");
			out.println("</errore>");
		}
		else
		{
			if ("studente".equalsIgnoreCase(tipo))
			{
				try
				{
					if (loginStudente(id, password))
					{
						UserLoggato user = new UserLoggato();
						user.setId(id);
						user.setTipo(userTypes.get("Stud"));
						session.setAttribute("user", user);

						out.println("<type>");
						out.println(user.getTipo());
						out.println("</type>");
					}
					else
					{
						out.println("<errore>");
						out.println("L'username e la password non corrispondono");
						out.println("</errore>");
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("Lo studente non esiste");
					out.println("</errore>");
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					out.println("</errore>");
				}
			}
			else if ("professore".equalsIgnoreCase(tipo))
			{
				try
				{
					int risultatoLoginProfessore = loginProfessore(id, password);
					if (risultatoLoginProfessore == 1)
					{
						UserLoggato user = new UserLoggato();
						user.setId(id);
						user.setTipo(userTypes.get("Prof"));
						session.setAttribute("user", user);

						out.println("<type>");
						out.println(user.getTipo());
						out.println("</type>");
					}
					else if (risultatoLoginProfessore == 2)
					{
						UserLoggato user = new UserLoggato();
						user.setId(id);
						user.setTipo(userTypes.get("RespAppr"));
						session.setAttribute("user", user);

						out.println("<type>");
						out.println(user.getTipo());
						out.println("</type>");
					}
					else
					{
						out.println("<errore>");
						out.println("L'username e la password non corrispondono");
						out.println("</errore>");
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("Il professore non esiste");
					out.println("</errore>");
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					out.println("</errore>");
				}
			}
			else if ("azienda".equalsIgnoreCase(tipo))
			{
				try
				{
					if (loginResponsabileAzienda(id, password))
					{
						UserLoggato user = new UserLoggato();
						user.setId(id);
						user.setTipo(userTypes.get("RespAz"));
						session.setAttribute("user", user);

						out.println("<type>");
						out.println(user.getTipo());
						out.println("</type>");
					}
					else
					{
						out.println("<errore>");
						out.println("La partitaIVA e la password non corrispondono");
						out.println("</errore>");
					}
				}
				catch (TuplaNotFoundException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("L'azienda non esiste");
					out.println("</errore>");
				}
				catch (SQLException e)
				{
					e.printStackTrace();
					out.println("<errore>");
					out.println("Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
					out.println("</errore>");
				}
			}
			else
			{
				out.println("<errore>");
				out.println("Il parametro tipo immesso non &egrave; corretto");
				out.println("</errore>");
			}
		}

		out.println("</result>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
