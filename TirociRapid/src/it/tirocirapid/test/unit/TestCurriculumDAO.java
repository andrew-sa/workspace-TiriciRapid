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
	protected Connection connection1;
	
	
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
		connection1=null;
		
	}
	
	
	
	@Test //Inserimento Corretto
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
	 

	/*FAX*/
	@Test //TC_AST_1_10 FAX
	public void testCreate1() {
		
		Curriculum cfax1= new Curriculum("123456789123","","","","","","","","","","","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(false);
                Statement statement = (Statement) connection1.createStatement();
                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
                statement.executeUpdate(query);
                curriculumDAOcreate.create(cfax1, user);
                
            } catch (InsertFailedException e) {
            	connection1.close();
				e.printStackTrace();
				
			}   
        } catch (SQLException e) {
        	
			e.printStackTrace();
		} 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			connection1.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	@Test //TC_AST_1_11
	public  void testCreate2() {
			
			Curriculum cfax1= new Curriculum("azz46463","a","a","a","a","a","a","a","a","a","A","a");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	
	
		@Test //TC_AST_1_12 FAX
		public void testCreate3() {
			
			Curriculum cfax1= new Curriculum("08912345","","","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
	
	
		
		@Test //TC_AST_1_13
		public void testCreate4() {
			
			Curriculum cfax1= new Curriculum("0899565232","","","","","","","","","","","");
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(false);
                Statement statement = (Statement) connection1.createStatement();
                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
                statement.executeUpdate(query);
                curriculumDAOcreate.create(cfax1, user);
                
            } catch (InsertFailedException e) {
            	connection1.close();
				e.printStackTrace();
				
			}   
        } catch (SQLException e) {
        	
			e.printStackTrace();
		} 
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			connection1.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
		
	/*ESPERIENZA LAVORATIVA*/
	
		@Test //TC_AST_1_14 VUOTO
		public void testCreate5() {
			
			Curriculum cfax1= new Curriculum("0899565232","","","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		
		}
		
		@Test //TC_AST_1_15 LUNGO
		public void testCreate6() {
			
			Curriculum cfax1= new Curriculum("0899565232","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		
		}
		
		@Test //TC_AST_1_16 LUNGO
		public void testCreate7() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	
		@Test //TC_AST_1_17 CAP PERSONALI
		public void testCreate8() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
		@Test //TC_AST_1_18 CAP PERSONALI
		public void testCreate9() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
		
		}
	
		@Test //TC_AST_1_19 CAP PERSONALI
		public void testCreate10() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_20 MADRELINGUA
		public void testCreate11() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
		
				e.printStackTrace();
			}
		
		}
		
		@Test //TC_AST_1_21 MADRELINGUA
		public void testCreate12() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italianoooooooooooooo","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_22 MADRELINGUA
		public void testCreate13() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_23 Altrelingue
		public void testCreate14() {
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
	
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_24 Altrelingue
		public void testCreate15() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco,Francese,Inglese,Spagnolo,Tedesco,Francese,Inglese,Spagnolo,Tedesco,Francese,Inglese,Spagnolo,Tedesco, ","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_25 Altrelingue
		public void testCreate16() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese Inglese Spagnolo Tedesco","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_26 Altrelingue
		public void testCreate17() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
		
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_27 CAP RELAZIONALI
		public void testCreate18() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_28 CAP RELAZIONALI
		public void testCreate19() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_29 CAP RELAZIONALI
		public void testCreate20() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_30 CAP ORGANIZZATIVE
		public void testCreate21() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_31 CAP ORGANIZZATIVE
		public void testCreate22() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_32 CAP ORGANIZZATIVE
		public void testCreate24() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_33 CAP ORGANIZZATIVE
		public void testCreate25() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_33 CAP Tecniche
		public void testCreate26() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_34 CAP Tecniche
		public void testCreate27() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_35 CAP Tecniche
		public void testCreate28() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_36 CAP Artistiche
		public void testCreate29() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_37 CAP Artistiche
		public void testCreate30() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_38 CAP Artistiche
		public void testCreate31() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_39 CAP Altre
		public void testCreate32() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_40 CAP Altre
		public void testCreate33() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_41 CAP Altre
		public void testCreate34() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_42 Pantenti
		public void testCreate35() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_43 Pantenti
		public void testCreate36() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1,B,A1,C,D1","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_44 Pantenti
		public void testCreate37() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B   A1","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_45 Pantenti
		public void testCreate38() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B,A1","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_46 Pantenti
		public void testCreate39() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B,A1","");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test //TC_AST_1_47 Pantenti
		public void testCreate40() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B,A1","Tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole tante parole");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		@Test //TC_AST_1_48 Pantenti
		public void testCreate41() {
			
			Curriculum cfax1= new Curriculum("0899565232","Ho lavorato presso líazienda AllSafe srl.","Ho competenze elevate nello sviluppo di applicazioni mobile.","Italiano","Francese,Inglese,Spagnolo,Tedesco","Ho ottime capacit‡ relazionali.","Ho ottime capacit‡ di problem solving soprattutto per progetti e lavori di gruppo.","Programmazione JAVA EE","Autonomia nellíutilizzo di chitarra classica e acustica","Capacit‡ nella scrittura di articoli di cultura, arte e attualit‡","B,A1","Disposto ad eventuali trasferte.");
			try {
				connection1 = DriverManagerConnectionPool.getIstance().getConnection();
		
			try
	        {
	            try(Statement stCheck=(Statement) connection1.createStatement())
	            {
	            	connection1.setAutoCommit(false);
	                Statement statement = (Statement) connection1.createStatement();
	                String query="INSERT INTO Studente VALUES('p.prova1','qwertyzxcvb','0349105789','Livio','Esposito','via Annarumma, 5','3287654324','liviocucuzzolo94@outlook.com','l.esposito23@studenti.unisa.it','23.000','Liceo Scientifico')";
	                statement.executeUpdate(query);
	                curriculumDAOcreate.create(cfax1, user);
	                
	            } catch (InsertFailedException e) {
	            	connection1.close();
					e.printStackTrace();
					
				}   
	        } catch (SQLException e) {
	        	
				e.printStackTrace();
			} 
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			try {
				connection1.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		@Test
		public void testRead1() {
			CurriculumDAO curriculumDAOread= new CurriculumDAO();
			
			String fax="09349994511";
			String Capacit‡CompetenzeRelazionali="Sono un attimo collega, e lo dimostrano gli ottimi voti conseguiti negli esami di gruppo che abbiamo dato nei precedenti anni universitari.";
			String Capacit‡CompetenzeTecniche="Conoscenza approfondita di Java e Javascript, oltre che alla maggior parte dei linguaggi utili per la programmazione web.";
			String Capacit‡CompetenzeArtistiche="Utilizzo da almeno due anni Photoshop a livelli alti.";
			String Capacit‡CompetenzePersonali="Sono bravissimo nei bricolage e nei giochi di logica.";
			String Capacit‡CompetenzeOrganizzative="Sono in pari con gli esami dell'universit‡ per quanto abbia dovuto organizzare il mio matrimonio e stia lavorando.";
			String AltreCapacitaCompetenze="Penso di essere veloce ad apprendere, e ho un'ottima memoria visiva.";
			String EsperienzaLavorativa="Ho gi‡ lavorato per 2 anni in un'azienda piccola nel campo dell'elettronica.";
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
			                assertEquals(Capacit‡CompetenzeRelazionali, c.getCapacitaCompetenzeRelazionali());
			                assertEquals(Capacit‡CompetenzeTecniche, c.getCapacitaCompetenzeTecniche());
			                assertEquals(Capacit‡CompetenzeArtistiche, c.getCapacitaCompetenzeArtistiche());
			                assertEquals(Capacit‡CompetenzePersonali, c.getCapacitaCompetenzePersonali());
			                assertEquals(Capacit‡CompetenzeOrganizzative, c.getCapacitaCompetenzeOrganizzative());
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
			                c.setFax("1111111111"); //Basta un solo set perchË i set sono stati testati precedentemente
			                curriculumDAOUp.update(c, username);
			                //Questo serve per risetterare l'entita nel DB
			                c.setFax("");
			                curriculumDAOUp.update(c, username);
			            } catch (InsertFailedException e) {
							e.printStackTrace();
						} catch (TuplaNotFoundException e) {
							
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
