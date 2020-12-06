package dto;

import java.time.LocalDateTime;

public class EncuentroDTO {

	private ParticipanteDTO participante1;
	private ParticipanteDTO participante2;
	private LocalDateTime fecha;
	private Integer etapa;
	
	public EncuentroDTO(ParticipanteDTO participante1, ParticipanteDTO participante2, LocalDateTime fechaYHora, Integer etapaEncuentro) {
		this.participante1 = participante1;
		this.participante2 = participante2;
		this.fecha = fechaYHora;
		this.etapa = etapaEncuentro;
	}

	public ParticipanteDTO getParticipante1() {
		return participante1;
	}

	public ParticipanteDTO getParticipante2() {
		return participante2;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Integer getEtapa() {
		return etapa;
	}
	
}