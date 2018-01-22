package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractProfessoreManager {

	public abstract int search(String username, String password) throws SQLException, TuplaNotFoundException;
	public abstract Professore read(String username) throws SQLException, TuplaNotFoundException;
	public abstract ArrayList<Professore> readAll() throws SQLException;
	public abstract String readEmail(String username) throws SQLException, TuplaNotFoundException;
}
