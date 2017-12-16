package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Professore;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Professore dal DB.
 */
public class ProfessoreDAO extends AbstractProfessoreManager {

	@Override
	public boolean search(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Professore read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Professore> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
