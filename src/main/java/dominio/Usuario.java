package dominio;

public class Usuario {

	public enum TipoDoc{
		DNI, PASAPORTE, PART_DE_NACIMIENTO
	}
	
	private Integer idUsuario;
	private String correoElectronico;
	private String contraseña;
	private String apellido;
	private String nombre;
	private TipoDoc tipoDocumento;
	private String documento;
	
}
