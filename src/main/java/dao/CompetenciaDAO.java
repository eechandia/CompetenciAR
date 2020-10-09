package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import dominio.Competencia;
import dominio.FormaPuntuacion;
import dominio.Reserva;
import dto.CompetenciaDTO;

public interface CompetenciaDAO {

	public abstract Competencia darDeAltaCompetencia(CompetenciaDTO compeDTO);
	
	public abstract void darDeBajaCompetencia();

	public abstract void modificarCompetencia();
	
	public abstract Boolean guardarCompetencia(Competencia competencia);

	
	public abstract Boolean verificarSiExiste(String nombre);
	
	public abstract Competencia recuperarCompetencia();
	
}
