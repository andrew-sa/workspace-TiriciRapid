package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Professore;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Professore dal DB.
 */
public class ProfessoreDAO extends AbstractProfessoreManager {

	/**
	 * Si occupa dell'interrogazione al database per verificare se il professore con quel username e password è presente nel DB
	 * @param username rappresenta l'username del professore da cercare
	 * @param password rappresenta la password del professore da cercare
	 * @return true se esiste un professore avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un professore avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste un professore avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public boolean search(String username, String password) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare il professore tramite l'username
	 * @param username rappresenta l'username del professore da cercare
	 * @return Professore il professore cercato
	 * @throws TuplaNotFoundException il professore non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Professore read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per caricare tutti i professori presenti
	 * @return ArrayList<Professore> rappresentano tutti i professori 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Professore> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readEmail(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
