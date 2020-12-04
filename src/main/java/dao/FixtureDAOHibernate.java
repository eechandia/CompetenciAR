package dao;

import org.hibernate.Session;

import dominio.Fixture;
import utils.HibernateUtils;

public class FixtureDAOHibernate implements FixtureDAO{

	@Override
	public void darDeBajaFixture(Fixture fixture) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			session.delete(fixture);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}	
		
	}

}
