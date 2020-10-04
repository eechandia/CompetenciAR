package dao;

import dominio.Sesion;
import dominio.Usuario;

public interface UsuarioDAO {

	public abstract Usuario darDeAltaUsuario();
	public abstract void darDeBajaUsuario();
	public abstract void modificarUsuario();
	public abstract void guardarUsuario();
	public abstract Usuario recuperarUsuario();
	
	public abstract void guardarSesion();
	public abstract Sesion recuperarSesion();
	
}
