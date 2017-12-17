package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Azienda;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractAziendaManager {
	
	public abstract boolean search(String partitaIVA, String password) throws SQLException, TuplaNotFoundException;
	public abstract void create(Azienda toCreate) throws SQLException;
	public abstract Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException;
	public abstract void update(Azienda toUpdate) throws SQLException, TuplaNotFoundException;
	public abstract ArrayList<Azienda> readAll() throws SQLException;
	public abstract String readEmail(String partitaIVA) throws SQLException, TuplaNotFoundException;
	public abstract String readPassword(String partitaIVA) throws SQLException, TuplaNotFoundException;
	
}
