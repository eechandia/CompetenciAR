package dominio;

import java.util.ArrayList;
import java.util.List;

public class Fixture {

	private Integer id;
	private List<Fecha> fechas;
	
	public Fixture() {
		fechas = new ArrayList<Fecha>();
	}
	
	public void agregarFecha(Fecha fecha) {
		this.fechas.add(fecha);
	}
	
}
