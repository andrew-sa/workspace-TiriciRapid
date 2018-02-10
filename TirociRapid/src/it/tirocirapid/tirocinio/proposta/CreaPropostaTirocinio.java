package it.tirocirapid.tirocinio.proposta;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractResponsabileApprovazioniManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire l'inseririmento una nuova proposta di tirocinio da parte di un'azienda
 */
@WebServlet("/crea_proposta_tirocinio")
public class CreaPropostaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaPropostaTirocinio() {
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
		parametri.add("descrizione");
		parametri.add("offertaFormativa");
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
		Azienda azienda = null;
		UserLoggato user = null;
		if ("registrazione".equals(request.getParameter("action")))
		{
			if (request.getSession().getAttribute("azienda") != null)
			{
				azienda = (Azienda) request.getSession().getAttribute("azienda");
			}
			else
			{
				request.setAttribute("errore", "Devi essere registrato come Azienda per insierire una offerta di tirocinio");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //RegistrazioneAzienda
				dispatcher.forward(request, response);
				return;
			}
		}
		else
		{
			if (request.getSession().getAttribute("user") != null)
			{
				user = (UserLoggato) request.getSession().getAttribute("user");
			}
			else
			{
				request.setAttribute("errore", "Devi essere registrato come Azienda per insierire una offerta di tirocinio");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_iscrizione.jsp"); //RegistrazioneAzienda
				dispatcher.forward(request, response);
				return;
			}
		}
		
//		String nome = request.getParameter("nome");
//		String descrizione = request.getParameter("descrizione");
//		String offertaFormativa = request.getParameter("offertaFormativa");
		String replacement = "";
		Tirocinio tirocinio = new Tirocinio();
		tirocinio.setStato(((HashMap<Integer, String>) request.getServletContext().getAttribute("statesPropTir")).get(1));
		for (String param : parametri)
		{
			if (replaceIfMissing(request.getParameter(param), replacement).equals(replacement))
			{
				request.setAttribute("errore", "Il campo " + param + " &egrave; obbligatorio");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //RegistrazioneAzienda
				dispatcher.forward(request, response);
				return;
			}
			
			/* Validazione parametro fissato dal ciclo */
			if (param.equals(parametri.get(0))) //nome
			{
				if (validaNome(request.getParameter(param)))
				{
					tirocinio.setNome(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 50 caratteri");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(1))) //descrizione
			{
				if (validaDescrizione(request.getParameter(param)))
				{
					tirocinio.setDescrizione(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 500 caratteri");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
			else if (param.equals(parametri.get(2))) //offertaFormativa
			{
				if (validaOffertaFormativa(request.getParameter(param)))
				{
					tirocinio.setOffertaFormativa(request.getParameter(param));
				}
				else
				{
					request.setAttribute("errore", "Il campo " + param + " non pu&ograve; superare 500 caratteri");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //RegistrazioneAzienda
					dispatcher.forward(request, response);
					return;
				}
			}
		}
		
		if (azienda != null)
		{
			try
			{
				salvaAzienda(azienda);
				tirocinio.setPartitaIVAAzienda(azienda.getPartitaIVA());
				salvaTirocinio(tirocinio);
				request.setAttribute("successo", "Azienda e Proposta di Tirocinio salvati con successo");
				try
				{
					inviaEmailResponsabileApprovazioni(tirocinio);
				}
				catch (MessagingException | SQLException e)
				{
					//do nothing
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp"); //LoginAzienda
				dispatcher.forward(request, response);
				return;
			} 
			catch (SQLException e) 
			{
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				e.printStackTrace();
			} 
			catch (InsertFailedException e) 
			{
				request.setAttribute("errore", e.getMessage());
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //InserisciTircinio
			dispatcher.forward(request, response);
		}
		else if (user != null)
		{
			try
			{
				//salvaAzienda(azienda);
				tirocinio.setPartitaIVAAzienda(user.getId());
				salvaTirocinio(tirocinio);
				request.setAttribute("successo", "Proposta di Tirocinio salvata con successo");
				try
				{
					inviaEmailResponsabileApprovazioni(tirocinio);
				}
				catch (MessagingException | SQLException e)
				{
					//do nothing
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //HomeAzienda
				dispatcher.forward(request, response);
				return;
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			} 
			catch (InsertFailedException e) 
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/azienda_aggiunta_tirocinio.jsp"); //InserisciTircinio
			dispatcher.forward(request, response);
		}
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
	 * @param descrizione rappresenta il nome dell'azienda che si sta per registrare
	 * @return true se il nome rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaDescrizione(String descrizione)
	{
		return (descrizione.length() <= 500);
	}
	
	/**
	 * 
	 * @param offertaFormativa rappresenta il nome dell'azienda che si sta per registrare
	 * @return true se il nome rispecchia il formato
	 * @return false altrimenti
	 */
	private boolean validaOffertaFormativa(String offertaFormativa)
	{
		return (offertaFormativa.length() <= 500);
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
	
	/**
	 * 
	 * @param azienda l'azienda da salvare sul database
	 * @throws SQLException
	 * @throws InsertFailedException se l'inserimento dell'azienda nel database fallisce
	 */
	private void salvaAzienda(Azienda azienda) throws SQLException, InsertFailedException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		managerAzienda.create(azienda);
	}
	
	/**
	 * 
	 * @param azienda il tirocinio da salvare sul database
	 * @throws SQLException
	 * @throws InsertFailedException se l'inserimento del tirocinio nel database fallisce
	 */
	private void salvaTirocinio(Tirocinio tirocinio) throws SQLException, InsertFailedException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractTirocinioManager managerTirocinio = factory.createTirocinioManager();
		managerTirocinio.create(tirocinio);
	}
	
	/**
	 * Questo metodo si occupa di notificare ai Responsabili Approvazioni l'aggiunta di una nuova proposta di tirocinio
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws SQLException 
	 */
	private void inviaEmailResponsabileApprovazioni(Tirocinio tirocinio) throws AddressException, MessagingException, SQLException
	{
		String host = "smtp.gmail.com";
//		String to = emailAzienda;
		String from = "tirocirapid@gmail.com";
		String subject = "Tirocirapid: Nuova Proposta di Tirocinio"; 
		String messageText = "L'Azienda " + tirocinio.getPartitaIVAAzienda() + "richiede una convalida per la Proposta di Tirocinio " + tirocinio.getNome() + "\n";
		final String username = "tirocirapid@gmail.com";
		final String password = "TirociRapid2018";
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		Session sessionMail = Session.getInstance(properties, new javax.mail.Authenticator() {
			
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
			
		  });
		
		AbstractManagerFactory factory = new DAOFactory();
		AbstractResponsabileApprovazioniManager managerTirocinio = factory.createResponsabileApprovazioniManager();
		ArrayList<String> emails = managerTirocinio.readEmailAll();
 
		for (String e: emails)
		{
			String to = e;
			Message message = new MimeMessage(sessionMail);
			sessionMail.setDebug(true);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
	 
			Transport.send(message);
		}
	}
	
	private static ArrayList<String> parametri;

}
