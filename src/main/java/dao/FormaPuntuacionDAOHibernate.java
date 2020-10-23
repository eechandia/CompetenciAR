/**
 * 
 */
package dao;

import org.hibernate.Session;

import dominio.FormaPuntuacion;
import dominio.Modalidad;
import dominio.SistemaDeCompetencia;

/**
 * @author josesei
 *
 */
public class FormaPuntuacionDAOHibernate implements FormaPuntuacionDAO {
	
	public static synchronized FormaPuntuacionDAO getInstance() {
		if(formaPuntuacionDAO==null) {
			formaPuntuacionDAO = new FormaPuntuacionDAOHibernate();
		}
		return formaPuntuacionDAO;		
	}
	
	private static FormaPuntuacionDAO formaPuntuacionDAO = null;

	@Override
	public FormaPuntuacion darDeAltaFormaPuntuacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void darDeBajaFormaPuntuacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarFormaPuntuacion() {
		// TODO Auto-generated method stub

	}

	@Override
	public FormaPuntuacion recuperarFormaPuntuacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarFormaPuntuacion(FormaPuntuacion formaPuntuacion, Modalidad modalidad,
			Session session) throws Exception {

		try {
			formaPuntuacion.setModalidad(modalidad);
			session.save(formaPuntuacion);
			
		}catch(Exception ex) {
			throw ex;
		}
		
	}

}
