package it.tirocirapid.profilo.azienda;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire la modifica dei dati di un'azienda
 */
@WebServlet("/modifica_dati_azienda")
public class ModificaDatiAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaDatiAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		parametri = new ArrayList<>();
		parametri.add("nome");
		parametri.add("sede");
		parametri.add("email");
		parametri.add("numeroTelefono");
		parametri.add("descrizioneAmbito");
		parametri.add("stato");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String pathHome = request.getServletContext().getContextPath() + "/index.jsp";
		response.getWriter().println("<h1>Il servizio richiesto non &egrave; disponibile</h1>");
		response.getWriter().println("<a href=\""+ pathHome + "\"><button>Torna alla Home</button></a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserLoggato user = (UserLoggato) request.getSession().getAttribute("user");
		String partitIVA = user.getId();
		HashMap<String, String> statesAzienda = (HashMap<String, String>) request.getServletContext().getAttribute("statesAzienda");
		final String replacement = "";
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractAziendaManager managerAzienda = factory.createAziendaManager();
			Azienda azienda;
			azienda = managerAzienda.read(partitIVA);
			for (String param: parametri)
			{
				if (replaceIfMissing(request.getParameter(param), replacement).equals(replacement))
				{
					request.setAttribute("errore", "Il campo " + param + " &egrave; obbligatorio");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
					dispatcher.forward(request, response);
					return;
				}
				
				/* Validazione parametro fissato dal ciclo */
				if (param.equals(parametri.get(0))) //nome
				{
					if (validaNome(request.getParameter(param)))
					{
						azienda.setNome(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 50 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(1))) //sede
				{
					azienda.setSede(request.getParameter(param));
				}
				else if (param.equals(parametri.get(2))) //email
				{
					if (validaEmail(request.getParameter(param)))
					{
						azienda.setEmail(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non &egrave; nel formato corretto (x@x.x)");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(3))) //numeroTelefono
				{
					if (validaNumeroTelefono(request.getParameter(param)))
					{
						azienda.setNumeroTelefono("+39" + request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " deve contenere 10 cifre");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(4))) //descrizioneAmbito
				{
					if (validaDescrizioneAmbito(request.getParameter(param)))
					{
						azienda.setDescrizioneAmbito(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 500 caratteri");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
						dispatcher.forward(request, response);
						return;
					}
				}
				else if (param.equals(parametri.get(5))) //stato
				{
					if (statesAzienda.containsValue(request.getParameter(param)))
					{
						azienda.setStato(request.getParameter(param));
					}
					else
					{
						request.setAttribute("errore", "Il valore scelto del campo " + param + " non &egrave; ammesso");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //ProfiloAzienda
						dispatcher.forward(request, response);
						return;
					}
				}
				else //Non si verifica mai
				{
					request.setAttribute("errore", "Si &egrave; verificato un errore, si prega di riprovare");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dati_azienda"); //ProfiloAzienda
					dispatcher.forward(request, response);
					return;
				}
			} //fine ciclo for
			
			managerAzienda.update(azienda);
			request.setAttribute("successo", "Dati modificati con successo");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
		}
		catch (TuplaNotFoundException e)
		{
			e.printStackTrace();
			request.setAttribute("errore", e.getMessage());
		}
		catch (InsertFailedException e)
		{
			e.printStackTrace();
			request.setAttribute("errore", e.getMessage());
		}
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/dati_azienda"); //ProfiloAzienda
		dispatcher.forward(request, response);
	}
	
	/**
	 * 
	 * @param nome rappresenta il nome da validare
	 * @return true se il nome rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaNome(String nome)
	{
		return (nome.length() <= 50);
	}
	
	/**
	 * 
	 * @param email rappresenta l'email da validare
	 * @return true se l'email rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaEmail(String email)
	{
		return Pattern.matches("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+", email);
	}
	
	/**
	 * 
	 * @param numero rappresenta il numero da validare
	 * @return true se il numero rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaNumeroTelefono(String numero)
	{
		//return Pattern.matches("\\+[0-9]{10}", numero);
		return Pattern.matches("[0-9]{10}", numero);
	}
	
	/**
	 * 
	 * @param numero rappresenta il numero da validare
	 * @return true se il numero rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaDescrizioneAmbito(String descrizione)
	{
		return (descrizione.length() <= 500);
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
	
	private static ArrayList<String> parametri;

}
