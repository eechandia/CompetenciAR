package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name="lugarderealizacion", schema = "tp")
public class LugarDeRealizacion {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer codigo;
	
	@Column
	private String nombre;
	
	@Column
	private String descripcion;

	@Column
	private boolean activo;
	
	@Embedded
	private Usuario usuario;
	
}
