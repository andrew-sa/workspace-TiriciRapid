package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.business.RichiestaTirocinio;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto RichiestaTirocinio dal DB.
 */
public class RichiestaTirocinioDAO extends AbstractRichiestaTirocinioManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un Richiesta di Tirocinio
	 * @param toCreate la RichiestaTirocinio da inserire
	 * @throws SQLException
	 */
	@Override
	public void create(RichiestaTirocinio toCreate) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare una richiesta di tirocinio
	 * @param usernameStudente l'username dello studente
	 * @param partitaIVAAzienda la partita iva dell'azienda 
	 * @param nomeTirocinio il nome del tirocinio
	 * @return RichiestaTirocinio la richiesta di tirocinio cercata
	 * @throws TuplaNotFoundException la richiesta cercata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio)
			throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per la modifica di una richiesta di tirocinio
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(RichiestaTirocinio toUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per la cancellazione di una richiesta 
	 * @param usernameStudente l'username dello studente
	 * @param partitaIVAAzienda la partita iva dell'azienda 
	 * @param nomeTirocinio il nome del tirocinio
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio)
			throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte le richieti di tirocinio presenti per un determinato studente
	 * @param usernameStudente l'username dello studente
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB di uno studente 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteStudente(String usernameStudente) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte le richieti di tirocinio presenti per una determinata azienda
	 * @param partitaIVA la partita iva dell'azienda 
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB di un'azienda
	 * @throws SQLException
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteAzienda(String partitaIVA) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte le richieti di tirocinio presenti per una determinato tutor
	 * @param  usernameProfessore rappresenta l'username del professore
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB di un tutor
	 * @throws SQLException
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteTutor(String usernameProfessore) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte le richieti del responsabile approvazione
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB del responsabile approvazione
	 * @throws SQLException
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteInAttesaResponsabileApprovazione() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
