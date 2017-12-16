package it.tirocirapid.classes.business;

/**
 * Bean che rappresenta una richeista di tirocinio all'interno del nostro sistema
 * @author Andrea
 *
 */
public class RichiestaTirocinio {

	private Studente studente;
	private Tirocinio tiroconio;
	private Professore tutorInterno;
	/**
	 * @inv stato == "" || 
	 */
	private String stato;
}
