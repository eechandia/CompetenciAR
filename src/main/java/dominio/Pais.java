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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="pais", schema = "tp")
public class Pais {
	
	@Id
	@SequenceGenerator(name="pais-seq",sequenceName="tp.pais_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="pais-seq")
	private Integer id;
	
	@Column
	private String nombre;
	
	@OneToMany(mappedBy = "pais")
	private List<Provincia> provincias;
	
	public Pais() {
		this.provincias = new ArrayList<Provincia>();
		
	}
}
