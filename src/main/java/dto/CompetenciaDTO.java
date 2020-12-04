/**
 * 
 */
package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.Tuple;



import dominio.Competencia;
import dominio.Deporte;
import dominio.FormaPuntuacion;
import dominio.Modalidad;
import dominio.Participante;
import dominio.Reserva;
import dominio.SistemaDeCompetencia;
import dominio.Usuario;
import utils.Pair;

/**
 * @author josesei
 *
 */
public class CompetenciaDTO implements Serializable {

	private Integer id;
	
	private String nombre;
	
	private Competencia.Estado estadoCompetencia;
	
	private SistemaDeCompetencia.Tipo tipoSistemaDeCompetencia;
	
	private FormaPuntuacion.Tipo tipoFormaPuntuacion;

	private List<Participante> participantes;
	
	private String reglamento;
	
	private Boolean dadaDeBaja;
	
	
	private LocalDate fechaBaja;
	
	private Usuario usuarioAsociado;

	private List<Pair<Integer, Integer>> reservasDisponibles;

	private Pair<Integer, String> deporteDeCompetencia; //ver en gestores, mejor para la pantalla 

	private Integer puntosSiRivalAusente;
	
	private Integer cantidadMaxSets;
	
	private Integer puntosPorPartido;
	
	private Boolean empatePermitido;
	
	private Integer puntosPorEmpate;
	
	private Integer puntosPorPresentarse;
	
	
	public CompetenciaDTO(
			Integer id,	String nombre, 
			Competencia.Estado estadoCompetencia, 
			SistemaDeCompetencia.Tipo tipoSistemaDeCompetencia,
			FormaPuntuacion.Tipo tipoFormaPuntuacion,
			List<Participante> participantes, 
			String reglamento, 
			Boolean dadaDeBaja, 
			LocalDate fechaBaja, 
			Usuario usuarioAsociado, 
			List<Pair<Integer, Integer>> reservasDisponibles, 
			Pair<Integer, String> deporteDeCompetencia,
			Integer puntosSiRivalAusente,
			Integer cantidadMaxSets,
			Integer puntosPorPartido,
			Boolean empatePermitido,
			Integer puntosPorEmpate,
			Integer puntosPorPresentarse
			){
		this.id = id;
		this.nombre = nombre;
		this.estadoCompetencia = estadoCompetencia;
		this.tipoSistemaDeCompetencia = tipoSistemaDeCompetencia;
		this.tipoFormaPuntuacion = tipoFormaPuntuacion;
		this.participantes = participantes;
		this.reglamento = reglamento;
		this.dadaDeBaja = dadaDeBaja;
		this.fechaBaja = fechaBaja;
		this.usuarioAsociado = usuarioAsociado;
		this.reservasDisponibles = reservasDisponibles;
		this.deporteDeCompetencia = deporteDeCompetencia;
		this.puntosSiRivalAusente = puntosSiRivalAusente;
		this.cantidadMaxSets = cantidadMaxSets;
		this.puntosPorPartido = puntosPorPartido;
		this.empatePermitido = empatePermitido;
		this.puntosPorEmpate = puntosPorEmpate;
		this.puntosPorPresentarse = puntosPorPresentarse;
	}
	
	public CompetenciaDTO(
			String nombre,
			SistemaDeCompetencia.Tipo tipoSistemaDeCompetencia,
			FormaPuntuacion.Tipo tipoFormaPuntuacion,
			String reglamento, 
			List<Pair<Integer, Integer>> reservasDisponibles,
			Pair<Integer, String> deporteDeCompetencia,
			Integer puntosSiRivalAusente,
			Integer cantidadMaxSets,
			Integer puntosPorPartido,
			Boolean empatePermitido,
			Integer puntosPorEmpate,
			Integer puntosPorPresentarse
			){
		this.nombre = nombre;
		this.tipoSistemaDeCompetencia = tipoSistemaDeCompetencia;
		this.tipoFormaPuntuacion = tipoFormaPuntuacion;
		this.reglamento = reglamento;
		this.reservasDisponibles = reservasDisponibles;
		this.deporteDeCompetencia = deporteDeCompetencia;
		this.puntosSiRivalAusente = puntosSiRivalAusente;
		this.cantidadMaxSets = cantidadMaxSets;
		this.puntosPorPartido = puntosPorPartido;
		this.empatePermitido = empatePermitido;
		this.puntosPorEmpate = puntosPorEmpate;
		this.puntosPorPresentarse = puntosPorPresentarse;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Competencia.Estado getEstadoCompetencia() {
		return estadoCompetencia;
	}

	public SistemaDeCompetencia.Tipo getTipoSistemaDeCompetencia() {
		return tipoSistemaDeCompetencia;
	}

	public FormaPuntuacion.Tipo getTipoFormaPuntuacion() {
		return tipoFormaPuntuacion;
	}

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public String getReglamento() {
		return reglamento;
	}

	public Boolean getDadaDeBaja() {
		return dadaDeBaja;
	}

	public LocalDate getFechaBaja() {
		return fechaBaja;
	}

	public Usuario getUsuarioAsociado() {
		return usuarioAsociado;
	}

	public List<Pair<Integer, Integer>> getReservasDisponibles() {
		return reservasDisponibles;
	}

	public Pair<Integer, String> getDeporteDeCompetencia() {
		return deporteDeCompetencia;
	}
	
	public Integer getIdDeporteDeCompetencia() {
		return deporteDeCompetencia.getFirst();
	}

	public Integer getPuntosSiRivalAusente() {
		return puntosSiRivalAusente;
	}

	public Integer getCantidadMaxSets() {
		return cantidadMaxSets;
	}

	public Integer getPuntosPorPartido() {
		return puntosPorPartido;
	}

	public Boolean getEmpatePermitido() {
		return empatePermitido;
	}

	public Integer getPuntosPorEmpate() {
		return puntosPorEmpate;
	}

	public Integer getPuntosPorPresentarse() {
		return puntosPorPresentarse;
	}


}
