package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="sistemadeeliminatoriasimple", schema = "tp")
public class SistemaDeEliminatoriaSimple extends SistemaDeCompetencia {
	
	@Id
	@JoinColumn(name = "idsistemacompetencia")
	protected Integer id;

	public SistemaDeEliminatoriaSimple(Modalidad modalidad) {
		super();
		this.modalidad = modalidad;
	}

	public SistemaDeEliminatoriaSimple() {
		super();
	}
	
}
