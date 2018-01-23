package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				throw new InsertFailedException("Si &egrave; verifacato un errore durante il salvataggio nel database");
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
	public Tirocinio read(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
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
	public void update(String partitaIVA, String nome, String statoToUpdate) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa di eliminare un tirocinio dal DB
	 * @param partitaIVA la partita IVA dell'azienda 
	 * @param nome il nome del tirocinio
	 * @throws TuplaNotFoundException il tirocinio non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void delete(String partitaIVA, String nome) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare tutti i tirocini di un azienda 
	 * @param partitaIVA la partita IVA dell'azienda
	 * @return ArrayList<Tirocinio> rappresenta i tirocini presenti nel DB di un azienda 
	 * @throws TuplaNotFoundException l'azienda con quella partita IVA non è presente all'interno del DB
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Tirocinio> readAllTirociniAzienda(String partitaIVA) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static final String CREATE = "INSERT INTO tirocinio(PartitaIVA, Nome, Descirzione, OffertaFormativa, Stato) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_ALL_KEY_BY_AZIENDA = "SELECT Nome FROM azienda WHERE PartitaIVA = ?";

}
