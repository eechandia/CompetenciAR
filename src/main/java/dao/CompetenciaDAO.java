package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import dominio.Competencia;
import dominio.FormaPuntuacion;
import dominio.Reserva;
import dto.CompetenciaDTO;

public interface CompetenciaDAO {
	
	public abstract void darDeBajaCompetencia();

	public abstract void modificarCompetencia(Competencia competencia);
	
	public abstract Boolean guardarCompetencia(Competencia competencia) throws Exception;

	
	public abstract Boolean verificarSiExiste(String nombre);
	
	public abstract Competencia recuperarCompetencia(CompetenciaDTO competencia);
	
}
