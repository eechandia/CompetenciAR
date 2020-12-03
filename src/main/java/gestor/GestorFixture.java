package gestor;

import java.util.List;

import dominio.EncuentroDeportivo;
import dominio.Fecha;
import dominio.Fixture;
import dominio.LugarDeRealizacion;
import dominio.Participante;

public class GestorFixture {

	private GestorEncuentro gestorEncuentro = new GestorEncuentro();
	
	public Fixture generarFixture(List<Participante> participantes, List<LugarDeRealizacion> lugaresDeRealizacion) {
		
		Fixture fixture = new Fixture();
		
		for (int i=1 ; i<participantes.size();i++) {
			
			Fecha fecha = new Fecha();
			fecha.setNumeroFecha(i);
			
			if((participantes.size() % 2) == 0) {
				
				Integer participante1=0;
				Integer participante2=participantes.size()-1;
				
				for (int j=0 ; j<participantes.size() /2 ; j++) {
					
					EncuentroDeportivo encuentro = gestorEncuentro.generarEncuentro(participantes.get(participante1),participantes.get(participante2),lugaresDeRealizacion.get(0));
					lugaresDeRealizacion.remove(0);
					fecha.agregarEncuentro(encuentro);
					participante1++;
					participante2--;
				
				}
				rotarParticipantes(participantes);
			}
			else {
				Participante ultimoParticipante = participantes.get(participantes.size()-1);
				participantes.remove(participantes.size()-1);
				
				Integer participante1=0;
				Integer participante2=participantes.size()-1;
				
					for (int j=0 ; j<participantes.size() /2 ; j++) {
					
					EncuentroDeportivo encuentro = gestorEncuentro.generarEncuentro(participantes.get(participante1),participantes.get(participante2),lugaresDeRealizacion.get(0));
					lugaresDeRealizacion.remove(0);
					fecha.agregarEncuentro(encuentro);
					participante1++;
					participante2--;
				
				}
				participantes.add(ultimoParticipante);
				
				if(i != (participantes.size()-2)) {
					rotarParticipantes(participantes);
				}
				else {
					Participante aux = participantes.get(0);
					participantes.set(0, participantes.get(participantes.size()-1));
					participantes.set((participantes.size()-1), aux);
					participantes.remove(participantes.size()-1);
					rotarParticipantes(participantes);
					participantes.add(aux);
				}
			}
			
			fixture.agregarFecha(fecha);
		
		}
		
		return fixture;
	}

	private void rotarParticipantes(List<Participante> participantes) {

		Participante aux = participantes.get(1);
		
		for(int i=1 ; i < (participantes.size()-1) ; i++) {
			participantes.set(i, participantes.get(i+1));
		}
		participantes.set(participantes.size()-1, aux);
	}

}
