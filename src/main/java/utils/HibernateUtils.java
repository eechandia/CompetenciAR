package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dominio.Competencia;
import dominio.Deporte;
import dominio.FormaPuntuacion;
import dominio.FormaPuntuacionPuntuacion;
import dominio.FormaPuntuacionResFinal;
import dominio.FormaPuntuacionSets;
import dominio.Localidad;
import dominio.LugarDeRealizacion;
import dominio.Modalidad;
import dominio.Pais;
import dominio.Participante;
import dominio.Provincia;
import dominio.Reserva;
import dominio.Sesion;
import dominio.SistemaDeCompetencia;
import dominio.SistemaDeEliminatoriaDoble;
import dominio.SistemaDeEliminatoriaSimple;
import dominio.SistemaDeLiga;
import dominio.Usuario;

public class HibernateUtils {

	private static SessionFactory factory = null;
	
	public static synchronized SessionFactory getSessionFactory() {
		if(factory == null) {
			factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Deporte.class)
										.addAnnotatedClass(Competencia.class)
										.addAnnotatedClass(FormaPuntuacion.class)
										.addAnnotatedClass(FormaPuntuacionPuntuacion.class)
										.addAnnotatedClass(FormaPuntuacionSets.class)
										.addAnnotatedClass(FormaPuntuacionResFinal.class)
										.addAnnotatedClass(Localidad.class)
										.addAnnotatedClass(LugarDeRealizacion.class)
										.addAnnotatedClass(Modalidad.class)
										.addAnnotatedClass(Pais.class)
										.addAnnotatedClass(Participante.class)
										.addAnnotatedClass(Provincia.class)
										.addAnnotatedClass(Reserva.class)
										.addAnnotatedClass(Sesion.class)
										.addAnnotatedClass(SistemaDeCompetencia.class)
										.addAnnotatedClass(SistemaDeEliminatoriaDoble.class)
										.addAnnotatedClass(SistemaDeEliminatoriaSimple.class)
										.addAnnotatedClass(SistemaDeLiga.class)
										.addAnnotatedClass(Usuario.class)
										.buildSessionFactory();
		}
		
		return factory;
	}
}
