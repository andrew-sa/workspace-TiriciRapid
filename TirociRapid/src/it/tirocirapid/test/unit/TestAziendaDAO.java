package it.tirocirapid.test.unit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.database.DriverManagerConnectionPool;
import it.tirocirapid.eccezioni.InsertFailedException;
import it.tirocirapid.eccezioni.TuplaNotFoundException;

public class TestAziendaDAO {
	
	
	@BeforeEach
	public void setUp() throws Exception {

		
	}

	@AfterEach
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testSearch() throws SQLException, TuplaNotFoundException {
		AziendaDAO aziendaDAOsearch= new AziendaDAO();
		
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
                
                /*Essendo un metodo che restituisce boolean posso fare il confronto con due booleani*/
                Boolean controllo;
                Boolean bool=true;
                String iva="07643520567";
                String pass="ciao";
                controllo=aziendaDAOsearch.search(iva,pass);
                assertEquals(bool,controllo);
            }
            finally
            {
                
                 connection.close();;
            }
        }	
    }

	@Test
	public void testCreate() throws SQLException, InsertFailedException {
		AziendaDAO aziendaDAOcreate= new AziendaDAO();
		Azienda azienda;
		try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
        {
            try(Statement stCheck=(Statement) connection.createStatement())
            {
                connection.setAutoCommit(false);
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
                	
                }   
            }
                
                 connection.close();
        }
	}
	
	@Test  /*PROBLEMA DI VISIBILITA'  E' UN METODO STATICO PRIVATO*/
	public void testIsNewKey() throws SQLException, TuplaNotFoundException {
		fail("Not yet implemented");	
		/*	
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
	public void testRead() throws SQLException, TuplaNotFoundException {
		AziendaDAO aziendaDAOread= new AziendaDAO();
		Azienda az;
				try(Connection connection=DriverManagerConnectionPool.getIstance().getConnection();)
		        {
		            try(Statement stCheck=(Statement) connection.createStatement())
		            {
		                connection.setAutoCommit(false);
		                String iva="07643520567";
		                az=aziendaDAOread.read(iva);
		                assertEquals(iva,az.getPartitaIVA());
		                System.out.println("SONO NEL TEST READ:"+iva);
		            }
		            connection.close();
		        }
	}
	
	
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadAll() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadEmail() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadPassword() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateStato() {
		fail("Not yet implemented");
	}

}
