package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="sistemaCompetencia", schema = "tp")

public abstract class SistemaDeCompetencia {
	


	
	public enum Tipo {
		LIGA, ELIMIN_SIMPLE, ELIMIN_DOBLE
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected Integer id;
	
	@OneToOne() 
    @JoinColumn(name = "idModalidad")
	protected Modalidad modalidad;
	
	
}
