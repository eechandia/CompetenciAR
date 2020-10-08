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
import javax.persistence.Table;


@Entity
@Table(name="localidad", schema = "tp")
public class Localidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column 
	private String[] codigoPostal;
	
	@Column 
	private String nombre;
	

	@ManyToOne()
    @JoinColumn(name = "idProvincia")
	private Provincia provincia;
	
	@OneToMany(mappedBy = "localidad")
	private List<Usuario> usuarios;
	
}
