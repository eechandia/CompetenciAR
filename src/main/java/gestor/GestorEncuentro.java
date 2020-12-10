package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.ParticipanteDAO;
import dominio.Competencia;
import dominio.EncuentroDeportivo;
import dominio.Fecha;
import dominio.Fixture;
import dominio.LugarDeRealizacion;
import dominio.Participante;
import dto.EncuentroDTO;
import dto.ParticipanteDTO;

public class GestorEncuentro {

	
	
	public EncuentroDeportivo generarEncuentro(Participante participante1, Participante participante2,
			LugarDeRealizacion lugarDeRealizacion) {
		
		EncuentroDeportivo encuentro = new EncuentroDeportivo();
		encuentro.setParticipante1(participante1);
		encuentro.setParticipante2(participante2);
		encuentro.setLugarDeRealizacion(lugarDeRealizacion);
		
		return encuentro;
		
	}

	public List<EncuentroDTO> recuperarProximosEncuentrosDTO(Competencia c) {
		
		
		List<EncuentroDTO> encuentros = new ArrayList<EncuentroDTO>();
		if(c.getFixture() != null) {
			for (EncuentroDeportivo unEncuentro : c.getFixture().getFechas().get(0).getEncuentros()) {
				ParticipanteDTO participante1 = new ParticipanteDTO(unEncuentro.getParticipante1().getNombre(), unEncuentro.getParticipante1().getEmail());
				ParticipanteDTO participante2 = new ParticipanteDTO(unEncuentro.getParticipante2().getNombre(), unEncuentro.getParticipante2().getEmail());
				EncuentroDTO encuentroDto = new EncuentroDTO(participante1, participante2, null, 1);
				encuentros.add(encuentroDto);
			}
		}
		return encuentros;
	}

}
