package dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




public abstract class SistemaDeCompetencia {
	
	public enum Tipo {
		LIGA, ELIMIN_SIMPLE, ELIMIN_DOBLE
	}
	
	

	protected Integer id;
	
	@ManyToOne()
    @JoinColumn(name = "idModalidad")
	protected Modalidad modalidad;
	
	
}
