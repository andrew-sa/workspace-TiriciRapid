package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.RichiestaTirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractRichiestaTirocinioManager {
	
	public abstract void create(RichiestaTirocinio toCreate) throws SQLException;
	public abstract RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws SQLException, TuplaNotFoundException;
	public abstract void update(RichiestaTirocinio toUpdate) throws SQLException, TuplaNotFoundException;
	public abstract void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws SQLException, TuplaNotFoundException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteStudente(String usernameStudente) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteAzienda(String partitaIVA) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteTutor(String usernameProfessore) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteInAttesaResponsabileApprovazione() throws SQLException;

}
