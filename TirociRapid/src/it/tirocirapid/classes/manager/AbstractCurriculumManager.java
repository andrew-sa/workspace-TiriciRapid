package it.tirocirapid.classes.manager;

import java.sql.SQLException;

import it.tirocirapid.classes.business.Curriculum;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractCurriculumManager {
	
	public abstract void create(Curriculum toCreate, String usernameStudente) throws SQLException;
	public abstract Curriculum read(String username) throws SQLException, TuplaNotFoundException;
	public abstract void update(Curriculum toUpdate) throws SQLException, TuplaNotFoundException;

}
