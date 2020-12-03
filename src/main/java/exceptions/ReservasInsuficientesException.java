package exceptions;

public class ReservasInsuficientesException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8190532880470255853L;

	public ReservasInsuficientesException() {
		super("La cantidad de encuentros asignados en la competencia no son suficientes.");
	}
}
