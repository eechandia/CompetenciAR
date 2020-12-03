package dominio;

import java.util.ArrayList;
import java.util.List;

public class Fecha {

	private Integer id;
	private Integer numeroFecha;
	private List<EncuentroDeportivo> encuentros;
	
	
	
	public Fecha() {
		encuentros = new ArrayList<EncuentroDeportivo>();
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumeroFecha() {
		return numeroFecha;
	}
	public void setNumeroFecha(Integer numeroFecha) {
		this.numeroFecha = numeroFecha;
	}
	public List<EncuentroDeportivo> getEncuentros() {
		return encuentros;
	}
	public void setEncuentros(List<EncuentroDeportivo> encuentros) {
		this.encuentros = encuentros;
	}
	
	public void agregarEncuentro(EncuentroDeportivo e) {
		this.encuentros.add(e);
	}
	
	
}
