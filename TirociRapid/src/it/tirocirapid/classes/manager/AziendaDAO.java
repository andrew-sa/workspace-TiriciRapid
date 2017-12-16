package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.Azienda;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Azienda dal DB.
 */
public class AziendaDAO extends AbstractAziendaManager {

	@Override
	public void create(Azienda toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Azienda toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Azienda> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String readEmail(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
