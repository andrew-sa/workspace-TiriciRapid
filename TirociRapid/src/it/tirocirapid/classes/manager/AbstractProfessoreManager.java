package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Professore;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractProfessoreManager {

	public abstract boolean search(String username, String password) throws SQLException;
	public abstract Professore read(String username) throws SQLException, TuplaNotFoundException;
	public abstract ArrayList<Professore> readAll() throws SQLException;
	
}
