package it.tirocirapid.classes.business;

import java.util.ArrayList;

/**
 * Bean che rappresenta un curriculum all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Curriculum {
	
	public Curriculum() {
		
	}
	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEsperienzaLavorativa() {
		return esperienzaLavorativa;
	}
	/**
	 * @param esperienzaLavorativa the esperienzaLavorativa to set
	 */
	public void setEsperienzaLavorativa(String esperienzaLavorativa) {
		this.esperienzaLavorativa = esperienzaLavorativa;
	}
	/**
	 * @return the istruzioneFormazione
	 */
	public String getIstruzioneFormazione() {
		return istruzioneFormazione;
	}
	/**
	 * @param istruzioneFormazione the istruzioneFormazione to set
	 */
	public void setIstruzioneFormazione(String istruzioneFormazione) {
		this.istruzioneFormazione = istruzioneFormazione;
	}
	/**
	 * @return the capacitaCompetenzePerosanali
	 */
	public String getCapacitaCompetenzePerosanali() {
		return capacitaCompetenzePerosanali;
	}
	/**
	 * @param capacitaCompetenzePerosanali the capacitaCompetenzePerosanali to set
	 */
	public void setCapacitaCompetenzePerosanali(String capacitaCompetenzePerosanali) {
		this.capacitaCompetenzePerosanali = capacitaCompetenzePerosanali;
	}
	/**
	 * @return the madrelingua
	 */
	public String getMadrelingua() {
		return madrelingua;
	}
	/**
	 * @param madrelingua the madrelingua to set
	 */
	public void setMadrelingua(String madrelingua) {
		this.madrelingua = madrelingua;
	}
	/**
	 * @return the altreLigue
	 */
	public ArrayList<String> getAltreLigue() {
		return altreLigue;
	}
	/**
	 * @param altreLigue the altreLigue to set
	 */
	public void setAltreLigue(ArrayList<String> altreLigue) {
		this.altreLigue = altreLigue;
	}
	/**
	 * @return the capicitaCompetenzeRelazionali
	 */
	public String getCapicitaCompetenzeRelazionali() {
		return capicitaCompetenzeRelazionali;
	}
	/**
	 * @param capicitaCompetenzeRelazionali the capicitaCompetenzeRelazionali to set
	 */
	public void setCapicitaCompetenzeRelazionali(String capicitaCompetenzeRelazionali) {
		this.capicitaCompetenzeRelazionali = capicitaCompetenzeRelazionali;
	}
	/**
	 * @return the capicitaCompetenzeOrganizzative
	 */
	public String getCapicitaCompetenzeOrganizzative() {
		return capicitaCompetenzeOrganizzative;
	}
	/**
	 * @param capicitaCompetenzeOrganizzative the capicitaCompetenzeOrganizzative to set
	 */
	public void setCapicitaCompetenzeOrganizzative(String capicitaCompetenzeOrganizzative) {
		this.capicitaCompetenzeOrganizzative = capicitaCompetenzeOrganizzative;
	}
	/**
	 * @return the capicitaCompetenzeTecniche
	 */
	public String getCapicitaCompetenzeTecniche() {
		return capicitaCompetenzeTecniche;
	}
	/**
	 * @param capicitaCompetenzeTecniche the capicitaCompetenzeTecniche to set
	 */
	public void setCapicitaCompetenzeTecniche(String capicitaCompetenzeTecniche) {
		this.capicitaCompetenzeTecniche = capicitaCompetenzeTecniche;
	}
	/**
	 * @return the capicitaCompetenzeArtistiche
	 */
	public String getCapicitaCompetenzeArtistiche() {
		return capicitaCompetenzeArtistiche;
	}
	/**
	 * @param capicitaCompetenzeArtistiche the capicitaCompetenzeArtistiche to set
	 */
	public void setCapicitaCompetenzeArtistiche(String capicitaCompetenzeArtistiche) {
		this.capicitaCompetenzeArtistiche = capicitaCompetenzeArtistiche;
	}
	/**
	 * @return the altreCapacitaCompetenze
	 */
	public String getAltreCapacitaCompetenze() {
		return altreCapacitaCompetenze;
	}
	/**
	 * @param altreCapacitaCompetenze the altreCapacitaCompetenze to set
	 */
	public void setAltreCapacitaCompetenze(String altreCapacitaCompetenze) {
		this.altreCapacitaCompetenze = altreCapacitaCompetenze;
	}
	/**
	 * @return the patenti
	 */
	public ArrayList<String> getPatenti() {
		return patenti;
	}
	/**
	 * @param patenti the patenti to set
	 */
	public void setPatenti(ArrayList<String> patenti) {
		this.patenti = patenti;
	}
	/**
	 * @return the ulterioriInformazioni
	 */
	public String getUlterioriInformazioni() {
		return ulterioriInformazioni;
	}
	/**
	 * @param ulterioriInformazioni the ulterioriInformazioni to set
	 */
	public void setUlterioriInformazioni(String ulterioriInformazioni) {
		this.ulterioriInformazioni = ulterioriInformazioni;
	}
	private String fax;
	private String esperienzaLavorativa;
	private String istruzioneFormazione;
	private String capacitaCompetenzePerosanali;
	private String madrelingua;
	private ArrayList<String> altreLigue;
	private String capicitaCompetenzeRelazionali;
	private String capicitaCompetenzeOrganizzative;
	private String capicitaCompetenzeTecniche;
	private String capicitaCompetenzeArtistiche;
	private String altreCapacitaCompetenze;
	private ArrayList<String> patenti;
	private String ulterioriInformazioni;
}
