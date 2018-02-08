package it.tirocirapid.classes.model;

import java.util.ArrayList;

/**
 * Bean che rappresenta un curriculum all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Curriculum {
	
	public Curriculum() {
		
	}
	
	public Curriculum(String fax, String esperienzaLavorativa, String capacitaCompetenzePersonali, String madrelingua,
			String altreLingue, String capacitaCompetenzeRelazionali, String capacitaCompetenzeOrganizzative,
			String capacitaCompetenzeTecniche, String capacitaCompetenzeArtistiche, String altreCapacitaCompetenze,
			String patenti, String ulterioriInformazioni) {
		super();
		this.fax = fax;
		this.esperienzaLavorativa = esperienzaLavorativa;
		this.capacitaCompetenzePersonali = capacitaCompetenzePersonali;
		this.madrelingua = madrelingua;
		this.altreLingue = altreLingue;
		this.capacitaCompetenzeRelazionali = capacitaCompetenzeRelazionali;
		this.capacitaCompetenzeOrganizzative = capacitaCompetenzeOrganizzative;
		this.capacitaCompetenzeTecniche = capacitaCompetenzeTecniche;
		this.capacitaCompetenzeArtistiche = capacitaCompetenzeArtistiche;
		this.altreCapacitaCompetenze = altreCapacitaCompetenze;
		this.patenti = patenti;
		this.ulterioriInformazioni = ulterioriInformazioni;
	}
	@Override public String toString(){
	      return "Fax: "+getFax()+
	    		 " EsperienzaLavorativa: " +getEsperienzaLavorativa()+
	    		 " CapacitaCompetenzePersonali: " +getCapacitaCompetenzePersonali()+
	    		 " Madrelingua: " +getMadrelingua()+
	    		 " AltreLingue: " +getAltreLingue()+
	    		 " CapacitaCompetenzeRelazionali: "+getCapacitaCompetenzeRelazionali()+
	    		 " CapacitaCompetenzeOrganizzative: " +getCapacitaCompetenzeOrganizzative()+
	    		 " CapacitaCompetenzeTecniche: " +getCapacitaCompetenzeTecniche()+
	    		 " CapacitaCompetenzeArtistiche: " +getCapacitaCompetenzeArtistiche()+
	    		 " AltreCapacitaCompetenze: " +getAltreCapacitaCompetenze()+
	    		 " Patenti: "+getPatenti()+
	    		 " UlterioriInformazioni: " +getUlterioriInformazioni();
	    }
	@Override public boolean equals(Object other) {
        boolean result=false;
        if (other instanceof Curriculum) {
            Curriculum that = (Curriculum) other;
            result = ((this.getFax().equals(that.getFax()))  && 
            		(this.getEsperienzaLavorativa().equals(that.getEsperienzaLavorativa())) &&
            		(this.getCapacitaCompetenzePersonali().equals(that.getCapacitaCompetenzePersonali())) &&
            		(this.getMadrelingua().equals(that.getMadrelingua())) &&
            		(this.getAltreLingue().equals(that.getAltreLingue())) &&
            		(this.getCapacitaCompetenzeRelazionali().equals(that.getCapacitaCompetenzeRelazionali())) &&
            		(this.getCapacitaCompetenzeOrganizzative().equals(that.getCapacitaCompetenzeOrganizzative())) &&
            		(this.getCapacitaCompetenzeTecniche().equals(that.getCapacitaCompetenzeTecniche())) &&
            		(this.getCapacitaCompetenzeArtistiche().equals(that.getCapacitaCompetenzeArtistiche())) &&
            		(this.getAltreCapacitaCompetenze().equals(that.getAltreCapacitaCompetenze())) &&
            		(this.getPatenti().equals(that.getPatenti())) &&
            		(this.getUlterioriInformazioni().equals(that.getUlterioriInformazioni())));
        }
        return result;
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
	 * @return the capacitaCompetenzePerosanali
	 */
	public String getCapacitaCompetenzePersonali() {
		return capacitaCompetenzePersonali;
	}
	/**
	 * @param capacitaCompetenzePerosanali the capacitaCompetenzePerosanali to set
	 */
	public void setCapacitaCompetenzePersonali(String capacitaCompetenzePersonali) {
		this.capacitaCompetenzePersonali = capacitaCompetenzePersonali;
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
	public String getAltreLingue() {
		return altreLingue;
	}
	/**
	 * @param altreLigue the altreLigue to set
	 */
	public void setAltreLingue(String altreLingue) {
		this.altreLingue = altreLingue;
	}
	/**
	 * @return the capicitaCompetenzeRelazionali
	 */
	public String getCapacitaCompetenzeRelazionali() {
		return capacitaCompetenzeRelazionali;
	}
	/**
	 * @param capicitaCompetenzeRelazionali the capicitaCompetenzeRelazionali to set
	 */
	public void setCapacitaCompetenzeRelazionali(String capacitaCompetenzeRelazionali) {
		this.capacitaCompetenzeRelazionali = capacitaCompetenzeRelazionali;
	}
	/**
	 * @return the capicitaCompetenzeOrganizzative
	 */
	public String getCapacitaCompetenzeOrganizzative() {
		return capacitaCompetenzeOrganizzative;
	}
	/**
	 * @param capicitaCompetenzeOrganizzative the capicitaCompetenzeOrganizzative to set
	 */
	public void setCapacitaCompetenzeOrganizzative(String capacitaCompetenzeOrganizzative) {
		this.capacitaCompetenzeOrganizzative = capacitaCompetenzeOrganizzative;
	}
	/**
	 * @return the capicitaCompetenzeTecniche
	 */
	public String getCapacitaCompetenzeTecniche() {
		return capacitaCompetenzeTecniche;
	}
	/**
	 * @param capicitaCompetenzeTecniche the capicitaCompetenzeTecniche to set
	 */
	public void setCapacitaCompetenzeTecniche(String capacitaCompetenzeTecniche) {
		this.capacitaCompetenzeTecniche = capacitaCompetenzeTecniche;
	}
	/**
	 * @return the capicitaCompetenzeArtistiche
	 */
	public String getCapacitaCompetenzeArtistiche() {
		return capacitaCompetenzeArtistiche;
	}
	/**
	 * @param capicitaCompetenzeArtistiche the capicitaCompetenzeArtistiche to set
	 */
	public void setCapacitaCompetenzeArtistiche(String capacitaCompetenzeArtistiche) {
		this.capacitaCompetenzeArtistiche = capacitaCompetenzeArtistiche;
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
	public String getPatenti() {
		return patenti;
	}
	/**
	 * @param patenti the patenti to set
	 */
	public void setPatenti(String patenti) {
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
	private String capacitaCompetenzePersonali;
	private String madrelingua;
	private String altreLingue;
	private String capacitaCompetenzeRelazionali;
	private String capacitaCompetenzeOrganizzative;
	private String capacitaCompetenzeTecniche;
	private String capacitaCompetenzeArtistiche;
	private String altreCapacitaCompetenze;
	private String patenti;
	private String ulterioriInformazioni;
}
