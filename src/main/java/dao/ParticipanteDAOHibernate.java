package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dominio.Participante;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import utils.HibernateUtils;

public class ParticipanteDAOHibernate implements ParticipanteDAO{

	@Override
	public Participante darDeAltaParticpante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeBajaParticpante(Participante participante) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.delete(participante);
			System.out.println("El participante "+ participante.getNombre()+" se elimino con exito");
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

	@Override
	public void modificarParticipante() {
	
		
	}

	@Override
	public void guardarParticipante(Participante participante) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
		    
			session.save(participante);
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

	@Override
	public Participante recuperarParticipante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean nombreOEmailYaExiste(ParticipanteDTO participante, CompetenciaDTO competencia) {
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		try {	
			String hql = "SELECT nombre FROM Participante p WHERE nombre = :nombre_participante and id_competencia = :id_competencia";
			Query query = session.createQuery(hql);
			query.setParameter("nombre_participante",participante.getNombre());
			query.setParameter("id_competencia", competencia.getId());
			List resultados = query.list();
			
			if (resultados.isEmpty()) {
				return false;
			}
			else {
				return true;
			}
		}
		finally {
			if(session!=null && session.isOpen())
			session.close();
		}
	}

}
