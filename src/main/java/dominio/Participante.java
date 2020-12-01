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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name="participante", schema = "tp")
public class Participante {
	
	@Id
	@SequenceGenerator(name="participante-seq",sequenceName="tp.participante_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="participante-seq")
	private Integer idParticipante;
	@Column
	private String nombre;
	@Column
	private String email;
	
	@ManyToOne()
    @JoinColumn(name = "idCompetencia")
	private Competencia competencia;

	public Integer getIdParticipante() {
		return idParticipante;
	}

	public String getNombre() {
		return nombre;
	}

	public String getEmail() {
		return email;
	}

	public Competencia getCompetencia() {
		return competencia;
	}
	
}
