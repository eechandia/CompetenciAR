package gestor;

import java.util.ArrayList;
import java.util.List;
import com.sun.xml.fastinfoset.UnparsedEntity;

import dao.CompetenciaDAOHibernate;
import dao.DeporteDAOHibernate;
import dominio.Competencia;
import dominio.Deporte;
import dominio.Fixture;
import dominio.FormaPuntuacionPuntuacion;
import dominio.FormaPuntuacionResFinal;
import dominio.FormaPuntuacionSets;
import dominio.LugarDeRealizacion;
import dominio.Modalidad;
import dominio.Participante;
import dominio.Reserva;
import dominio.SistemaDeCompetencia;
import dominio.SistemaDeEliminatoriaDoble;
import dominio.SistemaDeEliminatoriaSimple;
import dominio.SistemaDeLiga;
import dominio.Usuario;
import dominio.Competencia.Estado;
import dto.CompetenciaDTO;
import exceptions.EstadoCompetenciaException;
import exceptions.ReservasInsuficientesException;
import utils.Pair;

public class GestorCompetencia {
	
	private CompetenciaDAOHibernate daoCompetencia = new CompetenciaDAOHibernate();
	private DeporteDAOHibernate daoDeporte = new DeporteDAOHibernate();
	private GestorReserva gestorReserva = new GestorReserva();
	private GestorFixture gestorFixture = new GestorFixture();
	
	public void darDeAltaCompetencia(CompetenciaDTO competenciaDto) throws Exception{
		
		Usuario usuario = new Usuario(0, "ChecoPerez@gmail.com", "hunter12", "Perez", "Checo");
		Competencia competencia = new Competencia();
		
		if(daoCompetencia.verificarSiExiste(competenciaDto.getNombre())){
			throw new Exception("Nombre ya existe");
		}
		else {
			
			competencia.inicializarCompetencia(competenciaDto);
			competencia.setUsuarioAsociado(usuario);
			Deporte deporte = daoDeporte.recuperarDeporte(competenciaDto.getDeporteDeCompetencia()); //cambiar deporte
			competencia.setDeporteDeCompetencia(deporte);
			
			
			Modalidad modalidad = new Modalidad(competencia);

			switch(competenciaDto.getTipoSistemaDeCompetencia()) {
			
				case LIGA:
					SistemaDeLiga sLiga = new SistemaDeLiga(modalidad, 
															competenciaDto.getPuntosPorPartido(), 
															competenciaDto.getEmpatePermitido(), 
															competenciaDto.getPuntosPorEmpate(), 
															competenciaDto.getPuntosPorPresentarse());
					modalidad.setSistemaCompetencia(sLiga);
					break;
					
				case ELIMIN_SIMPLE:
					SistemaDeEliminatoriaSimple sElimSimple = new SistemaDeEliminatoriaSimple(modalidad);
					modalidad.setSistemaCompetencia(sElimSimple);
					break;
					
				case ELIMIN_DOBLE:
					SistemaDeEliminatoriaDoble sElimDoble = new SistemaDeEliminatoriaDoble(modalidad);
					modalidad.setSistemaCompetencia(sElimDoble);
					break;	
			}
			
			switch(competenciaDto.getTipoFormaPuntuacion()) {
				case SETS:
					FormaPuntuacionSets formaSets = new FormaPuntuacionSets(modalidad, competenciaDto.getCantidadMaxSets());
					modalidad.setFormaPuntuacion(formaSets);
					break;
					
				case PUNTUACION:
					FormaPuntuacionPuntuacion formaPuntuacion = new FormaPuntuacionPuntuacion(modalidad, competenciaDto.getPuntosSiRivalAusente());
					modalidad.setFormaPuntuacion(formaPuntuacion);
					break;
					
				case RESULTADO_FINAL:
					FormaPuntuacionResFinal formaResFinal = new FormaPuntuacionResFinal(modalidad);
					modalidad.setFormaPuntuacion(formaResFinal);
					break;
			}
			
			
			competencia.setModalidad(modalidad);
			
			List<Reserva> reservas = new ArrayList<Reserva>();
			
			for(Pair<Integer, Integer> unPair : competenciaDto.getReservasDisponibles()) {
				reservas.add(new Reserva(new LugarDeRealizacion(unPair.getFirst()),unPair.getSecond()));
			}
			
			competencia.setReservasDisponibles(reservas);
			
			daoCompetencia.guardarCompetencia(competencia);
			
		}
	
	}

	public void generarFixture(CompetenciaDTO compDto) throws EstadoCompetenciaException, ReservasInsuficientesException {
		Competencia competencia = daoCompetencia.recuperarCompetencia();
		
		if(competencia.getEstadoCompetencia() != Estado.CREADA || competencia.getEstadoCompetencia() != Estado.PLANIFICADA) {
			throw new EstadoCompetenciaException();
		}
		
		List<Reserva> reservas = competencia.getReservasDisponibles();
		List<Participante> participantes = competencia.getParticipantes();
		
		SistemaDeCompetencia sist = competencia.getModalidad().getSistemaCompetencia();
		if (sist instanceof SistemaDeLiga) {
			
			if(gestorReserva.cantidadDeReservasSuficientesSistDeLiga(reservas,participantes) == false) {
				throw new ReservasInsuficientesException();
			}
			
			List<LugarDeRealizacion> lugaresDeRealizacion = gestorReserva.convertirReservasAListaDeLugares(reservas);
			
			Fixture fixture = gestorFixture.generarFixture(participantes,lugaresDeRealizacion);
			
			if (competencia.getFixture() == null) {
				competencia.setFixture(fixture);
				competencia.setEstadoCompetencia(Estado.PLANIFICADA);
				daoCompetencia.update(competencia);
			}
			else {
				//mensaje de confirmacion
				
			}
		}
	}
	
	public List<CompetenciaDTO> obtenerCompetencias(){
		return null;
	}
	
	public List<CompetenciaDTO> obtenerCompetencias(Usuario usr){
		return null;
	}
	
	public CompetenciaDTO obtenerCompetencia(String nombre) {
		return null;
	}
	
	public boolean eliminarCompetencia ( CompetenciaDTO competencia ) {
		return false;
	}
}
