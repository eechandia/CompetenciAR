package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="usuario", schema = "tp")
public class Usuario {

	public enum TipoDoc{
		DNI, PASAPORTE, PART_DE_NACIMIENTO
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String correoElectronico;
	
	@Column
	private String contraseña;
	
	@Column
	private String apellido;
	
	@Column
	private String nombre;
	
	@Enumerated
	@Column
	private TipoDoc tipoDocumento;
	
	@Column
	private String documento;
	
	@ManyToOne()
    @JoinColumn(name = "idLocalidad")
	private Localidad localidad;
	

	@OneToMany(mappedBy = "usuarioAsociado")
	private List<Competencia> competencias;
	
}
