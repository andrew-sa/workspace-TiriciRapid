package it.tirocirapid.tirocinio.richiesta;

import java.io.IOException;
import java.sql.SQLException;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.UserLoggato;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa della gestione della scelta del tutor interno
 */
@WebServlet("/scegli_tutor")
public class ScegliTutorInterno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScegliTutorInterno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String usernameStudente = ((UserLoggato) request.getSession().getAttribute("user")).getId();
		String partitaIVA = request.getParameter("partitaIVAAzienda");
		String nomeTirocinio = request.getParameter("nomeTirocinio");
		nomeTirocinio = nomeTirocinio.replace("+", " ");
		String usernameProfessore = request.getParameter("professore");
		final String replacement = "";
		HashMap<Integer, String> states = (HashMap<Integer, String>) getServletContext().getAttribute("statesReqTir");
		
		if (replaceIfMissing(partitaIVA, replacement).equals(replacement) || replaceIfMissing(nomeTirocinio, replacement).equals(replacement))
		{
			request.setAttribute("errore", "La richiesta di tirocinio scelta non &egrave; valida");
		}
		else if (replaceIfMissing(usernameProfessore, replacement).equals(replacement))
		{
			request.setAttribute("errore", "Devi scegliere un professore per il ruolo di tutor interno");
		}
		else
		{
			try
			{
				AbstractManagerFactory factory = new DAOFactory();
				AbstractRichiestaTirocinioManager managerRichiestaTirocinio = factory.createRichiestaTirocinioManager();
				RichiestaTirocinio reqTir = managerRichiestaTirocinio.read(usernameStudente, partitaIVA, nomeTirocinio);
				if (reqTir.getStato().equals(states.get(2)) || reqTir.getStato().equals(states.get(-3))) // stato == "ScelTut" || stato == "RifTut"
				{
					reqTir.setStato(states.get(3)); // stato = "ConfTut"
					reqTir.setTutorInterno(new Professore(usernameProfessore));
					managerRichiestaTirocinio.updateAddTutor(reqTir);
					request.setAttribute("successo", "La scelta &egrave; stata salvata con successo");
					try
					{
						inviaEmailTutorInterno(usernameProfessore, usernameStudente, nomeTirocinio, partitaIVA);
					}
					catch (MessagingException | SQLException | TuplaNotFoundException e)
					{
						e.printStackTrace();
						// do nothing
					}
				}
				else
				{
					request.setAttribute("errore", "Lo stato attuale della richiesta di tirocinio non consente di effetuare le azione desiderate");
				}
			}
			catch (MySQLIntegrityConstraintViolationException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Il tutor interno scelto non &egrave; presente nel database");
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			}
			catch (TuplaNotFoundException | InsertFailedException e)
			{
				e.printStackTrace();
				request.setAttribute("errore", e.getMessage());
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studente_richieste.jsp"); //Alla schermata di RichiesteTirocinioStudente
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
	 * Questo metodo si occupa di notificare il professore scelto come tutor interno dallo studente
	 * @throws TuplaNotFoundException 
	 * @throws SQLException 
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	private void inviaEmailTutorInterno(String usernameProfessore, String usernameStudente, String nomeTirocinio, String partitaIVAAzienda) throws SQLException, TuplaNotFoundException, AddressException, MessagingException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractProfessoreManager managerProfessore = factory.createProfessoreManager();
		String to = managerProfessore.readEmail(usernameProfessore);
		String host = "smtp.gmail.com";
		String from = "tirocirapid@gmail.com";
		String subject = "TirociRapid: Sei stato scelto come tutor interno"; 
		String messageText = "Lo studente " + usernameStudente + " l'ha scelta come Tutor Interno per il tirocinio " + nomeTirocinio + " offerto dall'azienda " + partitaIVAAzienda + "./n";
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
