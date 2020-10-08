package dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="reserva", schema = "tp")
class Reserva  implements Serializable{
	
	@Id
	@ManyToOne()
    @JoinColumn(name = "codigoLugarDeRealizacion")
	private LugarDeRealizacion lugarDeRealizacion;
	
    @Id
	@ManyToOne()
    @JoinColumn(name = "idCompetencia")
	private Competencia competencia;
	
	@Column
	private Integer disponibilidad; 
}
