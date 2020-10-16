package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.DeporteDAOHibernate;
import dominio.Deporte;
import utils.Pair;

public class GestorDeporte {

	//hibernate.sessionFactory();
	public List<Pair<Integer, String>> getDeportesInterfaz(){
		List<Pair<Integer, String>> aux = new ArrayList<Pair<Integer, String>>();
		//ver db
		DeporteDAOHibernate deporteDAO = new DeporteDAOHibernate();
		List<Deporte> deportes = deporteDAO.recuperarDeportes();
		for(Deporte d: deportes) {
			Pair<Integer, String> deporte = new Pair<Integer, String>();
			deporte.setFirst(d.getId());
			deporte.setSecond(d.getNombre());
			aux.add(deporte);
		}
		return aux;
	}
}
