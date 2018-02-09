package it.tirocirapid.tirocinio.richiesta;



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
import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.manager.AbstractResponsabileApprovazioniManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.InvalidPreviousStateException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet che si occupa di gestire la decisione da parte del Responsabile Approvazioni, Professore (Tutor), Azienda su una richeista di tirocinio da parte di uno studente
 */
@WebServlet("/convalida_richiesta_tirocinio")
public class ConvalidaRichiestaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvalidaRichiestaTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    /**
//	 * @see Servlet#init(ServletConfig)
//	 */
//	public void init(ServletConfig config) throws ServletException
//	{
//		userTypes = (HashMap<String, String>) getServletContext().getAttribute("userTypes");
//		statesReqTir = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
//	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		userTypes = (HashMap<String, String>) getServletContext().getAttribute("userTypes");
		statesReqTir = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
		String tipo = ((UserLoggato) request.getSession().getAttribute("user")).getTipo();
		String action = request.getParameter("action");
		String tipoRichieste = request.getParameter("tipoRichieste");
		String partitaIVA = request.getParameter("partitaIVAAzienda");
		String nomeTirocinio = request.getParameter("nomeTirocinio");
		String usernameStudente = request.getParameter("usernameStudente");
		
		String page = "";
		if (userTypes.get("RespAz").equals(tipo))
		{
			page = "richieste"; // richiesteAzienda
		}
		else if (userTypes.get("Prof").equals(tipo))
		{
			page = "professore.jsp"; // richiesteProfessore
		}
		else if (userTypes.get("RespAppr").equals(tipo))
		{
			page = "responsabile_approvazioni_richieste_di_tutorato.jsp"; // richiesteAzienda
		}
		
		Boolean isAccept = false;
		if ("accetta".equals(action))
		{
			isAccept = true;
		}
		else if ("rifiuta".equals(action))
		{
			isAccept = false;
		}
		else
		{
			request.setAttribute("errore", "Devi selezionare un'azione (Acetta/Rifiuta)");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/" + page);
			dispatcher.forward(request, response);
			return;
		}
		
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			RichiestaTirocinio reqTir = managerRichiestaTirocinio.read(usernameStudente, partitaIVA, nomeTirocinio);
			
			try
			{
				if (userTypes.get("RespAz").equals(tipo))
				{
//					page = ""; // richiesteAzienda
					gestioneAzienda(isAccept, reqTir);
				}
				else if (userTypes.get("Prof").equals(tipo))
				{
//					page = ""; // richiesteProfessore
					gestioneTutor(isAccept, reqTir);
				}
				else if (userTypes.get("RespAppr").equals(tipo))
				{
					if(tipoRichieste.equals("tutor"))
					{
						page = "richieste?action=tutor";
						gestioneTutor(isAccept, reqTir);
					}
					else if(tipoRichieste.equals("all")) 
					{
						page = "richieste?action=all";
						gestioneResponsabileApprovazioni(isAccept, reqTir);
					}
				}
				else
				{
					// do nothing
				}
			}
			catch (InsertFailedException | InvalidPreviousStateException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
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
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/" + page);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	private void gestioneAzienda(boolean isAccept, RichiestaTirocinio reqTir) throws SQLException, InsertFailedException, InvalidPreviousStateException
	{
		if (reqTir.getStato().equals(statesReqTir.get(1))) // stato == "ConfAz"
		{
			String subject = "TirociRapid: Decisione dell'azienda sulla tua richiesta di tirocinio" + reqTir.getStudente().getEmail();
			String messageText = "";
			if (isAccept)
			{
				reqTir.setStato(statesReqTir.get(2)); // stato = "SceltTut"
				subject = "L'azienda ha ACCETTATO la tua richiesta per il tirocinio " + reqTir.getTirocinio().getNome() + "./n" + "Ora puoi scegliere un professore per il ruolo di tutor interno" + "./n";
			}
			else
			{
				reqTir.setStato(statesReqTir.get(-1)); // stato = "RifAz"
				subject = "L'azienda ha RIFIUTATO la tua richiesta per il tirocinio " + reqTir.getTirocinio().getNome() + "./n";
			}
			AbstractManagerFactory factory = new DAOFactory();
			AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			managerRichiestaTirocinio.updateStato(reqTir);
			inviaEmailStudente(reqTir.getStudente().getUsername(), subject, messageText);
		}
		else
		{
			throw new InvalidPreviousStateException("Lo stato attuale della richiesta di tirocinio non consente di effetuare le azione desiderate");
		}
	}
	
	private void gestioneTutor(boolean isAccept, RichiestaTirocinio reqTir) throws SQLException, InsertFailedException, InvalidPreviousStateException
	{
		if (reqTir.getStato().equals(statesReqTir.get(3))) // stato == "ConfTut"
		{
			String subjectResponsabileApprovazioni = "TirociRapid: Nuova richiesta di tirocinio dallo studente " + reqTir.getStudente().getEmail();
			String subjectStudente = "TirociRapid: Aggiornamento stato richiesta tirocinio";
			String messageTextResponsabileApprovazioni = "Lo studente " + reqTir.getStudente().getUsername() + " vorrebbe effettuare il tirocinio " + reqTir.getTirocinio().getNome() + " presso l'azienda " + reqTir.getTirocinio().getPartitaIVAAzienda() + "./n";
			String messageTextStudente = "";
			AbstractManagerFactory factory = new DAOFactory();
			AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			if (isAccept)
			{
				reqTir.setStato(statesReqTir.get(4)); // stato = "ConfResp"
				managerRichiestaTirocinio.updateStato(reqTir);
				messageTextStudente = "Il professore da te scelto come tutor interno per il tirocinio " + reqTir.getTirocinio().getNome() + "ha ACCETTATO." + "/n/n" + "In attesa della CONVALIDA da parte del Responsabile Approvazioni." + "/n";
				inviaEmailResponsabileApprovazioni(subjectResponsabileApprovazioni, messageTextResponsabileApprovazioni);
			}
			else
			{
				reqTir.setStato(statesReqTir.get(-3)); // stato = "RifTut"
				managerRichiestaTirocinio.updateRemoveTutor(reqTir);
				messageTextStudente = "Il professore da te scelto come tutor interno per il tirocinio " + reqTir.getTirocinio().getNome() + "ha RIFIUTATO." + "/n/n" + "SCEGLI un nuovo Professore come Tutor Interno." + "/n";
			}
			inviaEmailStudente(reqTir.getStudente().getEmail(), subjectStudente, messageTextStudente);
		}
		else
		{
			throw new InvalidPreviousStateException("Lo stato attuale della richiesta di tirocinio non consente di effetuare le azione desiderate");
		}
	}
	
	private void gestioneResponsabileApprovazioni(boolean isAccept, RichiestaTirocinio reqTir) throws SQLException, InsertFailedException, InvalidPreviousStateException
	{
		if (reqTir.getStato().equals(statesReqTir.get(4))) // stato == "ConfResp"
		{
			String subject = "TirociRapid: Decisione finale richiesta tirocinio studente " + reqTir.getStudente().getEmail();
			String subjectStudente = "TirociRapid: Decisione finale richiesta tirocinio";
			String messageText = "";
			String messageTextStudente = "";
			if (isAccept)
			{
				reqTir.setStato(statesReqTir.get(5)); // stato = "Acc"
				messageText = "La richiesta effettuata dallo studente " + reqTir.getStudente().getEmail() + " per il tirocinio " + reqTir.getTirocinio().getNome() + " è stata APPROVATA definitivamente." + "\n";
				messageTextStudente = "La richiesta effettuata da te per il tirocinio " + reqTir.getTirocinio().getNome() + " è stata APPROVATA definitivamente." + "\n";
			}
			else
			{
				reqTir.setStato(statesReqTir.get(-4)); // stato = "RifResp"
				messageText = "La richiesta effettuata dallo studente " + reqTir.getStudente().getEmail() + " per il tirocinio " + reqTir.getTirocinio().getNome() + " è stata RIFIUTATA definitivamente." + "\n";
				messageTextStudente = "La richiesta effettuata da te per il tirocinio " + reqTir.getTirocinio().getNome() + " è stata RIFIUTATA definitivamente." + "\n";
			}
			AbstractManagerFactory factory = new DAOFactory();
			AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			managerRichiestaTirocinio.updateStato(reqTir);
			inviaEmailResponsabileAzienda(reqTir.getTirocinio().getPartitaIVAAzienda(), subject, messageText);
			inviaEmailTutorInterno(reqTir.getTutorInterno().getUsername(), subject, messageText);
			inviaEmailStudente(reqTir.getStudente().getUsername(), subjectStudente, messageTextStudente);
		}
		else
		{
			throw new InvalidPreviousStateException("Lo stato attuale della richiesta di tirocinio non consente di effetuare le azione desiderate");
		}
	}
	
	/**
	 * Questo metodo si occupa di notificare il responsabile azienda della scelta finale del responsabile approvazioni
	 */
	private void inviaEmailResponsabileAzienda(String parititaIVAAzienda, String subject, String messageText)
	{
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractAziendaManager managerAzienda = factory.createAziendaManager();
			String to = managerAzienda.readEmail(parititaIVAAzienda);
			
			String host = "smtp.gmail.com";
			String from = "tirocirapid@gmail.com";
//			String subject = "Recupero password per TirocRapid"; 
//			String messageText = "La password per accedere a TirociRapid è:\n" + passwordRecuperata + "\n";
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
	 
			Message message = new MimeMessage(sessionMail);
			sessionMail.setDebug(true);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
	 
			Transport.send(message);
		}
		catch (SQLException | TuplaNotFoundException | MessagingException e)
		{
			e.printStackTrace();
			// do nothing
		}
	}
	
	/**
	 * Questo metodo si occupa di notificare il professore scelto come tutor interno dallo studente
	 */
	private void inviaEmailTutorInterno(String usernameProfessore, String subject, String messageText)
	{
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractProfessoreManager managerProfessore = factory.createProfessoreManager();
			String to = managerProfessore.readEmail(usernameProfessore);
			
			String host = "smtp.gmail.com";
			String from = "tirocirapid@gmail.com";
//			String subject = "Recupero password per TirocRapid"; 
//			String messageText = "La password per accedere a TirociRapid è:\n" + passwordRecuperata + "\n";
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
	 
			Message message = new MimeMessage(sessionMail);
			sessionMail.setDebug(true);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
	 
			Transport.send(message);
		}
		catch (SQLException | TuplaNotFoundException | MessagingException e)
		{
			e.printStackTrace();
			// do nothing
		}
	}
	
	/**
	 * Questo metodo si occupa di notificare ai Responsabili Approvazioni l'arrivo di una nuova richiesta di tirocinio
	 */
	private void inviaEmailResponsabileApprovazioni(String subject, String messageText)
	{
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractResponsabileApprovazioniManager managerResponsabileApprovazioni = factory.createResponsabileApprovazioniManager();
			ArrayList<String> emails = managerResponsabileApprovazioni.readEmailAll();
			
			String host = "smtp.gmail.com";
			String from = "tirocirapid@gmail.com";
//			String subject = "Recupero password per TirocRapid"; 
//			String messageText = "La password per accedere a TirociRapid è:\n" + passwordRecuperata + "\n";
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
	 
			for (String to : emails)
			{
				Message message = new MimeMessage(sessionMail);
				sessionMail.setDebug(true);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject(subject);
				message.setText(messageText);
				
				Transport.send(message);
			}
		}
		catch (SQLException | MessagingException e)
		{
			e.printStackTrace();
			// do nothing
		}
	}
	
	/**
	 * Questo metodo si occupa di notificare il cambiamento di stato allo studente
	 * @param messageText 
	 * @param subject 
	 */
	private void inviaEmailStudente(String usernameStudente, String subject, String messageText)
	{
		try
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractStudenteManager managerStudente = factory.createStudenteManager();
			String to = managerStudente.readEmail(usernameStudente);
			
			String host = "smtp.gmail.com";
			String from = "tirocirapid@gmail.com";
//			String subject = "Recupero password per TirocRapid"; 
//			String messageText = "La password per accedere a TirociRapid è:\n" + passwordRecuperata + "\n";
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
	 
			Message message = new MimeMessage(sessionMail);
			sessionMail.setDebug(true);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(messageText);
	 
			Transport.send(message);
		}
		catch (SQLException | TuplaNotFoundException | MessagingException e)
		{
			e.printStackTrace();
			// do nothing
		}
	}
	
	private static HashMap<String, String> userTypes;
	private static HashMap<Integer, String> statesReqTir;

}
