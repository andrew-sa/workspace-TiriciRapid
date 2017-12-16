package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Tirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Tirocinio dal DB.
 */
public class TirocinioDAO extends AbstractTirocinioManager {

	@Override
	public void create(Tirocinio toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Tirocinio toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
