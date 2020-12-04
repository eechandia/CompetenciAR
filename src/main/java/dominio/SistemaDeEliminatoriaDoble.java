package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="sistema_de_eliminatoria_doble", schema = "tp")
public class SistemaDeEliminatoriaDoble extends SistemaDeCompetencia {
	
	@Id
	@JoinColumn(name = "id_sistema_competencia")
	protected Integer id;

	public SistemaDeEliminatoriaDoble(Modalidad modalidad) {
		super();
		this.modalidad = modalidad;
	}
	
	public SistemaDeEliminatoriaDoble() {
		super();
	}

}
