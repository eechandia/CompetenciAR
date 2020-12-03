package dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="EncuentroDeportivo", schema = "tp")

public class EncuentroDeportivo {
	
	
	@Id
	@SequenceGenerator(name="encuentrodeportivo-seq",sequenceName="tp.encuentrodeportivo_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="encuentrodeportivo-seq")
	private Integer id;
	
	@ManyToOne()
    @JoinColumn(name = "participante1")
	private Participante participante1;
	
	@ManyToOne()
    @JoinColumn(name = "participante2")
	private Participante participante2;
	
	@ManyToOne()
    @JoinColumn(name = "CodigoLugarDeRealizacion")
	private LugarDeRealizacion lugarDeRealizacion;
	
	@OneToOne
	@JoinColumn(name = "idSiguienteEncuentroGanador")
	private EncuentroDeportivo siguienteEncuentroGanador;
	
	@OneToOne
	@JoinColumn(name = "idSiguienteEncuentroPerdedor")
	private EncuentroDeportivo siguienteEncuentroPerdedor;
	
	@OneToOne(mappedBy = "siguienteEncuentroGanador")
	private EncuentroDeportivo anteriorEncuentroGanador;
	
	@OneToOne(mappedBy = "siguienteEncuentroPerdedor")
	private EncuentroDeportivo anteriorEncuentroPerdedor;
	
	@OneToMany(mappedBy = "id")
	private List<Resultado> resultados;
	
	@ManyToOne()
    @JoinColumn(name = "idFecha")
	private Fecha fecha;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Participante getParticipante1() {
		return participante1;
	}
	public void setParticipante1(Participante participante1) {
		this.participante1 = participante1;
	}
	public Participante getParticipante2() {
		return participante2;
	}
	public void setParticipante2(Participante participante2) {
		this.participante2 = participante2;
	}
	public LugarDeRealizacion getLugarDeRealizacion() {
		return lugarDeRealizacion;
	}
	public void setLugarDeRealizacion(LugarDeRealizacion lugarDeRealizacion) {
		this.lugarDeRealizacion = lugarDeRealizacion;
	}
	
	
	
}
