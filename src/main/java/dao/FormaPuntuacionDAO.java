package dao;

import org.hibernate.Session;

import dominio.FormaPuntuacion;
import dominio.Modalidad;
import dominio.SistemaDeCompetencia;

public interface FormaPuntuacionDAO {

	public abstract FormaPuntuacion darDeAltaFormaPuntuacion();
	
	public abstract void darDeBajaFormaPuntuacion();
	
	public abstract void modificarFormaPuntuacion();
	
	
	public abstract FormaPuntuacion recuperarFormaPuntuacion();

	/**
	 * @param sistemaCompetencia
	 * @param modalidad
	 * @param session
	 * @throws Exception 
	 */
	public abstract void guardarFormaPuntuacion(FormaPuntuacion formaPuntuacion, Modalidad modalidad,
			Session session) throws Exception;
}
