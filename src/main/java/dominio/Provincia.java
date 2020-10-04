package dominio;

import java.util.ArrayList;
import java.util.List;

public class Provincia {

	private Integer id;
	private String nombre;
	private List<Localidad> localidades;
	private Pais pais;
	
	public Provincia() {
		this.localidades = new ArrayList<Localidad>();
		
	}
}
