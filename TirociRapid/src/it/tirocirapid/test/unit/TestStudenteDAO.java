package it.tirocirapid.test.unit;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mysql.jdbc.Statement;
import it.tirocirapid.classes.manager.StudenteDAO;
import it.tirocirapid.classes.model.Studente;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestStudenteDAO extends TestCase{

	protected StudenteDAO s;
	@BeforeEach
	protected void setUp(){
		 s=new StudenteDAO();
	}

	@AfterEach
	protected void tearDown(){
		s=null;
	}

	
	@Test // TC_AST_1_01
	public void testSearch1(){
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                String username="";
                String pass="";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test // TC_AST_1_02
	public void testSearch2(){
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                String username="rus55.alberto";
                String pass="";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test // TC_AST_1_03
	public void testSearch3(){
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                String username="GiovanniGiovanniGiovanniGiovanniGioGio";
                String pass="";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test // TC_AST_1_04
	public void testSearch4(){
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                String username="e.ferrari100";
                String pass="";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test //Formato non corretto username 1_05 e 1_08
	public void testSearch5(){
		
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
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
		
			e.printStackTrace();
		}	
	}
	
	@Test // TC_AST_1_06
	public void testSearch6(){
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                String username="a.gisi";
                String pass="";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}	
	
	@Test // TC_AST_1_07
	public void testSearch7() {
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                String username="aaa";
                String pass="aaaaaaaa";
                controllo=s.search(username,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
            connection.close();
        } catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	
	@Test //TC_AST_1_01 VUOTO
	public void testRead1() {

		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String username="";
		                st=s.read(username);
		                assertEquals(username,st.getUsername());
		                System.out.println("SONO NEL TEST READ:"+username);
		            }catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
			
					e.printStackTrace();
				}
	}
	
	@Test //TC_AST_1_03
	public void testRead2() {
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="rus55so.alberto";
		                st=s.read(username);
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
	
	@Test //TC_AST_1_03
	public void testRead3() {
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="GiovanniGiovanniGiovanniGiovanniGioGio";
		                st=s.read(username);
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

	@Test //TC_AST_1_04
	public void testRead4() {
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="e.ferrari100";
		                st=s.read(username);
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
	
	@Test //TC_AST_1_05
	public void testRead5() {
		Studente st;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.gisi";
		                st=s.read(username);
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
	public void testReadEmail1() {

				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.gisi";
		                String emailprova="a.gisi@studenti.unisa.it";
		                String email=null;
		                email = s.readEmail(username); 
		                assertEquals(email,emailprova);
		            } catch (TuplaNotFoundException e) {
					
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
				
					e.printStackTrace();
				}
	}

	public void testReadEmail2() {

				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                String username="a.a";
		                String emailprova="a.gisi@studenti.unisa.it";
		                String email=null;
		                email = s.readEmail(username); 
		                assertEquals(email,emailprova);
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		            connection.close();
		        }catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	
}
