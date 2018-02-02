package it.tirocirapid.classes.model;

import java.util.ArrayList;

/**
 * Bean che rappresenta un'azienda all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Azienda {
	
	public Azienda() {

	}
	
	public Azienda(String partitaIVA, String password, String email, String nome, String sede, String numeroTelefono,
			ArrayList<Tirocinio> tirociniOfferti, String stato, String descrizioneAmbito) {
		super();
		this.partitaIVA = partitaIVA;
		this.password = password;
		this.email = email;
		this.nome = nome;
		this.sede = sede;
		this.numeroTelefono = numeroTelefono;
		this.tirociniOfferti = tirociniOfferti;
		this.stato = stato;
		this.descrizioneAmbito = descrizioneAmbito;
	}

	/**
	 * @return the partitaIVA
	 */
	public String getPartitaIVA() {
		return partitaIVA;
	}
	/**
	 * @param partitaIVA the partitaIVA to set
	 */
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
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
	 * @return the sede
	 */
	public String getSede() {
		return sede;
	}
	/**
	 * @param sede the sede to set
	 */
	public void setSede(String sede) {
		this.sede = sede;
	}
	/**
	 * @return the numeroTelefono
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	/**
	 * @param numeroTelefono the numeroTelefono to set
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	/**
	 * @return the tirociniOfferti
	 */
	public ArrayList<Tirocinio> getTirociniOfferti() {
		return tirociniOfferti;
	}
	/**
	 * @param tirociniOfferti the tirociniOfferti to set
	 */
	public void setTirociniOfferti(ArrayList<Tirocinio> tirociniOfferti) {
		this.tirociniOfferti = tirociniOfferti;
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
	/**
	 * @return the descrizioneAmbito
	 */
	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}
	/**
	 * @param descrizioneAmbito the descrizioneAmbito to set
	 */
	public void setDescrizioneAmbito(String descrizioneAmbito) {
		this.descrizioneAmbito = descrizioneAmbito;
	}
	/**
	 * 
	 * @param t Il tirocinio da aggiungere
	 * @pre t != null
	 * @post tirociniOfferti.contains(t)
	 */
	public void aggiungiTirocinio(Tirocinio t)
	{
		
	}
	/**
	 * 
	 * @param t Il tirocinio da rimuovere
	 * @pre t != null && tirociniOfferti.contains(t)
	 * @post !tirociniOfferti.contains(t)
	 */
	public void cancellaTirocinio(Tirocinio t)
	{
		
	}
	private String partitaIVA;
	private String password;
	private String email;
	private String nome;
	private String sede;
	private String numeroTelefono;
	private ArrayList<Tirocinio> tirociniOfferti;
	/**
	 * @invariant stato.equals("AccettaRichieste") || stato.equals("NonAccettaRichieste")
	 */
	private String stato;
	private String descrizioneAmbito;
}
