package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

/**
 * Model che contiene le query per la creazione, lettura, update e cancellazione dell'oggetto Curriculum dal DB.
 */
public class CurriculumDAO extends AbstractCurriculumManager {

	/**
	 * Si occupa dell'interrogazione al database per l'inserimento di un Curriculum nel DB
	 * @param toCreate il Curriculum da inserire
	 * @param usernameStudente l'username dello studente a cui appartiene il curriculum
	 * @throws SQLException
	 */
	@Override
	public void create(Curriculum toCreate, String usernameStudente) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare un'curriculum tramite l'username dello studente
	 * @param L'username l'username dello Studente
	 * @return Curriculum il curriculum dello studente 
	 * @throws TuplaNotFoundException il curriculum di quello studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Curriculum read(String username) throws SQLException, TuplaNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Si occupa dell'interrogazione al database per modificare un curriculum
	 * @param toUpdate il curriculum modificato
	 * @throws InsertFailedException il curriculum di quello studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public void update(Curriculum toUpdate) throws SQLException, InsertFailedException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Si occupa di verificare se per lo studente è presente nel database il curriculum
	 * @param username l'username dello studente
	 * @throws SQLException
	 */
	@Override
	public boolean search(String username) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(SEARCH);
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next())
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
	
	private static final String SEARCH = "SELECT * FROM curriculum WHERE Username = ?";

}
