package utils;

import dominio.Competencia.Estado;
import dominio.SistemaDeCompetencia;

public class Filtro {

	private String nombreCompetencia;
	private Integer idDeporte;
	private SistemaDeCompetencia.Tipo modalidad;
	private Estado estado;
	
	public Filtro(String nombre, Integer id, SistemaDeCompetencia.Tipo tipo, Estado estado) {
		this.nombreCompetencia = nombre;
		this.idDeporte = id;
		this.modalidad = tipo;
		this.estado = estado;
	}

	public String getNombreCompetencia() {
		return nombreCompetencia;
	}

	public Integer getIdDeporte() {
		return idDeporte;
	}

	public SistemaDeCompetencia.Tipo getModalidad() {
		return modalidad;
	}

	public Estado getEstado() {
		return estado;
	}
	
	
}
