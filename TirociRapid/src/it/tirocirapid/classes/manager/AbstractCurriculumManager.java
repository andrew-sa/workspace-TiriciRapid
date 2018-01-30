package it.tirocirapid.classes.manager;

import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractCurriculumManager {
	
	public abstract void create(Curriculum toCreate, String usernameStudente) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException;
	public abstract Curriculum read(String usernameStudente) throws SQLException, TuplaNotFoundException;
	public abstract void update(Curriculum toUpdate, String usernameStudente) throws SQLException, InsertFailedException;
	public abstract boolean search(String usernameStudente) throws SQLException;
	
}
