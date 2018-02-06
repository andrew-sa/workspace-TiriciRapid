package it.tirocirapid.test.unit;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Curriculum;
import junit.framework.TestCase;

public class TestCurriculum extends TestCase {
	
	protected Curriculum cVuoto;
	protected Curriculum c;

	@BeforeEach
	protected void setUp() {
		c=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		cVuoto= new Curriculum();
	}

	@AfterEach
	protected void tearDown() {
		c=null;
		cVuoto=null;
	}
	
	@Test
	public void testGetFax() {
		assertEquals("fax", c.getFax());
	}

	@Test
	public void testGetEsperienzaLavorativa() {
		assertEquals("esperienzaLavorativa", c.getEsperienzaLavorativa());
	}
	
	@Test
	public void testGetCapacitaCompetenzePersonali() {
		assertEquals("capacitaCompetenzePersonali",c.getCapacitaCompetenzePersonali());
	}
	
	@Test
	public void testGetMadreLingua() {
		assertEquals("madrelingua",c.getMadrelingua());
	}
	
	@Test
	public void testGetAltreLingue() {
		assertEquals("altreLingue",c.getAltreLingue());
	}
	
	@Test
	public void testGetCapacitaCompetenzeRelazionali() {
		assertEquals("capacitaCompetenzeRelazionali",c.getCapacitaCompetenzeRelazionali());
	}
	
	@Test
	public void testGetCapacitaCompetenzeOrganizzative() {
		assertEquals("capacitaCompetenzeOrganizzative",c.getCapacitaCompetenzeOrganizzative());
	}
	
	@Test
	public void testGetCapacitaTecniche() {
		assertEquals("capacitaCompetenzeTecniche",c.getCapacitaCompetenzeTecniche());
	}
	
	@Test
	public void testGetCapacitaArtistiche() {
		assertEquals("capacitaCompetenzeArtistiche",c.getCapacitaCompetenzeArtistiche());
	}
	
	@Test
	public void testGetAltreCapacitaCompetenze() {
		assertEquals("altreCapacitaCompetenze",c.getAltreCapacitaCompetenze());
	}
	
	@Test
	public void testGetPatenti() {
		assertEquals("patenti",c.getPatenti());
	}
	
	@Test
	public void testGetUlterioriInformazioni() {
		assertEquals("ulterioriInformazioni",c.getUlterioriInformazioni());
	}
	
	@Test
	public void testSetFax() {
		c.setFax("pippo");
		assertEquals("pippo",c.getFax());
	}
	
	@Test
	public void testSetEsperienzaLavorativa() {
		c.setEsperienzaLavorativa("pippo");
		assertEquals("pippo",c.getEsperienzaLavorativa());
	}
	
	@Test
	public void testSetCapacitaCompetenzePersonali() {
		c.setCapacitaCompetenzePersonali("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzePersonali());
	}
	
	@Test
	public void testSetMadreLingua() {
		c.setMadrelingua("pippo");
		assertEquals("pippo", c.getMadrelingua());
	}
	
	@Test
	public void testSetAltreLingue() {
		c.setAltreLingue("pippo");
		assertEquals("pippo", c.getAltreLingue());
	}
	
	@Test
	public void testSetCapacitaCompetenzeRelazionali() {
		c.setCapacitaCompetenzeRelazionali("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeRelazionali());
	}
	
	@Test
	public void testSetCapacitaCompetenzeOrganizzative() {
		c.setCapacitaCompetenzeOrganizzative("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeOrganizzative());
	}
	
	@Test
	public void testSetCapacitaCompetenzeTecniche() {
		c.setCapacitaCompetenzeTecniche("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeTecniche());
	}
	
	@Test
	public void testSetCapacitaCompetenzeArtistiche() {
		c.setCapacitaCompetenzeArtistiche("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeArtistiche());
	}
	
	@Test
	public void testSetAltreCapacitaCompetenze() {
		c.setAltreCapacitaCompetenze("pippo");
		assertEquals("pippo", c.getAltreCapacitaCompetenze());
	}
	
	@Test
	public void testSetPatenti() {
		c.setPatenti("pippo");
		assertEquals("pippo", c.getPatenti());
	}
	
	@Test
	public void testSetUlterioriInformazioni() {
		c.setUlterioriInformazioni("pippo");
		assertEquals("pippo", c.getUlterioriInformazioni());
	}
	
}