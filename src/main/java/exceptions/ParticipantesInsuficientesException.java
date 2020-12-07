package exceptions;

public class ParticipantesInsuficientesException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 166137795394899269L;
	
	public ParticipantesInsuficientesException() {
		super("Error. La competencia no tiene participantes suficientes para general el fixture.");
	}
}
