/**
 * 
 */
package it.tirocirapid.test.unit;

import java.io.Console;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

/**
 * @author Antonino
 *
 */
public class TestAzienda extends TestCase{
	
	protected Azienda aVuoto;
	protected Azienda a;
	protected Azienda b;
	protected Azienda w;
	protected ArrayList<Tirocinio> array;
	protected ArrayList<Tirocinio> arrayb;
	protected Tirocinio t;
	protected String toStringEs;
	
	@BeforeEach
	protected void setUp(){
		aVuoto= new Azienda();
		a=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",array,"stato","descrizioneAmbito");
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		toStringEs=("PartitaIVA: partitaIVA Password: password Email: email Nome: nome Sede: sede NumeroTelefono: numeroTelefono TirociniOfferti: null Stato: stato DescrizioneAmbito: descrizioneAmbito");
		arrayb= new ArrayList<>();
		arrayb.add(t);
		b=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",arrayb,"stato","descrizioneAmbito");
	}

	@AfterEach
	protected void tearDown(){
		aVuoto=null;
		a=null;
		array=null;
		t=null;
		
	}
	@Test
	public void testEquals1() {
		boolean x=true;
		assertEquals(x,b.equals(b));
	}
	@Test
	public void testEquals2() {
		boolean x=false;
		assertEquals(x, b.equals(x));
	}
	@Test
	public void testEquals3() {
		boolean x=false;
		w=new Azienda("a","password","email","nome","sede","numeroTelefono",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals4() {
		boolean x=false;
		w=new Azienda("partitaIVA","a","email","nome","sede","numeroTelefono",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals5() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","a","nome","sede","numeroTelefono",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals6() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","email","a","sede","numeroTelefono",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals7() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","email","nome","a","numeroTelefono",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals8() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","email","nome","sede","a",arrayb,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals9() {
		boolean x=false;
		Tirocinio h= new Tirocinio("a","nome","descrizione","offertaFormativa","stato");
		ArrayList<Tirocinio> arrayc = new ArrayList<>();
		arrayc.add(h);
		w=new Azienda("partitaIVA","password","email","nome","sede","a",arrayc,"stato","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testEquals10() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",arrayb,"a","descrizioneAmbito");
		assertEquals(x, b.equals(w));
	}	
	@Test
	public void testEquals11() {
		boolean x=false;
		w=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",arrayb,"stato","a");
		assertEquals(x, b.equals(w));
	}
	@Test
	public void testToString() {
		assertEquals(toStringEs, a.toString());
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
