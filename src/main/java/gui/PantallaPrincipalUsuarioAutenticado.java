package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class PantallaPrincipalUsuarioAutenticado extends JFrame{

	public GridBagConstraints gbc;
	
	public void armarApp() {
		this.gbc = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		
		ImageIcon iconoPersona= new ImageIcon("Icono1.JPG");
		
		JButton competencias=new JButton("Competencias");
		competencias.setPreferredSize(new Dimension(300,40));
		
		JButton misCompetencias=new JButton("Mis Competencias");
		misCompetencias.setPreferredSize(new Dimension(300,40));
		
		JButton lugarDeRealizacion=new JButton("Lugares de Realización");
		lugarDeRealizacion.setPreferredSize(new Dimension(300,40));
		
		
		JMenuItem iniciarSesion= new JMenuItem("Iniciar Sesión");
		
		JButton persona = new JButton();
		persona.setIcon(iconoPersona);
		JPopupMenu menu=new JPopupMenu();
		persona.add(menu);
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth=2;
		gbc.gridheight=2;
		gbc.anchor=GridBagConstraints.CENTER;
		
		gbc.weightx=0.20;
		gbc.weighty=0.20;
		//gbc.fill=gbc.BOTH;
		panel.add(persona,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(misCompetencias,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(competencias,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(lugarDeRealizacion,gbc);

		
		//resetGbc();
		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		
		PantallaPrincipalUsuarioAutenticado aplicacion = new PantallaPrincipalUsuarioAutenticado();
		aplicacion.setSize(1000, 600);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Prueba pantalla principal Usuarui autenticado");
		aplicacion.setVisible(true);
	}
}

