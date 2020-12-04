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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="provincia", schema = "tp")
public class Provincia {
	
	@Id
	@SequenceGenerator(name="provincia-seq",sequenceName="tp.provincia_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="provincia-seq")
	private Integer id;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy = "provincia")
	private List<Localidad> localidades;
	
	@ManyToOne()
    @JoinColumn(name = "id_pais")
	private Pais pais;
	
	public Provincia() {
		this.localidades = new ArrayList<Localidad>();
		
	}
}
