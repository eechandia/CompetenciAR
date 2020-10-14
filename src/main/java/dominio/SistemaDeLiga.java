package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SistemaDeLiga", schema = "tp")
public class SistemaDeLiga extends SistemaDeCompetencia {

	@Column
	private Integer puntosPorPartido;
	@Column
	private Boolean empatePermitido;
	@Column
	private Integer puntosPorEmpate;
	@Column
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
	
	
	
}
