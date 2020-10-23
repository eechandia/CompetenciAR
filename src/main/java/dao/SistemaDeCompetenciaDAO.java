package dao;

import org.hibernate.Session;

import dominio.Modalidad;
import dominio.SistemaDeCompetencia;

public interface SistemaDeCompetenciaDAO {

	public abstract SistemaDeCompetencia darDeAltaSistemaDeCompetencia();
	
	public abstract void darDeBajaSistemaDeCompetencia();
	
	public abstract void guardarSistemaDeCompetencia(SistemaDeCompetencia sistemaDeCompetencia, Modalidad modalidad, Session session) throws Exception;
	
	public abstract SistemaDeCompetencia recuperarSistemaDeCompetencia();
	
}
