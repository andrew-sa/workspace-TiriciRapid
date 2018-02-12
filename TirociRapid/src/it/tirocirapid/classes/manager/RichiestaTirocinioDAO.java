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
	
	public RichiestaTirocinioDAO()
	{
		statesReqTir = new HashMap<>();
    	statesReqTir.put(1, "ConfAz");
    	statesReqTir.put(2, "ScelTut");
    	statesReqTir.put(3, "ConfTut");
    	statesReqTir.put(4, "ConfResp");
    	statesReqTir.put(5, "Acc");
    	statesReqTir.put(-4, "RifResp");
    	statesReqTir.put(-3, "RifTut");
    	statesReqTir.put(-1, "RifAz");
	}

	/**
	 * Crea una Richiesta di Tirocinio all'interno del DB
	 * @param toCreate la RichiestaTirocinio da inserire
	 * @param states i possibili stati della richiesta di tiroicnio
	 * @throws BadRequestTirocinioException eccezione che si verifica se lo studente che vuole effettuare la richiesta di tirocinio ha una richiesta di tirocinio già approvata o in fase di approvazione
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 * @throws InsertFailedException viene lanciata nel caso in cui non avviene con successo il salvtaggio nel DB
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
	 * Controlla se lo studente ha già una richiesta di tirocinio in fase di validazione
	 * @param username l'username dello studente che sta effettuando la richiesta
	 * @param states i possibili stati della richiesta di tiroicnio
	 * @param con la connessione al database
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	private boolean isGoodRequest(String username, HashMap<Integer, String> states, Connection con) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement(READ_ALL_STATES_BY_STUDENTE);
		ps.setString(1, username);
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
	 * Cerca nel DB una richiesta di tirocinio
	 * @param usernameStudente l'username dello studente
	 * @param partitaIVAAzienda la partita iva dell'azienda 
	 * @param nomeTirocinio il nome del tirocinio
	 * @return RichiestaTirocinio la richiesta di tirocinio cercata
	 * @throws TuplaNotFoundException la richiesta cercata non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public RichiestaTirocinio read(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, nomeTirocinio);
		ps.setString(2, partitaIVAAzienda);
		ps.setString(3, usernameStudente);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			RichiestaTirocinio reqTir = new RichiestaTirocinio();
			reqTir.setTirocinio(rs.getString(2), rs.getString(1));
			reqTir.setStudente(rs.getString(4));
			reqTir.setStato(rs.getString(3));
			reqTir.setTutorInterno(readTutorInterno(reqTir));
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return reqTir;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("La richiesta di tirocinio selezionata non &egrave; presente nel database");
		}
	}

	/**
	 * Modifica lo stato di una richiesta di tirocinio presente nel DB
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void updateStato(RichiestaTirocinio toUpdate) throws SQLException, InsertFailedException
	{	
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1, toUpdate.getStato());
		ps.setString(2, toUpdate.getTirocinio().getNome());
		ps.setString(3, toUpdate.getTirocinio().getPartitaIVAAzienda());
		ps.setString(4, toUpdate.getStudente().getUsername());

		int i = ps.executeUpdate();
				
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
		}
	}

	/**
	 * Modifica lo stato di una richiesta di tirocinio presente nel DB
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	private void updateStato(RichiestaTirocinio toUpdate, Connection con) throws SQLException, InsertFailedException
	{	
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1, toUpdate.getStato());
		ps.setString(2, toUpdate.getTirocinio().getNome());
		ps.setString(3, toUpdate.getTirocinio().getPartitaIVAAzienda());
		ps.setString(4, toUpdate.getStudente().getUsername());

		int i = ps.executeUpdate();
				
//		con.commit();
		ps.close();
		if (i != 1)
		{
			throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
		}
	}
	
	/**
	 * Nomina un professore ad una rihiesta di tirocinio
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void updateAddTutor(RichiestaTirocinio toUpdate) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(CREATE_NOMINARE);
		
		System.out.println(toUpdate.getTirocinio().getNome()+toUpdate.getTirocinio().getPartitaIVAAzienda());
		System.out.println(toUpdate.getStudente().getUsername()+toUpdate.getTutorInterno().getUsername());
		
		ps.setString(1, toUpdate.getTirocinio().getNome());
		ps.setString(2, toUpdate.getTirocinio().getPartitaIVAAzienda());
		ps.setString(3, toUpdate.getStudente().getUsername());
		ps.setString(4, toUpdate.getTutorInterno().getUsername());
		int i = ps.executeUpdate();
		
		System.out.println(toUpdate.getStato());
		System.out.println(i);
		
		if (i != 1)
		{
			con.rollback();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
		}
		else
		{
			try
			{
				updateStato(toUpdate, con);
				con.commit();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
			}
			catch (SQLException | InsertFailedException e)	
			{
				con.rollback();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
			}
		}
	}

	
	
	/**
	 * Modifica lo stato di una richiesta di tirocinio presente nel DB
	 * @param toUpdate la richiesta da modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void updateRemoveTutor(RichiestaTirocinio toUpdate) throws SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_NOMINARE);
		ps.setString(1, toUpdate.getTirocinio().getNome());
		ps.setString(2, toUpdate.getTirocinio().getPartitaIVAAzienda());
		ps.setString(3, toUpdate.getStudente().getUsername());
		ps.setString(4, toUpdate.getTutorInterno().getUsername());
		int i = ps.executeUpdate();
		if (i != 1)
		{
			con.rollback();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
		}
		else
		{
			try
			{
				updateStato(toUpdate, con);
				con.commit();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
			}
			catch (SQLException | InsertFailedException e)
			{
				con.rollback();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				throw new InsertFailedException("Impossibile aggiornare la richiesta di tirocinio selezionata");
			}
		}
	}

	/**
	 * Si occupa della cancellazione di una richiesta 
	 * @param usernameStudente l'username dello studente
	 * @param partitaIVAAzienda la partita iva dell'azienda 
	 * @param nomeTirocinio il nome del tirocinio
	 * @throws TuplaNotFoundException la richiesta non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void delete(String usernameStudente, String partitaIVAAzienda, String nomeTirocinio) throws MySQLIntegrityConstraintViolationException, SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(DELETE_NOMINARE_OF_DELETE_RICHIESTA);
		ps.setString(1, nomeTirocinio);
		ps.setString(2, partitaIVAAzienda);
		ps.setString(3, usernameStudente);
		ps.executeUpdate();
		
		ps = con.prepareStatement(DELETE);
		ps.setString(1, nomeTirocinio);
		ps.setString(2, partitaIVAAzienda);
		ps.setString(3, usernameStudente);
		int i = ps.executeUpdate();
		if (i != 1)
		{
			con.rollback();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("La richiesta di tirocinio selezionata non &egrave; presente nel database");
		}
		else
		{
			con.commit();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
		}
	}

	/**
	 * Cerca nel DB tutte le richieste di tirocinio presenti per un determinato studente
	 * @param usernameStudente l'username dello studente
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB di uno studente 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteStudente(String usernameStudente) throws SQLException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_BY_STUDENTE);
		ps.setString(1, usernameStudente);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			RichiestaTirocinio reqTir = new RichiestaTirocinio();
			reqTir.setTirocinio(rs.getString(2), rs.getString(1));
			reqTir.setStato(rs.getString(3));
			reqTir.setStudente(rs.getString(4));
			reqTir.setTutorInterno(readTutorInterno(reqTir));
			richieste.add(reqTir);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}

	/**
	 * Cerca nel tutte le richieste di tirocinio presenti per una determinata azienda in attesa della conferma dell'azienda
	 * @param partitaIVA la partita iva dell'azienda 
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB che rispecchiano le condizioni
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readRichiesteAzienda(String partitaIVA) throws SQLException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_BY_AZIENDA_AND_STATO);
		ps.setString(1, partitaIVA);
		ps.setString(2, statesReqTir.get(1)); //stato == "ConfAz"
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			RichiestaTirocinio reqTir = new RichiestaTirocinio();
			reqTir.setTirocinio(rs.getString(2), rs.getString(1));
			reqTir.setStato(rs.getString(3));
			reqTir.setStudente(rs.getString(4));
			reqTir.setTutorInterno(readTutorInterno(reqTir));
			richieste.add(reqTir);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}
	
	/**
	 * Cerca tutte le richieste di tirocinio presenti per una determinata azienda in attesa di altre approvazioni o approvate
	 * @param partitaIVA la partita iva dell'azienda 
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB che rispecchiano le condizioni
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readStoricoRichiesteAzienda(String partitaIVA) throws SQLException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_BY_AZIENDA);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			if (!rs.getString(3).equals(statesReqTir.get(1))) //stato != "ConfAz"
			{
				RichiestaTirocinio reqTir = new RichiestaTirocinio();
				reqTir.setTirocinio(rs.getString(2), rs.getString(1));
				reqTir.setStato(rs.getString(3));
				reqTir.setStudente(rs.getString(4));
				reqTir.setTutorInterno(readTutorInterno(reqTir));
				richieste.add(reqTir);
			}
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}

	/**
	 * Cerca tutte le richieste di tirocinio che sono in attesa di una scelta da parte del tutor interno specificato
	 * @param  usernameProfessore rappresenta l'username del professore
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB che rispecchiano le condizioni
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 * @throws TuplaNotFoundException se una richiesta di tirocinio dove è presente il professore come tutor interno non è presente sul database (non dovrebbe avvenire mai)
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readRichiesteTutor(String usernameProfessore) throws SQLException, TuplaNotFoundException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_NOMINARE_BY_TUTOR_INTERNO);
		ps.setString(1, usernameProfessore);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			RichiestaTirocinio reqTir = read(rs.getString(3), rs.getString(2), rs.getString(1));
			System.out.println(reqTir.getStato()+"Studente  "+ reqTir.getStudente().getUsername()+"Tirocinio:  "+reqTir.getTirocinio().getNome()+" Professore: "+reqTir.getTutorInterno().getUsername());
			System.out.println(statesReqTir.get(3).charAt(1));
			if (statesReqTir.get(3).equals(reqTir.getStato())) //stato == "ConfTut"
			richieste.add(reqTir);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}
	
	/**
	 * Cerca tutte le richieste di tirocinio presenti per una determinato tutor in attesa di altre approvazioni o approvate
	 * @param  usernameProfessore rappresenta l'username del professore
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB che rispecchiano le condizioni
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 * @throws TuplaNotFoundException se una richiesta di tirocinio dove è presente il professore come tutor interno non è presente sul database (non dovrebbe avvenire mai)
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readStoricoRichiesteTutor(String usernameProfessore) throws SQLException, TuplaNotFoundException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_NOMINARE_BY_TUTOR_INTERNO);
		ps.setString(1, usernameProfessore);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			RichiestaTirocinio reqTir = read(rs.getString(3), rs.getString(2), rs.getString(1));
			if (!statesReqTir.get(3).equals(reqTir.getStato())) //stato != "ConfTut"
			{
				richieste.add(reqTir);
			}
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}

	/**
	 * Cerca tutte le richieste del responsabile approvazione
	 * @return ArrayList<RichiestaTirocinio> rappresenta le richiesti presenti nel DB del responsabile approvazione
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<RichiestaTirocinio> readAllRichiesteInAttesaResponsabileApprovazione() throws SQLException
	{
		ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_FOR_RESPONSABILE_APPROVAZIONI);
		ps.setString(1, statesReqTir.get(4));
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			RichiestaTirocinio reqTir = new RichiestaTirocinio();
			reqTir.setTirocinio(rs.getString(2), rs.getString(1));
			reqTir.setStato(rs.getString(3));
			reqTir.setStudente(rs.getString(4));
			reqTir.setTutorInterno(readTutorInterno(reqTir));
			richieste.add(reqTir);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return richieste;
	}
	
	/**
	 * Cerca un tutor associato a quel tirocinio
	 * @return String l'username del professore associato a quel tirocinio
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	private String readTutorInterno(RichiestaTirocinio reqTir) throws SQLException
	{
		String usernameProfessore = null;
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_NOMINARE);
		ps.setString(1, reqTir.getTirocinio().getNome());
		ps.setString(2, reqTir.getTirocinio().getPartitaIVAAzienda());
		ps.setString(3, reqTir.getStudente().getUsername());
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			usernameProfessore = rs.getString(1);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return usernameProfessore;
	}
	
	/**
	 * Conta la richieste di tirocinio di quella azienda
	 * @return int il numero di tuple che sono state contate
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	public int countByTirocinio(String nomeTirocinio, String partitaIVAAzienda) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(COUNT_BY_TIROCINIO);
		ps.setString(1, nomeTirocinio);
		ps.setString(2, partitaIVAAzienda);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return count;
	}
	
	private static final String CREATE = "INSERT INTO richiestatirocinio(PartitaIVA, Nome,Stato, Username) VALUES (?, ?, ?, ?)";
	private static final String READ_ALL_STATES_BY_STUDENTE = "SELECT Stato FROM richiestatirocinio WHERE Username = ?";
	private static final String DELETE = "DELETE FROM richiestatirocinio WHERE Nome = ? AND PartitaIVA = ? AND Username = ?";
	private static final String DELETE_NOMINARE_OF_DELETE_RICHIESTA = "DELETE FROM nominare WHERE Nome = ? AND PartitaIVA = ? AND UsernameStudente = ?";
	private static final String READ = "SELECT * FROM richiestatirocinio WHERE Nome = ? AND PartitaIVA = ? AND Username = ?";
	private static final String UPDATE = "UPDATE richiestatirocinio SET Stato = ? WHERE Nome = ? AND PartitaIVA = ? AND Username = ?";
	private static final String CREATE_NOMINARE = "INSERT INTO nominare(Nome, PartitaIVA, UsernameStudente, UsernameProfessore) VALUES (?, ?, ?, ?)";
	private static final String DELETE_NOMINARE = "DELETE FROM nominare WHERE Nome = ? AND PartitaIVA = ? AND UsernameStudente = ? AND UsernameProfessore = ?";
	private static final String READ_NOMINARE = "SELECT UsernameProfessore FROM nominare WHERE Nome = ? AND PartitaIVA = ? AND UsernameStudente = ?";
	private static final String READ_ALL_BY_STUDENTE = "SELECT * FROM richiestatirocinio WHERE Username = ?";
	private static final String READ_BY_AZIENDA_AND_STATO = "SELECT * FROM richiestatirocinio WHERE PartitaIVA = ? AND Stato = ?";
	private static final String READ_BY_AZIENDA = "SELECT * FROM richiestatirocinio WHERE PartitaIVA = ?";
	private static final String READ_ALL_NOMINARE_BY_TUTOR_INTERNO = "SELECT * FROM nominare WHERE UsernameProfessore = ?";
	private static final String READ_ALL_FOR_RESPONSABILE_APPROVAZIONI = "SELECT * FROM richiestatirocinio WHERE Stato = ?";
	private static final String COUNT_BY_TIROCINIO = "SELECT COUNT(*) FROM richiestatirocinio WHERE Nome = ? AND PartitaIVA = ?";
	private static HashMap<Integer, String> statesReqTir;
}
