package dominio;

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
@Table(name="modalidad", schema = "tp")
public class Modalidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	
	@OneToOne()
    @JoinColumn(name = "idCompetencia")
	private Competencia competencia;
	
	@OneToOne(mappedBy = "modalidad")
	private SistemaDeCompetencia sistemaCompetencia;
	
	@OneToOne(mappedBy = "modalidad")
	private FormaPuntuacion formaPuntuacion;

	public Modalidad(Competencia competencia) {
		super();
		this.competencia = competencia;
	}


	public void setSistemaCompetencia(SistemaDeCompetencia sistemaCompetencia) {
		this.sistemaCompetencia = sistemaCompetencia;
	}


	public void setFormaPuntuacion(FormaPuntuacion formaPuntuacion) {
		this.formaPuntuacion = formaPuntuacion;
	}


	
	
	
	
	
	
}
