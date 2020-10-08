package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="SistemaDeLiga", schema = "tp")
public class SistemaDeLiga extends SistemaCompetencia {

	@Column
	private Integer puntosPorPartido;
	@Column
	private boolean empatePermitido;
	@Column
	private Integer puntosPorEmpate;
	@Column
	private Integer puntosPorPresentarse;
}
