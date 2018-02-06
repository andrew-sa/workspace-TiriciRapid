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
import it.tirocirapid.classes.manager.TirocinioDAO;
import it.tirocirapid.classes.model.Azienda;
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
	public void testIsNewKey() {
		fail("Not yet implemented");
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
	public void testDelete() {
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
	public void testReadAllTirociniAzienda() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadAllTirociniDisponibiliAzienda() {
		fail("Not yet implemented");
	}

	@Test
	public void testReadAllTirociniInAttesaApprovazione() {
		fail("Not yet implemented");
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
