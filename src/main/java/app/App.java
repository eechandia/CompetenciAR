package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dominio.Competencia;
import dominio.Deporte;
import gui.*;

public class App extends JFrame {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.println("testeando");
//		
//		
//		SessionFactory factory = new Configuration()
//									.configure("hibernate.cfg.xml")
//									.addAnnotatedClass(Deporte.class)
//									.buildSessionFactory();
//		Session session = factory.openSession();
//		
//		try {
//			Deporte temp = new Deporte("Tenis");
//			session.beginTransaction();
//			session.save(temp);
//			session.getTransaction().commit();
//			} finally {
//			factory.close();
//		}
//		
//	}
	
	public void armarApp() {
		PantallaPrincipalUsuarioNoAutenticado pantalla = new PantallaPrincipalUsuarioNoAutenticado();
		JPanel panel = pantalla.obtenerPantalla();
		this.setContentPane(panel);
		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {

		
//		App aplicacion = new App();
//		aplicacion.setSize(1000, 600);
//		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		aplicacion.armarApp();
//		aplicacion.setTitle("Gestor Camiones");
//		aplicacion.setVisible(true);

		
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.buildSessionFactory();
		
		Deporte dep = new Deporte("Sumo");
		Competencia comp = new Competencia("Compe10", "vale todo pa!");
		comp.setDeporteDeCompetencia(dep);
		
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			session.save(comp); 
		//	session.save(temp);
			session.getTransaction().commit();
			} finally {
				factory.close();
		
		}
		
//		try {
//			Session session = factory.openSession();
//			session.beginTransaction();
//			Competencia comp1 = session.get(Competencia.class, comp.getId());
//			System.out.println(comp1.getDeporteDeCompetencia().getId());
//			session.getTransaction().commit();
//			} finally {
//			factory.close();
//		}
//		
		
		
		

	}



}
