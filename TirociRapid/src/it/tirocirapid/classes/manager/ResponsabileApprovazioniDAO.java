package it.tirocirapid.classes.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import it.tirocirapid.database.DriverManagerConnectionPool;

public class ResponsabileApprovazioniDAO extends AbstractResponsabileApprovazioniManager {

	/**
	 * Si occupa di recupare le email di tutti i responsabili approvazioni dal DB
	 * @return ArrayList<String> rappresenta l'email dei responsabili approvazioni
	 * @throws SQLException
	 */
	@Override
	public ArrayList<String> readEmailAll() throws SQLException
	{
		ArrayList<String> emails = new ArrayList<>();
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery(READ_ALL_EMAIL);
		while (rs.next())
		{
			emails.add(rs.getString(1));
		}
		con.commit();
		rs.close();
		stm.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return emails;
	}
	
	private static final String READ_ALL_EMAIL = "SELECT Email FROM professore WHERE Tipo = \"ResponsabileApprovazioni\"";

}
