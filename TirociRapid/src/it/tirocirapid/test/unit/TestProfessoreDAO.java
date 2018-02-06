package it.tirocirapid.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
		                
		                String username0="a.aaaa";
		                String password0="aaaaa";
		                int n0=0;
		                int numero0;
		                numero0=professoreDAOSearch.search(username0, password0);
		                
		            } catch (TuplaNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            connection.commit();
		            connection.close();
		        } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	@Test
	public void testRead1() {
		ProfessoreDAO professoreDAORead= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                String username="a.prevete";
		                pro=professoreDAORead.read(username);
		                assertEquals(username,pro.getUsername());
		                System.out.println("SONO NEL TEST READ PROFESSORE:"+username);
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		            connection.commit();
		            connection.close();
		        } catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Test
	public void testRead2() {
		ProfessoreDAO professoreDAORead= new ProfessoreDAO();
		Professore pro;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.ciao1";
		                pro=professoreDAORead.read(username);
		                assertEquals(username,pro.getUsername());
		                System.out.println("SONO NEL TEST READ PROFESSORE:"+username);
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Test
	public void testReadAll() {
		ArrayList<Professore> professori = new ArrayList<>();
		try(Connection con = DriverManagerConnectionPool.getIstance().getConnection();){
			try(Statement stm=(Statement) con.createStatement()){
			ResultSet rs = stm.executeQuery("SELECT Nome, Cognome, EmailIstituzionale, Telefono, Ambito, Email FROM professore");
			while (rs.next()){
				Professore professore = new Professore();
				professore.setNome(rs.getString(1));
				professore.setCognome(rs.getString(2));
				professore.setEmailIstituzionale(rs.getString(3));
				professore.setTelefono(rs.getString(4));
				professore.setAmbito(rs.getString(5));
				professore.setEmail(rs.getString(6));
				professori.add(professore);
			}
			ArrayList<Professore> professoriTest = new ArrayList<>();
			Professore uno = new Professore();
			Professore due = new Professore();
			Professore tre = new Professore();
			uno.setNome("Antonio");
			uno.setCognome("Prevete");
			uno.setEmailIstituzionale("a.prevete@professori.unisa.it");
			uno.setTelefono("3278967434");
			uno.setAmbito("Ingegneria del Software");
			uno.setEmail("prevete@outlook.com");
			due.setNome("Davide");
			due.setCognome("Nini");
			due.setEmailIstituzionale("d.nini@professori.unisa.it");
			due.setTelefono("3279873216");
			due.setAmbito("Programmazione Web");
			due.setEmail("DavNin@outlook.com");
			tre.setNome("Giuseppe");
			tre.setCognome("Tomeo");
			tre.setEmailIstituzionale("a.tomeo8@professori.unisa.it");
			tre.setTelefono("3255674564");
			tre.setAmbito("Sicurezza");
			tre.setEmail("sicuinfo@outlook.com");
			professoriTest.add(uno);
			professoriTest.add(due);
			professoriTest.add(tre);
			for(Professore i: professori) {
				int w=0;
				Professore control = professoriTest.get(w);
				i.equals(control);
				w++;
			}
			con.commit();
			rs.close();
			stm.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			}
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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