package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.RichiestaTirocinioDAO;
import it.tirocirapid.classes.manager.TirocinioDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.RichiestaTirocinio;
import it.tirocirapid.classes.model.Tirocinio;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;
import junit.framework.TestCase;

public class TestTirocinioDAO extends TestCase{

	@BeforeEach
	protected void setUp(){
	}

	@AfterEach
	protected void tearDown(){
	}

	@Test
	public void testCreate1() {
		TirocinioDAO tirocinioDAOcreate= new TirocinioDAO();
		Tirocinio t;
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);

                String partitaIVA="06653061215"; // serve quella gia presente
                String nome="aaa";
                String descrizione="aaa";
                String offertaformativa="aaa";
                String stato="aaa";
                t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
                tirocinioDAOcreate.create(t);
                
                String partitaIVAID;
                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM tirocinio WHERE PartitaIVA='06653061215'"))
                {
                    assertTrue(rs.next());
                    partitaIVAID=rs.getString("PartitaIVA");
                    System.out.println("SONO NEL TEST CREATE TIROCINIO1:"+partitaIVAID);
                    
                }
                PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM tirocinio WHERE nome='aaa'");
                ps.executeUpdate();
                connection.close();
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void testCreate2() {
		TirocinioDAO tirocinioDAOcreate= new TirocinioDAO();
		Tirocinio t;
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);

                String partitaIVA="06653061215"; // serve quella gia presente
                String nome="Tirocinio di Java applicata";
                String descrizione="aaa";
                String offertaformativa="aaa";
                String stato="aaa";
                t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
                tirocinioDAOcreate.create(t);
                System.out.println("SONO NEL TEST CREATE TIROCINIO2 QUESTO DEVE FALLIRE:"+partitaIVA);
                connection.close();
                
                tirocinioDAOcreate.delete(partitaIVA, nome);
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void testCreate3() {
		TirocinioDAO tirocinioDAOcreate= new TirocinioDAO();
		Tirocinio t;
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);

                String partitaIVA="06653061215"; // serve quella gia presente
                String nome="Tirocinio di C";  //già presenti
                String descrizione="aaa";
                String offertaformativa="aaa";
                String stato="aaa";
                t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
                tirocinioDAOcreate.create(t);
                System.out.println("SONO NEL TEST CREATE TIROCINIO2 QUESTO DEVE FALLIRE:"+partitaIVA);
                connection.close();
                
                tirocinioDAOcreate.delete(partitaIVA, nome);
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}	
	@Test
	public void testUpdate1() {
		TirocinioDAO tirocinioDAOUp= new TirocinioDAO();
		Tirocinio t;
				
		String partitaIVA="06653061215"; // serve quella gia presente
        String nome="Tirocinio di Java applicata";
        String descrizione="ccc";
        String offertaformativa="ccc";
        String stato="ccc";
        t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
						try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
				        {
				            try(Statement stCheck=(Statement) connection.createStatement())
				            {
				                connection.setAutoCommit(true);
				                tirocinioDAOUp.create(t);
				                String statonuovo="d";
				                tirocinioDAOUp.update(partitaIVA, nome, statonuovo);
				                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM tirocinio WHERE Stato='bbbb'"))
				                {
				                    PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM tirocinio WHERE Stato='d'");
				                    ps.executeUpdate();
				                }
				                
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
	public void testUpdate2() {
		TirocinioDAO tirocinioDAOUp= new TirocinioDAO();
		Tirocinio t;
				
		String partitaIVA="06653061215"; // serve quella gia presente
        String nome="Tirocinio di Java applicata";
        String descrizione="ccc";
        String offertaformativa="ccc";
        String stato="ccc";
        t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
						try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
				        {
				            try(Statement stCheck=(Statement) connection.createStatement())
				            {
				                connection.setAutoCommit(true);
				                tirocinioDAOUp.create(t);
				                String statonuovo="d";
				                String iva="11111111";
				                tirocinioDAOUp.update(partitaIVA, nome, statonuovo);
				                try(ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM tirocinio WHERE Stato='bbbb'"))
				                {
				                    PreparedStatement ps= (PreparedStatement) connection.prepareStatement("DELETE FROM tirocinio WHERE Stato='d'");
				                    ps.executeUpdate();
				                }
				                
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
	public void testUpdate3() {
		try {
			TirocinioDAO tDAOUpdate = new TirocinioDAO();
			tDAOUpdate.update("a", "a", "a");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead1() {
		TirocinioDAO tirocinioDAOread= new TirocinioDAO();
		Tirocinio t;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="01281201218";
		                String nome="Tirocinio di Java applicata";
		                t=tirocinioDAOread.read(iva, nome);
		                assertEquals(iva,t.getPartitaIVAAzienda());
		                assertEquals(nome,t.getNome());
		                System.out.println("SONO NEL TEST READ TIROCINO:"+iva);
		                System.out.println("SONO NEL TEST READ TIROCINO:"+nome);
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					e.printStackTrace();
				}
	}

	public void testRead2() {
		TirocinioDAO tirocinioDAOread= new TirocinioDAO();
		Tirocinio t;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="ZZZZ";
		                String nome="ZZZZ";
		                t=tirocinioDAOread.read(iva, nome);
		            } catch (TuplaNotFoundException e) {
						e.printStackTrace();
					}
		           
		            connection.close();
		        } catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	@Test
	public void testDelete1() {
		TirocinioDAO tirocinioDAOdelete= new TirocinioDAO();
		Tirocinio t;
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);

                String partitaIVA="06653061215"; // serve quella gia presente
                String nome="zzz";
                String descrizione="zzz";
                String offertaformativa="zzz";
                String stato="zzz";
                t=new Tirocinio(partitaIVA,nome,descrizione,offertaformativa,stato);
                tirocinioDAOdelete.create(t);
                tirocinioDAOdelete.delete(partitaIVA, nome);
                ResultSet rs=(ResultSet) stCheck.executeQuery("SELECT * FROM tirocinio WHERE nome='aaa'&& PartitaIVA='06653061215'");
                    assertFalse(rs.next());
                connection.close();
            } catch (InsertFailedException e) {
				
				e.printStackTrace();
			}   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	@Test
	public void testDelete2() {
		try {
			TirocinioDAO tDAOReadAllTirociniAzienda = new TirocinioDAO();
			tDAOReadAllTirociniAzienda.delete("aaa", "bbbb");
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InsertFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testReadAllTirociniAzienda() {
		try {
			ArrayList<Tirocinio> richieste = new ArrayList<>();
			ArrayList<Tirocinio> richiesteTest = new ArrayList<>();
			TirocinioDAO tDAOReadAllTirociniAzienda = new TirocinioDAO();
			richieste = tDAOReadAllTirociniAzienda.readAllTirociniAzienda("06653061215");
			Tirocinio testt1= new Tirocinio("06653061215", "Tirocinio di C", "Studiare come usare c per creare un sito web.", "C", "TirRif");
			Tirocinio testt2= new Tirocinio("06653061215", "Tirocinio di PHP", "Due mesi di studio e creazione di siti web di interfaccia di applicazioni per cellulare.", "PHP, CSS, HTML, JQUERY", "TirConf");
			richiesteTest.add(testt1);
			richiesteTest.add(testt2);
			System.out.println(richieste);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getPartitaIVAAzienda(), richiesteTest.get(i).getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getNome(), richiesteTest.get(i).getNome());
				assertEquals(richieste.get(i).getDescrizione(), richiesteTest.get(i).getDescrizione());
				assertEquals(richieste.get(i).getOffertaFormativa(), richiesteTest.get(i).getOffertaFormativa());
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAllTirociniDisponibiliAzienda1() {
		try {
			ArrayList<Tirocinio> richieste = new ArrayList<>();
			ArrayList<Tirocinio> richiesteTest = new ArrayList<>();
			TirocinioDAO tDAOReadAllTirociniDisponibiliAzienda = new TirocinioDAO();
			richieste = tDAOReadAllTirociniDisponibiliAzienda.readAllTirociniDisponibiliAzienda("06653061215");
			Tirocinio testt1= new Tirocinio("06653061215", "Tirocinio di PHP", "Due mesi di studio e creazione di siti web di interfaccia di applicazioni per cellulare.", "PHP, CSS, HTML, JQUERY", "TirConf");
			richiesteTest.add(testt1);
			System.out.println(richieste);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getPartitaIVAAzienda(), richiesteTest.get(i).getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getNome(), richiesteTest.get(i).getNome());
				assertEquals(richieste.get(i).getDescrizione(), richiesteTest.get(i).getDescrizione());
				assertEquals(richieste.get(i).getOffertaFormativa(), richiesteTest.get(i).getOffertaFormativa());
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
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
	public void testReadAllTirociniDisponibiliAzienda2() {
		try {
			ArrayList<Tirocinio> richieste = new ArrayList<>();
			TirocinioDAO tDAOReadAllTirociniDisponibiliAzienda = new TirocinioDAO();
			richieste = tDAOReadAllTirociniDisponibiliAzienda.readAllTirociniDisponibiliAzienda("01281201218");
			assertTrue(richieste.isEmpty());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TuplaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testReadAllTirociniInAttesaApprovazione() {
		try {
			ArrayList<Tirocinio> richieste = new ArrayList<>();
			ArrayList<Tirocinio> richiesteTest = new ArrayList<>();
			TirocinioDAO tDAOReadAllTirociniInAttesaApprovazione = new TirocinioDAO();
			richieste = tDAOReadAllTirociniInAttesaApprovazione.readAllTirociniInAttesaApprovazione();
			Tirocinio testt1= new Tirocinio("01281201218", "Tirocinio di Java applicata", "Passa due mesi con noi a vedere come si può applicare java all'edilizia tramite l'utilizzo di programmi specifici.", "JAVA applicata", "TirProp");
			richiesteTest.add(testt1);
			System.out.println(richieste);
			for(int i=0; i<= richieste.size()-1; i++) {
				assertEquals(richieste.get(i).getPartitaIVAAzienda(), richiesteTest.get(i).getPartitaIVAAzienda());
				assertEquals(richieste.get(i).getNome(), richiesteTest.get(i).getNome());
				assertEquals(richieste.get(i).getDescrizione(), richiesteTest.get(i).getDescrizione());
				assertEquals(richieste.get(i).getOffertaFormativa(), richiesteTest.get(i).getOffertaFormativa());
				assertEquals(richieste.get(i).getStato(), richiesteTest.get(i).getStato());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCountByAzienda() {
		TirocinioDAO tirocinioDAOcount= new TirocinioDAO();
		Tirocinio t;
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(true);

                String partitaIVA="06653061215";
                
                int numero=2;
                int numeroritorno = 0;
                numeroritorno= tirocinioDAOcount.countByAzienda(partitaIVA);
                assertEquals(numero, numeroritorno);
                connection.close();
            }   
        } catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
