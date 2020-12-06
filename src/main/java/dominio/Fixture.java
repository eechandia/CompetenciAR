package dominio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="fixture", schema = "tp")

public class Fixture {
	
	@Id
//	@SequenceGenerator(name="fixture-seq",sequenceName="tp.fixture_id_seq", initialValue=1, allocationSize=1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fixture-seq")
	private Integer id;
	
	@OneToMany(mappedBy = "fixture")
	private List<Fecha> fechas;
	

	
	public Fixture() {
		fechas = new ArrayList<Fecha>();
		
	}
	
	public void agregarFecha(Fecha fecha) {
		this.fechas.add(fecha);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Fecha> getFechas() {
		return fechas;
	}

	public void setFechas(List<Fecha> fechas) {
		this.fechas = fechas;
	}


	
}
