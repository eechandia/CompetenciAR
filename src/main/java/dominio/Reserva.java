package dominio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
@Entity
@Table(name="reserva", schema = "tp")
class Reserva {
	
	@Embedded
	private LugarDeRealizacion lugarDeRealizacion;
	
	@Embedded
	private Competencia competencia;
	
	@Column
	private Integer disponibilidad; 
}
