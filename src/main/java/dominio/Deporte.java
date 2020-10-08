package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="Deporte", schema = "tp")
public class Deporte {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String nombre;

	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "DeporteLugarDeRealizacion", 
        joinColumns = { @JoinColumn(name = "idDeporte") }, 
        inverseJoinColumns = { @JoinColumn(name = "idLugarDeRealizacion") }
    )
	private List<LugarDeRealizacion> LugaresDeRealizacion;
	
	public Deporte() {
	};
	
	public Deporte(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
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
