package it.tirocirapid.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Statement;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.ProfessoreDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestProfessoreDAO extends TestCase{

	@BeforeEach
	protected void setUp(){
	}

	@AfterEach
	protected void tearDown(){
	}	

	@Test
	public void testSearch1() {
		ProfessoreDAO professoreDAOSearch= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.prevete";
		                String password="qwelkjdsa";
		                //2 
		                //1
		                //0
		                int n1=1;
		                int numero;
		                numero=professoreDAOSearch.search(username, password);
		                System.out.println("SONO NEL TEST SEARCH PROFESSORE:"+numero);
		                assertEquals(n1, numero);
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

	@Test
	public void testSearch2() {
		ProfessoreDAO professoreDAOSearch= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username2="g.tomeo8";
		                String password2="yuidfgzxc";
		                int n2=2;
		                int numero2;
		                numero2=professoreDAOSearch.search(username2, password2);
		                System.out.println("SONO NEL TEST SEARCH PROFESSORE:"+numero2);
		                assertEquals(n2, numero2);
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	@Test
	public void testSearch3() {
		ProfessoreDAO professoreDAOSearch= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username0="g.tomeo8";
		                String password0="aaaaa";
		                int n0=0;
		                int numero0;
		                numero0=professoreDAOSearch.search(username0, password0);
		                System.out.println("SONO NEL TEST SEARCH PROFESSORE:"+numero0);
		                assertEquals(n0, numero0);
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	@Test
	public void testSearch4() {
		ProfessoreDAO professoreDAOSearch= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username0="a.aaaa";
		                String password0="aaaaa";
		                int n0=0;
		                int numero0;
		                numero0=professoreDAOSearch.search(username0, password0);
		                
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	@Test
	public void testRead() throws SQLException, TuplaNotFoundException {
		ProfessoreDAO professoreDAORead= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="a.prevete";
		                pro=professoreDAORead.read(username);
		                assertEquals(username,pro.getUsername());
		                System.out.println("SONO NEL TEST READ PROFESSORE:"+username);
		            }
		           
		            connection.close();
		        }
	}
	
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmail1(){
		ProfessoreDAO professoreDAOEmail= new ProfessoreDAO();
		String email;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="d.nini";
		                email = professoreDAOEmail.readEmail(username);
		                String emailvera="d.nini@professori.unisa.it";
		                assertEquals(email,emailvera);
		                System.out.println("SONO NEL TEST READ EMAIL:"+email);
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
}
	
	@Test
	public void testEmail2(){
		ProfessoreDAO professoreDAOEmail= new ProfessoreDAO();
		String email;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.a";
		                email = professoreDAOEmail.readEmail(username);
		            
		            } catch (TuplaNotFoundException e) {
				
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
}
}