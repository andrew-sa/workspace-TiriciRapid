package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Azienda;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Azienda dal DB.
 */
public class AziendaDAO extends AbstractAziendaManager {
	
	/**
	 * Si occupa dell'interrogazione al database per verificare se l'azienda con quella partitaIVA e password è presente nel DB
	 * @param partitaIVA rappresenta la partitaIVA dello azienda da cercare
	 * @param password rappresenta la password dello azienda da cercare
	 * @return true se esiste un'azienda avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un'azienda avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste un'azienda avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public boolean search(String partitaIVA, String password) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un azienda
	 * @param toCreate l'azienda da inserire
	 * @throws SQLException
	 */
	@Override
	public void create(Azienda toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda da ricavare
	 * @return Azienda rappresenta l'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per la modifica di un'azienda
	 * @param toUpdate l'azienda modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(Azienda toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte l' aziende presenti 
	 * @return ArrayList<Azienda> rappresenta l'aziende presenti nel DB 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Azienda> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare l'email di un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo sapere l'email
	 * @return String rappresenta l'email dell'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public String readEmail(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readPassword(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
