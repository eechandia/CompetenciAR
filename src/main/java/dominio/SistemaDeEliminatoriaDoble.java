package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="sistemadeeliminatoriadoble", schema = "tp")
public class SistemaDeEliminatoriaDoble extends SistemaDeCompetencia {
	
	@Id
	@JoinColumn(name = "idsistemacompetencia")
	protected Integer id;

	public SistemaDeEliminatoriaDoble(Modalidad modalidad) {
		super();
		this.modalidad = modalidad;
	}

}
