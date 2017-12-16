package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.RichiestaTirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto RichiestaTirocinio dal DB.
 */
public class RichiestaTirocinioDAO extends AbstractRichiestaTirocinioManager {

	@Override
	public void create(RichiestaTirocinio toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio)
			throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RichiestaTirocinio toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio)
			throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteStudente(String usernameStudente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteAzienda(String partitaIVA) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteTutor(String usernameProfessore) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteInAttesaResponsabileApprovazione() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
