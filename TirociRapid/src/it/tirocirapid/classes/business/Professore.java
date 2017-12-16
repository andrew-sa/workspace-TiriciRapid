package it.tirocirapid.classes.business;

/**
 * Bean che rappresenta un professore all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Professore {
	
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
	
	private String username;
	private String password;
	/*/**
	 * La variabile d'istanza responsabileApprovazioni ha valore truese il professore ha anche il compito del responabile approvazioni, altrimenti ha valore false
	 */
	//private Boolean responsabileApprovazioni;
}
