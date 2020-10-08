package app;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		
		App aplicacion = new App();
		aplicacion.setSize(1000, 600);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Gestor Camiones");
		aplicacion.setVisible(true);
	}



}
