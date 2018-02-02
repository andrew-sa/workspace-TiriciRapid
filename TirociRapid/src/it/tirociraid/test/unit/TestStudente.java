package it.tirociraid.test.unit;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Studente;
import junit.framework.TestCase;

public class TestStudente extends TestCase{

	protected Studente s;
	protected Curriculum c;
	protected Curriculum c2;
	
	protected void setUp() {
		c=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		s=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
	}

	protected void tearDown() {
		c=null;
		s=null;
		c2=null;
	}
	
	public void testGetUsername() {
		assertEquals("username",s.getUsername());
	}
	
	public void testGetPassword() {
		assertEquals("password",s.getPassword());
	}
	
	public void testGetEmailIstituzionale() {
		assertEquals("emailIstituzionale",s.getEmailIstituzionale());
	}
	
	public void testGetMatricola() {
		assertEquals("matricola",s.getMatricola());
	}
	
	public void testGetMediaVoti() {
		assertEquals("mediaVoti",s.getMediaVoti());
	}
	
	public void testGetNome() {
		assertEquals("nome",s.getNome());
	}
	
	public void testGetCognome() {
		assertEquals("cognome",s.getCognome());
	}
	
	public void testGetIndirizzo() {
		assertEquals("indirizzo",s.getIndirizzo());
	}

	public void testGetTelefono() {
		assertEquals("telefono",s.getTelefono());
	}
	
	public void testGetIstruzioneFormazione() {
		assertEquals("istruzioneFormazione",s.getIstruzioneFormazione());
	}
	
	public void testGetEmail() {
		assertEquals("email",s.getEmail());
	}
	
	public void testGetCurriculum() {
		assertSame(c,s.getCurriculum());
	}

	public void testSetUsername() {
		s.setUsername("pippo");
		assertEquals("pippo",s.getUsername());
	}
	
	public void testSetPassword() {
		s.setPassword("pippo");
		assertEquals("pippo",s.getPassword());
	}
	
	public void testSetEmailIstituzionale() {
		s.setEmailIstituzionale("pippo");
		assertEquals("pippo",s.getEmailIstituzionale());
	}
	
	public void testSetMatricola() {
		s.setMatricola("pippo");
		assertEquals("pippo",s.getMatricola());
	}
	
	public void testSetMediaVoti() {
		s.setMediaVoti("pippo");
		assertEquals("pippo",s.getMediaVoti());
	}
	
	public void testSetNome() {
		s.setNome("pippo");
		assertEquals("pippo",s.getNome());
	}
	
	public void testSetCognome() {
		s.setCognome("pippo");
		assertEquals("pippo",s.getCognome());
	}
	
	public void testSetIndirizzo() {
		s.setIndirizzo("pippo");
		assertEquals("pippo",s.getIndirizzo());
	}
	
	public void testSetTelefono() {
		s.setTelefono("pippo");
		assertEquals("pippo",s.getTelefono());
	}
	
	public void testSetIstruzioneFormazione() {
		s.setIstruzioneFormazione("pippo");
		assertEquals("pippo",s.getIstruzioneFormazione());
	}
	
	public void testSetEmail() {
		s.setEmail("pippo");
		assertEquals("pippo",s.getEmail());
	}
	
	public void testSetCurriculum() {
		c2= new Curriculum("a","b","c","d","e","f","g","h","i","l","m","n");
		s.setCurriculum(c2);
		assertSame(c2,s.getCurriculum());
		
	}
}
