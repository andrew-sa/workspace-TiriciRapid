package it.tirocirapid.test.unit;


import it.tirocirapid.classes.model.UserLoggato;
import junit.framework.TestCase;

public class TestUserLoggato extends TestCase {

	protected UserLoggato uVuoto;
	protected UserLoggato u;
	
	protected void setUp() {
		u= new UserLoggato("id","tipo");
		uVuoto= new UserLoggato();
	}

	protected void tearDown() {
		u=null;
		uVuoto=null;
	}

	public void testGetId() {
		assertEquals("id", u.getId());
	}
	
	public void testGetTipo() {
		assertEquals("tipo",u.getTipo());
	}

	public void testSetId() {
		u.setId("pippo");
		assertEquals("pippo", u.getId());
	}
	
	public void testSetTipo() {
		u.setTipo("pippo");
		assertEquals("pippo", u.getTipo());
	}
}
