package it.tirocirapid.factory;

import it.tirocirapid.classes.manager.AbstractAziendaManager;
import it.tirocirapid.classes.manager.AbstractCurriculumManager;
import it.tirocirapid.classes.manager.AbstractProfessoreManager;
import it.tirocirapid.classes.manager.AbstractResponsabileApprovazioniManager;
import it.tirocirapid.classes.manager.AbstractRichiestaTirocinioManager;
import it.tirocirapid.classes.manager.AbstractStudenteManager;
import it.tirocirapid.classes.manager.AbstractTirocinioManager;
import it.tirocirapid.classes.manager.AziendaDAO;
import it.tirocirapid.classes.manager.CurriculumDAO;
import it.tirocirapid.classes.manager.ProfessoreDAO;
import it.tirocirapid.classes.manager.ResponsabileApprovazioniDAO;
import it.tirocirapid.classes.manager.RichiestaTirocinioDAO;
import it.tirocirapid.classes.manager.StudenteDAO;
import it.tirocirapid.classes.manager.TirocinioDAO;

public class DAOFactory extends AbstractManagerFactory {

	@Override
	public AbstractAziendaManager createAziendaManager()
	{
		return new AziendaDAO();
	}

	@Override
	public AbstractCurriculumManager createCurriculumManager() 
	{
		return new CurriculumDAO();
	}


	@Override
	public AbstractProfessoreManager createProfessoreManager() 
	{
		return new ProfessoreDAO();
	}

	@Override
	public AbstractRichiestaTirocinioManager createRichiestaTirocinioManager() 
	{
		return new RichiestaTirocinioDAO();
	}

	@Override
	public AbstractStudenteManager createStudenteManager() 
	{
		return new StudenteDAO();
	}

	@Override
	public AbstractTirocinioManager createTirocinioManager() 
	{
		return new TirocinioDAO();
	}

	@Override
	public AbstractResponsabileApprovazioniManager createResponsabileApprovazioniManager() 
	{	
		return new ResponsabileApprovazioniDAO();
	}

}
