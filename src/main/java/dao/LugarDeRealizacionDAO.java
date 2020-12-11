package dao;

import java.util.List;

import org.hibernate.Session;

import dominio.Competencia;
import dominio.LugarDeRealizacion;
import dominio.Reserva;

public interface LugarDeRealizacionDAO {

	public abstract LugarDeRealizacion darDeAltaLugarDeRealizacion();
	
	public abstract void modificarLugarDeRealizacion();
	
	public abstract void darDeBajaLugarDeRealizacion();
	
	public abstract void guardarLugarDeRealizacion();
	
	public abstract List<LugarDeRealizacion> recuperarLugaresDeRealizacion(int idDeporte);

	/**
	 * @param reservasDisponibles
	 * @param competencia
	 * @param session
	 * @throws Exception 
	 */
	public abstract void guardarReservas(List<Reserva> reservasDisponibles, Competencia competencia, Session session) throws Exception;

	public abstract void actualizarLugarDeRealizacion(LugarDeRealizacion lugar);
	
}
