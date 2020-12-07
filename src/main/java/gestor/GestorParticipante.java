package gestor;

import dao.ParticipanteDAO;
import dao.ParticipanteDAOHibernate;
import dominio.Competencia;
import dominio.Fixture;
import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;

public class GestorParticipante {
	ParticipanteDAO participanteDAO = new ParticipanteDAOHibernate();
	GestorCompetencia gestorCompetencia = new GestorCompetencia();
	GestorFixture gestorFixture = new GestorFixture();
	
	
	public void crearParticipante(ParticipanteDTO participanteDto, CompetenciaDTO competenciaDto) throws Exception {
	
		this.validar(participanteDto);
		gestorCompetencia.validarEstado(competenciaDto);
		
		if(participanteDAO.nombreOEmailYaExiste(participanteDto, competenciaDto)) {
			throw new Exception("El participante ya esta en la competencia");
		}
		
		Participante participante = new Participante();
		participante.inicializarParticipante(participanteDto);
		
		Competencia competencia = gestorCompetencia.obtenerCompetencia(competenciaDto);
		participante.setCompetencia(competencia);

		participanteDAO.guardarParticipante(participante);
		gestorCompetencia.agregarParticipante(competencia, participante);
		
	}
	
	public boolean eliminarParticipante(ParticipanteDTO participanteDto, CompetenciaDTO competenciaDto) {
		
		Competencia competencia = gestorCompetencia.obtenerCompetencia(competenciaDto);
		Fixture fixture = competencia.getFixture();

		if(fixture != null) {
			gestorFixture.bajaFixture(fixture);
		}
		participanteDAO.darDeBajaParticpante(participanteDto);
		return false;
	}
	
	public void validar(ParticipanteDTO participante) throws Exception {
		
		if ((participante.getNombre() == null  && participante.getEmail() == null) || 
				(participante.getNombre().isBlank() && participante.getEmail().isBlank())) {
			throw new Exception("Todos los campos de participante estan vacios");
		}
		
		else if (participante.getNombre() == null || participante.getNombre().isBlank()) {
			throw new Exception("El campo nombre esta vacio");
		}
		else if(participante.getEmail() == null || participante.getEmail().isBlank()) {
			throw new Exception("El campo email esta vacio");
		}
	}
	
	public void eliminarParticipante(Participante participante) {
		participanteDAO.darDeBajaParticpante(participante);
		
	}
	
	
}
