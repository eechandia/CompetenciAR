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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="lugarderealizacion", schema = "tp")
public class LugarDeRealizacion {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codigo;
	
	@OneToMany(mappedBy = "competencia")
	private List<Reserva> reservas;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;

	@Column
	private boolean activo;
	
    @ManyToMany(mappedBy = "lugarderealizacion")
	private List<Deporte> deportes;
	
	@ManyToOne()
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
}
