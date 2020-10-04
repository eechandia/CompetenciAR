package dao;

import dominio.LugarDeRealizacion;

public interface LugarDeRealizacionDAO {

	public abstract LugarDeRealizacion darDeAltaLugarDeRealizacion();
	
	public abstract void modificarLugarDeRealizacion();
	
	public abstract void darDeBajaLugarDeRealizacion();
	
	public abstract void guardarLugarDeRealizacion();
	
	public abstract LugarDeRealizacion recuperarLugarDeRealizacion();
	
}
