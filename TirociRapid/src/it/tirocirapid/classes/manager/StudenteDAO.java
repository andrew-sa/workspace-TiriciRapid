package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Studente dal DB.
 */
public class StudenteDAO extends AbstractStudenteManager {

	/**
	 * Si occupa dell'interrogazione al database per verificare se lo studente con quel username e password è presente nel DB
	 * @param username rappresenta l'username dello studente da cercare
	 * @param password rappresenta la password dello studente da cercare
	 * @return true se esiste uno studente avente l'username e la password passati come parametri nel DB
	 * @return false se esiste uno studente avente l'username passato come parametro, ma la password non corrisponde
	 * @throws TuplaNotFoundException se non esiste uno studente avente l'username passato come parametro sul DB
	 * @throws SQLException
	 */
	@Override
	public boolean search(String username, String password) throws SQLException, TuplaNotFoundException
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
			throw new TuplaNotFoundException("Lo studente selezionato non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare uno studente tramite l'username
	 * @param username rappresenta l'username dello studente da cercare
	 * @return Studente lo studente cercato
	 * @throws TuplaNotFoundException lo studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Studente read(String username) throws SQLException, TuplaNotFoundException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			Studente studente = new Studente();
			studente.setUsername(rs.getString(1));
			studente.setPassword(rs.getString(2));
			studente.setMatricola(rs.getString(3));
			studente.setNome(rs.getString(4));
			studente.setCognome(rs.getString(5));
			studente.setIndirizzo(rs.getString(6));
			studente.setTelefono(rs.getString(7));
			studente.setEmail(rs.getString(8));
			studente.setEmailIstituzionale(rs.getString(9));
			studente.setMediaVoti(rs.getString(10));
			studente.setIstruzioneFormazione(rs.getString(11));
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			return studente;
		}
		else
		{
			con.commit();
			rs.close();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			throw new TuplaNotFoundException("Lo studente cercato non &egrave; presente nel database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare l'email di uno studente tramite l' username
	 * @param username l'username dello studente di cui vogliamo sapere l'email
	 * @return String rappresenta l'email dello studente con quel username 
	 * @throws TuplaNotFoundException lo studente con l'username specificato non è presente all'interno del DB 
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
			throw new TuplaNotFoundException("Lo studente selezionato non &egrave; presente nel database");
		}
	}
	
	private static final String SEARCH = "SELECT Username, Pass FROM studente WHERE Username = ?";
	private static final String READ = "SELECT * FROM studente WHERE Username = ?";
	private static final String READ_EMAIL = "SELECT EmailIstituzionale FROM studente WHERE Username = ?";
	
}
