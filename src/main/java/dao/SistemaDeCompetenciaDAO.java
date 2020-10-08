package dao;

import dominio.SistemaDeCompetencia;

public interface SistemaDeCompetenciaDAO {

	public abstract SistemaDeCompetencia darDeAltaSistemaDeCompetencia();
	
	public abstract void darDeBajaSistemaDeCompetencia();
	
	public abstract void guardarSistemaDeCompetencia();
	
	public abstract SistemaDeCompetencia recuperarSistemaDeCompetencia();
	
}
