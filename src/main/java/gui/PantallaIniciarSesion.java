package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		panel.setBackground(Color.WHITE);
		
		ImageIcon iconoVolver= new ImageIcon("IconoVolver.JPG");
		
		JLabel ingresarUsuario = new JLabel("Usuario: ");
		JLabel ingresarContraseña = new JLabel("Contraseña: ");
		Font fuente =  new Font("fuentePantalla", ingresarContraseña.getFont().getStyle(), 35);
		ingresarUsuario.setFont(fuente);
		ingresarContraseña.setFont(fuente);
		
		
		JTextField usuario = new JTextField();
		JPasswordField contraseña = new JPasswordField();
		
		usuario.setFont(fuente);
		contraseña.setFont(fuente);
		
		usuario.setPreferredSize(new Dimension(550,50));
		usuario.setMinimumSize(new Dimension(550, 50));
		usuario.setMaximumSize(new Dimension(550, 50));
		
		contraseña.setPreferredSize(new Dimension(550, 50));
		contraseña.setMinimumSize(new Dimension(550, 50));
		contraseña.setMaximumSize(new Dimension(550, 50));
		
		JButton volver = new JButton();
		volver.setPreferredSize(new Dimension(33,33));
		volver.setIcon(iconoVolver);
		
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		
		JButton aceptar = new JButton("Aceptar");
		aceptar.setFont(fuente);
		aceptar.setBackground(Color.GRAY);
		aceptar.setPreferredSize(new Dimension(200,50));
		aceptar.setMinimumSize(new Dimension(200, 50));
		aceptar.setMaximumSize(new Dimension(200, 50));
		
		aceptar.addActionListener( e -> {
			
			if (usuario.getText().isBlank()) {
				JOptionPane.showMessageDialog(panel,"Debe ingresar un usuario", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if(contraseña.getPassword().length == 0) {
				JOptionPane.showMessageDialog(panel,"Debe ingresar una contraseña", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				String usuarioIngresado = usuario.getText();
				char[] contraseñaIngresada = contraseña.getPassword();
			}
		});
		
		JButton filler = new JButton ();
		filler.setBorder(compound);
		filler.setEnabled(false);
		filler.setBackground(Color.WHITE);
		filler.setPreferredSize(new Dimension(117,20));
		filler.setMinimumSize(new Dimension(117, 20));
		filler.setMaximumSize(new Dimension(117, 20));
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.NORTHWEST;
		panel.add(volver,gbc);
		

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(Box.createRigidArea(new Dimension(150,200)),gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.EAST;
		panel.add(ingresarUsuario,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;
		panel.add(usuario,gbc);
		
	
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth=1;
		gbc.gridheight=1;

		panel.add(Box.createRigidArea(new Dimension(117,10)),gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel.add(ingresarContraseña,gbc);
	
		gbc.gridx = 3;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		panel.add(contraseña,gbc);
		
		
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.gridwidth=1;
		gbc.gridheight=1;

		panel.add(Box.createRigidArea(new Dimension(117,20)),gbc);
		gbc.gridx = 3;
		gbc.gridy = 6;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.EAST;
		//gbc.weightx=0.1;

		panel.add(aceptar,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 7;
		gbc.gridwidth=1;
		gbc.gridheight=1;

		panel.add(Box.createRigidArea(new Dimension(100,150)),gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;

		panel.add(Box.createRigidArea(new Dimension(200,0)),gbc);

		setContentPane(panel);
		revalidate();
		repaint();
	}
	
	
public static void main(String[] args) {
		
		PantallaIniciarSesion aplicacion = new PantallaIniciarSesion();
		aplicacion.setSize(1280, 720);
		aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aplicacion.armarApp();
		aplicacion.setTitle("Prueba pantalla Iniciar sesion");
		aplicacion.setVisible(true);
	}
}
