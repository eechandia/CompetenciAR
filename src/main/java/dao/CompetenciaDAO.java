package dao;

import dominio.Competencia;

public interface CompetenciaDAO {

	public abstract Competencia darDeAltaCompetencia();
	
	public abstract void darDeBajaCompetencia();
	
	public abstract void modificarCompetencia();
	
	public abstract void guardarCompetencia();
	
	public abstract Competencia recuperarCompetencia();
	
}
