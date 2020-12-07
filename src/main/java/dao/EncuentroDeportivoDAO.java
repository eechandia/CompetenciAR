package dao;

import dominio.EncuentroDeportivo;

public interface EncuentroDeportivoDAO {

	public void guardarEncuentroDeportivo(EncuentroDeportivo encuentro);

	public void darDeBajaEncuentro(EncuentroDeportivo encuentro);
}
