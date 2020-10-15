package gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class PantallaPrincipalUsuarioNoAutenticado extends JPanel{

	public GridBagConstraints gbc;
	private JPanel tpPanel;
	
	public PantallaPrincipalUsuarioNoAutenticado(JPanel tp) {
		this.tpPanel = tp;
		this.obtenerPantalla();	
	}
	
	public void obtenerPantalla() {
		
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		ImageIcon iconoPersona= new ImageIcon("IconoPersona.JPG");
		
		JButton competencias=new JButton("Competencias");
		competencias.setPreferredSize(new Dimension(300,40));
		Font fuente =  new Font("fuentePantalla", competencias.getFont().getStyle(), 35);
		competencias.setFont(fuente);
		competencias.setBackground(Color.GRAY);
		competencias.setPreferredSize(new Dimension(600, 70));
		competencias.setMinimumSize(new Dimension(600, 70));
		competencias.setMaximumSize(new Dimension(600, 70));
		
		final JButton iniciarSesion= new JButton();
		iniciarSesion.setIcon(iconoPersona);
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		iniciarSesion.setBorder(compound);
		
		
		JMenuItem opcion1= new JMenuItem("Iniciar Sesion");
		final JPopupMenu popUp = new JPopupMenu();
		popUp.add(opcion1);
		popUp.setBackground(new Color(251, 251, 251));
		opcion1.setBackground(new Color(251, 251, 251));
		
		iniciarSesion.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Point ubicacion = iniciarSesion.getLocation();
				ubicacion.x = ubicacion.x + 8;
				ubicacion.y = ubicacion.y + 97;
				
				popUp.setLocation(ubicacion);
				popUp.setVisible(true);
				
				revalidate();
				repaint();
				
			}
		});
		
		opcion1.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout layout = (CardLayout)tpPanel.getLayout();
		        layout.show(tpPanel, "Card__IniciarSesion");
		        popUp.setVisible(false);
			}
		});
		
		
		this.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				popUp.setVisible(false);
				revalidate();
				repaint();
			}
			
		});
			
		
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.NORTHEAST;
		this.add(iniciarSesion,gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(Box.createRigidArea(new Dimension(20,60)),gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(competencias,gbc);
		
	}
	
}
