package it.tirocirapid.eccezioni;

public class InvalidPreviousStateException extends Exception {

	public InvalidPreviousStateException()
	{
		super("Lo stato attuale dell'oggetto non consente di apportare la modifica desisderata");
	}

	public InvalidPreviousStateException(String msg)
	{
		super(msg);
	}

}
