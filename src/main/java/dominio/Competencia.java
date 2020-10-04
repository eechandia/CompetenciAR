package dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Competencia {

	public enum Estado {
		CREADA, PLANIFICADA, EN_DISPUTA, FINALIZADA, CANCELADA
	}
	
	private Integer id;
	private String nombre;
	private Estado estadoCompetencia;
	private List<Participante> participantes;
	private String reglamento;
	private boolean dadaDeBaja;
	private LocalDateTime fechaBaja;
	
	public Competencia(){
		this.participantes = new ArrayList<Participante>();
	}
}
