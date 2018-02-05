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
import it.tirocirapid.classes.manager.StudenteDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestStudenteDAO extends TestCase{

	@BeforeEach
	protected void setUp(){
	}

	@AfterEach
	protected void tearDown(){
	}

	@Test
	public void testSearch1(){
		StudenteDAO studenteDAOsearch= new StudenteDAO();
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                String username="a.gisi";
                String pass="tyudfgzxc";
                controllo=studenteDAOsearch.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testSearch2() {
		StudenteDAO studenteDAOsearch= new StudenteDAO();
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                String username="a.gisi";
                String pass="aaa";
                controllo=studenteDAOsearch.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}	
	
	@Test
	public void testSearch3() {
		StudenteDAO studenteDAOsearch= new StudenteDAO();
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                String username="aaa";
                String pass="aaa";
                controllo=studenteDAOsearch.search(username,pass);
               // assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testRead1() {
		StudenteDAO aziendaDAOread= new StudenteDAO();
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="a.a";
		                st=aziendaDAOread.read(username);
		                assertEquals(username,st.getUsername());
		                System.out.println("SONO NEL TEST READ:"+username);
		            } catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
			
					e.printStackTrace();
				}
	}
	
	@Test
	public void testRead2() throws SQLException, TuplaNotFoundException {
		StudenteDAO aziendaDAOread= new StudenteDAO();
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="a.gisi";
		                st=aziendaDAOread.read(username);
		                assertEquals(username,st.getUsername());
		                System.out.println("SONO NEL TEST READ:"+username);
		            }
		            connection.close();
		        }
	}	
	
	@Test
	public void testReadEmail1() throws SQLException, TuplaNotFoundException {
		StudenteDAO aziendaDAOEmail= new StudenteDAO();

				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="a.gisi";
		                String emailprova="a.gisi@studenti.unisa.it";
		                String email=null;
		                email = aziendaDAOEmail.readEmail(username); 
		                assertEquals(email,emailprova);
		            }
		            connection.close();
		        }
	}

	public void testReadEmail2() {
		StudenteDAO aziendaDAOEmail= new StudenteDAO();

				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="a.a";
		                String emailprova="a.gisi@studenti.unisa.it";
		                String email=null;
		                email = aziendaDAOEmail.readEmail(username); 
		                assertEquals(email,emailprova);
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
	
	
}
