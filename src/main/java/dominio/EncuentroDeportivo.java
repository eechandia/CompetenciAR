package dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import dominio.Resultado.tipoRonda;

@Entity
@Table(name="encuentro_deportivo", schema = "tp")

public class EncuentroDeportivo {
	
	enum tipoRonda{
		LIGA, ELIMIN_SIMPLE, ELIMIN_DOBLE_GANADORES, ELIMIN_DOBLE_PERDEDORES
	}
	
	
	
	@Id
	@SequenceGenerator(name="encuentrodeportivo-seq",sequenceName="tp.encuentrodeportivo_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="encuentrodeportivo-seq")
	private Integer id_encuentro_deportivo;
	
	@ManyToOne()
    @JoinColumn(name = "participante1")
	private Participante participante1;
	
	@ManyToOne()
    @JoinColumn(name = "participante2")
	private Participante participante2;
	
	@ManyToOne()
    @JoinColumn(name = "codigo_lugar_de_realizacion")
	private LugarDeRealizacion lugarDeRealizacion;
	
	
	
	@OneToOne
	@JoinColumn(name = "id_siguiente_encuentro_ganador")
	private EncuentroDeportivo siguienteEncuentroGanador;
	
	@OneToOne
	@JoinColumn(name = "id_siguiente_encuentro_perdedor")
	private EncuentroDeportivo siguienteEncuentroPerdedor;
	
	@OneToOne(mappedBy = "siguienteEncuentroGanador")
	private EncuentroDeportivo anteriorEncuentroGanador;
	
	@OneToOne(mappedBy = "siguienteEncuentroPerdedor")
	private EncuentroDeportivo anteriorEncuentroPerdedor;
	
	@OneToMany(mappedBy = "encuentroAsociado")
	private List<Resultado> resultados;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_ronda")
	private tipoRonda ronda;
	
	@ManyToOne()
    @JoinColumn(name = "id_fecha")
	private Fecha fecha;
	

	
	public Integer getId() {
		return id_encuentro_deportivo;
	}
	public void setId(Integer id) {
		this.id_encuentro_deportivo = id;
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
	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	
	
	
}
