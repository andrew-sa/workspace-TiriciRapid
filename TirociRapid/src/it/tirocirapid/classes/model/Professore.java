package it.tirocirapid.classes.model;

/**
 * Bean che rappresenta un professore all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Professore {
	
	public Professore() {
		
	}
	
	public Professore(String username, String password, String ambito, String emailIstituzionale, String nome,
			String cognome, String email, String telefono, String indirizzo, String matricola) {
		super();
		this.username = username;
		this.password = password;
		this.ambito = ambito;
		this.emailIstituzionale = emailIstituzionale;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.telefono = telefono;
		this.indirizzo = indirizzo;
		this.matricola = matricola;
	}

	public Professore(String username)
	{
		this.username = username;
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
	 * @return the ambito
	 */
	public String getAmbito() {
		return ambito;
	}
	/**
	 * @param ambito the ambito to set
	 */
	public void setAmbito(String ambito) {
		this.ambito = ambito;
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

	private String username;
	private String password;
	private String ambito;
	private String emailIstituzionale;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private String indirizzo;
	private String matricola;
	/*/**
	 * La variabile d'istanza responsabileApprovazioni ha valore truese il professore ha anche il compito del responabile approvazioni, altrimenti ha valore false
	 */
	//private Boolean responsabileApprovazioni;
}
