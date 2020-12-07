package dao;

import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;

public interface ParticipanteDAO {
	
	public abstract void darDeBajaParticpante(ParticipanteDTO participante);
	
	public abstract void modificarParticipante();
	
	
	public abstract Participante recuperarParticipante();

	public abstract Boolean nombreOEmailYaExiste(ParticipanteDTO participante, CompetenciaDTO competencia);

	public void guardarParticipante(Participante participante);
}