package dominio;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class SistemaCompetencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	protected Integer id;
	
}
