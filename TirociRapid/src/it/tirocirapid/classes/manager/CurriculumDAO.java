package it.tirocirapid.classes.manager;

import java.sql.SQLException;

import it.tirocirapid.classes.business.Curriculum;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Curriculum dal DB.
 */
public class CurriculumDAO extends AbstractCurriculumManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un Curriculum nel DB
	 * @param toCreate il Curriculum da inserire
	 * @param usernameStudente l'username dello studente a cui appartiene il curriculum
	 * @throws SQLException
	 */
	@Override
	public void create(Curriculum toCreate, String usernameStudente) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare un'curriculum tramite l'username dello studente
	 * @param L'username l'username dello Studente
	 * @return Curriculum il curriculum dello studente 
	 * @throws TuplaNotFoundException il curriculum di quello studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Curriculum read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per modificare un curriculum
	 * @param toUpdate il curriculum modificato
	 * @throws TuplaNotFoundException il curriculum di quello studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(Curriculum toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
