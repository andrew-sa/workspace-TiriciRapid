package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.eccezioni.BadRequestTirocinioException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public abstract class AbstractRichiestaTirocinioManager {
	
	public abstract void create(RichiestaTirocinio toCreate, HashMap<Integer, String> states) throws SQLException, BadRequestTirocinioException, InsertFailedException;
	public abstract RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws SQLException, TuplaNotFoundException;
	public abstract void updateStato(RichiestaTirocinio toUpdate) throws SQLException, InsertFailedException;
	public abstract void updateAddTutor(RichiestaTirocinio toUpdate) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException;
	public abstract void updateRemoveTutor(RichiestaTirocinio toUpdate) throws SQLException, InsertFailedException;
	public abstract void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws MySQLIntegrityConstraintViolationException, SQLException, TuplaNotFoundException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteStudente(String usernameStudente) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteAzienda(String partitaIVA) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteTutor(String usernameProfessore) throws SQLException;
	public abstract ArrayList<RichiestaTirocinio> readAllRichiesteInAttesaResponsabileApprovazione() throws SQLException;

}
