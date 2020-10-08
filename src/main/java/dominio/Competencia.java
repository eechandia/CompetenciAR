package dominio;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

 @Entity
 @Table(name="Competencia", schema = "tp")
 
public class Competencia {

	public enum Estado {
		CREADA, PLANIFICADA, EN_DISPUTA, FINALIZADA, CANCELADA
	}
	
	@Id
	@Column(name="id")
	private Integer id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="estado")
	private Estado estadoCompetencia;
	
	private List<Participante> participantes;
	
	@Column(name="reglamento")
	private String reglamento;
	
	@Column(name="dadadebaja")
	private boolean dadaDeBaja;
	
	@Column(name="fechabaja")
	private LocalDateTime fechaBaja;
	
	@Column(name="idusuario")
	private Usuario usuarioAsociado;
	
	private List<Reserva> reservasDisponibles;
	
	@Column(name="iddeporte")
	private Integer deporteDeCompetencia;
	
	private Modalidad modalidadCompetencia;
	
	public Competencia(){
		this.participantes = new ArrayList<Participante>();
		this.reservasDisponibles = new ArrayList<Reserva>();
		
	}

	
	
	public Competencia(String nombre, String reglamento) {
		super();
		this.nombre = nombre;
		this.reglamento = reglamento;
	
	}



	
	
}
