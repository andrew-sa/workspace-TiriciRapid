package it.tirocirapid.classes.model;

/**
 * Bean che rappresenta una richeista di tirocinio all'interno del nostro sistema
 * @author Andrea
 *
 */
public class RichiestaTirocinio {

	
	public RichiestaTirocinio() {
		
	}
	
	public RichiestaTirocinio(Studente studente, Tirocinio tirocinio, Professore tutorInterno, String stato) {
		super();
		this.studente = studente;
		this.tirocinio = tirocinio;
		this.tutorInterno = tutorInterno;
		this.stato = stato;
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
	public Tirocinio getTirocinio() {
		return tirocinio;
	}
	/**
	 * @param tirocinio the tiroconio to set
	 */
	public void setTiroconio(Tirocinio tirocinio) {
		this.tirocinio = tirocinio;
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
	
	public void setStudente(String username)
	{
		Studente studente = new Studente();
		studente.setEmail(username);
		this.studente = studente;
	}
	
	public void setTirocinio(String partitaIVAAzienda, String nomeTirocinio)
	{
		Tirocinio tirocinio = new Tirocinio();
		tirocinio.setPartitaIVAAzienda(partitaIVAAzienda);
		tirocinio.setNome(nomeTirocinio);
		this.tirocinio = tirocinio;
	}
	
	public void setTutorInterno(String username)
	{
		Professore professore = new Professore();
		professore.setUsername(username);
		tutorInterno = professore;
	}
	
	private Studente studente;
	private Tirocinio tirocinio;
	private Professore tutorInterno;
	/**
	 * @invariant stato.equals("ConfAz") || stato.equals("RifAz") || stato.equals("ScelTut") || stato.equals("ConfTut") || stato.equals("RifTut") || stato.equals("ConfResp") || stato.equals("RifResp") || stato.equals("Acc")
	 */
	private String stato;
}
