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
}
