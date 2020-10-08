package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;



public class PantallaIniciarSesion extends JFrame{

public GridBagConstraints gbc;
	
	public void armarApp() {
		this.gbc = new GridBagConstraints();
		JPanel panel = new JPanel(new GridBagLayout());
		
		ImageIcon iconoVolver= new ImageIcon("IconoVolver.JPG");
		
		JLabel ingresarUsuario = new JLabel("Usuario: ");
		JLabel ingresarContraseña = new JLabel("Contraseña: ");
		
		JTextField usuario = new JTextField();
		JPasswordField contraseña = new JPasswordField();
		
		usuario.setPreferredSize(new Dimension(200,20));
		usuario.setMinimumSize(new Dimension(30,20));
		usuario.setMaximumSize(new Dimension(250,20));
		
		contraseña.setPreferredSize(new Dimension(200,20));
		contraseña.setMinimumSize(new Dimension(30,20));
		contraseña.setMaximumSize(new Dimension(250,20));
		
		JButton volver = new JButton();
		volver.setIcon(iconoVolver);
		Border line = new LineBorder(Color.BLACK);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		JButton aceptar = new JButton("Aceptar");
		
		JButton filler = new JButton ();
		filler.setPreferredSize(new Dimension(200,20));
		filler.setMinimumSize(new Dimension(30,20));
		filler.setMaximumSize(new Dimension(250,20));
		filler.setVisible(false);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=1;

		panel.add(volver,gbc);
		
		
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		panel.add(ingresarUsuario,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		
		panel.add(usuario,gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		panel.add(ingresarContraseña,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		panel.add(contraseña,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.EAST;
		//gbc.weightx=0.1;
		//gbc.fill=GridBagConstraints.NONE;
		
		panel.add(aceptar,gbc);
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridx = 4;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		//gbc.weightx=0.1;
		//gbc.fill=GridBagConstraints.NONE;
		
		panel.add(filler,gbc);
		
		//resetGbc();
		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	
public static void main(String[] args) {
		
		PantallaIniciarSesion aplicacion = new PantallaIniciarSesion();
		aplicacion.setSize(1000, 600);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Prueba pantalla Iniciar sesion");
		aplicacion.setVisible(true);
	}
}
