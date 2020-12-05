package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Fecha", schema = "tp")

public class Fecha {

	@Id
	@SequenceGenerator(name="fecha-seq",sequenceName="tp.fecha_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fecha-seq")
	private Integer id_fecha;

	@Column(name="numero_fecha")
	private Integer numeroFecha;
	
	@OneToMany(mappedBy = "fecha")
	private List<EncuentroDeportivo> encuentros;
	
	@ManyToOne()
    @JoinColumn(name = "id_fixture")
	private Fixture fixture;
	
	
	public Fecha() {
		encuentros = new ArrayList<EncuentroDeportivo>();
	}
	
	public Integer getId() {
		return id_fecha;
	}
	public void setId(Integer id) {
		this.id_fecha = id;
	}
	public Integer getNumeroFecha() {
		return numeroFecha;
	}
	public void setNumeroFecha(Integer numeroFecha) {
		this.numeroFecha = numeroFecha;
	}
	public List<EncuentroDeportivo> getEncuentros() {
		return encuentros;
	}
	public void setEncuentros(List<EncuentroDeportivo> encuentros) {
		this.encuentros = encuentros;
	}
	
	public void agregarEncuentro(EncuentroDeportivo e) {
		this.encuentros.add(e);
	}
	
	
}
