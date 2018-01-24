package it.tirocirapid.tirocinio.proposta;

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

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import it.tirocirapid.factory.AbstractManagerFactory;
import it.tirocirapid.factory.DAOFactory;

/**
 * Servlet che si occupa di gestire la decisione da parte del Responsabile Approvazioni su una proposta di tirocinio da parte di un'azienda
 */
@WebServlet("/convalida_proposta_tirocinio")
public class ConvalidaPropostaTirocinio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvalidaPropostaTirocinio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HashMap<Integer, String> states = (HashMap<Integer, String>) request.getServletContext().getAttribute("statesPropTir");
		String partitaIVA = request.getParameter("partitaIVA");
		String nome = request.getParameter("nome");
		String stato = request.getParameter("action");
		AbstractManagerFactory factory = new DAOFactory();
		AbstractTirocinioManager managerTirocinio = factory.createTirocinioManager();
		try
		{
//			Tirocinio tirocinio = managerTirocinio.read(partitaIVA, nome);
			switch (stato)
			{
				case "accetta":
					managerTirocinio.update(partitaIVA, nome, states.get(2));
					request.setAttribute("successo", "Tirocinio: " + partitaIVA + "-" + nome + " accettato con successo");
					try
					{
						inviaEmailResponsabileAzienda(partitaIVA, nome, "accettata");
					}
					catch (MessagingException | SQLException | TuplaNotFoundException e)
					{
						// do nothing
						e.printStackTrace();
					}
					break;
				case "rifiuta":
					managerTirocinio.update(partitaIVA, nome, states.get(0));
					request.setAttribute("successo", "Tirocinio: " + partitaIVA + "-" + nome + " rifiutato con successo");
					try
					{
						inviaEmailResponsabileAzienda(partitaIVA, nome, "rifiutata");
					}
					catch (MessagingException | SQLException | TuplaNotFoundException e)
					{
						// do nothing
						e.printStackTrace();
					}
					break;
				default:
					request.setAttribute("errore", "Devi selezionare un'azione");
			}
		}
		catch (SQLException e)
		{
			request.setAttribute("errore", "Si &egrave; verificato un errore durante l'interazione col database, si prega di riprovare");
			e.printStackTrace();
		}
		catch (TuplaNotFoundException e)
		{
			request.setAttribute("errore", e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"); //VisualizzaProposteTiroicinioInSospeso
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
	/**
	 * Questo metodo si occupa di notificare al repsonsabile azienda la scelta del responsabile approvazioni
	 * @param partitaIVA la partitaIVA dell'azienda
	 * @param nomeTirocinio nome del tirocinio
	 * @param responso esito della scelta del ResponsabileApprovazioni
	 * @throws MessagingException 
	 * @throws AddressException 
	 * @throws TuplaNotFoundException se l'azienda non è presente nel database
	 * @throws SQLException 
	 */
	private void inviaEmailResponsabileAzienda(String partitaIVA, String nomeTirocinio, String responso) throws AddressException, MessagingException, SQLException, TuplaNotFoundException
	{
		AbstractManagerFactory factory = new DAOFactory();
		AbstractAziendaManager managerAzienda = factory.createAziendaManager();
		String emailAzienda = managerAzienda.readEmail(partitaIVA);
		
		String host = "smtp.gmail.com";
		String to = emailAzienda;
		String from = "tirocirapid@gmail.com";
		String subject = "Tirocirapid: Esito approvazione Proposta Tirocinio " + nomeTirocinio; 
		String messageText = "La propostata di tirocinio " + nomeTirocinio + "è stata " + responso.toUpperCase() + ".\n";
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

}
