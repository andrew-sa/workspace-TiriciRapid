package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Tirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractTirocinioManager {
	
	public abstract void create(Tirocinio toCreate) throws SQLException;
	public abstract Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException;
	public abstract void update(Tirocinio toUpdate) throws SQLException, TuplaNotFoundException;
	public abstract void delete(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException;
	public abstract ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException;

}
