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
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestAziendaDAO extends TestCase{
	
	
	@BeforeEach
	public void setUp() throws Exception {

		
	}

	@AfterEach
	public void tearDown() throws Exception {
		
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
	
	@Test  /*PROBLEMA DI VISIBILITA'  E' UN METODO STATICO PRIVATO*/
	public void testIsNewKey()  {
		fail("Not yet implemented");	
		/*
			AziendaDAO aziendais=new AziendaDAO();
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		           
		            	Boolean falso= false;
		            	Boolean vero=true;
		            	String iva="07643520567";
		                String pass="ciao";
		            }
		            finally
		            {
		                 connection.close();;
		            }
		        }	
		        */
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		           connection.close();
		            
		        } catch (SQLException e) {
					e.printStackTrace();
				}	
		        
	}
	
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
		/*
		AziendaDAO aziendaDAOreadAll= new AziendaDAO();
		ArrayList<Azienda> aziendearray=new ArrayList<Azienda>();
		ArrayList<Azienda> aziendearraydiconfronto = new ArrayList<Azienda>();
		
		Connection connection=DriverManagerConnectionPool.getIstance().getConnection();
		Statement stCheck=(Statement) connection.createStatement();
		Azienda temp=null;
		System.out.println("aziendearraydiconfronto");
//		int k=0;
		try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM azienda"))
        {
			while(rs.next()) {
//				Azienda a=(Azienda)rs.getArray(k);
//				System.out.println(a.toString());
				//Azienda(partitaIVA,password,email,nome,sede,numero,null,stato,descrizioneAmbito);
				temp=new Azienda(rs.getString("PartitaIVA"),rs.getString("Pass"),rs.getString("Email"),
						rs.getString("Nome"),rs.getString("Sede"),rs.getString("NumeroTelefono"),null,
						rs.getString("Stato"),rs.getString("DescrizioneAmbito"));
//	            temp.setPartitaIVA(rs.getString("PartitaIVA"));
//	            temp.setPassword(rs.getString("Pass"));
//	            temp.setEmail("Email");
//	            temp.setNome("Nome");
//	            temp.setSede("Sede");
//	            temp.setNumeroTelefono("NumeroTelefono");
//	            temp.setStato("Stato");
//	            temp.setDescrizioneAmbito("Descrizione");
//	            temp.setDescrizioneAmbito("DescrizioneAmbito");
	            aziendearraydiconfronto.add(temp);
//	            k++;
	            System.out.println(temp.toString());
			}
        	
        } 
		System.out.println("aziendearray");
		try(Connection connection2=DriverManagerConnectionPool.getIstance().getConnection();)
		{
			try(Statement stCheck2=(Statement) connection.createStatement())
			{
				connection.setAutoCommit(false);
				aziendearray=aziendaDAOreadAll.readAll();
				
				for(int i=0;i<aziendearray.size();i++) {
					 System.out.println(aziendearray.get(i).toString());
					 aziendearray.get(i).setTirociniOfferti(null);
					assertEquals(aziendearray.get(i), aziendearraydiconfronto.get(i));
					
				}
				//for(int i = 0;i<aziendearray.size();i++)
				//assertArrayEquals(aziendearray,aziendearraydiconfronto);
				//assertEquals(aziendearray, aziendearraydiconfronto);
			}
		          
		 }
		 */
	}
	
	@Test
	public void testReadEmail() {
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
	public void testReadPassword() {
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
	public void testUpdateStato()  {
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
						// TODO Auto-generated catch block
						e.printStackTrace();
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
