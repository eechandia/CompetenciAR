package dominio.utils;

import dominio.Modalidad;

public class CompetenciaDTO {

	String nombre;
	String deporte;
	String reglamento;
	Boolean empatePermitido;
	Integer cantidadMaxSets;
	Integer puntosPorPresentarse;
	String formaPuntuacion;
	Integer puntosPorEmpate;
	Integer puntosSiRivalAusente;
	
	public CompetenciaDTO(String nombre, String deporte, String reglamento, Boolean empatePermitido,
			Integer cantidadMaxSets, Integer puntosPorPresentarse, String formaPuntuacion, Integer puntosPorEmpate,
			Integer puntosSiRivalAusente) {
		this.nombre = nombre;
		this.deporte = deporte;
		this.reglamento = reglamento;
		this.empatePermitido = empatePermitido;
		this.cantidadMaxSets = cantidadMaxSets;
		this.puntosPorPresentarse = puntosPorPresentarse;
		this.formaPuntuacion = formaPuntuacion;
		this.puntosPorEmpate = puntosPorEmpate;
		this.puntosSiRivalAusente = puntosSiRivalAusente;
	}
	
	
	
}
