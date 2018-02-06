package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.DuplicateKeyException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Tirocinio dal DB.
 */
public class TirocinioDAO extends AbstractTirocinioManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un tirocinio
	 * @param toCreate il tirocinio da inserire
	 * @throws SQLException
	 * @throws InsertFailedException 
	 */
	@Override
	public void create(Tirocinio toCreate) throws SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		if (isNewKey(toCreate.getPartitaIVAAzienda(), toCreate.getNome(), con))
		{
			PreparedStatement ps = con.prepareStatement(CREATE);
			ps.setString(1, toCreate.getPartitaIVAAzienda());
			ps.setString(2, toCreate.getNome());
			ps.setString(3, toCreate.getDescrizione());
			ps.setString(4, toCreate.getOffertaFormativa());
			ps.setString(5, toCreate.getStato());
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
			throw new DuplicateKeyException("L'azienda propone gi&agrave; un tirocinio con il nome inserito");
		}
	}
	
	/**
	 * 
	 * @param partitaIVA la partitaIVA dell'azienda che propone il tirocinio
	 * @param nome il nome del tirocinio da salvare
	 * @param con la connessione al database
	 * @throws SQLException
	 */
	private static boolean isNewKey(String partitaIVA, String nome, Connection con) throws SQLException
	{
		PreparedStatement ps = con.prepareStatement(READ_ALL_KEY_BY_AZIENDA);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			if (nome.equals(rs.getString(1)))
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
	 * Si occupa dell'interrogazione al database per ricavare un tirocinio tramite la partita IVA e il nome 
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @return Tirocinio il tirocinio cercato
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, partitaIVA);
		ps.setString(2, nome);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			Tirocinio tirocinio = new Tirocinio();
			tirocinio.setNome(nome);
			tirocinio.setPartitaIVAAzienda(partitaIVA);
			tirocinio.setStato(rs.getString(3));
			tirocinio.setOffertaFormativa(rs.getString(4));
			tirocinio.setDescrizione(rs.getString(5));
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return tirocinio;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Il tirocinio selezionato non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per la modificare lo stoto del tirocinio
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @param statoToUpdate il nuovo stato della proposta di tirocinio
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(String partitaIVA, String nome, String statoToUpdate) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(UPDATE);
		ps.setString(1, statoToUpdate);
		ps.setString(2, partitaIVA);
		ps.setString(3, nome);
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new TuplaNotFoundException("Il tirocinio selezionato non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa di eliminare un tirocinio dal DB
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @throws SQLException
	 * @throws InsertFailedException il tirocinio non è presente all'interno del DB, quindi l'eliminazione fallisce
	 */
	@Override
	public void delete(String partitaIVA, String nome) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();	
		PreparedStatement ps = con.prepareStatement(DELETE);
		ps.setString(1, nome);
		ps.setString(2, partitaIVA);
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new InsertFailedException("La richiesta di tirocinio selezionata non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutti i tirocini di un azienda 
	 * @param partitaIVA la partita IVA dell'azienda
	 * @return ArrayList<Tirocinio> rappresenta i tirocini presenti nel DB di un azienda 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException
	{
		ArrayList<Tirocinio> tirocini = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_BY_AZIENDA);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			Tirocinio tirocinio = new Tirocinio();
			tirocinio.setNome(rs.getString(1));
			tirocinio.setPartitaIVAAzienda(rs.getString(2));
			tirocinio.setStato(rs.getString(3));
			tirocinio.setOffertaFormativa(rs.getString(4));
			tirocinio.setDescrizione(rs.getString(5));
			tirocini.add(tirocinio);
		}
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return tirocini;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per ricavare tutti i tirocini di un azienda 
	 * @param partitaIVA la partita IVA dell'azienda
	 * @return ArrayList<Tirocinio> rappresenta i tirocini presenti nel DB di un azienda 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Tirocinio> readAllTirociniDisponibiliAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException
	{
		ArrayList<Tirocinio> tirocini = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_ALL_DISPONIBILI_BY_AZIENDA);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		
		if(!rs.next()) 
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Lo studente selezionato non &egrave; presente nel database");
		}
		
		do 
		{
			Tirocinio tirocinio = new Tirocinio();
			tirocinio.setNome(rs.getString(1));
			tirocinio.setPartitaIVAAzienda(rs.getString(2));
			tirocinio.setStato(rs.getString(3));
			tirocinio.setOffertaFormativa(rs.getString(4));
			tirocinio.setDescrizione(rs.getString(5));
			tirocini.add(tirocinio);
		} 
		while (rs.next());
		
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return tirocini;
	}
	
	/**
	 * Si occupa dell'interrogazione al database per ricavare tutti i tirocini di un azienda 
	 * @param partitaIVA la partita IVA dell'azienda
	 * @return ArrayList<Tirocinio> rappresenta i tirocini presenti nel DB di un azienda 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Tirocinio> readAllTirociniInAttesaApprovazione() throws SQLException
	{
		ArrayList<Tirocinio> tirocini = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL_IN_ATTESA_APPROVAZIONE);
		while (rs.next())
		{
			Tirocinio tirocinio = new Tirocinio();
			tirocinio.setNome(rs.getString(1));
			tirocinio.setPartitaIVAAzienda(rs.getString(2));
			tirocinio.setStato(rs.getString(3));
			tirocinio.setOffertaFormativa(rs.getString(4));
			tirocinio.setDescrizione(rs.getString(5));
			tirocini.add(tirocinio);
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return tirocini;
	}
	
	@Override
	public int countByAzienda(String partitaIVA) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(COUNT_BY_AZIENDA);
		ps.setString(1, partitaIVA);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		con.commit();
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return count;
	}
	
	private static final String CREATE = "INSERT INTO tirocinio(PartitaIVA, Nome, Descrizione, OffertaFormativa, Stato) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_ALL_KEY_BY_AZIENDA = "SELECT Nome FROM tirocinio WHERE PartitaIVA = ?";
	private static final String READ = "SELECT * FROM tirocinio WHERE PartitaIVA = ? AND Nome = ?";
	private static final String UPDATE = "UPDATE tirocinio SET Stato = ? WHERE PartitaIVA = ? AND Nome = ?";
	private static final String DELETE = "DELETE FROM tirocinio WHERE Nome = ? AND PartitaIVA";
	private static final String READ_ALL_BY_AZIENDA = "SELECT * FROM tirocinio WHERE PartitaIVA = ?";
	private static final String READ_ALL_DISPONIBILI_BY_AZIENDA = "SELECT * FROM tirocinio WHERE PartitaIVA = ? AND Stato = \"TirConf\"";
	private static final String READ_ALL_IN_ATTESA_APPROVAZIONE = "SELECT * FROM tirocinio WHERE PartitaIVA = ? AND Stato = \"TirProp\"";
	private static final String COUNT_BY_AZIENDA = "SELECT COUNT(*) FROM richiestatirocinio WHERE PartitaIVA = ?";
	
}
