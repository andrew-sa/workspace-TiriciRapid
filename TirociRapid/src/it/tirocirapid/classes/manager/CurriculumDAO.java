package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

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
	public void create(Curriculum toCreate, String usernameStudente) throws MySQLIntegrityConstraintViolationException, SQLException, InsertFailedException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(CREATE);
		ps.setString(1, usernameStudente);
		ps.setString(2, toCreate.getFax());
		ps.setString(3, toCreate.getCapacitaCompetenzeRelazionali());
		ps.setString(4, toCreate.getCapacitaCompetenzeTecniche());
		ps.setString(5, toCreate.getCapacitaCompetenzeArtistiche());
		ps.setString(6, toCreate.getCapacitaCompetenzePersonali());
		ps.setString(7, toCreate.getCapacitaCompetenzeOrganizzative());
		ps.setString(8, toCreate.getAltreCapacitaCompetenze());
		ps.setString(9, toCreate.getEsperienzaLavorativa());
		ps.setString(10, toCreate.getMadrelingua());
		ps.setString(11, toCreate.getAltreLingue());
		ps.setString(12, toCreate.getPatenti());
		ps.setString(13, toCreate.getUlterioriInformazioni());
		int i = ps.executeUpdate();
		con.commit();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		if (i != 1)
		{
			throw new InsertFailedException("Si &egrave; verifacato un errore durante il salvataggio sul database");
		}
	}

	/**
	 * Si occupa dell'interrogazione al database per ricavare un'curriculum tramite l'username dello studente
	 * @param L'username l'username dello Studente
	 * @return Curriculum il curriculum dello studente 
	 * @throws TuplaNotFoundException il curriculum di quello studente non è presente all'interno del DB 
	 * @throws SQLException
	 */
	@Override
	public Curriculum read(String usernameStudente) throws SQLException, TuplaNotFoundException
	{
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
	public void update(Curriculum toUpdate, String usernameStudente) throws SQLException, InsertFailedException
	{
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
	
	private static final String CREATE = "INSERT INTO curriculum VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SEARCH = "SELECT * FROM curriculum WHERE Username = ?";

}
