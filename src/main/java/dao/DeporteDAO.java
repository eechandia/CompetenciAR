package dao;

import dominio.Deporte;

public interface DeporteDAO {

	public abstract Deporte darDeAltaDeporte();
	
	public abstract void darDeBajaDeporte();
	
	public abstract void modificarDeporte();

	public abstract void guardarDeporte();

	public abstract Deporte recuperarDeporte();
}
