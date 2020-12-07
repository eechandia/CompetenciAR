package gestor;

import dominio.Sesion;
import dominio.Usuario;

public class GestorUsuario {
	
	
	private static Sesion sesionActual = new Sesion(1, new Usuario(1, "ChecoPerez@gmail.com", "hunter12", "Perez", "Checo"));	
	
	Usuario getUsuario() {
		return sesionActual.getUsuario();
	}

}
