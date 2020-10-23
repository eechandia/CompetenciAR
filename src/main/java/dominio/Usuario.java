package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="usuario", schema = "tp")
public class Usuario {

	public enum TipoDoc{
		DNI, PASAPORTE, PART_DE_NACIMIENTO
	}
	
	public Usuario() {
		
	}
	
	@Id
	@SequenceGenerator(name="usuario-seq",sequenceName="tp.usuario_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="usuario-seq")
	@Column
	private Integer id;
	
	@Column
	private String correoElectronico;
	
	@Column
	private String contrasena;
	
	@Column
	private String apellido;
	
	@Column
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column
	private TipoDoc tipoDocumento;
	
	@Column
	private String documento;
	
//	@ManyToOne()
//	@JoinColumn(name = "idLocalidad")
//	private Localidad localidad;
//	

	@OneToMany(mappedBy = "usuarioAsociado")
	private List<Competencia> competencias;


	public Usuario(Integer id, String correoElectronico, String contrasena, String apellido, String nombre) {
		super();
		this.id = id;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.apellido = apellido;
		this.nombre = nombre;
	}
	
	
	
}
