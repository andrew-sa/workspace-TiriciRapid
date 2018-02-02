package it.tirocirapid.test.unit;

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
	
	protected void setUp() {
		rVuoto=null;
		s= new Studente("username","password","emailIstituzionale","matricola","mediaVoti","nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		t= new Tirocinio("partitaIVAAzienda","nome","descrizione","offertaFormativa","stato");
		p= new Professore("username","password","ambito","emailIstituzionale","nome","cognome","email","telefono","indirizzo","matricola");
		c= new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua","altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative","capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze","patenti","ulterioriInformazioni");
		r= new RichiestaTirocinio(s,t,p,"stato");
	}

	
	protected void tearDown() {
		rVuoto=null;
		r=null;
		s=null;
		t=null;
		p=null;
		c=null;
	}
	
	public void testGetStudente() {
		assertSame(s,r.getStudente());
	}

	public void testGetTirocinio() {
		assertSame(t,r.getTirocinio());
	}
	
	public void testGetTutorInterno() {
		assertSame(p,r.getTutorInterno());
	}
	
	public void testGetStato() {
		assertEquals("stato",r.getStato());
	}

}
