package dominio;

import javax.persistence.Column;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FormaPuntuacionResFinal", schema = "tp")
public class FormaPuntuacionResFinal extends FormaPuntuacion{
	
	public FormaPuntuacionResFinal(Modalidad m1) {
		super();
		this.modalidad = m1;
	
	}

	public FormaPuntuacionResFinal() {
		super();
	}
}
