package it.tirocirapid.test.unit;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

public class TestTirocinio extends TestCase {
	
	protected Tirocinio tVuoto;
	protected Tirocinio t;
	protected Tirocinio h;
	protected Tirocinio w;
	protected String toStringEs;

	@BeforeEach
	protected void setUp() {
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		h= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		toStringEs="PartitaIVAAzienda: partitaIVAAzienda Nome: nome Descrizione: descrizione OffertaFormativa: offertaFormativa Stato: stato";
		tVuoto= new Tirocinio();
	}

	@AfterEach
	protected void tearDown() {
		t=null;
		tVuoto=null;
	}
	@Test
	public void testEquals1() {
		boolean x=true;
		assertEquals(x,t.equals(h));
	}
	@Test
	public void testEquals2() {
		boolean x=false;
		assertEquals(x, t.equals(x));
	}
	@Test
	public void testEquals3() {
		boolean x=false;
		w=new Tirocinio("a","nome","descrizione","offertaFormativa","stato");
		assertEquals(x, t.equals(w));
	}
	@Test
	public void testEquals4() {
		boolean x=false;
		w=new Tirocinio("partitaIVAAzienda","a","descrizione","offertaFormativa","stato");
		assertEquals(x, t.equals(w));
	}
	@Test
	public void testEquals5() {
		boolean x=false;
		w=new Tirocinio("partitaIVAAzienda","nome","a","offertaFormativa","stato");
		assertEquals(x, t.equals(w));
	}
	@Test
	public void testEquals6() {
		boolean x=false;
		w=new Tirocinio("partitaIVAAzienda","nome","descrizione","a","stato");
		assertEquals(x, t.equals(w));
	}
	@Test
	public void testEquals7() {
		boolean x=false;
		w=new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","a");
		assertEquals(x, t.equals(w));
	}
	public void testToString() {
		assertEquals(toStringEs, t.toString());
	}
	@Test
	public void testGetPartitaIVAAzienda() {
		assertEquals("partitaIVAAzienda", t.getPartitaIVAAzienda());
	}
	
	@Test
	public void testGetNome() {
		assertEquals("nome", t.getNome());
	}
	
	@Test
	public void testGetDescrizione() {
		assertEquals("descrizione", t.getDescrizione());
	}

	@Test
	public void testGetOffertaFormativa() {
		assertEquals("offertaFormativa", t.getOffertaFormativa());
	}
	
	@Test
	public void testGetStato() {
		assertEquals("stato", t.getStato());
	}
	
	@Test
	public void testSetPartitaIVAAzienda() {
		t.setPartitaIVAAzienda("pippo");
		assertEquals("pippo", t.getPartitaIVAAzienda());
	}
	
	@Test
	public void testSetNome() {
		t.setNome("pippo");
		assertEquals("pippo", t.getNome());
	}
	
	@Test
	public void testSetDescrizione() {
		t.setDescrizione("pippo");
		assertEquals("pippo", t.getDescrizione());
	}
	
	@Test
	public void testSetOffertaFormativa() {
		t.setOffertaFormativa("pippo");
		assertEquals("pippo", t.getOffertaFormativa());
	}
	
	@Test
	public void testSetStato() {
		t.setStato("pippo");
		assertEquals("pippo", t.getStato());
	}
}
