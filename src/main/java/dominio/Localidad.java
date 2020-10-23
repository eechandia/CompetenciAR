package dominio;

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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="localidad", schema = "tp")
public class Localidad {
	
	@Id
	@SequenceGenerator(name="localidad-seq",sequenceName="tp.localidad_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="localidad-seq")
	private Integer id;
	
	@Column 
	private String[] codigoPostal;
	
	@Column 
	private String nombre;
	

	@ManyToOne()
    @JoinColumn(name = "idProvincia")
	private Provincia provincia;
	
//	@OneToMany(mappedBy = "localidad")
//	private List<Usuario> usuarios;
	
}
