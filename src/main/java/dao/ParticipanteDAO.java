package dao;

import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;

public interface ParticipanteDAO {
	
	public abstract void darDeBajaParticpante(Participante participante);
	
	public abstract void modificarParticipante();
	
	
	public abstract Participante recuperarParticipante(ParticipanteDTO participanteDTO);

	public abstract Boolean nombreOEmailYaExiste(ParticipanteDTO participante, CompetenciaDTO competencia);

	public void guardarParticipante(Participante participante);
}