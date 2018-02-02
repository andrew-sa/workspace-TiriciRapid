/**
 * 
 */
package it.tirocirapid.test.unit;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;

import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

/**
 * @author Antonino
 *
 */
public class TestAzienda extends TestCase{
	
	protected Azienda a;
	protected ArrayList<Tirocinio> array;
	protected Tirocinio t;
	
	protected void setUp(){
		a=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",array,"stato","descrizioneAmbito");
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		a.aggiungiTirocinio(t);
	}

	protected void tearDown(){
		a=null;
		t=null;
	}
	
	public void testGetPartitaIVA() {
		assertEquals("partitaIVA", a.getPartitaIVA());
	}

	public void testGetPassword() {
		assertEquals("password", a.getPassword());
	}
	
	public void testGetEmail() {
		assertEquals("email", a.getEmail());
	}
	
	public void testGetNome() {
		assertEquals("nome", a.getNome());
	}
	
	public void testGetSede() {
		assertEquals("sede", a.getSede());
	}
	
	public void testGetNumeroTelefono() {
		assertEquals("numeroTelefono", a.getNumeroTelefono());
	}
	
	public void testGetTirociniOfferti() {
		assertSame(array,a.getTirociniOfferti());
	}
	
	public void testGetStato() {
		assertEquals("stato", a.getStato());
	}
	
	public void testGetDescrizioneAmbito() {
		assertEquals("descrizioneAmbito", a.getDescrizioneAmbito());
	}
	
	public void testSetPartitaIVA() {
		a.setPartitaIVA("pippo");
		assertEquals("pippo", a.getPartitaIVA());
	}
	
	public void testSetPassword() {
		a.setPassword("pippo");
		assertEquals("pippo", a.getPassword());
	}
	
	public void testSetEmail() {
		a.setEmail("pippo");
		assertEquals("pippo", a.getEmail());
	}
	
	public void testSetNome() {
		a.setNome("pippo");
		assertEquals("pippo", a.getNome());
	}
	
	public void testSetSede() {
		a.setSede("pippo");
		assertEquals("pippo", a.getSede());
	}
	
	public void testSetNumeroTelefono() {
		a.setNumeroTelefono("pippo");
		assertEquals("pippo", a.getNumeroTelefono());
	}
	
	public void testSetTirociniOfferti() {
		ArrayList<Tirocinio> tirociniOfferti=new ArrayList<>();
		tirociniOfferti.add(t);
		a.setTirociniOfferti(tirociniOfferti);
		assertEquals(a.getTirociniOfferti(),tirociniOfferti);
	}
	
	public void testSetStato() {
		a.setStato("pippo");
		assertEquals("pippo", a.getStato());
	}
	
	public void testSetDescrizioneAmbito() {
		a.setDescrizioneAmbito("pippo");
		assertEquals("pippo", a.getDescrizioneAmbito());
	}
}
