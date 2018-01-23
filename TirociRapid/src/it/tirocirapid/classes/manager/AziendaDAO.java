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
	 * Si occupa dell'interrogazione al database per verificare se l'azienda con quella partitaIVA e password è presente nel DB
	 * @param partitaIVA rappresenta la partitaIVA dello azienda da cercare
	 * @param password rappresenta la password dello azienda da cercare
	 * @return true se esiste un'azienda avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un'azienda avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste un'azienda avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public boolean search(String partitaIVA, String password) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SEARCH);
		ps.setString(1, partitaIVA);
		ps.setString(2, password);
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
	 * Si occupa dell'interrogazione al database per l'inserimento di un azienda
	 * @param toCreate l'azienda da inserire
	 * @throws SQLException
	 * @throws InsertFailedException 
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
				throw new InsertFailedException("Si &egrave; verifacato un errore durante il salvataggio nel database");
			}
		}
		else
		{
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new DuplicateKeyException("La partitaIVA inserita &egrave; gi&agrave; presente nel database");
		}
		
	}
	
	/**
	 * 
	 * @param partitaIVA la partitaIVA dell'azienda da inserire
	 * @param con la connessione al database
	 * @throws SQLException
	 */
	private static boolean isNewKey(String partitaIVA, Connection con) throws SQLException
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
	 * Si occupa dell'interrogazione al database per ricavare un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda da ricavare
	 * @return Azienda rappresenta l'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Azienda read(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per la modifica di un'azienda
	 * @param toUpdate l'azienda modificata da inserire all'interno del DB
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(Azienda toUpdate) throws SQLException, TuplaNotFoundException
	{
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutte l' aziende presenti 
	 * @return ArrayList<Azienda> rappresenta l'aziende presenti nel DB 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Azienda> readAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare l'email di un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo sapere l'email
	 * @return String rappresenta l'email dell'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
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
			throw new TuplaNotFoundException();
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare la password di un' azienda tramite la partita IVA 
	 * @param partitaIVA la partita IVA dell'azienda di cui vogliamo sapere l'email
	 * @return String rappresenta la password dell'azienda con quella partita IVA 
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
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
			throw new TuplaNotFoundException();
		}
	}

	private static final String SEARCH = "SELECT PartitaIVA, Pass FROM azienda WHERE PartitaIVA = ?";
	private static final String READ_EMAIL = "SELECT Email FROM azienda WHERE PartitaIVA = ?";
	private static final String READ_PASSWORD = "SELECT Pass FROM azienda WHERE PartitaIVA = ?";
	private static final String CREATE = "INSERT INTO azienda(PartitaIVA, Nome, Sede, DescrizioneAmbito, NumeroTelefono, Email, Pass, Stato) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String READ_ALL_KEY = "SELECT PartitaIVA FROM azienda";
}
