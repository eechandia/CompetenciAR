package gestor;

import java.util.ArrayList;
import java.util.List;

import dominio.LugarDeRealizacion;
import dominio.Participante;
import dominio.Reserva;

public class GestorReserva {

	public boolean cantidadDeReservasSuficientesSistDeLiga(List<Reserva> reservas, List<Participante> participantes) {

		List<LugarDeRealizacion> lugares = convertirReservasAListaDeLugares(reservas);
		
		if((participantes.size() - 1 ) > lugares.size()) {
			return false;
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
	
}
