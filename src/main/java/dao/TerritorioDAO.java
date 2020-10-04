package dao;

import dominio.Localidad;
import dominio.Pais;
import dominio.Provincia;

public interface TerritorioDAO {
	
	public abstract Pais darDeAltaPais();
	public abstract void darDeBajaPais();
	public abstract void modificarPais();
	public abstract Pais recuperarPais();
	
	public abstract Provincia darDeAltaProvincia();
	public abstract void darDeBajaProvincia();
	public abstract void modificarProvincia();
	public abstract Provincia recuperarProvincia();
	
	public abstract Localidad darDeAltaLocalidad();
	public abstract void darDeBajaLocalidad();
	public abstract void modificarLocalidad();
	public abstract void guardarLocalidad();
	public abstract Localidad recuperarLocalidad();
}
