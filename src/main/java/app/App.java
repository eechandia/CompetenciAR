package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dominio.Deporte;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("testeando");
		
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Deporte.class)
									.buildSessionFactory();
		Session session = factory.openSession();
		
		try {
			Deporte temp = new Deporte("Tenis");
			session.beginTransaction();
			session.save(temp);
			session.getTransaction().commit();
			} finally {
			factory.close();
		}
		
	}
	


}
