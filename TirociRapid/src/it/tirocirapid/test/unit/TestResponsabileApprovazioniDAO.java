package it.tirocirapid.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.tirocirapid.classes.manager.ResponsabileApprovazioniDAO;

class TestResponsabileApprovazioniDAO {
	ArrayList<String> emails;
	ArrayList<String> emailsTest;
	@BeforeEach
	void setUp() throws Exception {
		emails = new ArrayList<>();
		emailsTest = new ArrayList<>();
	}

	@AfterEach
	void tearDown() throws Exception {
		emails = null;
		emailsTest = null;
	}

	@Test
	public void testreadEmailAll() {
		try {
			ResponsabileApprovazioniDAO raReadEmailAll = new ResponsabileApprovazioniDAO();
			emails = raReadEmailAll.readEmailAll();
			emailsTest.add("sicuinfo@outlook.com");
			for(int i=0; i<=emails.size()-1; i++) {
				assertEquals(emails.get(i), emailsTest.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
