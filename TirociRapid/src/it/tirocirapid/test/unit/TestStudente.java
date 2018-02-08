package it.tirocirapid.test.unit;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Studente;
import junit.framework.TestCase;

public class TestStudente extends TestCase{

	protected Studente sVuoto;
	protected Studente s;
	protected Studente s2;
	protected Curriculum c;
	protected Curriculum c2;
	
	@BeforeEach
	protected void setUp() {
		c=new Curriculum("fax","esperienzaLavorativa","capacitaCompetenzePersonali","madrelingua",
				"altreLingue","capacitaCompetenzeRelazionali","capacitaCompetenzeOrganizzative",
				"capacitaCompetenzeTecniche","capacitaCompetenzeArtistiche","altreCapacitaCompetenze",
				"patenti","ulterioriInformazioni");
		sVuoto=new Studente();
		s=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
	}

	@AfterEach
	protected void tearDown() {
		c=null;
		sVuoto=null;
		s=null;
		c2=null;
	}
	public void testEquals1() {
		boolean x=true;
		assertEquals(x,s.equals(s));
	}
	@Test
	public void testEquals2() {
		boolean x=false;
		assertEquals(x, s.equals(x));
	}
	@Test
	public void testEquals3() {
		boolean x=false;
		s2=new Studente("a","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals4() {
		boolean x=false;
		s2=new Studente("username","a","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals5() {
		boolean x=false;
		s2=new Studente("username","password","a","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals6() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","a","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals7() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","a",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals8() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"a","cognome","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals9() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","a","indirizzo","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals10() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","a","telefono","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals11() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","a","istruzioneFormazione","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals12() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","a","email",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals13() {
		boolean x=false;
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","a",c);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testEquals14() {
		boolean x=false;
		c2= new Curriculum("a","b","c","d","e","f","g","h","i","l","m","n");
		s2=new Studente("username","password","emailIstituzionale","matricola","mediaVoti",
				"nome","cognome","indirizzo","telefono","istruzioneFormazione","email",c2);
		assertEquals(x, s.equals(s2));
	}
	@Test
	public void testGetUsername() {
		assertEquals("username",s.getUsername());
	}
	
	@Test
	public void testGetPassword() {
		assertEquals("password",s.getPassword());
	}
	
	@Test
	public void testGetEmailIstituzionale() {
		assertEquals("emailIstituzionale",s.getEmailIstituzionale());
	}
	
	@Test
	public void testGetMatricola() {
		assertEquals("matricola",s.getMatricola());
	}
	
	@Test
	public void testGetMediaVoti() {
		assertEquals("mediaVoti",s.getMediaVoti());
	}
	
	@Test
	public void testGetNome() {
		assertEquals("nome",s.getNome());
	}
	
	@Test
	public void testGetCognome() {
		assertEquals("cognome",s.getCognome());
	}
	
	@Test
	public void testGetIndirizzo() {
		assertEquals("indirizzo",s.getIndirizzo());
	}

	@Test
	public void testGetTelefono() {
		assertEquals("telefono",s.getTelefono());
	}
	
	@Test
	public void testGetIstruzioneFormazione() {
		assertEquals("istruzioneFormazione",s.getIstruzioneFormazione());
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("email",s.getEmail());
	}
	
	@Test
	public void testGetCurriculum() {
		assertSame(c,s.getCurriculum());
	}

	@Test
	public void testSetUsername() {
		s.setUsername("pippo");
		assertEquals("pippo",s.getUsername());
	}
	
	@Test
	public void testSetPassword() {
		s.setPassword("pippo");
		assertEquals("pippo",s.getPassword());
	}
	
	@Test
	public void testSetEmailIstituzionale() {
		s.setEmailIstituzionale("pippo");
		assertEquals("pippo",s.getEmailIstituzionale());
	}
	
	@Test
	public void testSetMatricola() {
		s.setMatricola("pippo");
		assertEquals("pippo",s.getMatricola());
	}
	
	@Test
	public void testSetMediaVoti() {
		s.setMediaVoti("pippo");
		assertEquals("pippo",s.getMediaVoti());
	}
	
	@Test
	public void testSetNome() {
		s.setNome("pippo");
		assertEquals("pippo",s.getNome());
	}
	
	@Test
	public void testSetCognome() {
		s.setCognome("pippo");
		assertEquals("pippo",s.getCognome());
	}
	
	@Test
	public void testSetIndirizzo() {
		s.setIndirizzo("pippo");
		assertEquals("pippo",s.getIndirizzo());
	}
	
	@Test
	public void testSetTelefono() {
		s.setTelefono("pippo");
		assertEquals("pippo",s.getTelefono());
	}
	
	@Test
	public void testSetIstruzioneFormazione() {
		s.setIstruzioneFormazione("pippo");
		assertEquals("pippo",s.getIstruzioneFormazione());
	}
	
	@Test
	public void testSetEmail() {
		s.setEmail("pippo");
		assertEquals("pippo",s.getEmail());
	}
	
	@Test
	public void testSetCurriculum() {
		c2= new Curriculum("a","b","c","d","e","f","g","h","i","l","m","n");
		s.setCurriculum(c2);
		assertSame(c2,s.getCurriculum());
		
	}
}
