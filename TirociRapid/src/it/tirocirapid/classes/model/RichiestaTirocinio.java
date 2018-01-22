package it.tirocirapid.classes.model;

/**
 * Bean che rappresenta una richeista di tirocinio all'interno del nostro sistema
 * @author Andrea
 *
 */
public class RichiestaTirocinio {

	
	public RichiestaTirocinio() {
		
	}
	/**
	 * @return the studente
	 */
	public Studente getStudente() {
		return studente;
	}
	/**
	 * @param studente the studente to set
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	/**
	 * @return the tiroconio
	 */
	public Tirocinio getTiroconio() {
		return tiroconio;
	}
	/**
	 * @param tiroconio the tiroconio to set
	 */
	public void setTiroconio(Tirocinio tiroconio) {
		this.tiroconio = tiroconio;
	}
	/**
	 * @return the tutorInterno
	 */
	public Professore getTutorInterno() {
		return tutorInterno;
	}
	/**
	 * @param tutorInterno the tutorInterno to set
	 */
	public void setTutorInterno(Professore tutorInterno) {
		this.tutorInterno = tutorInterno;
	}
	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}
	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}
	private Studente studente;
	private Tirocinio tiroconio;
	private Professore tutorInterno;
	/**
	 * @invariant stato.equals("ConfAz") || stato.equals("RifAz") || stato.equals("ScelTut") || stato.equals("ConfTut") || stato.equals("RifTut") || stato.equals("ConfResp") || stato.equals("RifResp") || stato.equals("Acc")
	 */
	private String stato;
}
