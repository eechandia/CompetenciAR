package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="FormaPuntuacionSets", schema = "tp")
public class FormaPuntuacionSets extends FormaPuntuacion {
	@Column
	private Integer cantidadMaxSets;
}
