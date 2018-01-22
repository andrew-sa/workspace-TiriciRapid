package it.tirocirapid.listener;

import java.util.HashMap;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class InizializzazioneContextListener
 *
 */
@WebListener
public class InizializzazioneContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InizializzazioneContextListener() {
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)
    { 
    	HashMap<String, String> userTypes = new HashMap<>();
    	userTypes.put("Studente", "Studente"); //chiave, valore
    	userTypes.put("Professore", "Professore");
    	userTypes.put("ResponsabileApprovazioni", "ResponsabileApprovazioni");
    	userTypes.put("ResponsabileAzienda", "ResponsabileAzienda");
    	arg0.getServletContext().setAttribute("userTypes", userTypes);
    	System.out.println("INIZIALIZZAZIONE");
    }
	
}
