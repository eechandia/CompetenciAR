package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="provincia", schema = "tp")
public class Provincia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy = "provincia")
	private List<Localidad> localidades;
	
	@ManyToOne()
    @JoinColumn(name = "idPais")
	private Pais pais;
	
	public Provincia() {
		this.localidades = new ArrayList<Localidad>();
		
	}
}
