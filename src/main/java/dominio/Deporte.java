package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Deporte")
public class Deporte {

	@Id
	@Column
	private Integer id;
	@Column
	private String nombre;
	
	
	public Deporte(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	
}
