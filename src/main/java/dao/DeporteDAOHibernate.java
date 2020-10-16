package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dominio.Competencia;
import dominio.Deporte;

public class DeporteDAOHibernate implements DeporteDAO{

	private SessionFactory factory = null;

	public Deporte recuperarDeporte(Integer id) {
		
		Session session = factory.openSession();
		try {
		session.beginTransaction();
		Deporte deporte = session.get(Deporte.class, id);
		session.getTransaction().commit();
		return deporte;
		}
		finally {
			session.close();
		}

	}

	public Deporte darDeAltaDeporte() {
		// TODO Auto-generated method stub
		return null;
	}

	public void darDeBajaDeporte() {
		// TODO Auto-generated method stub
		
	}

	public void modificarDeporte() {
		// TODO Auto-generated method stub
		
	}

	public void guardarDeporte() {
		// TODO Auto-generated method stub
		
	}

	public List<Deporte> recuperarDeportes() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
