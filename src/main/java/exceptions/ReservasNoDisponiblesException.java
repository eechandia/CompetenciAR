package exceptions;

public class ReservasNoDisponiblesException extends Exception{
//	public Integer disponibilidadEnLugar;
//	public Integer disponibilidadSolicitada;
//	
	public ReservasNoDisponiblesException(String nombreLugar,Integer disponibilidadEnLugar,Integer disponibilidadSolicitada){
		super("Error. No hay disponibilidad en el lugar: " + nombreLugar + ". La disponibilidad es de: " + disponibilidadEnLugar
				+ ", la disponibilidad solicitada es de: " + disponibilidadSolicitada);
	}
	
}
