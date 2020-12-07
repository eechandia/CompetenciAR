package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="forma_puntuacion_sets", schema = "tp")
public class FormaPuntuacionSets extends FormaPuntuacion {
	@Column
	private Integer cantidad_max_sets;
	
	public FormaPuntuacionSets(Modalidad m1, Integer cantidadMaxSets) {
		super();
		this.modalidad = m1;
		this.cantidad_max_sets = cantidadMaxSets;
	}
	
	public FormaPuntuacionSets() {
		super();
	}

	public Integer getCantidad_max_sets() {
		return cantidad_max_sets;
	}
	
	
}
