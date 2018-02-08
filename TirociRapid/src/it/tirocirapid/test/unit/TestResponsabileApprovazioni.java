package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tirocirapid.classes.model.ResponsabileApprovazioni;
import junit.framework.TestCase;

public class TestResponsabileApprovazioni extends TestCase{

	protected ResponsabileApprovazioni a;
	
	@BeforeEach
	protected void setUp() {
		a= new ResponsabileApprovazioni();
		
	}

	@AfterEach
	protected void tearDown(){
	
	}

	@Test
	void test() {
		assertTrue(true);
	}

}
