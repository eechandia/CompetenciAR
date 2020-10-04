package dao;

import dominio.Participante;

public interface ParticipanteDAO {

	public abstract Participante darDeAltaParticpante();
	
	public abstract void darDeBajaParticpante();
	
	public abstract void modificarParticipante();
	
	public abstract void guardarParticipante();
	
	public abstract Participante recuperarParticipante();
}