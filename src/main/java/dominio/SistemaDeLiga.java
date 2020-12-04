package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="sistema_de_liga", schema = "tp")
public class SistemaDeLiga extends SistemaDeCompetencia {

	@Id
	@JoinColumn(name = "id_sistema_competencia")
	@Column(name="id_sistema_competencia")
	protected Integer id;
	
	@Column(name="puntos_por_partido_ganado")
	private Integer puntosPorPartido;
	@Column(name="empate_permitido")
	private Boolean empatePermitido;
	@Column(name="puntos_por_empate")
	private Integer puntosPorEmpate;
	@Column(name="puntos_por_presentarse")
	private Integer puntosPorPresentarse;
	
	public SistemaDeLiga(Modalidad modalidad, Integer puntosPorPartido, Boolean empatePermitido, Integer puntosPorEmpate,
			Integer puntosPorPresentarse) {
		super();
		this.modalidad = modalidad;
		this.puntosPorPartido = puntosPorPartido;
		this.empatePermitido = empatePermitido;
		this.puntosPorEmpate = puntosPorEmpate;
		this.puntosPorPresentarse = puntosPorPresentarse;
	}
	
	public SistemaDeLiga() {
		super();
	}
	
	
}
