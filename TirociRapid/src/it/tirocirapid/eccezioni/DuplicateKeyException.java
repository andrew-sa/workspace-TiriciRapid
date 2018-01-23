package it.tirocirapid.eccezioni;

/**
 * Questa eccezzione si verifica quando si prova a caricare una tupla nel database che possiede una chiava gi� presente nella tabella
 * @author Andrea
 *
 */
public class DuplicateKeyException extends InsertFailedException {
	
	public DuplicateKeyException()
	{
		super("La chiave della tupla che si vuole � gia presente nel database");
	}
	
	public DuplicateKeyException(String msg)
	{
		super(msg);
	}

}
