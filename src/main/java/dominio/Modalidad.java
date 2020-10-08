package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="modalidad", schema = "tp")
public class Modalidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Embedded
	private FormaPuntuacion formaPuntuacion;
	
	@Embedded
	private SistemaCompetencia sistemaCompetencia;
	
	@OneToOne @MapsId
	@JoinColumn(name="idCompetencia")
	private Competencia competencia;
	
}
