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
public class Reserva  implements Serializable{
	
	
	
	
	public Reserva(LugarDeRealizacion lugarDeRealizacion, Integer disponibilidad) {
		this.lugarDeRealizacion=lugarDeRealizacion;
		this.disponibilidad=disponibilidad;
	}
	public Reserva() {
		super();
	}

	@Id
	@ManyToOne()
    @JoinColumn(name = "codigo_lugar_de_realizacion")
	private LugarDeRealizacion lugarDeRealizacion;
	
    @Id
	@ManyToOne()
    @JoinColumn(name = "id_competencia")
	private Competencia competencia;
	
    @Column(name="disponibilidad")
	private Integer disponibilidad;

	public LugarDeRealizacion getLugarDeRealizacion() {
		return lugarDeRealizacion;
	}

	public void setLugarDeRealizacion(LugarDeRealizacion lugarDeRealizacion) {
		this.lugarDeRealizacion = lugarDeRealizacion;
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}

	public Integer getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Integer disponibilidad) {
		this.disponibilidad = disponibilidad;
	} 
	
	
	
	
}
