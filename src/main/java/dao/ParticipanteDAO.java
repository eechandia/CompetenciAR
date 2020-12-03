package dao;

import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;

public interface ParticipanteDAO {

	public abstract Participante darDeAltaParticpante();
	
	public abstract void darDeBajaParticpante();
	
	public abstract void modificarParticipante();
	
	public abstract void guardarParticipante();
	
	public abstract Participante recuperarParticipante();

	public abstract Boolean nombreOEmailYaExiste(ParticipanteDTO participante, CompetenciaDTO competencia);
}