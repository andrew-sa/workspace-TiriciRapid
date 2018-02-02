package it.tirocirapid.test.unit;


import it.tirocirapid.classes.model.Professore;
import junit.framework.TestCase;

public class TestProfessore extends TestCase{

	protected Professore p;
	
	protected void setUp() {
		p=new Professore("username","password","ambito","emailIstituzionale","nome",
				"cognome","email","telefono","indirizzo","matricola");
	}

	protected void tearDown() {
		p=null;
	}

	public void testGetUsername() {
		assertEquals("username",p.getUsername());
	}
	
	public void testGetPassword() {
		assertEquals("password", p.getPassword());
	}
	
	public void testGetAmbito() {
		assertEquals("ambito",p.getAmbito());
	}
	
	public void testGetEmailIstituzionale() {
		assertEquals("emailIstituzionale", p.getEmailIstituzionale());
	}
	
	public void testGetNome() {
		assertEquals("nome",p.getNome());
	}
	
	public void testGetCognome() {
		assertEquals("cognome", p.getCognome());
	}
	
	public void testGetEmail() {
		assertEquals("email", p.getEmail());
	}
	
	public void testGetTelefono() {
		assertEquals("telefono",p.getTelefono());
	}
	
	public void testGetIndirizzo() {
		assertEquals("indirizzo",p.getIndirizzo());
	}
	
	public void testGetMatricola() {
		assertEquals("matricola",p.getMatricola());
	}
	
	public void testSetUsername() {
		p.setUsername("pippo");
		assertEquals("pippo", p.getUsername());
	}
	
	public void testSetPassword() {
		p.setPassword("pippo");
		assertEquals("pippo", p.getPassword());
	}
	
	public void testSetAmbito() {
		p.setAmbito("pippo");
		assertEquals("pippo", p.getAmbito());
	}
	
	public void testSetEmailIstituzionale() {
		p.setEmailIstituzionale("pippo");
		assertEquals("pippo", p.getEmailIstituzionale());
	}
	
	public void testSetNome() {
		p.setNome("pippo");
		assertEquals("pippo", p.getNome());
	}
	
	public void testSetCognome() {
		p.setCognome("pippo");
		assertEquals("pippo", p.getCognome());
	}
	
	public void testSetEmail() {
		p.setEmail("pippo");
		assertEquals("pippo", p.getEmail());
	}
	
	public void testSetTelefono() {
		p.setTelefono("pippo");
		assertEquals("pippo", p.getTelefono());
	}
	
	public void testSetIndirizzo() {
		p.setIndirizzo("pippo");
		assertEquals("pippo", p.getIndirizzo());
	}
	
	public void testSetMatricola() {
		p.setMatricola("pippo");
		assertEquals("pippo", p.getMatricola());
	}
}
