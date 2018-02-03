package it.tirocirapid.test.unit;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public class TestAziendaDAO {
	
	protected AziendaDAO aziendaDAO;
	protected AziendaDAO aziendaDAO2;
	protected Azienda azienda;
	protected Connection con;
	
	@BeforeEach
	public void setUp() throws Exception {
		aziendaDAO=new AziendaDAO();
		aziendaDAO2=new AziendaDAO();
		azienda=new Azienda("partitaIVA","password","email","nome","sede","3333333333",null,"stato","descrizioneAmbito");
		con=DriverManagerConnectionPool.getIstance().getConnection();
	}

	@AfterEach
	public void tearDown() throws Exception {
		azienda=null;
		aziendaDAO=null;
		aziendaDAO2=null;
		con.close();
	}
	
	@Test
	public void testSearch() throws SQLException, TuplaNotFoundException {
		//aziendaDAO2.search("07643520567", "ciao");
		
	}

	@Test
	public void testCreate() throws SQLException, InsertFailedException {
		aziendaDAO.create(azienda);
		/*Verifico nella base di fati se l'ho salvato*/
		/*
		PreparedStatement ps = con.prepareStatement("SELECT PartitaIVA FROM azienda WHERE PartitaIVA = ?");
		ResultSet rs = ps.executeQuery();
		String aaa=rs.getString("PartitaIVA");
        assertEquals("partitaIVA",aaa);*/
	    	
	}
	
	@Test
	public void testIsNewKey() {
		
	}
	
	@Test
	public void testRead() {
		
	}
	
	
	@Test
	public void testUpdate() {
		
	}
	
	@Test
	public void testReadAll() {
		
	}
	
	@Test
	public void testReadEmail() {
		
	}
	
	@Test
	public void testReadPassword() {
		
	}
	
	@Test
	public void testUpdateStato() {
		
	}

}
