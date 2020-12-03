package exceptions;

public class EstadoCompetenciaException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2354856611277306333L;

	public EstadoCompetenciaException() {
		super("Error. La competencia esta en disputa o ya ha finalizado.");
	}
}
