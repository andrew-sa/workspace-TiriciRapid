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
	protected Professore q;
	protected Professore w;
	protected String toStringEs;
	
	@BeforeEach
	protected void setUp() {
		p=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		q=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		toStringEs ="Username: username Password: password Ambito: ambito EmailIstituzionale: emailIstituzionale Nome: nome Cognome: cognome Email: email Telefono: telefono Indirizzo: indirizzo Matricola: matricola";
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
	public void testEquals1() {
		boolean x=true;
		assertEquals(x,p.equals(q));
	}
	@Test
	public void testEquals2() {
		boolean x=false;
		assertEquals(x, p.equals(x));
	}
	@Test
	public void testEquals3() {
		boolean x=false;
		w=new Professore("a","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals4() {
		boolean x=false;
		w=new Professore("username","a","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals5() {
		boolean x=false;
		w=new Professore("username","password","a","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals6() {
		boolean x=false;
		w=new Professore("username","password","ambito","a","nome","cognome","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals7() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","a","cognome","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals8() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","nome","a","email","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals9() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","a","telefono","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals10() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","a","indirizzo","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals11() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","a","matricola");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testEquals12() {
		boolean x=false;
		w=new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","a");
		assertEquals(x, p.equals(w));
	}
	@Test
	public void testToString() {
		assertEquals(toStringEs, p.toString());
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
