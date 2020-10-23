package dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import dto.CompetenciaDTO;

@Entity
@Table(name="competencia", schema = "tp")
 
public class Competencia {

	public enum Estado {
		CREADA, PLANIFICADA, EN_DISPUTA, FINALIZADA, CANCELADA
	}
	
	@Id
	@SequenceGenerator(name="competencia-seq",sequenceName="tp.competencia_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="competencia-seq")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Enumerated(EnumType.STRING)
	@Column(name="estado")
	private Estado estadoCompetencia;

	@OneToMany(mappedBy = "competencia")
	private List<Participante> participantes;
	
	@Column(name="reglamento")
	private String reglamento;
	
	@Column(name="dadadebaja")
	private Boolean dadaDeBaja;
	
	
	@Column(name="fechadebaja")
	private LocalDate fechaBaja;
	
	@ManyToOne()
    @JoinColumn(name = "idUsuario")
	private Usuario usuarioAsociado;
	
	@OneToMany(mappedBy = "competencia")
	private List<Reserva> reservasDisponibles;
	
	
	@OneToOne @MapsId
	@JoinColumn(name="iddeporte")
	private Deporte deporteDeCompetencia;
	
	@OneToOne(mappedBy = "competencia")
	private Modalidad modalidad;

	
	public Competencia(){
	}

	
	public void inicializarCompetencia(CompetenciaDTO compeDTO) {
		this.id=null;
		this.nombre = compeDTO.getNombre();
		this.estadoCompetencia = compeDTO.getEstadoCompetencia();
		this.participantes = compeDTO.getParticipantes();
		this.reglamento = compeDTO.getReglamento();
		this.dadaDeBaja = false;
		this.fechaBaja = null;
		this.estadoCompetencia = Estado.CREADA;
	
	};
	
	



	public void setUsuarioAsociado(Usuario usuarioAsociado) {
		this.usuarioAsociado = usuarioAsociado;
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

	public List<Reserva> getReservasDisponibles() {
		return reservasDisponibles;
	}


	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}
	
	public void setReservasDisponibles(List<Reserva> reservas) {
		this.reservasDisponibles = reservas;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Estado getEstadoCompetencia() {
		return estadoCompetencia;
	}


	public void setEstadoCompetencia(Estado estadoCompetencia) {
		this.estadoCompetencia = estadoCompetencia;
	}


	public List<Participante> getParticipantes() {
		return participantes;
	}


	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}


	public String getReglamento() {
		return reglamento;
	}


	public void setReglamento(String reglamento) {
		this.reglamento = reglamento;
	}


	public Boolean getDadaDeBaja() {
		return dadaDeBaja;
	}


	public void setDadaDeBaja(Boolean dadaDeBaja) {
		this.dadaDeBaja = dadaDeBaja;
	}


	public LocalDate getFechaBaja() {
		return fechaBaja;
	}


	public void setFechaBaja(LocalDate fechaBaja) {
		this.fechaBaja = fechaBaja;
	}


	public Usuario getUsuarioAsociado() {
		return usuarioAsociado;
	}


	public Modalidad getModalidad() {
		return modalidad;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
		
}



	

