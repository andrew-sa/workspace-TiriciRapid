/**
 * 
 */
package it.tirociraid.test.unit;

import java.util.ArrayList;

import it.tirocirapid.classes.model.Azienda;
import it.tirocirapid.classes.model.Tirocinio;
import junit.framework.TestCase;

/**
 * @author Antonino
 *
 */
class TestAzienda extends TestCase{
	
	protected Azienda a;
	protected ArrayList<Tirocinio> array;
	protected Tirocinio t;

	protected void setUp(){
		a=new Azienda("partitaIVA","password","email","nome","sede","numeroTelefono",
			array,"stato","descrizioneAmbito");
		}

	protected void tearDown(){
	}

}
