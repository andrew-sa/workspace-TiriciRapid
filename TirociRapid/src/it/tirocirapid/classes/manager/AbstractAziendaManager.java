package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractAziendaManager {
	
	public abstract boolean search(String partitaIVA, String password) throws SQLException, TuplaNotFoundException;
	public abstract void create(Azienda toCreate) throws SQLException, InsertFailedException;
	public abstract Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException;
	public abstract void update(Azienda toUpdate) throws SQLException, InsertFailedException;
	public abstract ArrayList<Azienda> readAll() throws SQLException;
	public abstract String readEmail(String partitaIVA) throws SQLException, TuplaNotFoundException;
	public abstract String readPassword(String partitaIVA) throws SQLException, TuplaNotFoundException;
	public abstract void updateStato(String partitaIVA, String statoToUpdate) throws SQLException, InsertFailedException;
	public abstract ArrayList<Azienda> readDisponibili() throws SQLException;
	public abstract boolean isNewKey(String partitaIVA) throws SQLException;
}
