package dao;

import dominio.FormaPuntuacion;

public interface FormaPuntuacionDAO {

	public abstract FormaPuntuacion darDeAltaFormaPuntuacion();
	
	public abstract void darDeBajaFormaPuntuacion();
	
	public abstract void modificarFormaPuntuacion();
	
	public abstract void guardarFormaPuntuacion();
	
	public abstract FormaPuntuacion recuperarFormaPuntuacion();
}
