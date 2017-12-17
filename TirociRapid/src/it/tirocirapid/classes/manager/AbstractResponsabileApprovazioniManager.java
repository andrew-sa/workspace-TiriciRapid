package it.tirocirapid.classes.manager;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class AbstractResponsabileApprovazioniManager {

	public abstract ArrayList<String> readEmailAll() throws SQLException;
	
}
