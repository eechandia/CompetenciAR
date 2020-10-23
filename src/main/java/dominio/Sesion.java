package dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Embeddable
@Entity
@Table(name="sesion", schema = "tp")
public class Sesion {
	
	@Id
	@SequenceGenerator(name="sesion-seq",sequenceName="tp.sesion_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sesion-seq")
	private Integer id;
	

	@Column(name="fechabaja")
	private LocalDateTime fechaInicio;
	

	private LocalDateTime fechaFin;	
	
	@ManyToOne()
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;
}
