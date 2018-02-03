package it.tirocirapid.test.unit;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Professore;
import junit.framework.TestCase;

public class TestProfessore extends TestCase{

	protected Professore pUser;
	protected Professore pVuoto;
	protected Professore p;
	
	@BeforeEach
	protected void setUp() {
		p=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		pVuoto= new Professore();
		pUser= new Professore("username");
	}

	@AfterEach
	protected void tearDown() {
		p=null;
		pVuoto=null;
		pUser=null;
	}

	@Test
	public void testGetUsername() {
		assertEquals("username",p.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("password", p.getPassword());
	}
	
	@Test
	public void testGetAmbito() {
		assertEquals("ambito",p.getAmbito());
	}
	
	@Test
	public void testGetEmailIstituzionale() {
		assertEquals("emailIstituzionale", p.getEmailIstituzionale());
	}
	
	@Test
	public void testGetNome() {
		assertEquals("nome",p.getNome());
	}
	
	@Test
	public void testGetCognome() {
		assertEquals("cognome", p.getCognome());
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("email", p.getEmail());
	}
	
	@Test
	public void testGetTelefono() {
		assertEquals("telefono",p.getTelefono());
	}
	
	@Test
	public void testGetIndirizzo() {
		assertEquals("indirizzo",p.getIndirizzo());
	}
	
	@Test
	public void testGetMatricola() {
		assertEquals("matricola",p.getMatricola());
	}
	
	@Test
	public void testSetUsername() {
		p.setUsername("pippo");
		assertEquals("pippo", p.getUsername());
	}
	
	@Test
	public void testSetPassword() {
		p.setPassword("pippo");
		assertEquals("pippo", p.getPassword());
	}
	
	@Test
	public void testSetAmbito() {
		p.setAmbito("pippo");
		assertEquals("pippo", p.getAmbito());
	}
	
	@Test
	public void testSetEmailIstituzionale() {
		p.setEmailIstituzionale("pippo");
		assertEquals("pippo", p.getEmailIstituzionale());
	}
	
	@Test
	public void testSetNome() {
		p.setNome("pippo");
		assertEquals("pippo", p.getNome());
	}
	
	@Test
	public void testSetCognome() {
		p.setCognome("pippo");
		assertEquals("pippo", p.getCognome());
	}
	
	@Test
	public void testSetEmail() {
		p.setEmail("pippo");
		assertEquals("pippo", p.getEmail());
	}
	
	@Test
	public void testSetTelefono() {
		p.setTelefono("pippo");
		assertEquals("pippo", p.getTelefono());
	}
	
	@Test
	public void testSetIndirizzo() {
		p.setIndirizzo("pippo");
		assertEquals("pippo", p.getIndirizzo());
	}
	
	@Test
	public void testSetMatricola() {
		p.setMatricola("pippo");
		assertEquals("pippo", p.getMatricola());
	}
}
