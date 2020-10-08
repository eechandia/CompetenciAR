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
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name="participante", schema = "tp")
public class Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer idParticipante;
	@Column
	private String nombre;
	@Column
	private String email;
	
	@ManyToOne()
    @JoinColumn(name = "idCompetencia")
	private Competencia competencia;
	
}
