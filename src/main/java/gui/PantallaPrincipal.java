package gui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class PantallaPrincipal extends JFrame{

	public GridBagConstraints gbc;
	
	public void armarApp() {
		this.gbc = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		
		ImageIcon iconoPersona= new ImageIcon("Icono1.JPG");
		
		JButton competencias=new JButton("Competencias");
		competencias.setPreferredSize(new Dimension(300,40));
		
		JButton iniciarSesion= new JButton();
		iniciarSesion.setIcon(iconoPersona);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		iniciarSesion.setBorder(compound);
		
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth=2;
		gbc.gridheight=2;
		gbc.anchor=GridBagConstraints.CENTER;
		
		gbc.weightx=0.15;
		gbc.weighty=0.15;
		//gbc.fill=gbc.BOTH;
		panel.add(iniciarSesion,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.weightx=0.5;
		gbc.weighty=0.5;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(competencias,gbc);
		
		
		
		//resetGbc();
		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	public static void main(String[] args) {
		
		PantallaPrincipal aplicacion = new PantallaPrincipal();
		aplicacion.setSize(1000, 600);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Prueba pantalla principal");
		aplicacion.setVisible(true);
	}
}
