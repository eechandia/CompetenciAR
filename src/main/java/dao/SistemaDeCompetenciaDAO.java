package dao;

import dominio.SistemaCompetencia;

public interface SistemaDeCompetenciaDAO {

	public abstract SistemaCompetencia darDeAltaSistemaDeCompetencia();
	
	public abstract void darDeBajaSistemaDeCompetencia();
	
	public abstract void guardarSistemaDeCompetencia();
	
	public abstract SistemaCompetencia recuperarSistemaDeCompetencia();
	
}
