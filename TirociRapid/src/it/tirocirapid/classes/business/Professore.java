package it.tirocirapid.classes.business;

/**
 * Bean che rappresenta un professore all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Professore {
	
	public Professore() {
		
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

	private String username;
	private String password;
	private String emailIstituzionale;
	private String nome;
	private String cognome;
	private String email;
	private String telefono;
	private String indirizzo;
	private String ambito;
	/*/**
	 * La variabile d'istanza responsabileApprovazioni ha valore truese il professore ha anche il compito del responabile approvazioni, altrimenti ha valore false
	 */
	//private Boolean responsabileApprovazioni;
}
