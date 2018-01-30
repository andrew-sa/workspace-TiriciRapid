package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import java.util.HashMap;
import java.util.Properties;

import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.BadRequestTirocinioException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire una nuova richiesta di tirocinio effettuata da parte di uno studente
 */
@WebServlet("/invia_richiesta")
public class CreaRichiestaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreaRichiestaTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = ((UserLoggato) request.getSession().getAttribute("user")).getId();
		String partitaIVA = request.getParameter("partitaIVAAzienda");
		String nomeTirocinio = request.getParameter("nomeTirocinio");
		final String replacement = "";
		if (replaceIfMissing(partitaIVA, replacement).equals(replacement) || replaceIfMissing(nomeTirocinio, replacement).equals(replacement))
		{
			request.setAttribute("errore", "Devi scegliere un tirocinio valido");	
		}
		else
		{
			RichiestaTirocinio reqTir = new RichiestaTirocinio();
			reqTir.setStudente(username);
			reqTir.setTirocinio(partitaIVA, nomeTirocinio);
			HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
			AbstractManagerFactory factory = new DAOFactory();
			AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
			try
			{
				managerRichiestaTirocinio.create(reqTir, states);
				request.setAttribute("successo", "La richiesta di tirocinio &egrave; stato creata con successo");
				try
				{
					inviaEmailResponsabileAzienda(partitaIVA, nomeTirocinio, username);
				}
				catch (MessagingException | TuplaNotFoundException | SQLException e)
				{
					e.printStackTrace();
					// do nothing
				}
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (BadRequestTirocinioException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
			catch (InsertFailedException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di HomeStudente
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	/**
	 * Questo metodo si occupa di notificare al responsabile azienda l'arrivo di una nuova richeista di tirocinio
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws TuplaNotFoundException 
	 * @throws SQLException 
	 */
	private void inviaEmailResponsabileAzienda(String partitaIVA, String nomeTirocinio, String usernameStudente) throws AddressException, MessagingException, SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		
		String host = "smtp.gmail.com";
		String to = managerAzienda.readEmail(partitaIVA);
		String from = "tirocirapid@gmail.com";
		String subject = "TirocRapid: Nuova richesta di tirocinio"; 
		String messageText = "Lo studente " + usernameStudente + " vorrebbe effettuare il tirocinio " + nomeTirocinio + " presso la sua azienda." + "\n";
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
