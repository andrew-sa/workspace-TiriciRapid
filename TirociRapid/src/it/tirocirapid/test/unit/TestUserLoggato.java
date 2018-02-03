package it.tirocirapid.test.unit;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import it.tirocirapid.classes.model.UserLoggato;
import junit.framework.TestCase;

public class TestUserLoggato extends TestCase {

	protected UserLoggato uVuoto;
	protected UserLoggato u;
	
	@BeforeEach
	protected void setUp() {
		u= new UserLoggato("id","tipo");
		uVuoto= new UserLoggato();
	}

	@AfterEach
	protected void tearDown() {
		u=null;
		uVuoto=null;
	}

	@Test
	public void testGetId() {
		assertEquals("id", u.getId());
	}
	
	@Test
	public void testGetTipo() {
		assertEquals("tipo",u.getTipo());
	}

	@Test
	public void testSetId() {
		u.setId("pippo");
		assertEquals("pippo", u.getId());
	}
	
	@Test
	public void testSetTipo() {
		u.setTipo("pippo");
		assertEquals("pippo", u.getTipo());
	}
}
