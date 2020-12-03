package dto;

public class ParticipanteDTO {

	private String nombre;
	private String email;
	
	public ParticipanteDTO(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}


	
}
