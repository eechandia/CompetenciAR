package dominio;

public class EncuentroDeportivo {

	private Integer id;
	private Participante participante1;
	private Participante participante2;
	private LugarDeRealizacion lugarDeRealizacion;
	EncuentroDeportivo siguienteEncuentroGanador;
	EncuentroDeportivo siguienteEncuentroPerdedor;
	
	
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
