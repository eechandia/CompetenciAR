package gestor;

import java.util.ArrayList;
import java.util.List;

import dominio.LugarDeRealizacion;
import utils.Pair;

public class GestorLugarDeRealizacion {

	public static List<Pair<Integer, String>> recuperarLugares() {
		List<Pair<Integer, String>> aux = new ArrayList<Pair<Integer, String>>();
		//ver db
		LugarDeRealizacionDAOHibernate lugarDeRealizacionDAO = new LugarDeRealizacionDAOHibernate();
		List<LugarDeRealizacion> lugares = lugarDeRealizacionDAO.recuperarLugares();
		for(LugarDeRealizacion l: lugares) {
			Pair<Integer, String> lugar = new Pair<Integer, String>();
			lugar.setFirst(l.getCodigo());
			lugar.setSecond(l.getNombre());
			aux.add(lugar);
		}
		return aux;
	}
	
}
