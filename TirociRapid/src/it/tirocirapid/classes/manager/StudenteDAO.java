package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import it.tirocirapid.classes.business.Studente;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Studente dal DB.
 */
public class StudenteDAO extends AbstractStudenteManager {

	@Override
	public boolean search(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Studente read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
