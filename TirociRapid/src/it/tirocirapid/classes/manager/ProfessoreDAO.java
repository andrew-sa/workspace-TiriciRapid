package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Professore dal DB.
 */
public class ProfessoreDAO extends AbstractProfessoreManager {

	/**
	 * Si occupa dell'interrogazione al database per verificare se il professore con quel username e password è presente nel DB
	 * @param username rappresenta l'username del professore da cercare
	 * @param password rappresenta la password del professore da cercare
	 * @return true se esiste un professore avente l'username e la password passati come parametri nel DB
	 * @return false se esiste un professore avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste un professore avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public int search(String username, String password) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SEARCH);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			if (password.equals(rs.getString(2)))
			{
				if ("ResponsabileApprovazioni".equals(rs.getString(3)))
				{
					con.commit();
					rs.close();
					ps.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
					return 2;
				}
				else
				{
					con.commit();
					rs.close();
					ps.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
					return 1;
				}
			}
			else
			{
				con.commit();
				rs.close();
				ps.close();
				DriverManagerConnectionPool.getIstance().releaseConnection(con);
				return 0;
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
	 * Si occupa dell'interrogazione al database per ricavare il professore tramite l'username
	 * @param username rappresenta l'username del professore da cercare
	 * @return Professore il professore cercato
	 * @throws TuplaNotFoundException il professore non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Professore read(String username) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			Professore professore = new Professore();
			professore.setUsername(username);
			professore.setNome(rs.getString(1));
			professore.setCognome(rs.getString(2));
			professore.setEmailIstituzionale(rs.getString(3));
			professore.setTelefono(rs.getString(4));
			professore.setAmbito(rs.getString(5));
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return professore;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Il professore selezionato non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per caricare tutti i professori presenti
	 * @return ArrayList<Professore> rappresentano tutti i professori 
	 * @throws SQLException
	 */
	@Override
	public ArrayList<Professore> readAll() throws SQLException
	{
		ArrayList<Professore> professori = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL);
		while (rs.next())
		{
			Professore professore = new Professore();
			professore.setNome(rs.getString(1));
			professore.setCognome(rs.getString(2));
			professore.setEmailIstituzionale(rs.getString(3));
			professore.setTelefono(rs.getString(4));
			professore.setAmbito(rs.getString(5));
			professori.add(professore);
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return professori;
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare l'email di un professore tramite l'username 
	 * @param username l'username del professore di cui vogliamo sapere l'email
	 * @return String rappresenta l'email del professore con quel username
	 * @throws TuplaNotFoundException l'azienda con la partita IVA specificata non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public String readEmail(String username) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ_EMAIL);
		ps.setString(1, username);
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
			throw new TuplaNotFoundException("Il professore selezionato non &egrave; presente nel database");
		}
	}
	
	private static final String SEARCH = "SELECT Username, Pass, Tipo FROM profesore WHERE Username = ?";
	private static final String READ = "SELECT Nome, Cognome, EmailIstituzionale, Telefono, Ambito FROM professore WHERE Username = ?";
	private static final String READ_ALL = "SELECT Nome, Cognome, EmailIstituzionale, Telefono, Ambito FROM professore";
	private static final String READ_EMAIL = "SELECT EmailIstituzionale FROM professore WHERE Username = ?";
}
