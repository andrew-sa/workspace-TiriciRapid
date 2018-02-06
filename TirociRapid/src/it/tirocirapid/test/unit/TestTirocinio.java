package it.tirocirapid.test.unit;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

public class TestTirocinio extends TestCase {
	
	protected Tirocinio tVuoto;
	protected Tirocinio t;

	@BeforeEach
	protected void setUp() {
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		tVuoto= new Tirocinio();
	}

	@AfterEach
	protected void tearDown() {
		t=null;
		tVuoto=null;
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
