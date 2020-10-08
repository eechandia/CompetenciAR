package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dominio.Competencia;
import dominio.Deporte;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("testeando");
		
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		
		Deporte temp = new Deporte("Hockey");
		Competencia comp = new Competencia("Compe1", "vale todo pa!");
		comp.setDeporteDeCompetencia(temp);
		
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.save(comp); 
			session.save(temp);
			session.getTransaction().commit();
			} finally {
		
		}
		
		
		
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			Competencia comp1 = session.get(Competencia.class, comp.getId());
			System.out.println(comp1.getDeporteDeCompetencia().getId());
			session.getTransaction().commit();
			} finally {
			factory.close();
		}
		
		
		
		
	}
	


}
