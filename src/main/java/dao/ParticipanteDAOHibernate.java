package dao;

import java.util.List;

import org.hibernate.Session;
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
	public void darDeBajaParticpante() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarParticipante() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarParticipante() {
		// TODO Auto-generated method stub
		
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
			String hql = "SELECT nombre FROM Participante p WHERE nombre = :nombre_participante and idCompetencia = :id_competencia";
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
