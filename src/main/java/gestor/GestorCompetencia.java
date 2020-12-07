package gestor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.sun.xml.fastinfoset.UnparsedEntity;

import dao.CompetenciaDAOHibernate;
import dao.DeporteDAOHibernate;
import dominio.Competencia;
import dominio.Deporte;
import dominio.Fixture;
import dominio.FormaPuntuacion;
import dominio.FormaPuntuacionPuntuacion;
import dominio.FormaPuntuacionResFinal;
import dominio.FormaPuntuacionSets;
import dominio.LugarDeRealizacion;
import dominio.Modalidad;
import dominio.Participante;
import dominio.Reserva;
import dominio.SistemaDeCompetencia;
import dominio.SistemaDeCompetencia.Tipo;
import dominio.SistemaDeEliminatoriaDoble;
import dominio.SistemaDeEliminatoriaSimple;
import dominio.SistemaDeLiga;
import dominio.Usuario;
import dominio.Competencia.Estado;
import dto.CompetenciaDTO;
import dto.EncuentroDTO;
import dto.ParticipanteDTO;
import exceptions.EstadoCompetenciaException;
import exceptions.ParticipantesInsuficientesException;
import exceptions.ReservasInsuficientesException;
import exceptions.ReservasNoDisponiblesException;
import utils.Filtro;
import utils.Pair;

public class GestorCompetencia {
	
	private CompetenciaDAOHibernate daoCompetencia = new CompetenciaDAOHibernate();
	private DeporteDAOHibernate daoDeporte = new DeporteDAOHibernate();
	private GestorReserva gestorReserva = new GestorReserva();
	private GestorFixture gestorFixture = new GestorFixture();
	
	
	public void darDeAltaCompetencia(CompetenciaDTO competenciaDto) throws Exception{
		
		//usuario de prueba
		Usuario usuario = new Usuario(1, "ChecoPerez@gmail.com", "hunter12", "Perez", "Checo");
		Competencia competencia = new Competencia();
		
		if(daoCompetencia.verificarSiExiste(competenciaDto.getNombre())){
			throw new Exception("El nombre de la competencia ya esta en uso, por favor elija otro.");
		}
		else {
			
			competencia.inicializarCompetencia(competenciaDto);
			competencia.setUsuarioAsociado(usuario);
			Deporte deporte = daoDeporte.recuperarDeporte(competenciaDto.getIdDeporteDeCompetencia()); //cambiar deporte
			competencia.setDeporteDeCompetencia(deporte);
			
			
			if(competenciaDto.getParticipantes() != null) {
				List<Participante> participantes = new ArrayList<Participante>();
				for(ParticipanteDTO participante: competenciaDto.getParticipantes()) {
					Participante p = new Participante();
					p.inicializarParticipante(participante);
					p.setCompetencia(competencia);
					participantes.add(p);
				}			
				competencia.setParticipantes(participantes);
			}

			
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

	public void generarFixture(CompetenciaDTO compDto) throws EstadoCompetenciaException, ReservasInsuficientesException, ParticipantesInsuficientesException, ReservasNoDisponiblesException {
		Competencia competencia = daoCompetencia.recuperarCompetencia(compDto);
		System.out.println(competencia);
		if(competencia.getEstadoCompetencia() != Estado.CREADA && competencia.getEstadoCompetencia() != Estado.PLANIFICADA) {
			throw new EstadoCompetenciaException();
		}
		
		List<Reserva> reservas = competencia.getReservasDisponibles();
		List<Participante> participantes = competencia.getParticipantes();
		if(participantes.size() <= 1) throw new ParticipantesInsuficientesException();
		SistemaDeCompetencia sist = competencia.getModalidad().getSistemaCompetencia();
		if (sist instanceof SistemaDeLiga) {
			
			
			gestorReserva.reservasDisponibles(reservas);
			if(gestorReserva.cantidadDeReservasSuficientesSistDeLiga(reservas,participantes) == false) {
				throw new ReservasInsuficientesException();
			}
			
			List<LugarDeRealizacion> lugaresDeRealizacion = gestorReserva.convertirReservasAListaDeLugares(reservas);
			
			Fixture fixture = gestorFixture.generarFixture(participantes,lugaresDeRealizacion);
			fixture.setId(competencia.getId());
			

			
			if (competencia.getFixture() == null) {
				competencia.setFixture(fixture);
				competencia.setEstadoCompetencia(Estado.PLANIFICADA);
				daoCompetencia.modificarCompetencia(competencia);
				gestorFixture.guardarFixture(competencia);
			}
			else {
				gestorFixture.bajaFixture(competencia.getFixture());
				competencia.setFixture(fixture);
				competencia.setEstadoCompetencia(Estado.PLANIFICADA);
				daoCompetencia.modificarCompetencia(competencia);
				gestorFixture.guardarFixture(competencia);
			}
		}
	}
	

	
	public void validarEstado(CompetenciaDTO competencia) throws Exception {
		Estado ESTADO = competencia.getEstadoCompetencia();
		if(ESTADO != Estado.CREADA && ESTADO != Estado.PLANIFICADA) {
			throw new Exception("La competencia no se encuentra en estado Creada o Planificada.");
		}
	}
	
	public void agregarParticipante(Competencia competencia, Participante nuevoParticipante) {
		
		competencia.addParticipante(nuevoParticipante);	
		competencia.setEstadoCompetencia(Estado.CREADA);
		
		Fixture fixture = competencia.getFixture();
		
		if(fixture != null) {
			competencia.setFixture(null);
			daoCompetencia.modificarCompetencia(competencia, fixture);
		}
		
		daoCompetencia.modificarCompetencia(competencia);
		
		//Mensaje de exito
	
	}
	
	public Competencia obtenerCompetencia(CompetenciaDTO competencia) {
		return daoCompetencia.recuperarCompetencia( competencia);
	
	}
	
	public void eliminarCompetencia ( Competencia competencia ) {
		daoCompetencia.darDeBajaCompetencia(competencia);
		
	}
	
	public List<EncuentroDTO> obtenerProximosEncuentros(CompetenciaDTO competenciaDto){
		return null;
	}

	public List<CompetenciaDTO> obtenerCompetencias(Usuario usr, Filtro filtros) throws Exception {
		List<CompetenciaDTO> competenciasDTO = new ArrayList<CompetenciaDTO>();
			List <Competencia> competencias = daoCompetencia.obtenerCompetenciasDeUsuario(filtros, usr.getId());
			for(Competencia c: competencias) {
				Integer puntosSiRivalAusente = 0;
				Integer cantidadMaxSets = 0;
				Integer puntosPorPartido = 0;
				Boolean empatePermitido = false;
				Integer puntosPorEmpate = 0;
				Integer puntosPorPresentarse = 0;
				
				List<ParticipanteDTO> participantes = new ArrayList<ParticipanteDTO>();
				for(Participante p: c.getParticipantes()) {
					ParticipanteDTO pDTO = new ParticipanteDTO(p.getNombre(), p.getEmail());
					participantes.add(pDTO);
				}
				
				SistemaDeCompetencia.Tipo tipoCompetencia = null;
				if(c.getModalidad().getSistemaCompetencia() instanceof SistemaDeEliminatoriaDoble) {
					tipoCompetencia = SistemaDeCompetencia.Tipo.ELIMIN_DOBLE;
				}
				else {
					if(c.getModalidad().getSistemaCompetencia() instanceof SistemaDeLiga) {
						tipoCompetencia = SistemaDeCompetencia.Tipo.LIGA;
						empatePermitido = ((SistemaDeLiga) c.getModalidad().getSistemaCompetencia()).getEmpatePermitido();
						puntosPorPartido = ((SistemaDeLiga) c.getModalidad().getSistemaCompetencia()).getPuntosPorPartido();
						puntosPorEmpate = ((SistemaDeLiga) c.getModalidad().getSistemaCompetencia()).getPuntosPorEmpate();
						puntosPorPresentarse = ((SistemaDeLiga) c.getModalidad().getSistemaCompetencia()).getPuntosPorPresentarse();
					}
					else {
						tipoCompetencia = SistemaDeCompetencia.Tipo.ELIMIN_SIMPLE;
					}
				}
				
				FormaPuntuacion.Tipo formaP = null;
				if(c.getModalidad().getFormaPuntuacion() instanceof FormaPuntuacionSets) {
					formaP = FormaPuntuacion.Tipo.SETS;
					cantidadMaxSets = ((FormaPuntuacionSets) c.getModalidad().getFormaPuntuacion()).getCantidad_max_sets();
				}
				else {
					if(c.getModalidad().getFormaPuntuacion() instanceof FormaPuntuacionPuntuacion) {
						formaP = FormaPuntuacion.Tipo.PUNTUACION;
						puntosSiRivalAusente = ((FormaPuntuacionPuntuacion) c.getModalidad().getFormaPuntuacion()).getPuntos_si_rival_ausente();
					}
					else {
						formaP = FormaPuntuacion.Tipo.RESULTADO_FINAL;
					}
				}

				List<Pair<Integer, Integer>> reservas = new ArrayList<Pair<Integer, Integer>>();
				for(Reserva r: c.getReservasDisponibles()) {
					Pair<Integer, Integer> re = new Pair<Integer, Integer>();
					re.setFirst(r.getLugarDeRealizacion().getCodigo());
					re.setSecond(r.getDisponibilidad()); //???????????
					reservas.add(re);
				}
				
				Pair<Integer, String> deporte = new Pair<Integer, String>();
				deporte.setFirst(c.getDeporteDeCompetencia().getId());
				deporte.setSecond(c.getDeporteDeCompetencia().getNombre());
				
				CompetenciaDTO cDTO = new CompetenciaDTO( c.getId(), c.getNombre(), c.getEstadoCompetencia(), 
						tipoCompetencia, formaP, participantes, c.getReglamento(), c.getDadaDeBaja(), c.getFechaBaja(), c.getUsuarioAsociado(),
						reservas, deporte, puntosSiRivalAusente, cantidadMaxSets, puntosPorPartido, empatePermitido, 
						puntosPorEmpate, puntosPorPresentarse);	
						
				competenciasDTO.add(cDTO);
			}

		return competenciasDTO;
	}
}
