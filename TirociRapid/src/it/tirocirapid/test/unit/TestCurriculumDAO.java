package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.CurriculumDAO;
import it.tirocirapid.classes.manager.StudenteDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestCurriculumDAO extends TestCase{

	protected CurriculumDAO curriculumDAOcreate;
	protected Curriculum cur;
	protected String user;
	@BeforeEach
	protected void setUp(){
		curriculumDAOcreate= new CurriculumDAO();
		 cur= new Curriculum("","a","b","c","d","e","f","g","Italiano", "Inglese, Tedesco, Wu","A","Sono allergico alle banane");
		 user="p.prova1";
	}

	@AfterEach
	protected void tearDown(){
		curriculumDAOcreate=null;
		cur=null;
		user=null;
	}

	@Test
	public void testCreate() throws InsertFailedException {

		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
            	connection.setAutoCommit(true);
                Statement statement = (Statement) connection.createStatement();
                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
                statement.executeUpdate(query);
                curriculumDAOcreate.create(cur, user);
                
                PreparedStatement ps2= (PreparedStatement) connection.prepareStatement("DELETE FROM curriculum WHERE Username='p.prova1'");
                ps2.executeUpdate();
                
                PreparedStatement ps1= (PreparedStatement) connection.prepareStatement("DELETE FROM studente WHERE Username='p.prova1'");
                ps1.executeUpdate();
                connection.close();
            }   
        } catch (SQLException e) {
			e.printStackTrace();
		} 
	
	}
	
	@Test
	public void testRead1() {
		CurriculumDAO curriculumDAOread= new CurriculumDAO();
		
		String fax="0934 999451";
		String CapacitąCompetenzeRelazionali="Sono un attimo collega, e lo dimostrano gli ottimi voti conseguiti negli esami di gruppo che abbiamo dato nei precedenti anni universitari.";
		String CapacitąCompetenzeTecniche="Conoscenza approfondita di Java e Javascript, oltre che alla maggior parte dei linguaggi utili per la programmazione web.";
		String CapacitąCompetenzeArtistiche="Utilizzo da almeno due anni Photoshop a livelli alti.";
		String CapacitąCompetenzePersonali="Sono bravissimo nei bricolage e nei giochi di logica.";
		String CapacitąCompetenzeOrganizzative="Sono in pari con gli esami dell'universitą per quanto abbia dovuto organizzare il mio matrimonio e stia lavorando.";
		String AltreCapacitaCompetenze="Penso di essere veloce ad apprendere, e ho un'ottima memoria visiva.";
		String EsperienzaLavorativa="Ho gią lavorato per 2 anni in un'azienda piccola nel campo dell'elettronica.";
		String Madrelingua="Italiano";
		String AltreLingue="Inglese, Tedesco, Wu";
		String Patenti="B";
		String UlterioriInformazioni="Sono allergico alle banane.";
		Curriculum c;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.gisi";
		                c=curriculumDAOread.read(username);
		                assertEquals(fax, c.getFax());
		                assertEquals(CapacitąCompetenzeRelazionali, c.getCapacitaCompetenzeRelazionali());
		                assertEquals(CapacitąCompetenzeTecniche, c.getCapacitaCompetenzeTecniche());
		                assertEquals(CapacitąCompetenzeArtistiche, c.getCapacitaCompetenzeArtistiche());
		                assertEquals(CapacitąCompetenzePersonali, c.getCapacitaCompetenzePersonali());
		                assertEquals(CapacitąCompetenzeOrganizzative, c.getCapacitaCompetenzeOrganizzative());
		                assertEquals(AltreCapacitaCompetenze, c.getAltreCapacitaCompetenze());
		                assertEquals(EsperienzaLavorativa, c.getEsperienzaLavorativa());
		                assertEquals(Madrelingua, c.getMadrelingua());
		                assertEquals(AltreLingue, c.getAltreLingue());
		                assertEquals(Patenti, c.getPatenti());
		                assertEquals(UlterioriInformazioni, c.getUlterioriInformazioni());
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
		CurriculumDAO curriculumDAOread= new CurriculumDAO();

		Curriculum c;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.aaaaa";
		                c=curriculumDAOread.read(username);
		                
		                
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Test
	public void testUpdate() {
		CurriculumDAO curriculumDAOUp= new CurriculumDAO();
        Curriculum c = new Curriculum();
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="l.esposito23";
		                c=curriculumDAOUp.read(username);
		                c.setFax("1111111111"); //Basta un solo set perchč i set sono stati testati precedentemente
		                curriculumDAOUp.update(c, username);
		                //Questo serve per risetterare l'entita nel DB
		                c.setFax("");
		                curriculumDAOUp.update(c, username);
		            } catch (InsertFailedException e) {
						e.printStackTrace();
					} catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           connection.close();
		            
		        } catch (SQLException e) {
					e.printStackTrace();
				}	
			
	}	

	@Test
	public void testSearch1() {
		CurriculumDAO curriculumDAOsearch= new CurriculumDAO();
		String username="a.gisi";
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                Boolean controllo;
                Boolean bool=true;
                
                controllo=curriculumDAOsearch.search(username);
                assertEquals(bool,controllo);
            }
      } catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	
	@Test
	public void testSearch2() {
		CurriculumDAO curriculumDAOsearch= new CurriculumDAO();
		String username="a.aaa";
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                Boolean controllo;
                Boolean bool=false;
                
                controllo=curriculumDAOsearch.search(username);
                assertEquals(bool,controllo);
            }
      } catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
}
