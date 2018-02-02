package it.tirocirapid.test.unit;

import it.tirocirapid.classes.model.Curriculum;
import junit.framework.TestCase;

public class TestCurriculum extends TestCase {
	
	protected Curriculum c;

	
	protected void setUp() {
		c=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
	}

	
	protected void tearDown() {
		c=null;
	}
	
	public void testGetFax() {
		assertEquals("fax", c.getFax());
	}

	public void testGetEsperienzaLavorativa() {
		assertEquals("esperienzaLavorativa", c.getEsperienzaLavorativa());
	}
	
	public void testGetCapacitaCompetenzePersonali() {
		assertEquals("capacitaCompetenzePersonali",c.getCapacitaCompetenzePersonali());
	}
	
	public void testGetMadreLingua() {
		assertEquals("madrelingua",c.getMadrelingua());
	}
	
	public void testGetAltreLingue() {
		assertEquals("altreLingue",c.getAltreLingue());
	}
	
	public void testGetCapacitaCompetenzeRelazionali() {
		assertEquals("capacitaCompetenzeRelazionali",c.getCapacitaCompetenzeRelazionali());
	}
	
	public void testGetCapacitaCompetenzeOrganizzative() {
		assertEquals("capacitaCompetenzeOrganizzative",c.getCapacitaCompetenzeOrganizzative());
	}
	
	public void testGetCapacitaTecniche() {
		assertEquals("capacitaCompetenzeTecniche",c.getCapacitaCompetenzeTecniche());
	}
	
	public void testGetCapacitaArtistiche() {
		assertEquals("capacitaCompetenzeArtistiche",c.getCapacitaCompetenzeArtistiche());
	}
	
	public void testGetAltreCapacitaCompetenze() {
		assertEquals("altreCapacitaCompetenze",c.getAltreCapacitaCompetenze());
	}
	
	public void testGetPatenti() {
		assertEquals("patenti",c.getPatenti());
	}
	
	public void testGetUlterioriInformazioni() {
		assertEquals("ulterioriInformazioni",c.getUlterioriInformazioni());
	}
	
	public void testSetFax() {
		c.setFax("pippo");
		assertEquals("pippo",c.getFax());
	}
	
	public void testSetEsperienzaLavorativa() {
		c.setEsperienzaLavorativa("pippo");
		assertEquals("pippo",c.getEsperienzaLavorativa());
	}
	
	public void testSetCapacitaCompetenzePersonali() {
		c.setCapacitaCompetenzePersonali("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzePersonali());
	}
	
	public void testSetMadreLingua() {
		c.setCapacitaCompetenzePersonali("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzePersonali());
	}
	
	public void testSetAltreLingue() {
		c.setAltreLingue("pippo");
		assertEquals("pippo", c.getAltreLingue());
	}
	
	public void testSetCapacitaCompetenzeRelazionali() {
		c.setCapacitaCompetenzeRelazionali("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeRelazionali());
	}
	
	public void testSetCapacitaCompetenzeOrganizzative() {
		c.setCapacitaCompetenzeOrganizzative("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeOrganizzative());
	}
	
	public void testSetCapacitaCompetenzeTecniche() {
		c.setCapacitaCompetenzeTecniche("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeTecniche());
	}
	
	public void testSetCapacitaCompetenzeArtistiche() {
		c.setCapacitaCompetenzeArtistiche("pippo");
		assertEquals("pippo", c.getCapacitaCompetenzeArtistiche());
	}
	
	public void testSetAltreCapacitaCompetenze() {
		c.setAltreCapacitaCompetenze("pippo");
		assertEquals("pippo", c.getAltreCapacitaCompetenze());
	}
	
	public void testSetPatenti() {
		c.setPatenti("pippo");
		assertEquals("pippo", c.getPatenti());
	}
	
	public void testSetUlterioriInformazioni() {
		c.setUlterioriInformazioni("pippo");
		assertEquals("pippo", c.getUlterioriInformazioni());
	}
	
}