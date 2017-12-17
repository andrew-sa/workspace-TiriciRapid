package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Studente;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Studente dal DB.
 */
public class StudenteDAO extends AbstractStudenteManager {

	/**
	 * Si occupa dell'interrogazione al database per verificare se lo studente con quel username e password è presente nel DB
	 * @param username rappresenta l'username dello studente da cercare
	 * @param password rappresenta la password dello studente da cercare
	 * @return true se esiste uno studente avente l'username e la password passati come parametri nel DB
	 * @return false se esiste uno studente avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste uno studente avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public boolean search(String username, String password) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare uno studente tramite l'username
	 * @param username rappresenta l'username dello studente da cercare
	 * @return Studente lo studente cercato
	 * @throws TuplaNotFoundException lo studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Studente read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare l'email di uno studente tramite l' username
	 * @param username l'username dello studente di cui vogliamo sapere l'email
	 * @return String rappresenta l'email dello studente con quel username 
	 * @throws TuplaNotFoundException lo studente con l'username specificato non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public String readEmail(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
