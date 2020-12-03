package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.ParticipanteDAO;
import dao.ParticipanteDAOHibernate;
import dominio.Deporte;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import gestor.*;
import gui.*;

@SuppressWarnings("serial")
public class App extends JFrame {

	private JPanel tpPanel;
	private CardLayout cl;
	private GestorDeporte gestorDeporte;
	private GestorCompetencia gestorCompetencia;
	private PantallaPrincipalUsuarioNoAutenticado usuarioNoA;
	private PantallaIniciarSesion iniciarSesion;
	private PantallaPrincipalUsuarioAutenticado usuarioA;
	private AltaCompetencia altaCompetencia;
	private ListarCompetencias listarCompetencias;
	private ListarCompetencias listarMisCompetencias;
	
	
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
		this.setBackground(Color.WHITE);
		this.setTitle("Trabajo Práctico 2020 - Diseño de Sistemas");
		this.setMinimumSize(new Dimension(1280, 720));
		this.setMaximumSize(new Dimension(1280, 720));
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBounds(43, 43, 1280, 720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Inicializar gestores
		gestorDeporte = new GestorDeporte();
		gestorCompetencia = new GestorCompetencia();
		
		//Inicializar paneles y CardLayout
		tpPanel = new JPanel();
		cl = new CardLayout(0,0);
		tpPanel.setLayout(cl);
		
		usuarioNoA = new PantallaPrincipalUsuarioNoAutenticado(tpPanel);
		iniciarSesion = new PantallaIniciarSesion(tpPanel);
		usuarioA = new PantallaPrincipalUsuarioAutenticado(tpPanel);
		altaCompetencia = new AltaCompetencia(tpPanel, gestorDeporte, gestorCompetencia);
		listarCompetencias = new ListarCompetencias(tpPanel, gestorDeporte, gestorCompetencia);
		listarMisCompetencias = new ListarCompetencias(tpPanel, gestorDeporte, gestorCompetencia);
		
		tpPanel.add(usuarioNoA, "Card__UsuarionNoAutenticado");
		tpPanel.add(iniciarSesion, "Card__IniciarSesion");
		tpPanel.add(usuarioA, "Card__UsuarioAutenticado");
		tpPanel.add(altaCompetencia, "Card__AltaCompetencia");
		tpPanel.add(listarCompetencias, "Card__ListarCompetencias");
		tpPanel.add(listarMisCompetencias, "Card__MisCompetencias");

		this.setContentPane(tpPanel);
		this.pack();
		//revalidate();
		//repaint();
	} 
	
	private App(){
		armarApp();
	}
	
	public static void main(String[] args) {
		
		
//		Para probar metodo de participanteDAO
//		ParticipanteDTO participante = new ParticipanteDTO("esteban", "esteban@gmail.com");
//		CompetenciaDTO competencia = new CompetenciaDTO(5, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
//		ParticipanteDAO participanteDAO = new ParticipanteDAOHibernate();
//		System.out.println(participanteDAO.nombreOEmailYaExiste(participante, competencia));

		
//		App aplicacion = new App();
//		aplicacion.setSize(1000, 600);
//		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		aplicacion.armarApp();
//		aplicacion.setTitle("Gestor Camiones");
//		aplicacion.setVisible(true);

		// TODO Auto-generated method stub
/*		System.out.println("testeando");
		
		
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
		}*/
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		
		Deporte temp = new Deporte("Boleibol");

		
		try {
			Session session = factory.openSession();
			session.beginTransaction(); 
			session.save(temp);
			session.getTransaction().commit();
			} finally {
				factory.close();
		}
		
		System.out.println("probando updatear");
		
		try {
			Session session = factory.openSession();
			session.beginTransaction(); 
			temp.setNombre("Baseboll");
			session.update(temp);
			session.getTransaction().commit();
			} finally {
				factory.close();
		}
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
					  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					  
				  }  
			    catch (UnsupportedLookAndFeelException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (ClassNotFoundException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (InstantiationException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (IllegalAccessException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
				  
				  EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								App ventana = new App();
								ventana.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
			}
		});
	}
}

