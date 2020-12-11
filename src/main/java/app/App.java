package app;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dao.CompetenciaDAO;
import dao.CompetenciaDAOHibernate;
import gestor.*;
import gui.*;

@SuppressWarnings("serial")
public class App extends JFrame {

	private JPanel tpPanel;
	private CardLayout cl;
	private GestorParticipante gestorParticipante;
	private PantallaPrincipalUsuarioAutenticado usuarioA;

	
	public void armarApp(){
		this.setBackground(Color.WHITE);
		this.setTitle("Trabajo Practico 2020 - Diseño de Sistemas");
		this.setMinimumSize(new Dimension(1280, 720));
		this.setMaximumSize(new Dimension(1280, 720));
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBounds(43, 43, 1280, 720);
		this.setIconImage(new ImageIcon("src/main/resources/icono.JPG").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	

		//Inicializar paneles y CardLayout
		tpPanel = new JPanel();
		cl = new CardLayout(0,0);
		tpPanel.setLayout(cl);
		
		usuarioA = new PantallaPrincipalUsuarioAutenticado(tpPanel);
		tpPanel.add(usuarioA, "UsuarioAutenticado");
		this.setContentPane(tpPanel);
		this.pack();

	} 
	
	private App(){
		armarApp();
	}
	
	public static void main(String[] args) {
		//Inicializar gestores
		CompetenciaDAO compeDAO = new CompetenciaDAOHibernate();
		compeDAO.verificarSiExiste("hola"); //busca un objeto null para que hibernate arranque antes de la app
		
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
