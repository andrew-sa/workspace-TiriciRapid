package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractTirocinioManager {
	
	public abstract void create(Tirocinio toCreate) throws SQLException, InsertFailedException;
	public abstract Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException;
	public abstract void update(String partitaIVA, String nome, String statoToUpdate) throws SQLException, TuplaNotFoundException;
	public abstract void delete(String partitaIVA, String nome) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException;
	public abstract ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException;
	public abstract int countByAzienda(String partitaIVA) throws SQLException;
	public abstract ArrayList<Tirocinio> readAllTirociniDisponibiliAzienda(String partitaIVA, String stato) throws SQLException;
	
}
