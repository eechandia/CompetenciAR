package gestor;

import dominio.Sesion;
import dominio.Usuario;

public class GestorUsuario {
	
	
	private static Sesion sesionActual = new Sesion(1);	
	
	Usuario getUsuario() {
		return sesionActual.getUsuario();
	}

}
