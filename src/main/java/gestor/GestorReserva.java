package gestor;

import java.util.ArrayList;
import java.util.List;

import dominio.LugarDeRealizacion;
import dominio.Participante;
import dominio.Reserva;

public class GestorReserva {

	public boolean cantidadDeReservasSuficientesSistDeLiga(List<Reserva> reservas, List<Participante> participantes) {

		List<LugarDeRealizacion> lugares = convertirReservasAListaDeLugares(reservas);
		
		if ((participantes.size() % 2) == 0) {
			if(((participantes.size()-1) * (participantes.size()/2)) > lugares.size()) {
				return false;
			}
		}
		else {
			if ((participantes.size() * ((participantes.size()-1)/2)) > lugares.size()) {
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
	
}
