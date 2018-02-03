package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
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
		fail("Not yet implemented");
		//aziendaDAO.create(azienda);
		/*Verifico nella base di fati se l'ho salvato*/
		/*
		PreparedStatement ps = con.prepareStatement("SELECT PartitaIVA FROM azienda WHERE PartitaIVA = ?");
		ResultSet rs = ps.executeQuery();
		String aaa=rs.getString("PartitaIVA");
        assertEquals("partitaIVA",aaa);*/
	    	
	}
	
	@Test
	public void testIsNewKey() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testRead() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadEmail() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadPassword() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateStato() {
		fail("Not yet implemented");
	}

}
