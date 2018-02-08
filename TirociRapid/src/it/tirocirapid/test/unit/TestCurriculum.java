package it.tirocirapid.test.unit;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Curriculum;
import junit.framework.TestCase;

public class TestCurriculum extends TestCase {
	
	protected Curriculum cVuoto;
	protected Curriculum c;
	protected Curriculum w;

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
	
	public void testEquals1() {
		boolean x=true;
		assertEquals(x,c.equals(c));
	}
	@Test
	public void testEquals2() {
		boolean x=false;
		assertEquals(x, c.equals(x));
	}
	@Test
	public void testEquals3() {
		boolean x=false;
		w=new Curriculum("a","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals4() {
		boolean x=false;
		w=new Curriculum("fax","a","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals5() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","a","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals6() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","a",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals7() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"a","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals8() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","a","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals9() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","a",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals10() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"a","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals11() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","a","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals12() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","a",
				"patenti","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals13() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"a","ulterioriInformazioni");
		assertEquals(x, c.equals(w));
	}
	@Test
	public void testEquals14() {
		boolean x=false;
		w=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","a");
		assertEquals(x, c.equals(w));
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