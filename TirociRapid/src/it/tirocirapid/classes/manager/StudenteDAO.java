package it.tirocirapid.classes.manager;

import java.sql.SQLException;
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
	 * @return true se il professore è presente nel DB
	 * @return false altrimenti
	 * @throws SQLException
	 */
	@Override
	public boolean search(String username, String password) throws SQLException {
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
	
}
