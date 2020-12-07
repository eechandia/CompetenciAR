package dao;

import org.hibernate.Session;

import dominio.EncuentroDeportivo;
import dominio.Fecha;
import utils.HibernateUtils;

public class EncuentroDeportivoDAOHibernate implements EncuentroDeportivoDAO{

	
	public void guardarEncuentroDeportivo(EncuentroDeportivo encuentro) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
				session.beginTransaction();
				session.save(encuentro);
				session.getTransaction().commit();
			}catch (Exception e) {
			e.printStackTrace();
			}
			finally {
				if(session!=null && session.isOpen())
					session.close();
		}	
	}
	
	public void darDeBajaEncuentro(EncuentroDeportivo encuentro) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
				session.beginTransaction();
				session.delete(encuentro);
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
