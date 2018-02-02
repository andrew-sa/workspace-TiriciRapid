package it.tirociraid.test.unit;


import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

public class TestTirocinio extends TestCase {
	
	protected Tirocinio t;

	protected void setUp() {
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
	}

	protected void tearDown() {
		t=null;
	}

	public void testGetPartitaIVAAzienda() {
		assertEquals("partitaIVAAzienda", t.getPartitaIVAAzienda());
	}
	
	public void testGetNome() {
		assertEquals("nome", t.getNome());
	}
	
	public void testGetDescrizione() {
		assertEquals("descrizione", t.getDescrizione());
	}

	public void testGetOffertaFormativa() {
		assertEquals("offertaFormativa", t.getOffertaFormativa());
	}
	
	public void testGetStato() {
		assertEquals("stato", t.getStato());
	}
	
	public void testSetPartitaIVAAzienda() {
		t.setPartitaIVAAzienda("pippo");
		assertEquals("pippo", t.getPartitaIVAAzienda());
	}
	
	public void testSetNome() {
		t.setNome("pippo");
		assertEquals("pippo", t.getNome());
	}
	
	public void testSetDescrizione() {
		t.setDescrizione("pippo");
		assertEquals("pippo", t.getDescrizione());
	}
	
	public void testSetOffertaFormativa() {
		t.setOffertaFormativa("pippo");
		assertEquals("pippo", t.getOffertaFormativa());
	}
}
