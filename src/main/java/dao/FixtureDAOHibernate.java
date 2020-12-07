package dao;

import org.hibernate.Session;

import dominio.Fecha;
import dominio.Fixture;
import dominio.Reserva;
import utils.HibernateUtils;

public class FixtureDAOHibernate implements FixtureDAO{

	
	public void guardarFixture(Fixture fixture) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
				session.beginTransaction();
				session.save(fixture);
				session.getTransaction().commit();
			}catch (Exception e) {
			e.printStackTrace();
			}
			finally {
				if(session!=null && session.isOpen())
					session.close();
			}		
		}
	
	
	@Override
	public void darDeBajaFixture(Fixture fixture) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(fixture);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}	
	}


	public Integer guardarFecha(Fecha fecha) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Integer id=0;
		try {
				session.beginTransaction();
				id = (Integer) session.save(fecha);
				session.getTransaction().commit();
				return id;
			}catch (Exception e) {
			e.printStackTrace();
			}
			finally {
				if(session!=null && session.isOpen())
					session.close();
		}
		return id;
	}
	
	public void darDeBajaFecha(Fecha fecha) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(fecha);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}	
	}


}
