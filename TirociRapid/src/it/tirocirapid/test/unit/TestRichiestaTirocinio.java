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
	protected String toStringEs;
	
	
	@BeforeEach
	protected void setUp() {
		rVuoto=new RichiestaTirocinio();
		s= new Studente("username","password","emailIstituzionale","matricola","mediaVoti","nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		p= new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		c= new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua","altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative","capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze","patenti","ulterioriInformazioni");
		r= new RichiestaTirocinio(s,t,p,"stato");
		toStringEs = "Studente: username Tirocinio: partitaIVAAzienda nome TutorInterno: username Stato: stato";
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
	public void testToString() {
		assertEquals(toStringEs, r.toString());
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
	public void testSetStudente1() {
		r.setStudente(s);
		assertSame(s,r.getStudente());
	}
	
	@Test 
	public void testSetTirocinio1() {
		r.setTiroconio(t);
		assertSame(t,r.getTirocinio());
	}
	
	@Test 
	public void testSetTutorInterno1() {
		r.setTutorInterno(p);
		assertSame(p,r.getTutorInterno());
	}
	
	@Test 
	public void testSetStato() {
		r.setStato("stato");
		assertEquals("stato",r.getStato());
	}
	
 	@Test 
	public void testSetStudente2() {
 		String a="username";
		Studente studente = new Studente();
		studente.setUsername(a);
		r.setStudente(a);
		assertEquals(studente.getUsername(),r.getStudente().getUsername());	
	}

	public void testSetTirocinio2() {
		String a="partitaIVAAzienda";
		String b="nomeTirocinio";
		Tirocinio tirocinio = new Tirocinio();
		tirocinio.setPartitaIVAAzienda(a);
		tirocinio.setNome(b);
		r.setTirocinio(a, b);
		assertEquals(tirocinio.getPartitaIVAAzienda(),r.getTirocinio().getPartitaIVAAzienda());
		assertEquals(tirocinio.getNome(),r.getTirocinio().getNome());
	}

	public void testSetTutorInterno2() {
		String a="username";
		Professore professore = new Professore();
		professore.setUsername(a);
		r.setTutorInterno(a);
		assertEquals(professore.getUsername(),r.getTutorInterno().getUsername());
		
	}
	
}
