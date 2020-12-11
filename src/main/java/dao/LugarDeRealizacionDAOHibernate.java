package dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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

	public List<LugarDeRealizacion> recuperarLugaresDeRealizacion(int idDeporte) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		String finalHql;
		try {	
			
			List<LugarDeRealizacion> lugares = session.createSQLQuery("Select * from tp.lugar_de_realizacion LR INNER JOIN tp.deporte_lugar_de_realizacion DLR ON LR.codigo = DLR.id_lugar_de_realizacion WHERE id_deporte = "+ idDeporte).addEntity(LugarDeRealizacion.class).list();
		    
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
