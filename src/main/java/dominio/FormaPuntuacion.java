package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="FormaPuntuacion", schema = "tp")
public abstract class FormaPuntuacion {
	
	public enum Tipo {
		SETS, PUNTUACION, RESULTADO_FINAL
	}
	
	@Id
	@Column
	protected Integer id;
	
	@ManyToOne()
    @JoinColumn(name = "idModalidad")
	protected Modalidad modalidad;
	
	
}
