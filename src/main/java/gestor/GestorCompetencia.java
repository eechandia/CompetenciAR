package gestor;

import java.util.ArrayList;
import java.util.List;
import com.sun.xml.fastinfoset.UnparsedEntity;

import dao.CompetenciaDAOHibernate;
import dao.DeporteDAOHibernate;
import dominio.Competencia;
import dominio.Deporte;
import dominio.FormaPuntuacionPuntuacion;
import dominio.FormaPuntuacionResFinal;
import dominio.FormaPuntuacionSets;
import dominio.LugarDeRealizacion;
import dominio.Modalidad;
import dominio.Reserva;
import dominio.SistemaDeEliminatoriaDoble;
import dominio.SistemaDeEliminatoriaSimple;
import dominio.SistemaDeLiga;
import dominio.Usuario;
import dto.CompetenciaDTO;
import utils.Pair;

public class GestorCompetencia {
	
	private CompetenciaDAOHibernate daoCompetencia = new CompetenciaDAOHibernate();
	private DeporteDAOHibernate daoDeporte = new DeporteDAOHibernate();
	
	public void darDeAltaCompetencia(CompetenciaDTO competenciaDto) throws Exception{
		
		Usuario usuario = new Usuario(1, "ChecoPerez@gmail.com", "hunter12", "Perez", "Checo");
		Competencia competencia = new Competencia();
		
		if(daoCompetencia.verificarSiExiste(competenciaDto.getNombre())){
			throw new Exception("Nombre ya existe");
		}
		else {
			
			competencia.inicializarCompetencia(competenciaDto);
			competencia.setUsuarioAsociado(usuario);
			Deporte deporte = daoDeporte.recuperarDeporte(competenciaDto.getDeporteDeCompetencia());
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
			
			
			//ACA FALTA LO DE RESERVA -------
			
			daoCompetencia.guardarCompetencia(competencia);
			
		}
	
	}

}
