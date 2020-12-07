package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dao.CompetenciaDAOHibernate;
import dao.ParticipanteDAO;
import dao.ParticipanteDAOHibernate;
import dominio.Competencia.Estado;
import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import exceptions.EstadoCompetenciaException;
import exceptions.ParticipantesInsuficientesException;
import exceptions.ReservasInsuficientesException;
import gestor.*;
import gui.*;

@SuppressWarnings("serial")
public class App extends JFrame {

	private JPanel tpPanel;
	private CardLayout cl;
	private GestorCompetencia gestorCompetencia;
	private PantallaPrincipalUsuarioAutenticado usuarioA;
	
	
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
	
	public void armarApp(){
		this.setBackground(Color.WHITE);
		this.setTitle("Trabajo Practico 2020 - Disenio de Sistemas");
		this.setMinimumSize(new Dimension(1280, 720));
		this.setMaximumSize(new Dimension(1280, 720));
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBounds(43, 43, 1280, 720);
		this.setIconImage(new ImageIcon("src/main/resources/icono.JPG").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		
		

	//	Inicializar gestores
		gestorDeporte = new GestorDeporte();
		gestorCompetencia = new GestorCompetencia();
		

		CompetenciaDTO compDTO = new CompetenciaDTO(31, null, null, null, null, null, null, null, null, null, null, null, null,null, null, null, null, null);
		try {
			gestorCompetencia.generarFixture(compDTO);
		} catch (ParticipantesInsuficientesException e) {
			System.out.println(e.getMessage());
		} catch (EstadoCompetenciaException e) {
			System.out.println(e.getMessage());
		} catch (ReservasInsuficientesException e) {
			System.out.println(e.getMessage());
		}
		
//		//###########Para probar metodo de alta Participante###########
//				CompetenciaDAOHibernate competenciaDAO= new CompetenciaDAOHibernate();
//				
//				//Esta fallando esto de competenciaDTO
//				GestorCompetencia gestorCompetencia = new GestorCompetencia();
//				
//				
//				CompetenciaDTO competencia= new CompetenciaDTO(31,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
//
//				
//				ParticipanteDTO participante = new ParticipanteDTO("aaaa", "aaaa@gmail.com");

//				GestorParticipante gestorParticipante = new GestorParticipante();
//				try {
//					gestorParticipante.crearParticipante(participante, competencia);
//					System.out.println("funciono");
//				} catch (Exception e1) {
//					System.out.println("F");
//					e1.printStackTrace();
//				}

		
		//Inicializar paneles y CardLayout
		tpPanel = new JPanel();
		cl = new CardLayout(0,0);
		tpPanel.setLayout(cl);
		
		usuarioA = new PantallaPrincipalUsuarioAutenticado(tpPanel);
		
		tpPanel.add(usuarioA, "UsuarioAutenticado");

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

		
		*/
	
	
		
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
					  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					  
				  }  
			    catch (UnsupportedLookAndFeelException e) {
			    	e.printStackTrace();
			    }
			    catch (ClassNotFoundException e) {
			    	e.printStackTrace();
			    }
			    catch (InstantiationException e) {
			    	e.printStackTrace();
			    }
			    catch (IllegalAccessException e) {
			    	e.printStackTrace();
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
