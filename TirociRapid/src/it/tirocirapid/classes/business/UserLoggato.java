package it.tirocirapid.classes.business;

public class UserLoggato {
	
	public UserLoggato() {
		
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	private String id;
	/**
	 * tipo.equals("Studente") || tipo.equals("ResponsabileApprovazioni") || tipo.equals("Professore") || tipo.equals("ResponsabileAzienda")
	 */
	private String tipo;

}
