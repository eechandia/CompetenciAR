package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.LugarDeRealizacionDAO;
import dao.LugarDeRealizacionDAOHibernate;
import dominio.LugarDeRealizacion;
import dominio.Participante;
import dominio.Reserva;
import exceptions.ReservasNoDisponiblesException;

public class GestorReserva {

	public boolean cantidadDeReservasSuficientesSistDeLiga(List<Reserva> reservas, List<Participante> participantes) {

		List<LugarDeRealizacion> lugares = convertirReservasAListaDeLugares(reservas);
		
		//Se controla que las reservas alcanzen para la cantidad de partidos
		//Como lo indica el CU-AltaCompetencia, la cantidad de reservas debe alcanzar a la cantidad de partidos por fecha.
		
		if ((participantes.size() % 2) == 0) {
			if((participantes.size()/2) > lugares.size()) {
				return false;
			}
		}
		else {
			if (((participantes.size()-1)/2) > lugares.size()) {
				return false;
			}
		}
		return true;
	}

	public List<LugarDeRealizacion> convertirReservasAListaDeLugares(List<Reserva> reservas) {

		List<LugarDeRealizacion> lugares = new ArrayList<LugarDeRealizacion>();
		
		for (Reserva unaReserva : reservas) {
			for(int i=0 ; i < unaReserva.getDisponibilidad() ; i++) {
				lugares.add(unaReserva.getLugarDeRealizacion());
			}
		}

		return lugares;
	}

	public boolean reservasDisponibles(List<Reserva> reservas) throws ReservasNoDisponiblesException {
		
		
		for (Reserva unaReserva : reservas) {
			if(unaReserva.getDisponibilidad()>unaReserva.getLugarDeRealizacion().getDisponibilidad()) {
				throw new ReservasNoDisponiblesException(unaReserva.getLugarDeRealizacion().getNombre(), unaReserva.getLugarDeRealizacion().getDisponibilidad(), unaReserva.getDisponibilidad());
			}
		}
		return true;
	}

	public void actualizarLugaresDeRealizacionGenerarFixture(List<Reserva> reservas) {

		LugarDeRealizacionDAO daoLugarDeRealizacion = new LugarDeRealizacionDAOHibernate();
		
		for (Reserva unaReserva : reservas) {
			LugarDeRealizacion lugar = unaReserva.getLugarDeRealizacion();
			lugar.setDisponibilidad(lugar.getDisponibilidad() - unaReserva.getDisponibilidad());
			daoLugarDeRealizacion.actualizarLugarDeRealizacion(lugar);
		}
		
	}
	
}
