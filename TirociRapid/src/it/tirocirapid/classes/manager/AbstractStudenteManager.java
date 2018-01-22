package it.tirocirapid.classes.manager;

import java.sql.SQLException;

import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractStudenteManager {
	
	public abstract boolean search(String username, String password) throws SQLException, TuplaNotFoundException;
	public abstract Studente read(String username) throws SQLException, TuplaNotFoundException;
	public abstract String readEmail(String username) throws SQLException, TuplaNotFoundException;

}
