/**
 * 
 */
package it.tirocirapid.test.unit;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

/**
 * @author Antonino
 *
 */
public class TestAzienda extends TestCase{
	
	protected Azienda aVuoto;
	protected Azienda a;
	protected ArrayList<Tirocinio> array;
	protected Tirocinio t;
	
	@BeforeEach
	protected void setUp(){
		aVuoto= new Azienda();
		a=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",array,"stato","descrizioneAmbito");
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
	//	a.aggiungiTirocinio(t);
	}

	@AfterEach
	protected void tearDown(){
		aVuoto=null;
		a=null;
		t=null;
	}
	
	@Test 
	public void testGetPartitaIVA() {
		assertEquals("partitaIVA", a.getPartitaIVA());
	}
	
	@Test 
	public void testGetPassword() {
		assertEquals("password", a.getPassword());
	}
	
	@Test 
	public void testGetEmail() {
		assertEquals("email", a.getEmail());
	}
	
	@Test 
	public void testGetNome() {
		assertEquals("nome", a.getNome());
	}
	
	@Test 
	public void testGetSede() {
		assertEquals("sede", a.getSede());
	}
	
	@Test 
	public void testGetNumeroTelefono() {
		assertEquals("numeroTelefono", a.getNumeroTelefono());
	}
	
	@Test 
	public void testGetTirociniOfferti() {
		assertSame(array,a.getTirociniOfferti());
	}
	
	@Test 
	public void testGetStato() {
		assertEquals("stato", a.getStato());
	}
	
	@Test 
	public void testGetDescrizioneAmbito() {
		assertEquals("descrizioneAmbito", a.getDescrizioneAmbito());
	}
	
	@Test 
	public void testSetPartitaIVA() {
		a.setPartitaIVA("pippo");
		assertEquals("pippo", a.getPartitaIVA());
	}
	
	@Test 
	public void testSetPassword() {
		a.setPassword("pippo");
		assertEquals("pippo", a.getPassword());
	}
	
	@Test 
	public void testSetEmail() {
		a.setEmail("pippo");
		assertEquals("pippo", a.getEmail());
	}
	
	@Test 
	public void testSetNome() {
		a.setNome("pippo");
		assertEquals("pippo", a.getNome());
	}
	
	@Test 
	public void testSetSede() {
		a.setSede("pippo");
		assertEquals("pippo", a.getSede());
	}
	
	@Test 
	public void testSetNumeroTelefono() {
		a.setNumeroTelefono("pippo");
		assertEquals("pippo", a.getNumeroTelefono());
	}
	
	@Test 
	public void testSetTirociniOfferti() {
		ArrayList<Tirocinio> tirociniOfferti=new ArrayList<>();
		tirociniOfferti.add(t);
		a.setTirociniOfferti(tirociniOfferti);
		assertEquals(a.getTirociniOfferti(),tirociniOfferti);
	}
	
	@Test 
	public void testSetStato() {
		a.setStato("pippo");
		assertEquals("pippo", a.getStato());
	}
	
	@Test 
	public void testSetDescrizioneAmbito() {
		a.setDescrizioneAmbito("pippo");
		assertEquals("pippo", a.getDescrizioneAmbito());
	}
}
