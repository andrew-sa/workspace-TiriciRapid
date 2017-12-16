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
	private Curriculum curriculum;
}
