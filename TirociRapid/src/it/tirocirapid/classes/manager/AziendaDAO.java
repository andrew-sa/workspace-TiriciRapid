package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.DuplicateKeyException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Azienda dal DB.
 */
public class AziendaDAO extends AbstractAziendaManager {
	
	/**
	 * Verifica se l'azienda con quella partitaIVA e password è presente nel DB
	 * @param partitaIVA rappresenta la partitaIVA dello azienda da cercare
	 * @param password rappresenta la password dello azienda da cercare
	 * @return true se esiste un'azienda avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un'azienda avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste un'azienda avente l'username passato come parametro sul DB
	 * @throws SQLException viene lanciata nel caso in cui c'è un problema con l'iterazione della DB
	 */
	@Override
	public boolean search(String partitaIVA, String password) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SEARCH);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			if (password.equals(rs.getString(2)))
			{
				con.commit();
				rs.close();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				return true;
			}
			else
			{
				con.commit();
				rs.close();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				return false;
			}
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException();
		}
	}
	
	/**
	 * Crea un azienda all'interno del DB
	 * @param toCreate l'azienda da inserire
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 * @throws InsertFailedException viene lanciata nel caso in cui il salvataggio nel DB non avviene con successo
	 */
	@Override
	public void create(Azienda toCreate) throws SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		if (isNewKey(toCreate.getPartitaIVA(), con))
		{
			PreparedStatement ps = con.prepareStatement(CREATE);
			ps.setString(1, toCreate.getPartitaIVA());
			ps.setString(2, toCreate.getNome());
			ps.setString(3, toCreate.getSede());
			ps.setString(4, toCreate.getDescrizioneAmbito());
			ps.setString(5, toCreate.getNumeroTelefono());
			ps.setString(6, toCreate.getEmail());
			ps.setString(7, toCreate.getPassword());
			ps.setString(8, toCreate.getStato());
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
			throw new DuplicateKeyException("La partitaIVA inserita &egrave; gi&agrave; presente nel database");
		}
	}
	
	/**
	 * Verifica se partitaIVA è già presente nel DB
	 * @param partitaIVA la partitaIVA dell'azienda da inserire
	 * @param con la connessione al database
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	private boolean isNewKey(String partitaIVA, Connection con) throws SQLException
	{
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL_KEY);
		while (rs.next())
		{
			if (rs.getString(1).equalsIgnoreCase(partitaIVA))
			{
				con.commit();
				rs.close();
				stm.close();
				return false;
			}
		}
		con.commit();
		rs.close();
		stm.close();
		return true;
	}

	/**
	 * Cerca un' azienda tramite la partita IVA all'interno del DB
	 * @param partitaIVA la partita IVA dell'azienda da ricavare
	 * @return Azienda rappresenta l'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			Azienda azienda = new Azienda();
			azienda.setPartitaIVA(rs.getString(1));
			azienda.setNome(rs.getString(2));
			azienda.setSede(rs.getString(3));
			azienda.setDescrizioneAmbito(rs.getString(4));
			azienda.setNumeroTelefono(rs.getString(5));
			azienda.setEmail(rs.getString(6));
			azienda.setPassword(rs.getString(7));
			azienda.setStato(rs.getString(8));
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return azienda;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("L'azienda cercata non &egrave; presente nel database");
		}
	}

	/**
	 * Modifica un'azienda già presenete nel DB
	 * @param toUpdate l'azienda modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void update(Azienda toUpdate) throws SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1, toUpdate.getNome());
		ps.setString(2, toUpdate.getSede());
		ps.setString(3, toUpdate.getDescrizioneAmbito());
		ps.setString(4, toUpdate.getNumeroTelefono());
		ps.setString(5, toUpdate.getEmail());
		ps.setString(6, toUpdate.getStato());
		ps.setString(7, toUpdate.getPartitaIVA());
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new InsertFailedException("Impossibile salvare le modifiche al curriculum nel database");
		}
	}

	/**
	 * Cerca tutte le aziende presenti nel DB
	 * @return ArrayList<Azienda> rappresenta l'aziende presenti nel DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<Azienda> readAll() throws SQLException
	{
		ArrayList<Azienda> aziende = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL);
		while (rs.next())
		{
			Azienda azienda = new Azienda();
			azienda.setPartitaIVA(rs.getString(1));
			azienda.setNome(rs.getString(2));
			azienda.setSede(rs.getString(3));
			azienda.setDescrizioneAmbito(rs.getString(4));
			azienda.setNumeroTelefono(rs.getString(5));
			azienda.setEmail(rs.getString(6));
			azienda.setPassword(rs.getString(7));
			azienda.setStato(rs.getString(8));
			aziende.add(azienda);
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return aziende;
	}
	
	/**
	 * Cerca nel DB tutte le aziende che accettano richieste di tirocinio 
	 * @return ArrayList<Azienda> rappresenta l'aziende presenti nel DB che accettano richieste di tirocinio
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public ArrayList<Azienda> readDisponibili() throws SQLException
	{
		ArrayList<Azienda> aziende = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_DISPONIBILI);
		while (rs.next())
		{
			Azienda azienda = new Azienda();
			azienda.setPartitaIVA(rs.getString(1));
			azienda.setNome(rs.getString(2));
			azienda.setSede(rs.getString(3));
			azienda.setDescrizioneAmbito(rs.getString(4));
			azienda.setNumeroTelefono(rs.getString(5));
			azienda.setEmail(rs.getString(6));
			azienda.setPassword(rs.getString(7));
			azienda.setStato(rs.getString(8));
			aziende.add(azienda);
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return aziende;
	}

	/**
	 * Cerca l'email di un' azienda tramite la partita IVA presente nel DB
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo sapere l'email
	 * @return String rappresenta l'email dell'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public String readEmail(String partitaIVA) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_EMAIL);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			String email = rs.getString(1);
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return email;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("L'azienda selezionata non &egrave; presente nel database");
		}
	}

	/**
	 * Cerca nel DB la password di un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo sapere l'email
	 * @return String rappresenta la password dell'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public String readPassword(String partitaIVA) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_PASSWORD);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			String password = rs.getString(1);
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return password;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("L'azienda selezionata non &egrave; presente nel database");
		}
	}
	
	
	
	/**
	 * Si occupa di aggiornare lo Stato dell'Azienda 
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo aggiornare l'email
	 * @param statoToUpdate rappresenta lo stato attuale dell'Azienda
	 * @throws InsertFailedException viene se l'update dello stato non avviene con successo 
	 * @throws SQLException viene lanciata nel caso in cui avviene un errore con la DB
	 */
	@Override
	public void updateStato(String partitaIVA, String statoToUpdate) throws SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE_STATO);
		ps.setString(1, statoToUpdate);
		ps.setString(2, partitaIVA);
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new InsertFailedException("Impossibile aggiornare lo stato dell'azienda");
		}
	}

	private static final String SEARCH = "SELECT PartitaIVA, Pass FROM azienda WHERE PartitaIVA = ?";
	private static final String READ = "SELECT * FROM azienda WHERE PartitaIVA = ?";
	private static final String READ_EMAIL = "SELECT Email FROM azienda WHERE PartitaIVA = ?";
	private static final String READ_PASSWORD = "SELECT Pass FROM azienda WHERE PartitaIVA = ?";
	private static final String CREATE = "INSERT INTO azienda(PartitaIVA, Nome, Sede, DescrizioneAmbito, NumeroTelefono, Email, Pass, Stato) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_ALL_KEY = "SELECT PartitaIVA FROM azienda";
	private static final String READ_ALL = "SELECT * FROM azienda";
	private static final String READ_DISPONIBILI = "SELECT * FROM azienda WHERE Stato = \"AccettaRichieste\"";
	private static final String UPDATE = "UPDATE azienda SET Nome = ?, Sede = ?, DescrizioneAmbito = ?, NumeroTelefono = ?, "
			+ "Email = ?, Stato = ? WHERE PartitaIVA = ?";
	private static final String UPDATE_STATO = "UPDATE azienda SET Stato = ? WHERE PartitaIVA = ?";
	
}
