package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name="FormaPuntuacionPuntuacion", schema = "tp")
public class FormaPuntuacionPuntuacion extends FormaPuntuacion {
	
	@Column
	private Integer puntosSiRivalAusente;
}
