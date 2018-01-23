package it.tirocirapid.autenticazione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Collections;
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
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire la registrazione di una nuova azienda
 */
@WebServlet("/registrazione_azienda")
public class RegistrazioneAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneAzienda() {
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
		parametri.add("partitaIVA");
		parametri.add("email");
		parametri.add("numeroTelefono");
		parametri.add("descrizioneAmbito");
		parametri.add("password");
		parametri.add("confPassword");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
//		if (Collections.list(request.getParameterNames()).size() != 8)
//		{
//			request.setAttribute("errore", "Il numero di valori immessi non &egrave; corretto");
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
//			dispatcher.forward(request, response);
//			return;
//		}
		
		String replacement = "";
		Azienda azienda = new Azienda();
		azienda.setStato(((HashMap<String, String>) request.getServletContext().getAttribute("statesAzienda")).get("Disponibile"));
		for (String param: parametri)
		{
			if (replaceIfMissing(request.getParameter(param), replacement).equals(replacement))
			{
				request.setAttribute("errore", "Il campo " + param + " &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
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
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(1))) //sede
			{
				azienda.setSede(request.getParameter(param));
			}
			else if (param.equals(parametri.get(2))) //partitaIVA
			{
				if (validaPartitaIVA(request.getParameter(param)))
				{
					azienda.setPartitaIVA(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere 11 cifre");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(3))) //email
			{
				if (validaEmail(request.getParameter(param)))
				{
					azienda.setEmail(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non &egrave; nel formato corretto (x@x.x)");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(4))) //numeroTelefono
			{
				if (validaNumeroTelefono(request.getParameter(param)))
				{
					azienda.setNumeroTelefono("+39" + request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere 10 cifre");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(5))) //descrizioneAmbito
			{
				if (validaDescrizioneAmbito(request.getParameter(param)))
				{
					azienda.setDescrizioneAmbito(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 500 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(6))) //password
			{
				if (validaFormatoPassword(request.getParameter(param)))
				{
					azienda.setPassword(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere dai 6 ai 20 caratteri alfanumerici");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(7))) //confPassword
			{
				if (!validaConfermaPassword(request.getParameter(param), request.getParameter(parametri.get(6))))
				{
					request.setAttribute("errore", "Le password non corrispondono");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else //Non si verifica mai
			{
				request.setAttribute("errore", "Si &egrave; verificato un errore, si prega di riprovare");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
				dispatcher.forward(request, response);
				return;
			}
		} //fine ciclo for
		
//		try
//		{
//			salvaAzienda(azienda);
		request.setAttribute("successo", "Inserisici una proposta di tirocinio per salvare l'azienda");
//			request.setAttribute("partitaIVA", azienda.getPartitaIVA());
//			request.setAttribute("registrazione", true);
		request.getSession().setAttribute("azienda", azienda);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //AggiungiTirocinio
		dispatcher.forward(request, response);
//			return;
//		}
//		catch (SQLException e)
//		{
//			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
//			e.printStackTrace();
//		}
//		catch (InsertFailedException e)
//		{
//			request.setAttribute("errore", e.getMessage());
//			e.printStackTrace();
//		}
//		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //RegistrazioneAzienda
//		dispatcher.forward(request, response);
		
	}

	/**
	 * 
	 * @param nome rappresenta il nome dell'azienda che si sta per registrare
	 * @return true se il nome rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaNome(String nome)
	{
		return (nome.length() <= 50);
	}

	/**
	 * 
	 * @param partitaIVA rappresenta la partita IVA dell'azienda che si sta per registrare 
	 * @return true se la partitaIVA rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaPartitaIVA(String partitaIVA)
	{
		return Pattern.matches("[0-9]{11}", partitaIVA);
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
	 * @param passw rappresenta la password da validare
	 * @return true se passw è formattata in modo corretto
	 * @return false se passw non è formattata in modo corretto
	 */
	private boolean validaFormatoPassword(String password)
	{
		return Pattern.matches("[A-Za-z0-9]{6,20}", password);
	}
	
	/**
	 * 
	 * @param password rappresenta la password 
	 * @param confPassword rappresenta la password da confermare
	 * @return true se password è uguale a confPassword
	 * @return false se password non è uguale a confPassword
	 */
	private boolean validaConfermaPassword(String password, String confPassword)
	{
		return password.equals(confPassword);
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
