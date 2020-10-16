package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.DeporteDAOHibernate;
import dominio.Deporte;

public class GestorDeporte {

	hibernate.sessionFactory();
	public List<List<String>> getDeportesInterfaz(){
		List<List<String>> aux = new ArrayList<List<String>>();
		//ver db
		DeporteDAOHibernate deporteDAO = new DeporteDAOHibernate();
		List<Deporte> deportes = deporteDAO.recuperarDeportes();
		for(Deporte d: deportes) {
			List<String> deporte = new ArrayList<String>();
			deporte.add(d.getId().toString());
			deporte.add(d.getNombre());
			aux.add(deporte);
		}
		return aux;
	}
}
