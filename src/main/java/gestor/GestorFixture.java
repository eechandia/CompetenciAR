package gestor;

import java.util.Collections;
import java.util.List;

import dao.EncuentroDeportivoDAO;
import dao.EncuentroDeportivoDAOHibernate;
import dao.FixtureDAO;
import dao.FixtureDAOHibernate;
import dominio.Competencia;
import dominio.EncuentroDeportivo;
import dominio.Fecha;
import dominio.Fixture;
import dominio.LugarDeRealizacion;
import dominio.Participante;
import dominio.Reserva;

public class GestorFixture {

	private GestorEncuentro gestorEncuentro = new GestorEncuentro();
	private FixtureDAOHibernate daoFixture = new FixtureDAOHibernate();
	
	
	public Fixture generarFixture(List<Participante> participantes, List<LugarDeRealizacion> lugaresDeRealizacion) {
		
		Fixture fixture = new Fixture();
		
		
		
		if((participantes.size() % 2) == 0) {
			
			for (int i=1 ; i<participantes.size();i++) {
				
				Fecha fecha = new Fecha();
				fecha.setNumeroFecha(i);
				fecha.setFixture(fixture);
				
				Integer participante1=0;
				Integer participante2=participantes.size()-1;
				
				for (int j=0 ; j<participantes.size() /2 ; j++) {
					EncuentroDeportivo encuentro = gestorEncuentro.generarEncuentro(participantes.get(participante1),participantes.get(participante2),lugaresDeRealizacion.get(j));
					fecha.agregarEncuentro(encuentro);
					encuentro.setFecha(fecha);
					participante1++;
					participante2--;
				
				}
				Collections.shuffle(lugaresDeRealizacion);
				rotarParticipantes(participantes);
				fixture.agregarFecha(fecha);
			}
			
		}
		else {
			
			for (int i=1 ; i<=participantes.size();i++) {
				
				Fecha fecha = new Fecha();
				fecha.setNumeroFecha(i);
				
				Participante ultimoParticipante = participantes.get(participantes.size()-1);
				participantes.remove(participantes.size()-1);
				
				Integer participante1=0;
				Integer participante2=participantes.size()-1;
				
					for (int j=0 ; j<participantes.size() /2 ; j++) {
					
					LugarDeRealizacion lugar = lugaresDeRealizacion.get(0);
					EncuentroDeportivo encuentro = gestorEncuentro.generarEncuentro(participantes.get(participante1),participantes.get(participante2),lugar);
					lugaresDeRealizacion.remove(0);
					lugaresDeRealizacion.add(lugar);
					fecha.agregarEncuentro(encuentro);
					participante1++;
					participante2--;
				
				}
				participantes.add(ultimoParticipante);
				
				if(i != (participantes.size()-1)) {
					rotarParticipantes(participantes);
					fixture.agregarFecha(fecha);
				}
				else {
					Participante aux = participantes.get(0);
					participantes.set(0, participantes.get(participantes.size()-1));
					participantes.set((participantes.size()-1), aux);
					participantes.remove(participantes.size()-1);
					rotarParticipantes(participantes);
					participantes.add(aux);
					fixture.agregarFecha(fecha);
				}
			}
		}
		return fixture;
	}

	private void rotarParticipantes(List<Participante> participantes) {

		Participante aux = participantes.get(participantes.size()-1);
		
		for(int i=participantes.size()-1 ; i > 1 ; i--) {
			participantes.set(i, participantes.get(i-1));
		}
		participantes.set(1, aux);
	}

	public void guardarFixture(Competencia competencia) {
		
		EncuentroDeportivoDAO daoEncuentro = new EncuentroDeportivoDAOHibernate();
		Fixture fixture = competencia.getFixture();
		daoFixture.guardarFixture(fixture);
		List<Fecha> fechas = fixture.getFechas();
		
		for(Fecha unaFecha : fechas) {
			unaFecha.setFixture(fixture);
			daoFixture.guardarFecha(unaFecha);
			List<EncuentroDeportivo> encuentros = unaFecha.getEncuentros();
			for (EncuentroDeportivo unEncuentro : encuentros) {
				unEncuentro.setFecha(unaFecha);
				daoEncuentro.guardarEncuentroDeportivo(unEncuentro);
			}
		}
	}
	
	public void borrarFixture() {
		
	}

	public void bajaFixture(Fixture fixture) {

		List<Fecha> fechas = fixture.getFechas();
		EncuentroDeportivoDAO daoEncuentro = new EncuentroDeportivoDAOHibernate();
		
		for (Fecha unaFecha : fechas) {
			List<EncuentroDeportivo> encuentros = unaFecha.getEncuentros();
			for (EncuentroDeportivo unEncuentro : encuentros) {
				daoEncuentro.darDeBajaEncuentro(unEncuentro);
			}
			daoFixture.darDeBajaFecha(unaFecha);
		}
		daoFixture.darDeBajaFixture(fixture);
	}

}
