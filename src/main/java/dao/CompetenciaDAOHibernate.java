package dao;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dominio.Competencia;
import dominio.Deporte;
import dominio.Reserva;
import dto.CompetenciaDTO;

public class CompetenciaDAOHibernate implements CompetenciaDAO{
	
	private SessionFactory factory = null;
	
	public CompetenciaDAOHibernate() {
		super();
		
	}
	

	public Competencia darDeAltaCompetencia(CompetenciaDTO compeDTO) {
	
		return null;
	}
	
	public Boolean guardarCompetencia(Competencia competencia) {
		
		Session session = factory.openSession();
		Transaction tx=null;
		
		try{
		    tx = session.beginTransaction();
			session.save(competencia); 
			tx.commit();
		}catch(Exception ex){
			tx.rollback();	
		}
		finally {
			session.close();
		}
		return true;
	}

	public Boolean verificarSiExiste(String nombre) {
		Session session = factory.openSession();
		List<String> nombres = session.createQuery("select nombre from tp.Competencia c where c.nombre = :nombre").setParameter("id", nombre).list();
		if(!nombres.isEmpty()) return true;
		else return false;
	}

	public Competencia recuperarCompetencia() {
	
		return null;
	}


	public void darDeBajaCompetencia() {
		// TODO Auto-generated method stub
		
	}


	public void modificarCompetencia() {
		// TODO Auto-generated method stub
		
	}



	
	
	
	
}
