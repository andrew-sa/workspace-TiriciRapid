package it.tirocirapid.eccezioni;

/**
 * Questa eccezzione si verifica quando si prova a caricare una tupla non presente nel database
 * @author Andrea
 *
 */
public class TuplaNotFoundException extends Exception {

	public TuplaNotFoundException()
	{
		super("La tupla cercata non è stata trovata nel database");
	}
	
	public TuplaNotFoundException(String msg)
	{
		super(msg);
	}
	
}
