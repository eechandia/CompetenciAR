package dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import dominio.Competencia;
import dominio.Deporte;
import dominio.Fixture;
import dominio.Reserva;
import dto.CompetenciaDTO;
import utils.HibernateUtils;
import utils.Pair;

public class CompetenciaDAOHibernate implements CompetenciaDAO{
	
	public static synchronized CompetenciaDAO getInstance() {
		if(competenciaDAO==null) {
			competenciaDAO = new CompetenciaDAOHibernate();
		}
		return competenciaDAO;		
	}
	
	private static CompetenciaDAO competenciaDAO = null;
	
	public CompetenciaDAOHibernate() {
		super();
		
	}
	
	
	public Boolean guardarCompetencia(Competencia competencia) throws Exception{
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.save(competencia);
			session.flush();
			
			LugarDeRealizacionDAOHibernate.getInstance().guardarReservas(competencia.getReservasDisponibles(), competencia, session);
			
			session.flush();
			
			competencia.getModalidad().setCompetencia(competencia);
			session.save(competencia.getModalidad());
			session.flush();
					
			SistemaDeCompetenciaDAOHibernate.getInstance().guardarSistemaDeCompetencia(competencia.getModalidad().getSistemaCompetencia(), competencia.getModalidad(), session);
			session.flush();
			
			FormaPuntuacionDAOHibernate.getInstance().guardarFormaPuntuacion(competencia.getModalidad().getFormaPuntuacion(), competencia.getModalidad(), session);
			session.flush();
			
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();	
			throw ex;
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
			
		}
		return true;
	}

	public Boolean verificarSiExiste(String nombre) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Competencia> criteria = builder.createQuery(Competencia.class);
		    Root<Competencia> root = criteria.from(Competencia.class);
		    criteria.select(root).where(builder.equal(root.get("nombre"), nombre));
		    List<Competencia> deportes = session.createQuery(criteria).getResultList();
		    
			return deportes.size()>0;
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}
	}
	public Competencia recuperarCompetencia(CompetenciaDTO competencia) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Competencia competenciaObtenida = null;
		try {	
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
		    CriteriaQuery<Competencia> criteria = builder.createQuery(Competencia.class);
		    Root<Competencia> root = criteria.from(Competencia.class);
		    criteria.select(root).where(builder.equal(root.get("id"), competencia.getId()));
		    competenciaObtenida = session.createQuery(criteria).getSingleResult();

			
			
//			CompetenciaDTO competenciaDTO= new CompetenciaDTO();
//			session.beginTransaction();
//			competenciaObtenida = session.byId(Competencia.class).getReference(competencia.getId());
//			System.out.println("Se obtuvo competencia id: "+ competenciaObtenida.getId());
////			competenciaDTO.setId(competenciaObtenida.getId());
////			competenciaDTO.setNombre(competenciaObtenida.getNombre());
////			competenciaDTO.setDeporteDeCompetencia(new Pair<Integer,String>(competenciaObtenida.getDeporteDeCompetencia().getId(),competenciaObtenida.getDeporteDeCompetencia().getNombre()));
//			session.flush();
//			session.getTransaction().commit();

			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen()) {
	
				session.close();
			}
		}
		return competenciaObtenida;
	}


	public void darDeBajaCompetencia(Competencia competencia) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.delete(competencia);
			System.out.println("La competencia de id: "+ competencia.getId()+" se elimino con exito");
			session.flush();	
			tx.commit();
			
		}catch(Exception ex){
			tx.rollback();	
			throw ex;
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
			
		}
		
	}


	public void modificarCompetencia(Competencia competencia) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			session.beginTransaction();
			session.update(competencia);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}		
	}


	public void modificarCompetencia(Competencia competencia, Fixture fixture) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		FixtureDAOHibernate fixtureDAO = new FixtureDAOHibernate();
		
		try {	
			fixtureDAO.darDeBajaFixture(fixture);
			session.update(competencia);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}	
		
	}










	
	
	
	
}
