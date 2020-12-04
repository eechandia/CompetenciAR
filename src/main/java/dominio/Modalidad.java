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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="modalidad", schema = "tp")
public class Modalidad {
	
	@Id
	@SequenceGenerator(name="modalidad-seq",sequenceName="tp.modalidad_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="modalidad-seq")
	private Integer id;

	
	@OneToOne()
    @JoinColumn(name = "id_competencia")
	private Competencia competencia;
	
	@OneToOne(mappedBy = "modalidad")
	private SistemaDeCompetencia sistemaCompetencia;
	
	@OneToOne(mappedBy = "modalidad")
	private FormaPuntuacion formaPuntuacion;

	public Modalidad(Competencia competencia) {
		super();
		this.competencia = competencia;
	}
	public Modalidad() {
		super();
	}

	public void setSistemaCompetencia(SistemaDeCompetencia sistemaCompetencia) {
		this.sistemaCompetencia = sistemaCompetencia;
	}


	public void setFormaPuntuacion(FormaPuntuacion formaPuntuacion) {
		this.formaPuntuacion = formaPuntuacion;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Competencia getCompetencia() {
		return competencia;
	}


	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}


	public SistemaDeCompetencia getSistemaCompetencia() {
		return sistemaCompetencia;
	}


	public FormaPuntuacion getFormaPuntuacion() {
		return formaPuntuacion;
	}
	
	


	
	
	
	
	
	
}
