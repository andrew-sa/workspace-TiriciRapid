package it.tirocirapid.classes.business;

/**
 * Bean che rappresenta uno studente all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Studente {
	
	public Studente() {
		
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the emailIstituzionale
	 */
	public String getEmailIstituzionale() {
		return emailIstituzionale;
	}
	/**
	 * @param emailIstituzionale the emailIstituzionale to set
	 */
	public void setEmailIstituzionale(String emailIstituzionale) {
		this.emailIstituzionale = emailIstituzionale;
	}
	/**
	 * @return the matricola
	 */
	public String getMatricola() {
		return matricola;
	}
	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	/**
	 * @return the mediaVoti
	 */
	public String getMediaVoti() {
		return mediaVoti;
	}
	/**
	 * @param mediaVoti the mediaVoti to set
	 */
	public void setMediaVoti(String mediaVoti) {
		this.mediaVoti = mediaVoti;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}
	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the curriculum
	 */
	public Curriculum getCurriculum() {
		return curriculum;
	}
	/**
	 * @param curriculum the curriculum to set
	 */
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	private String username;
	private String password;
	private String emailIstituzionale;
	private String matricola;
	private String mediaVoti;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private String email;
	private Curriculum curriculum;
}
