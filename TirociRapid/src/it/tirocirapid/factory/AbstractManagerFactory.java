package it.tirocirapid.factory;

/**
 * Rappresenta l'interfaccia dei metodi delle classi Manager
 */

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractCurriculumManager;
import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.manager.AbstractResponsabileApprovazioniManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;

public abstract class AbstractManagerFactory {
	
	public abstract AbstractAziendaManager createAziendaManager();
	public abstract AbstractCurriculumManager createCurriculumManager();
	public abstract AbstractProfessoreManager createProfessoreManager();
	public abstract AbstractRichiestaTirocinioManager createRichiestaTirocinioManager();
	public abstract AbstractStudenteManager createStudenteManager();
	public abstract AbstractTirocinioManager createTirocinioManager();
	public abstract AbstractResponsabileApprovazioniManager createResponsabileApprovazioniManager();
	
}
