package it.tirocirapid.classes.model;

/**
 * Bean che rappresenta una proposta di tirocinio di un'azienda all'interno del nostro sistema
 * @author Andrea
 *
 */
public class Tirocinio {
	
	public Tirocinio() {
		
	}
	
	public Tirocinio(String partitaIVAAzienda, String nome, String descrizione, String offertaFormativa, String stato) {
		super();
		this.partitaIVAAzienda = partitaIVAAzienda;
		this.nome = nome;
		this.descrizione = descrizione;
		this.offertaFormativa = offertaFormativa;
		this.stato = stato;
	}

	/**
	 * @return the partitaIVAAzienda
	 */
	public String getPartitaIVAAzienda() {
		return partitaIVAAzienda;
	}
	/**
	 * @param partitaIVAAzienda the partitaIVAAzienda to set
	 */
	public void setPartitaIVAAzienda(String partitaIVAAzienda) {
		this.partitaIVAAzienda = partitaIVAAzienda;
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
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}
	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * @return the offertaFormativa
	 */
	public String getOffertaFormativa() {
		return offertaFormativa;
	}
	/**
	 * @param offertaFormativa the offertaFormativa to set
	 */
	public void setOffertaFormativa(String offertaFormativa) {
		this.offertaFormativa = offertaFormativa;
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
	private String partitaIVAAzienda;
	private String nome;
	private String descrizione;
	private String offertaFormativa;
	/**
	 * @invariant stato.equals("TirProp") || stato.equals("TirConf") || stato.equals("TirRif") || stato.equals("TirNDisp")
	 */
	private String stato;
}
