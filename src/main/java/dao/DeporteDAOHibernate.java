package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import dominio.Deporte;
import utils.HibernateUtils;

public class DeporteDAOHibernate implements DeporteDAO{


	public Deporte recuperarDeporte(Integer id) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
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
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Deporte> criteria = builder.createQuery(Deporte.class);
		    criteria.from(Deporte.class);
		    List<Deporte> deportes = session.createQuery(criteria).getResultList();
		    
			return deportes;
		}
		finally {
			session.close();
		}

	}
	

}
