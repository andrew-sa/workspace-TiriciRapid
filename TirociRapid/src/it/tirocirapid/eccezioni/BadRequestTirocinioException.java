package it.tirocirapid.eccezioni;

/**
 * Questa eccezzione si verifica quando uno studente prova ad effettuare una nuova richiesta di tirocinio mentre ne ha una già accettata o in fase di validazione
 * @author Andrea
 *
 */
public class BadRequestTirocinioException extends Exception {
	
	public BadRequestTirocinioException()
	{
		super("Lo studente ha gia' un richiesta di tirocinio accettata o in fase di validazione");
	}
	
	public BadRequestTirocinioException(String msg)
	{
		super(msg);
	}

}
