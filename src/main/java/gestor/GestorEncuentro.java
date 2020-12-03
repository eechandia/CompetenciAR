package gestor;

import dominio.EncuentroDeportivo;
import dominio.LugarDeRealizacion;
import dominio.Participante;

public class GestorEncuentro {

	public EncuentroDeportivo generarEncuentro(Participante participante1, Participante participante2,
			LugarDeRealizacion lugarDeRealizacion) {
		
		EncuentroDeportivo encuentro = new EncuentroDeportivo();
		encuentro.setParticipante1(participante1);
		encuentro.setParticipante2(participante2);
		encuentro.setLugarDeRealizacion(lugarDeRealizacion);
		
		return encuentro;
		
	}

}
