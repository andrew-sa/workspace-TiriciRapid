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
    	userTypes.put("Stud", "Studente"); //chiave, valore
    	userTypes.put("Prof", "Professore");
    	userTypes.put("RespAppr", "ResponsabileApprovazioni");
    	userTypes.put("RespAz", "ResponsabileAzienda");
    	arg0.getServletContext().setAttribute("userTypes", userTypes);
    	
    	HashMap<String, String> statesAzienda = new HashMap<>();
    	statesAzienda.put("Disponibile", "AccettaRichieste");
    	statesAzienda.put("NoNDisponibile", "NonAccettaRichieste");
    	arg0.getServletContext().setAttribute("statesAzienda", statesAzienda);
    	
    	HashMap<Integer, String> statesPropTir = new HashMap<>();
    	statesPropTir.put(1, "TirProp");
    	statesPropTir.put(2, "TirConf");
    	statesPropTir.put(0, "TirRif");
    	statesPropTir.put(3, "TirNDisp");
    	arg0.getServletContext().setAttribute("statesPropTir", statesPropTir);
    	
    	HashMap<Integer, String> statesReqTir = new HashMap<>();
    	statesReqTir.put(1, "ConfAz");
    	statesReqTir.put(2, "ScelTut");
    	statesReqTir.put(3, "ConfTut");
    	statesReqTir.put(4, "ConfResp");
    	statesReqTir.put(5, "Acc");
    	statesReqTir.put(6, "RifResp");
    	statesReqTir.put(7, "RifTut");
    	statesReqTir.put(0, "RifAz");
    	
    	System.out.println("INIZIALIZZAZIONE");
    }
	
}
