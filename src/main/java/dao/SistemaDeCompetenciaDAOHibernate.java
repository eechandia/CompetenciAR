/**
 * 
 */
package dao;

import org.hibernate.Session;

import dominio.Modalidad;
import dominio.SistemaDeCompetencia;

/**
 * @author josesei
 *
 */
public class SistemaDeCompetenciaDAOHibernate implements SistemaDeCompetenciaDAO {
	
	public static synchronized SistemaDeCompetenciaDAO getInstance() {
		if(sistemaDeCompetenciaDAO==null) {
			sistemaDeCompetenciaDAO = new SistemaDeCompetenciaDAOHibernate();
		}
		return sistemaDeCompetenciaDAO;		
	}
	
	private static SistemaDeCompetenciaDAO sistemaDeCompetenciaDAO = null;
	
	

	@Override
	public SistemaDeCompetencia darDeAltaSistemaDeCompetencia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeBajaSistemaDeCompetencia() {
		// TODO Auto-generated method stub

	}


	@Override
	public SistemaDeCompetencia recuperarSistemaDeCompetencia() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarSistemaDeCompetencia(SistemaDeCompetencia sistemaDeCompetencia, Modalidad modalidad, Session session) throws Exception {
		// TODO Auto-generated method stub
		try {
			sistemaDeCompetencia.setModalidad(modalidad);
			session.save(sistemaDeCompetencia);
		}catch(Exception ex) {
			throw ex;
		}
		
		
	}

}
