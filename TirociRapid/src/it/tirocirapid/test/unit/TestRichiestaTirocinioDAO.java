package it.tirocirapid.test.unit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.CurriculumDAO;
import it.tirocirapid.classes.manager.ProfessoreDAO;
import it.tirocirapid.classes.manager.RichiestaTirocinioDAO;
import it.tirocirapid.classes.manager.StudenteDAO;
import it.tirocirapid.classes.manager.TirocinioDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.BadRequestTirocinioException;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestRichiestaTirocinioDAO extends TestCase{

	protected TirocinioDAO t;
	protected Connection connection1;
	protected RichiestaTirocinioDAO richDAO;
	protected 	HashMap<Integer, String> statesReqTir = new HashMap<>();

	@BeforeEach
	protected void setUp(){
		t= new TirocinioDAO();
		richDAO= new RichiestaTirocinioDAO();
		statesReqTir.put(1, "ConfAz");
		statesReqTir.put(2, "ScelTut");
		statesReqTir.put(3, "ConfTut");
		statesReqTir.put(4, "ConfResp");
		statesReqTir.put(5, "Acc");
		statesReqTir.put(-4, "RifResp");
		statesReqTir.put(-3, "RifTut");
		statesReqTir.put(-1, "RifAz");
		try {
			RichiestaTirocinio l = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("c.iandolo");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("c.iandolo");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			l.setStudente(testStu);
			l.setTiroconio(testTir);
			RichiestaTirocinioDAO rtDAOCreate = new RichiestaTirocinioDAO();
			rtDAOCreate.create(l, statesReqTir);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadRequestTirocinioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterEach
	protected void tearDown(){
		t=null;
		connection1=null;
		richDAO=null;
		statesReqTir = null;
		try {
			RichiestaTirocinioDAO rtDAODelete = new RichiestaTirocinioDAO();
			rtDAODelete.delete("m.evangelista", "06653061215", "Tirocinio di PHP");
			rtDAODelete.delete("c.iandolo", "06653061215", "Tirocinio di PHP");
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test //caso fallimento
	public void testCreate1() {
		try {
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("g.iannaccone2");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("g.iannaccone2");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			RichiestaTirocinioDAO rtDAOCreate = new RichiestaTirocinioDAO();
			rtDAOCreate.create(h, statesReqTir);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadRequestTirocinioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test //caso successo
	public void testCreate2() {
		try {
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("m.evangelista");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("m.evangelista");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			RichiestaTirocinioDAO rtDAOCreate = new RichiestaTirocinioDAO();
			rtDAOCreate.create(h, statesReqTir);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadRequestTirocinioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead1() {

		RichiestaTirocinio richiesta=  new RichiestaTirocinio();
					try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
			        {
			            try(Statement stCheck=(Statement) connection.createStatement())
			            {
			                connection.setAutoCommit(false);
			        		String usernamestudente="a.gisi"; 
			        		String iva="06653061215";
			        		String nome="Tirocinio di PHP";
			        		richiesta=richDAO.read(usernamestudente, iva, nome);
			        		assertEquals(nome, richiesta.getTirocinio().getNome());
			        		assertEquals(usernamestudente, richiesta.getStudente().getUsername());
			        		assertEquals(iva, richiesta.getTirocinio().getPartitaIVAAzienda());
			            } catch (TuplaNotFoundException e) {
							e.printStackTrace();
						}
			           
			            connection.close();
			        } catch (SQLException e) {
						e.printStackTrace();
					}
	}

	@Test
	public void testRead2() {

		RichiestaTirocinio richiesta=  new RichiestaTirocinio();
					try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
			        {
			            try(Statement stCheck=(Statement) connection.createStatement())
			            {
			                connection.setAutoCommit(false);
			        		String usernamestudente="a.a"; 
			        		String iva="06653061215";
			        		String nome="Tirocinio di PHP";
			        		richiesta=richDAO.read(usernamestudente, iva, nome);
			        		assertEquals(nome, richiesta.getTirocinio().getNome());
			        		assertEquals(usernamestudente, richiesta.getStudente().getUsername());
			        		assertEquals(iva, richiesta.getTirocinio().getPartitaIVAAzienda());
			            } catch (TuplaNotFoundException e) {
							e.printStackTrace();
						}
			           
			            connection.close();
			        } catch (SQLException e) {
						e.printStackTrace();
					}
	}
	
	@Test
	public void testUpdateStato() {
		RichiestaTirocinio richiesta=  new RichiestaTirocinio();
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
        		String usernamestudente="a.gisi"; 
        		String iva="06653061215";
        		String nome="Tirocinio di PHP";
        		richiesta=richDAO.read(usernamestudente, iva, nome);
        		richDAO.updateStato(richiesta);
        		
            } catch (TuplaNotFoundException e) {
				e.printStackTrace();
			} catch (InsertFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
            connection.close();
        } catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateAddTutor() {
		try {
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("c.iandolo");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("c.iandolo");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setTutorInterno("d.nini");
			RichiestaTirocinioDAO rtDAOUpdateAddTutor = new RichiestaTirocinioDAO();
			rtDAOUpdateAddTutor.updateAddTutor(h);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateRemoveTutor() {
		try {
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("m.evangelista");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("m.evangelista");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setTutorInterno("d.nini");
			RichiestaTirocinioDAO rtDAOUpdateRemoveTutor = new RichiestaTirocinioDAO();
			rtDAOUpdateRemoveTutor.updateRemoveTutor(h);;
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testDelete() {
		try {
			RichiestaTirocinioDAO rtDAODelete = new RichiestaTirocinioDAO();
			rtDAODelete.delete("c.iandolo", "06653061215", "Tirocinio di PHP");
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testReadAllRichiesteStudente() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			RichiestaTirocinioDAO rtDAOReadAllRichiesteStudente = new RichiestaTirocinioDAO();
			richieste = rtDAOReadAllRichiesteStudente.readAllRichiesteStudente("l.esposito23");
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.esposito23");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.esposito23");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setTutorInterno("d.nini");
			h.setStato("ConfResp");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTutorInterno().getUsername(), richiesteTest.get(i).getTutorInterno().getUsername());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void  testReadRichiesteAzienda() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			RichiestaTirocinioDAO rtDAOReadAllRichiesteAzienda = new RichiestaTirocinioDAO();
			String piva = "07071080639";
			richieste = rtDAOReadAllRichiesteAzienda.readRichiesteAzienda(piva);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("g.iannaccone2");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("g.iannaccone2");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("07071080639", "Tirocinio di Grafica");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfAz");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test //controllo normale
	public void  testStoricoRichiesteAzienda1() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String piva ="06653061215";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readStoricoRichiesteAzienda(piva);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("a.gisi");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("a.gisi");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read(piva, "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("SceltTut");
			richiesteTest.add(h);
			RichiestaTirocinio y = new RichiestaTirocinio();
			Studente testStu2 = s.read("l.esposito23");
			Curriculum testcur2 = c.read("l.esposito23");
			testStu2.setCurriculum(testcur2);
			Tirocinio testTir2 = t.read(piva, "Tirocinio di PHP");
			y.setStudente(testStu2);
			y.setTiroconio(testTir2);
			y.setStato("ConfResp");
			richiesteTest.add(y);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test //testing sull'if con stato=confAz
	public void  testStoricoRichiesteAzienda2() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String piva ="07071080639";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readStoricoRichiesteAzienda(piva);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("g.iannaccone2");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("g.iannaccone2");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read(piva, "Tirocinio di Grafica");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("SceltTut");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertNotEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertNotEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReadRichiesteTutor1() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String prof ="a.prevete";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readRichiesteTutor(prof);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.grasso");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.grasso");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("07071080639", "Tirocinio di Grafica");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfTut");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadRichiesteTutor2() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String prof ="d.nini";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readRichiesteTutor(prof);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.esposito23");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.esposito23");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfResp");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadStoricoRichiesteTutor1() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String prof ="d.nini";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readStoricoRichiesteTutor(prof);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.esposito23");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.esposito23");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfResp");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadStoricoRichiesteTutor2() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			String prof ="a.prevete";
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readStoricoRichiesteTutor(prof);
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.grasso");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.grasso");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("07071080639", "Tirocinio di Grafica");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfTut");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadAllRichiesteInAttesaResponsabileApprovazione() {
		try {
			ArrayList<RichiestaTirocinio> richieste = new ArrayList<>();
			ArrayList<RichiestaTirocinio> richiesteTest = new ArrayList<>();
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			richieste = rtDAOStoricoRichiesteAzienda.readAllRichiesteInAttesaResponsabileApprovazione();
			RichiestaTirocinio h = new RichiestaTirocinio();
			StudenteDAO s = new StudenteDAO();
			Studente testStu = s.read("l.esposito23");
			CurriculumDAO c = new CurriculumDAO();
			Curriculum testcur = c.read("l.esposito23");
			testStu.setCurriculum(testcur);
			TirocinioDAO t = new TirocinioDAO();
			Tirocinio testTir = t.read("06653061215", "Tirocinio di PHP");
			h.setStudente(testStu);
			h.setTiroconio(testTir);
			h.setStato("ConfResp");
			richiesteTest.add(h);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
				assertEquals(richieste.get(i).getTirocinio().getPartitaIVAAzienda(), richiesteTest.get(i).getTirocinio().getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getTirocinio().getNome(), richiesteTest.get(i).getTirocinio().getNome());
				assertEquals(richieste.get(i).getStudente().getUsername(), richiesteTest.get(i).getStudente().getUsername());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCountByTirocinio() {
		try {
			int conta;
			RichiestaTirocinioDAO rtDAOStoricoRichiesteAzienda = new RichiestaTirocinioDAO();
			conta = rtDAOStoricoRichiesteAzienda.countByTirocinio("Tirocinio di Grafica", "07071080639");
			assertEquals(conta, 2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
