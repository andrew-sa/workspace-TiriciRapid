package it.tirocirapid.test.unit;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.CurriculumDAO;
import it.tirocirapid.classes.manager.ProfessoreDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Curriculum;
import it.tirocirapid.classes.model.Professore;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestAziendaDAO extends TestCase{
	
	protected Connection connection1;
	protected AziendaDAO aziendadao;
	
	@BeforeEach
	public void setUp() {
		aziendadao=new AziendaDAO();
		
	}

	@AfterEach
	public void tearDown() {
		aziendadao=null;
		connection1=null;
	}
	
	@Test
	public void testSearch1()  {
		AziendaDAO aziendaDAOsearch= new AziendaDAO();
		String iva="01281201218";
        String pass="rtyfghcvb";
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                
                controllo=aziendaDAOsearch.search(iva,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
      } catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	
	@Test
	public void testSearch2()  {
		AziendaDAO aziendaDAOsearch= new AziendaDAO();
		String iva="01281201218";
        String pass="111";
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                
                controllo=aziendaDAOsearch.search(iva,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
      } catch (SQLException e) {
	
		e.printStackTrace();
	}
	}

	public void testSearch3()  {
		AziendaDAO aziendaDAOsearch= new AziendaDAO();
		String iva="aaa";
        String pass="rtyfghcvb";
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=false;
                
                controllo=aziendaDAOsearch.search(iva,pass);
                assertEquals(bool,controllo);
            } catch (TuplaNotFoundException e) {
				
				e.printStackTrace();
			}
      } catch (SQLException e) {
	
		e.printStackTrace();
	}
	}
	
	@Test
	public void testCreate() {
		AziendaDAO aziendaDAOcreate= new AziendaDAO();
		Azienda azienda;
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                /*CANCELLARE LA TUPLA DI TESTIG PRIMA DI ESEGUIRE*/
                //stCheck.executeUpdate("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");

                String partitaIVA="partitaIVA";
                String password="password";
                String email="email";
                String nome="nome";
                String sede="sede";
                String numero="3333333333";
                String stato="stato";
                String descrizioneAmbito="descrizioneAmbito";
                
                azienda=new Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
                aziendaDAOcreate.create(azienda);

                String partitaIVAID;
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='partitaIVA'"))
                {
                    assertTrue(rs.next());
                    partitaIVAID=rs.getString("PartitaIVA");
                    System.out.println("SONO NEL TEST CREATE:"+partitaIVAID);
                    PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");
                    ps.executeUpdate();
                }
                connection.close();
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test //TC_AAZ_1_07 Partiata iva vuota
	public void testCreate1() {  //01281201299
		Azienda azienda= new Azienda(null,"","","","","",null,"","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(false);
                aziendadao.create(azienda);              
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA=''"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 connection1.rollback();
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
	
	@Test //TC_AAZ_1_08 Partiata iva sbagliata nel formato
	public void testCreate2() {  //01281201299
		Azienda azienda= new Azienda("blabla123456","","","","","",null,"","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(false);
                aziendadao.create(azienda);
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='blabla123456'"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 connection1.rollback();
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
	
	@Test //TC_AAZ_1_09 Partiata iva sbagliata nel formato
	public void testCreate3() {  //01281201299
		Azienda azienda= new Azienda("blabla123","","","","","",null,"","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(true);
                aziendadao.create(azienda);  
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='blabla123'"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='blabla123'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 connection1.rollback();
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
	
	@Test //TC_AAZ_1_10 Partiata iva sbagliata nel formato 
	public void testCreate4() {  //01281201299
		Azienda azienda= new Azienda("blabla12345","","","","","",null,"","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(true);
                aziendadao.create(azienda);
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='blabla12345'"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='blabla12345'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 connection1.rollback();
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
	
	@Test //TC_AAZ_1_11 Partiata iva ok
	public void testCreate5() {  //01281201299
		Azienda azienda= new Azienda("07643520567","","","","","",null,"","");
		
		try {
			connection1 = DriverManagerConnectionPool.getIstance().getConnection();
	
		try
        {
            try(Statement stCheck=(Statement) connection1.createStatement())
            {
            	connection1.setAutoCommit(true);
                aziendadao.create(azienda);
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='07643520567'"))
                {
                    assertTrue(rs.next());
                    PreparedStatement ps= (PreparedStatement) connection1.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='07643520567'");
                    ps.executeUpdate();
                }
               
                connection1.close();
            } catch (InsertFailedException e) {
            	 connection1.rollback();
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
	public void testCreate6() {
		AziendaDAO aziendaDAOcreate= new AziendaDAO();
		Azienda azienda;
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);
                /*CANCELLARE LA TUPLA DI TESTIG PRIMA DI ESEGUIRE*/
                //stCheck.executeUpdate("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");

                String partitaIVA="01281201218";
                String password="password";
                String email="email";
                String nome="nome";
                String sede="sede";
                String numero="3333333333";
                String stato="stato";
                String descrizioneAmbito="descrizioneAmbito";
                
                azienda=new Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
                aziendaDAOcreate.create(azienda);

                String partitaIVAID;
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda WHERE PartitaIVA='partitaIVA'"))
                {
                    assertTrue(rs.next());
                    partitaIVAID=rs.getString("PartitaIVA");
                    System.out.println("SONO NEL TEST CREATE:"+partitaIVAID);
                    PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='partitaIVA'");
                    ps.executeUpdate();
                }
                connection.close();
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead() {
		AziendaDAO aziendaDAOread= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201218";
		                az=aziendaDAOread.read(iva);
		                assertEquals(iva,az.getPartitaIVA());
		                System.out.println("SONO NEL TEST READ:"+iva);
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
		AziendaDAO aziendaDAOread= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201217";
		                az=aziendaDAOread.read(iva);
		                assertEquals(iva,az.getPartitaIVA());
		                System.out.println("SONO NEL TEST READ:"+iva);
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
		
		AziendaDAO aziendaDAOUp= new AziendaDAO();
		
		String partitaIVA="a";
        String password="a";
        String email="a";
        String nome="a";
        String sede="a";
        String numero="a";
        String stato="a";
        String descrizioneAmbito="a";
        
        Azienda a = new Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(true);
		                aziendaDAOUp.create(a);
		                Azienda b=aziendaDAOUp.read(partitaIVA);
		        		
		                String email1="b";
		                String nome1="b";
		                String sede1="b";
		                String numero1="b";
		                String stato1="b";
		                String descrizioneAmbito1="b";
		                b.setEmail(email1);
		                b.setNome(nome1);
		                b.setSede(sede1);
		                b.setNumeroTelefono(numero1);
		                b.setStato(stato1);
		                b.setDescrizioneAmbito(descrizioneAmbito1);
		                aziendaDAOUp.update(b);
		                assertEquals(partitaIVA,b.getPartitaIVA());
		                assertEquals(password,b.getPassword());
		                assertEquals(email1,b.getEmail());
		                System.out.println("SONO NELl' UPDATE:"+b.getPartitaIVA());
		                System.out.println("SONO NELl' UPDATE:"+b.getPassword());
		                System.out.println("SONO NELl' UPDATE:"+b.getEmail());
		                PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='a'");
	                    ps.executeUpdate();
		                
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
	public void testReadAll() {
		AziendaDAO aziendaDAOreadAll= new AziendaDAO();
		ArrayList<Azienda> aziendaTest = new ArrayList<>();
		ArrayList<Azienda> azienda = new ArrayList<>();
		try(Connection con = DriverManagerConnectionPool.getIstance().getConnection();){
			try(Statement stm=(Statement) con.createStatement()){
					con.setAutoCommit(true);
					azienda=aziendaDAOreadAll.readAll();
					Azienda uno = new Azienda("01281201218","rtyfghcvb","soferm1@biu.com","Sofer","Napoli","08119365223", null,"AccettaRichieste","Edilizia");
					Azienda due = new Azienda("06653061215","wersdfxcv","info@webapp.it","WEBAPP","Napoli","0815706309", null,"AccettaRichieste","App personalizzate e software gestionali");
					Azienda tre = new Azienda("07071080639","poiuytlkj","sales@vulcanair.com","Vulcanair","Napoli","0815918111", null,"AccettaRichieste","Fabbricazione di aeromobili, di veicoli spaziali e dei relativi dispositivi nca");
					aziendaTest.add(uno);
					aziendaTest.add(due);
					aziendaTest.add(tre);
					for(int i=0; i<=azienda.size()-1; i++) {
						assertEquals(azienda.get(i).getPartitaIVA(), aziendaTest.get(i).getPartitaIVA());
						assertEquals(azienda.get(i).getPassword(), aziendaTest.get(i).getPassword());
						assertEquals(azienda.get(i).getEmail(), aziendaTest.get(i).getEmail());
						assertEquals(azienda.get(i).getNome(), aziendaTest.get(i).getNome());
						assertEquals(azienda.get(i).getSede(), aziendaTest.get(i).getSede());
						assertEquals(azienda.get(i).getNumeroTelefono(), aziendaTest.get(i).getNumeroTelefono());
						assertEquals(azienda.get(i).getStato(), aziendaTest.get(i).getStato());
						assertEquals(azienda.get(i).getDescrizioneAmbito(), aziendaTest.get(i).getDescrizioneAmbito());
					}
					con.commit();
					stm.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
				}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadEmail1() {
		AziendaDAO aziendaDAOEmail= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201218";
		                az = aziendaDAOEmail.read(iva);
		                String email=aziendaDAOEmail.readEmail(iva);
		                
		                assertEquals(email,az.getEmail());
		                System.out.println("SONO NEL TEST READ EMAIL:"+iva);
		            } catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	@Test
	public void testReadEmail2() {
		AziendaDAO aziendaDAOEmail= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201266";
		                
		                String email=aziendaDAOEmail.readEmail(iva);
		                
		                //assertEquals(email,az.getEmail());
		                System.out.println("SONO NEL TEST READ EMAIL:"+iva);
		            } catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	@Test
	public void testReadPassword1() {
		AziendaDAO aziendaDAOEmail= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201218";
		                az = aziendaDAOEmail.read(iva);
		                String password=aziendaDAOEmail.readPassword(iva);
		                
		                assertEquals(password,az.getPassword());
		                System.out.println("SONO NEL TEST READ PASSWORD:"+iva);
		            } catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	@Test
	public void testReadPassword2() {
		AziendaDAO aziendaDAOEmail= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201266";
		                
		                String password=aziendaDAOEmail.readPassword(iva);
		                
		                //assertEquals(password,az.getPassword());
		                System.out.println("SONO NEL TEST READ PASSWORD:"+iva);
		            } catch (TuplaNotFoundException e) {
						
						e.printStackTrace();
					}
		            connection.close();
		        } catch (SQLException e) {
					
					e.printStackTrace();
				}
	}
	
	@Test
	public void testUpdateStato1()  {
		AziendaDAO aziendaDAOUpStato= new AziendaDAO();
		String partitaIVA="c";
        String password="c";
        String email="c";
        String nome="c";
        String sede="c";
        String numero="c";
        String stato="c";
        String descrizioneAmbito="c";
        
        Azienda a = new Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		            	connection.setAutoCommit(true);
		            	aziendaDAOUpStato.create(a);
		            	Azienda b=aziendaDAOUpStato.read(partitaIVA);
		                String stato1="aaaaaaaaa";
		                aziendaDAOUpStato.updateStato(partitaIVA, stato1);
		                assertEquals(stato,b.getStato());
		                System.out.println("SONO NELl' UPDATE:"+b.getStato());
		                PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='c'");
	                    ps.executeUpdate();
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
	public void testUpdateStato2()  {
		AziendaDAO aziendaDAOUpStato= new AziendaDAO();
		String partitaIVA="c";
        String password="c";
        String email="c";
        String nome="c";
        String sede="c";
        String numero="c";
        String stato="c";
        String descrizioneAmbito="c";
        
        Azienda a = new Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		            	connection.setAutoCommit(true);
		            	aziendaDAOUpStato.create(a);
		            	Azienda b=aziendaDAOUpStato.read(partitaIVA);
		                String stato1="aaaaaaaaa";
		                aziendaDAOUpStato.updateStato("aaa", stato1);
		                assertEquals(stato,b.getStato());
		                System.out.println("SONO NELl' UPDATE:"+b.getStato());
		                PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM azienda WHERE PartitaIVA='c'");
	                    ps.executeUpdate();
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
	public void testReadDisponibili() {
		AziendaDAO aziendaDAOreadAll= new AziendaDAO();
		ArrayList<Azienda> aziendaTest = new ArrayList<>();
		ArrayList<Azienda> azienda = new ArrayList<>();
		try(Connection con = DriverManagerConnectionPool.getIstance().getConnection();){
			try(Statement stm=(Statement) con.createStatement()){
					con.setAutoCommit(true);
					azienda=aziendaDAOreadAll.readDisponibili();
					Azienda uno = new Azienda("01281201218","rtyfghcvb","soferm1@biu.com","Sofer","Napoli","08119365223", null,"AccettaRichieste","Edilizia");
					Azienda due = new Azienda("06653061215","wersdfxcv","info@webapp.it","WEBAPP","Napoli","0815706309", null,"AccettaRichieste","App personalizzate e software gestionali");
					Azienda tre = new Azienda("07071080639","poiuytlkj","sales@vulcanair.com","Vulcanair","Napoli","0815918111", null,"AccettaRichieste","Fabbricazione di aeromobili, di veicoli spaziali e dei relativi dispositivi nca");
					aziendaTest.add(uno);
					aziendaTest.add(due);
					aziendaTest.add(tre);
					for(int i=0; i<=azienda.size()-1; i++) {
						assertEquals(azienda.get(i).getPartitaIVA(), aziendaTest.get(i).getPartitaIVA());
						assertEquals(azienda.get(i).getPassword(), aziendaTest.get(i).getPassword());
						assertEquals(azienda.get(i).getEmail(), aziendaTest.get(i).getEmail());
						assertEquals(azienda.get(i).getNome(), aziendaTest.get(i).getNome());
						assertEquals(azienda.get(i).getSede(), aziendaTest.get(i).getSede());
						assertEquals(azienda.get(i).getNumeroTelefono(), aziendaTest.get(i).getNumeroTelefono());
						assertEquals(azienda.get(i).getStato(), aziendaTest.get(i).getStato());
						assertEquals(azienda.get(i).getDescrizioneAmbito(), aziendaTest.get(i).getDescrizioneAmbito());
					}
					con.commit();
					stm.close();
					DriverManagerConnectionPool.getIstance().releaseConnection(con);
				}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

}
