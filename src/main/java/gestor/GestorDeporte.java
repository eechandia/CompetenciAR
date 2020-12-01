package gestor;

import java.util.ArrayList;
import java.util.List;

import dao.DeporteDAOHibernate;
import dominio.Deporte;
import utils.Pair;

public class GestorDeporte {

	//hibernate.sessionFactory();
	public List<String> getDeportesInterfaz(){
		List<String> aux = new ArrayList<String>();
		//ver db
		DeporteDAOHibernate deporteDAO = new DeporteDAOHibernate();
		List<Deporte> deportes = deporteDAO.recuperarDeportes();
		for(Deporte d: deportes) {
			aux.add(d.getNombre());
		}
		return aux;
	}
}
