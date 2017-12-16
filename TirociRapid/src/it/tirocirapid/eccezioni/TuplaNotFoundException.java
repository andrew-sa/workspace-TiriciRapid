package it.tirocirapid.eccezioni;

public class TuplaNotFoundException extends Exception {

	public TuplaNotFoundException()
	{
		super("La tupla cercata non � stata trovata nel database");
	}
	
	public TuplaNotFoundException(String msg)
	{
		super(msg);
	}
	
}
