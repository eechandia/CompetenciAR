package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="FormaPuntuacionSets", schema = "tp")
public class FormaPuntuacionSets extends FormaPuntuacion {
	@Column
	private Integer cantidadMaxSets;
	
	public FormaPuntuacionSets(Modalidad m1, Integer cantidadMaxSets) {
		super();
		this.modalidad = m1;
		this.cantidadMaxSets = cantidadMaxSets;
	}
}
