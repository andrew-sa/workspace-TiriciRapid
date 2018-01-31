package it.tirocirapid.eccezioni;

/**
 * Questa eccezzione si verifica quando si prova a caricare/modificare/eliminare una tupla nel database ma l'operazione fallisce
 * @author Andrea
 *
 */
public class InsertFailedException extends Exception {
	
	public InsertFailedException()
	{
		super("Si è verificato un errore nell'insermento della tupla nel database");
	}
	
	public InsertFailedException(String msg)
	{
		super(msg);
	}


}
