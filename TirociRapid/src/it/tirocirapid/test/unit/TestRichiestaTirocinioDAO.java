package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

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
		
	}

	@AfterEach
	protected void tearDown(){
		t=null;
		connection1=null;
		richDAO=null;
		statesReqTir = null;
	}

	@Test
	public void testCreate() {
			/*RichiestaTirocinio toCreate, HashMap<Integer, String> states)*/
			/*MI RICAVO CURRICULUM STUDENTE E TIROCINIO*/
			
			String usernamestudente="g.iannaccone2"; 
			StudenteDAO s1= new StudenteDAO();
			Studente studente = null;
			
			try {
				studente = s1.read(usernamestudente);
			} catch (SQLException | TuplaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String usernameprofessore="a.prevete";
			ProfessoreDAO p1= new ProfessoreDAO();
			Professore professore = null;
			
			try {
				professore = p1.read(usernameprofessore);
			} catch (SQLException | TuplaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			String partitaIVA="06653061215";
			String nome="Tirocinio di C";
			TirocinioDAO t1= new TirocinioDAO();
			Tirocinio tirocinio = null;
			try {
				tirocinio = t1.read(partitaIVA, nome);
			} catch (SQLException | TuplaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RichiestaTirocinio rich= new RichiestaTirocinio(studente, tirocinio, professore, "SceltTut");
			try {
				richDAO.create(rich, statesReqTir);
				richDAO.delete(usernamestudente, partitaIVA, nome);
			} catch (SQLException | BadRequestTirocinioException | InsertFailedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TuplaNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	@Test
	public void testIsGoodRequest() {
		fail("Not yet implemented");
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
	
	@Test // 1 stato TirRif
	public void testUpdateStato1() {
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
		/*DEVO METTERE I DATI DEL TIROCINIO*/
	//(String partitaIVAAzienda, String nome, String descrizione, String offertaFormativa, String stato)
		Tirocinio tir= new  Tirocinio("06653061215","AllSafe","Sicurezza","Studiare come usare c per creare un sito web.","TirProp");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(true);
            	
                t.create(tir);            
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM tirocinio WHERE PartitaIVA='06653061215' and Nome='AllSafe'"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM tirocinio WHERE PartitaIVA='06653061215' and Nome='AllSafe'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 
				connection1.close();
				e.printStackTrace();
			}   
        } catch (SQLException e) {
        	
        	connection1.close();
			e.printStackTrace();
		}
            } 

			catch (SQLException e1) {
				
				e1.printStackTrace();
			}
	}
	
	@Test
	public void testUpdateRemoveTutor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDelete() {
		String usernamestudente="g.iannaccone2"; 
		StudenteDAO s1= new StudenteDAO();
		Studente studente = null;
		
		try {
			studente = s1.read(usernamestudente);
		} catch (SQLException | TuplaNotFoundException e) {
			
			e.printStackTrace();
		}
		
		String usernameprofessore="a.prevete";
		ProfessoreDAO p1= new ProfessoreDAO();
		Professore professore = null;
		
		try {
			professore = p1.read(usernameprofessore);
		} catch (SQLException | TuplaNotFoundException e) {

			e.printStackTrace();
		}

		
		String partitaIVA="06653061215";
		String nome="Tirocinio di C";
		TirocinioDAO t1= new TirocinioDAO();
		Tirocinio tirocinio = null;
		try {
			tirocinio = t1.read(partitaIVA, nome);
		} catch (SQLException | TuplaNotFoundException e) {

			e.printStackTrace();
		}

		RichiestaTirocinio rich= new RichiestaTirocinio(studente, tirocinio, professore, "SceltTut");
		try {
			richDAO.create(rich, statesReqTir);
			richDAO.delete(usernamestudente, partitaIVA, nome);
		} catch (SQLException | BadRequestTirocinioException | InsertFailedException e) {
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testReadAllRichiesteStudente() {
		fail("Not yet implemented");
	}
	
	@Test
	public void  testReadAllRichiesteAzienda() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadAllRichiesteTutor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadAllRichiesteInAttesaResponsabileApprovazione() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadTutorInterno() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testCountByTirocinio() {
		fail("Not yet implemented");
	}
}
