package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.BadRequestTirocinioException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

import java.util.HashMap;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto RichiestaTirocinio dal DB.
 */
public class RichiestaTirocinioDAO extends AbstractRichiestaTirocinioManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un Richiesta di Tirocinio
	 * @param toCreate la RichiestaTirocinio da inserire
	 * @param states i possibili stati della richiesta di tiroicnio
	 * @throws BadRequestTirocinioException eccezione che si verifica se lo studente che vuole effettuare la richiesta di tirocinio ha una richiesta di tirocinio già approvata o in fase di approvazione
	 * @throws SQLException
	 * @throws InsertFailedException 
	 */
	@Override
	public void create(RichiestaTirocinio toCreate, HashMap<Integer, String> states) throws SQLException, BadRequestTirocinioException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		if (isGoodRequest(toCreate.getStudente().getUsername(), states, con))
		{
			
			PreparedStatement ps = con.prepareStatement(CREATE);
			ps.setString(1, toCreate.getTirocinio().getPartitaIVAAzienda());
			ps.setString(2, toCreate.getTirocinio().getNome());
			ps.setString(3, states.get(1));
			ps.setString(4, toCreate.getStudente().getUsername());
			int i = ps.executeUpdate();
			con.commit();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			if (i != 1)
			{
				throw new InsertFailedException("Si &egrave; verifacato un errore durante il salvataggio sul database");
			}
		}
		else
		{
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new BadRequestTirocinioException("Hai gi&agrave; una richiesta di tirocinio in fase di convalidazione");
		}
	}
	
	/**
	 * 
	 * @param username l'username dello studente che sta effettuando la richiesta
	 * @param states i possibili stati della richiesta di tiroicnio
	 * @param con la connessione al database
	 * @throws SQLException
	 */
	private static boolean isGoodRequest(String usename, HashMap<Integer, String> states, Connection con) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement(READ_ALL_STATES_BY_STUDENTE);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			if (!(rs.getString(1).equals(states.get(-1)) || rs.getString(1).equals(states.get(-4))))
			{
				con.commit();
				rs.close();
				ps.close();
				return false;
			}
		}
		con.commit();
		rs.close();
		ps.close();
		return true;
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
	public RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws SQLException, TuplaNotFoundException
	{
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per la modifica di una richiesta di tirocinio
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(RichiestaTirocinio toUpdate) throws MySQLIntegrityConstraintViolationException, SQLException, TuplaNotFoundException
	{
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
	public void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws MySQLIntegrityConstraintViolationException, SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();	
		PreparedStatement ps = con.prepareStatement(DELETE);
		ps.setString(1, nomeTirocinio);
		ps.setString(2, partitaIVAAzienda);
		ps.setString(3, usernameStudente);
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new TuplaNotFoundException("La richiesta di tirocinio selezionata non &egrave; presente nel database");
		}
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
	
	private static final String CREATE = "INSERT INTO richiestatirocinio(Nome, PartitaIVA, Stato, Username) VALUES (?, ?, ?, ?)";
	private static final String READ_ALL_STATES_BY_STUDENTE = "SELECT Stato FROM richiestatirocinio WHERE Studente = ?";
	private static final String DELETE = "DELETE FROM richiestatirocinio WHERE Nome = ? AND PartitaIVA = ? AND Username = ?";

}
