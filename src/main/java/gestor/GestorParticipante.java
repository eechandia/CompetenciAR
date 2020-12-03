package gestor;

import dao.ParticipanteDAO;
import dao.ParticipanteDAOHibernate;
import dominio.Competencia;
import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;

public class GestorParticipante {
	ParticipanteDAO participanteDAO = new ParticipanteDAOHibernate();
	GestorCompetencia gestorCompetencia = new GestorCompetencia();
	
	
	public void CrearParticipante(ParticipanteDTO participanteDto, CompetenciaDTO competenciaDto) throws Exception {
		this.validar(participanteDto);
		
		if(participanteDAO.nombreOEmailYaExiste(participanteDto, competenciaDto)) {
			throw new Exception("El participante ya esta en la competencia");
		}
		
		Participante participante = new Participante();
		
		participante.inicializarParticipante(participanteDto);
		
		Competencia competencia = gestorCompetencia.obtenerCompetencia(competenciaDto);
		participante.setCompetencia(competencia);
		
		gestorCompetencia.agregarParticipante(competencia, participante);
		
	}
	
	public void validar(ParticipanteDTO participante) throws Exception {
		if (participante.getNombre() == null) {
			throw new Exception("El campo nombre esta vacio");
		}
		if(participante.getEmail() == null) {
			throw new Exception("El campo email esta vacio");
		}
	}
	
	
	
}
