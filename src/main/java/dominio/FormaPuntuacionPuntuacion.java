package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

@Table(name="forma_puntuacion_puntuacion", schema = "tp")
public class FormaPuntuacionPuntuacion extends FormaPuntuacion {
	
	@Column
	private Integer puntos_si_rival_ausente;

	public FormaPuntuacionPuntuacion(Modalidad m1, Integer puntosSiRivalAusente) {
		super();
		this.modalidad = m1;
		this.puntos_si_rival_ausente = puntosSiRivalAusente;
	}
	
	public FormaPuntuacionPuntuacion() {
		super();
	}
	
}
