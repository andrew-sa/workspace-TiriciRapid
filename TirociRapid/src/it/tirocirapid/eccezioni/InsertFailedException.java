package it.tirocirapid.eccezioni;

/**
 * Questa eccezzione si verifica quando si prova a caricare una tupla nel database ma l'inserimento fallisce
 * @author Andrea
 *
 */
public class InsertFailedException extends Exception {
	
	public InsertFailedException()
	{
		super("Si � verificato un errore nell'insermento della tupla nel database");
	}
	
	public InsertFailedException(String msg)
	{
		super(msg);
	}


}
