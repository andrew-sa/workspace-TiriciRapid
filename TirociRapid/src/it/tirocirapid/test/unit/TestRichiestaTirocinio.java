package it.tirocirapid.test.unit;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

public class TestRichiestaTirocinio extends TestCase {

	protected RichiestaTirocinio rVuoto;
	protected RichiestaTirocinio r;
	protected Studente s;
	protected Tirocinio t;
	protected Professore p;
	protected Curriculum c;
	protected String usernameprova;
	protected String nometirocinioprova;
	protected String iva;
	
	
	@BeforeEach
	protected void setUp() {
		rVuoto=new RichiestaTirocinio();
		s= new Studente("username","password","emailIstituzionale","matricola","mediaVoti","nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		p= new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		c= new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua","altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative","capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze","patenti","ulterioriInformazioni");
		r= new RichiestaTirocinio(s,t,p,"stato");
		usernameprova="aaa";
		nometirocinioprova="bbb";
		iva="ccc";
		
	}

	@AfterEach
	protected void tearDown() {
		rVuoto=null;
		r=null;
		s=null;
		t=null;
		p=null;
		c=null;
		usernameprova=null;
		nometirocinioprova=null;
		iva=null;
	}
	
	@Test 
	public void testGetStudente() {
		assertSame(s,r.getStudente());
	}

	@Test 
	public void testGetTirocinio() {
		assertSame(t,r.getTirocinio());
	}
	
	@Test 
	public void testGetTutorInterno() {
		assertSame(p,r.getTutorInterno());
	}
	
	@Test 
	public void testGetStato() {
		assertEquals("stato",r.getStato());
	}
	
	@Test 
	public void testSetStudente() {
		r.setStudente(s);
		assertSame(s,r.getStudente());
	}
	
	@Test 
	public void testSetTirocinio() {
		r.setTiroconio(t);
		assertSame(t,r.getTirocinio());
	}
	
	@Test 
	public void testSetTutorInterno() {
		r.setTutorInterno(p);
		assertSame(p,r.getTutorInterno());
	}
	
	@Test 
	public void testSetStato() {
		r.setStato("stato");
		assertEquals("stato",r.getStato());
	}
/*	
 * 	@Test 
	public void testSetStudente(String username) {
		username=usernameprova;
		
	}
*//*	
	public void testSetTirocinio(String partitaIVAAzienda, String nomeTirocinio)
	{
		Tirocinio tirocinio = new Tirocinio();
		tirocinio.setPartitaIVAAzienda(partitaIVAAzienda);
		tirocinio.setNome(nomeTirocinio);
		assertEquals(partitaIVAAzienda,r2.getTirocinio().getPartitaIVAAzienda());
		assertEquals(nomeTirocinio,r2.getTirocinio().getNome());
	}
/*
	public void testSetStudente(String usernamemetodo) {
		usernamemetodo="aaaa";
		s.setUsername(usernamemetodo);
	}
*/
}
