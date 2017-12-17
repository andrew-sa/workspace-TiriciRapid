package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Tirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Tirocinio dal DB.
 */
public class TirocinioDAO extends AbstractTirocinioManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un tirocinio
	 * @param toCreate il tirocinio da inserire
	 * @throws SQLException
	 */
	@Override
	public void create(Tirocinio toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare un tirocinio tramite la partita IVA e il nome 
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @return Tirocinio il tirocinio cercato
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per la modificare lo stoto del tirocinio
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @param statoToUpdate il nuovo stato della proposta di tirocinio
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(String partitaIVA, String nome, String statoToUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa di eliminare un tirocinio dal DB
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void delete(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutti i tirocini di un azienda 
	 * @param partitaIVA la partita IVA dell'azienda
	 * @return ArrayList<Tirocinio> rappresenta i tirocini presenti nel DB di un azienda 
	 * @throws TuplaNotFoundException l'azienda con quella partita IVA non è presente all'interno del DB
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
