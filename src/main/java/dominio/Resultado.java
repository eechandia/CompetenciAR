package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
>>>>>>> 8c4698b92c5469136283ef608414db96490948fa
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="resultado", schema = "tp")

public class Resultado {

	
	@Id
	@SequenceGenerator(name="resultado-seq",sequenceName="tp.resultado_id_seq", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="resultado-seq")
	private Integer id;
	
	@Column(name="participante1Presente")
	private Boolean participante1Presente;
	
<<<<<<< HEAD
	@Column(name="participante1Presente")
=======
	@Column(name="participante2Presente")
>>>>>>> 8c4698b92c5469136283ef608414db96490948fa
	private Boolean participante2Presente;
	
	@Column(name="participante1Ganador")
	private Boolean participante1Ganador;
	
	@Column(name="participante2Ganador")
	private Boolean participante2Ganador;
	
<<<<<<< HEAD
	//falta mapeo
=======
	@ManyToOne()
    @JoinColumn(name = "idEncuentroDeportivo")
>>>>>>> 8c4698b92c5469136283ef608414db96490948fa
	private EncuentroDeportivo encuentroAsociado;
	
}
