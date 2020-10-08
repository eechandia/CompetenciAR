package dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
@Entity
@Table(name="sesion", schema = "tp")
public class Sesion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	@Column(name="fechabaja")
	private LocalDateTime fechaInicio;
	
	@Temporal(TemporalType.DATE)
	private LocalDateTime fechaFin;	
	
	@Embedded
	private Usuario usuario;
}
