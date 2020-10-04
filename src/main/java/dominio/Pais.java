package dominio;

import java.util.ArrayList;
import java.util.List;

public class Pais {

	private Integer id;
	private String nombre;
	private List<Provincia> provincias;
	
	public Pais() {
		this.provincias = new ArrayList<Provincia>();
		
	}
}
