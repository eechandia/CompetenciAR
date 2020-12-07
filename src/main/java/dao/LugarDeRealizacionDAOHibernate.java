package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dominio.Competencia;
import dominio.Deporte;
import dominio.LugarDeRealizacion;
import dominio.Reserva;
import utils.HibernateUtils;

public class LugarDeRealizacionDAOHibernate implements LugarDeRealizacionDAO{

	
	public static synchronized LugarDeRealizacionDAO getInstance() {
		if(lugarDeRealizacionDAO==null) {
			lugarDeRealizacionDAO = new LugarDeRealizacionDAOHibernate();
		}
		return lugarDeRealizacionDAO;		
	}
	
	private static LugarDeRealizacionDAO lugarDeRealizacionDAO = null;
	
	
	
	public LugarDeRealizacion darDeAltaLugarDeRealizacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public void modificarLugarDeRealizacion() {
		// TODO Auto-generated method stub
		
	}

	public void darDeBajaLugarDeRealizacion() {
		// TODO Auto-generated method stub
		
	}

	public void guardarLugarDeRealizacion() {
		// TODO Auto-generated method stub
		
	}

	public LugarDeRealizacion recuperarLugarDeRealizacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LugarDeRealizacion> recuperarLugaresDeRealizacion() {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			
			//List<LugarDeRealizacion> lugares = session.createSQLQuery("Select * from tp.lugarDeRealizacion").addEntity(LugarDeRealizacion.class).list();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<LugarDeRealizacion> criteria = builder.createQuery(LugarDeRealizacion.class);
		    criteria.from(LugarDeRealizacion.class);
		    List<LugarDeRealizacion> lugares = session.createQuery(criteria).getResultList();
		    
			return lugares;
		}
		finally {
			session.close();
		}

	}
	
	public void guardarReservas(List<Reserva> reservas, Competencia competencia, Session session) throws Exception {
		try {
			for(Reserva reserva : reservas) {
				reserva.setCompetencia(competencia);
				session.save(reserva);
			}
		}catch(Exception ex){
			throw ex;
		}
	}

	@Override
	public void actualizarLugarDeRealizacion(LugarDeRealizacion lugar) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(lugar);
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
