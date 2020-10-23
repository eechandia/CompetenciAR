package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="LugarDeRealizacion", schema = "tp")
public class LugarDeRealizacion {

	
	public LugarDeRealizacion(Integer codigo) {
		this.codigo=codigo;
	}
	
	public LugarDeRealizacion() {
	}

	@Id
	@SequenceGenerator(name="lugar-de-realizacion-seq",sequenceName="tp.lugarderealizacion_codigo_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lugar-de-realizacion-seq")
	private Integer codigo;
	
	@OneToMany(mappedBy = "competencia")
	private List<Reserva> reservas;
	
	@Column
	private String nombre;
	
	@Column
	private Integer disponibilidad;
	
	@Column
	private String descripcion;

	@Column
	private Boolean activo;
	
    @ManyToMany(mappedBy = "LugaresDeRealizacion")
	private List<Deporte> deportes = new ArrayList<Deporte>();
	
	@ManyToOne()
    @JoinColumn(name = "idUsuario")
	private Usuario usuario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<Deporte> getDeportes() {
		return deportes;
	}

	public void setDeportes(List<Deporte> deportes) {
		this.deportes = deportes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Integer disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	
	
	
	
}