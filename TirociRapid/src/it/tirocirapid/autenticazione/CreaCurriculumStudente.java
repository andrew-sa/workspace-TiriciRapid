package it.tirocirapid.autenticazione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.tirocinio.proposta.Servlet;

/**
 * Servlet che si occupa della creazione di un curriculum da parte dello studente
 */
@WebServlet("/inserisci_curriculum")
public class CreaCurriculumStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreaCurriculumStudente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		parametri = new ArrayList<>();
		parametri.add("username");
		parametri.add("fax");
		parametri.add("capacitaCompetenzeRelazionali");
		parametri.add("capacitaCompetenzeTecniche");
		parametri.add("capacitaCompetenzeArtistiche");
		parametri.add("capacitaCompetenzePersonali");
		parametri.add("capacitaCompetenzeOrganizzative");
		parametri.add("altreCapacitaCompetenze");
		parametri.add("esperienzaLavorativa");
		parametri.add("madrelingua");
		parametri.add("altreLingue");
		parametri.add("patenti");
		parametri.add("ulterioriInformazioni");
		
		patenti = new ArrayList<>();
		patenti.add("AM");
		patenti.add("A1");
		patenti.add("A2");
		patenti.add("A");
		patenti.add("B1");
		patenti.add("B");
		patenti.add("C1");
		patenti.add("C");
		patenti.add("D1");
		patenti.add("D");
		patenti.add("BE");
		patenti.add("C1E");
		patenti.add("CE");
		patenti.add("D1E");
		patenti.add("DE");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		final String replacement = "";
		Curriculum curriculum = new Curriculum();
		for (String param: parametri)
		{
			if (!param.equals(parametri.get(0)) && replaceIfMissing(request.getParameter(param), replacement).equals(replacement))
			{
				request.setAttribute("errore", "Il campo " + param + " &egrave; obbligatorio");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
				dispatcher.forward(request, response);
				return;
			}
			
			/* Validazione parametro fissato dal ciclo */
			if (param.equals(parametri.get(1))) //fax
			{
				if (validaFax(request.getParameter(param)))
				{
					curriculum.setFax(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere 11 cifre");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(2))) //capacitaCompetenzeRelazionali
			{
				if (validaCapacitaCompetenzeX(request.getParameter(param)))
				{
					curriculum.setCapacitaCompetenzePersonali(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(3))) //capacitaCompetenzeTecniche
			{
				if (validaCapacitaCompetenzeX(request.getParameter(param)))
				{
					curriculum.setCapacitaCompetenzeTecniche(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(4))) //capacitaCompetenzePersonali
			{
				if (validaCapacitaCompetenzeX(request.getParameter(param)))
				{
					curriculum.setCapacitaCompetenzePersonali(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(5))) //capacitaCompetenzeOrganizzative
			{
				if (validaCapacitaCompetenzeX(request.getParameter(param)))
				{
					curriculum.setCapacitaCompetenzeOrganizzative(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(6))) //altreCapacitaCompetenze
			{
				if (validaCapacitaCompetenzeX(request.getParameter(param)))
				{
					curriculum.setAltreCapacitaCompetenze(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(7))) //esperienzaLavorativa
			{
				if (validaEsperienzaLavorativa(request.getParameter(param)))
				{
					curriculum.setEsperienzaLavorativa(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere dai 6 ai 20 caratteri alfanumerici");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(8))) //madrelingua
			{
				if (validaMadrelingua(request.getParameter(param)))
				{
					curriculum.setMadrelingua(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere dai 6 ai 20 caratteri alfanumerici");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(9))) //altreLingue
			{
				if (validaAltreLingue(request.getParameter(param)))
				{
					curriculum.setAltreLingue(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " deve contenere dai 6 ai 20 caratteri alfanumerici");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(10))) //patenti
			{
				if (validaPatenti(request.getParameter(param).toUpperCase()))
				{
					curriculum.setPatenti(request.getParameter(param.toUpperCase()));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non &egrave; nel formato corretto (Le sigle delle patenti devono essere separate dalla virgola)");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(11))) //ulterioriInformazioni
			{
				if (validaUlterioriInformazioni(request.getParameter(param)))
				{
					curriculum.setUlterioriInformazioni(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 200 caratteri");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
					dispatcher.forward(request, response);
					return;
				}
			}
			else //Non si verifica mai
			{
				request.setAttribute("errore", "Si &egrave; verificato un errore, si prega di riprovare");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //CreaCurriculum
				dispatcher.forward(request, response);
				return;
			}
		} //fine ciclo for
	}

	/**
	 * 
	 * @param fax Il fax da validare
	 * @return true se il fax rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaFax(String fax)
	{
		if (fax.equals("") || fax == null)
		{
			return true;
		}
		else
		{
			return Pattern.matches("[0-9]{11}", fax);
		}
	}
	
	/**
	 * 
	 * @param capacitaCompetenzeX Le cpaicitaCompetenze da validare
	 * @return true se capacitaCompetenzeX rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaCapacitaCompetenzeX(String capacitaCompetenzeX)
	{
		return (capacitaCompetenzeX.length() <= 200);
	}
	
	/**
	 * 
	 * @param esperienzaLavorativa L'esperienzaLavorativa da validare
	 * @return true se esperienzaLavorativa rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaEsperienzaLavorativa(String esperienzaLavorativa)
	{
		return (esperienzaLavorativa.length() <= 200);
	}
	
	private boolean validaPatenti(String patenti)
	{
		String[] p = patenti.split(",");
		for (int i = 0; i < p.length; i++)
		{
			if (!this.patenti.contains(p))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param ulterioriInformazioni Le ulterioriInformazioni da validare
	 * @return true se le ulterioriInformazioni rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaUlterioriInformazioni(String ulterioriInformazioni)
	{
		return (ulterioriInformazioni.length() <= 200);
	}

	/**
	 * 
	 * @param str rappresenta il contenuto dei compi multi valore
	 * @return true se str � formattato in modo corretto
	 * @return false se str non � formattanto in modo corretto
	 */
	private boolean validaFormattazioneValoriMultipli(String str)
	{
		return false;
	}
	
	/**
	 * 
	 * @param orig la stringa da controllare
	 * @param replacement la stringa da restituire nel caso orig fosse vuota o nulla  
	 * @return orig se orig non � ne nulla e ne vuota
	 * @return replacement se orig � nulla o vuota
	 */
	private String replaceIfMissing(String orig, String replacement)
	{
		if (orig == null || orig.trim().equals(""))
			return replacement;
		else
			return orig;
	}
	
	private static ArrayList<String> parametri;
	private static ArrayList<String> patenti;
	
}
