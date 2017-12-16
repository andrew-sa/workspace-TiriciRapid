package it.tirocirapid.classes.manager;

import java.sql.SQLException;

import it.tirocirapid.classes.business.Curriculum;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Curriculum dal DB.
 */
public class CurriculumDAO extends AbstractCurriculumManager {

	@Override
	public void create(Curriculum toCreate, String usernameStudente) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Curriculum read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Curriculum toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
