package dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;




public abstract class SistemaCompetencia {
	

	protected Integer id;
	
	@ManyToOne()
    @JoinColumn(name = "idModalidad")
	private Modalidad modalidad;
}
