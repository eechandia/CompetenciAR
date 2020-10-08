package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Competencia", schema = "tp")
 
public class Competencia {

	public enum Estado {
		CREADA, PLANIFICADA, EN_DISPUTA, FINALIZADA, CANCELADA
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private Estado estadoCompetencia;

	
	@Embedded
	private List<Participante> participantes;
	
	@Column(name="reglamento")
	private String reglamento;
	
	@Column(name="dadadebaja")
	private boolean dadaDeBaja;
	
	
	@Column(name="fechadebaja")
	private LocalDate fechaBaja;
	
	
//	@Column(name="idusuario")
//	private Usuario usuarioAsociado;
	
//	@Column
//	private List<Reserva> reservasDisponibles;
//	
	
	@OneToOne @MapsId
	@JoinColumn(name="iddeporte")
	private Deporte deporteDeCompetencia;
	
//	@Column
//	private Modalidad modalidadCompetencia;
//	
	public Competencia(){
		this.participantes = new ArrayList<Participante>();
//		this.reservasDisponibles = new ArrayList<Reserva>();
		
	}

	
	
	public Competencia(String nombre, String reglamento) {
		super();
		this.nombre = nombre;
		this.reglamento = reglamento;
	
	}



	public void setDeporteDeCompetencia(Deporte deporteDeCompetencia) {
		this.deporteDeCompetencia = deporteDeCompetencia;
	}



	public Integer getId() {
		return id;
	}



	public Deporte getDeporteDeCompetencia() {
		return deporteDeCompetencia;
	}






	
	
}
