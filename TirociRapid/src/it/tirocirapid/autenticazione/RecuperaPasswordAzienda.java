package it.tirocirapid.autenticazione;

import java.io.IOException;
import java.sql.SQLException;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa del recupero password dell'azienda
 */
@WebServlet("/recupera_password")
public class RecuperaPasswordAzienda extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperaPasswordAzienda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String partitaIVA = request.getParameter("partitaIVA");
		if (!replaceIfMissing(partitaIVA, "").equals(""))
		{
			AbstractManagerFactory factory = new DAOFactory();
			AbstractAziendaManager managerAzienda = factory.createAziendaManager();
			try
			{
				String email = managerAzienda.readEmail(partitaIVA);
				String password = managerAzienda.readEmail(partitaIVA);
				inviaEmail(email, password);
				request.setAttribute("successo", "La email di recupero password &egrave; stata inviata con successo");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di LoginAzienda
				dispatcher.forward(request, response);
				return;
			}
			catch (SQLException e)
			{
				request.setAttribute("successo", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
				e.printStackTrace();
			}
			catch (TuplaNotFoundException e)
			{
				request.setAttribute("errore", "La partitaIVA inserita non apartiene a nessuna azienda");
				e.printStackTrace();
			}
			catch (AddressException e)
			{
				request.setAttribute("errore", "Si &egrave; verificato un errore nell'invio della email di recupero password");
				e.printStackTrace();
			}
			catch (MessagingException e)
			{
				request.setAttribute("errore", "Si &egrave; verificato un errore nell'invio della email di recupero password");
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //Alla schermata di RecuperaPasswordAzienda
			dispatcher.forward(request, response);
		}
		else
		{
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 
	 * @param emailAzienda
	 * @param passwordRecuperata
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private void inviaEmail(String emailAzienda, String passwordRecuperata) throws AddressException, MessagingException
	{
		String host = "smtp.gmail.com";
		String to = emailAzienda;
		String from = "tirocirapid@gmail.com";
		String subject = "Recupero password per TirocRapid"; 
		String messageText = "La password per accedere a TirociRapid è:\n" + passwordRecuperata + "\n";
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
