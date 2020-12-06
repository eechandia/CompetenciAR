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
import java.util.ArrayList;

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

@SuppressWarnings("serial")
public class PantallaPrincipalUsuarioAutenticado extends JPanel {

	private GridBagConstraints gbc;
	private JPanel tpPanel;
	
	public PantallaPrincipalUsuarioAutenticado(JPanel tp) {
		this.tpPanel = tp;
		this.armarPanel();
	}
	
	private void armarPanel() {
		this.gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		ImageIcon iconoPersona= new ImageIcon("IconoPersona.JPG");
		
		JButton competencias=new JButton("Competencias");
		Font fuente =  new Font("fuentePantalla", competencias.getFont().getStyle(), 35);
		competencias.setFont(fuente);
		competencias.setBackground(Color.GRAY);
		competencias.setPreferredSize(new Dimension(600, 70));
		competencias.setMinimumSize(new Dimension(600, 70));
		competencias.setMaximumSize(new Dimension(600, 70));
		
		JButton misCompetencias=new JButton("Mis Competencias");
		misCompetencias.setFont(fuente);
		misCompetencias.setBackground(Color.GRAY);
		misCompetencias.setPreferredSize(new Dimension(600, 70));
		misCompetencias.setMinimumSize(new Dimension(600, 70));
		misCompetencias.setMaximumSize(new Dimension(600, 70));
		
		JButton lugarDeRealizacion=new JButton("Lugares de Realización");
		lugarDeRealizacion.setFont(fuente);
		lugarDeRealizacion.setBackground(Color.GRAY);
		lugarDeRealizacion.setPreferredSize(new Dimension(600, 70));
		lugarDeRealizacion.setMinimumSize(new Dimension(600, 70));
		lugarDeRealizacion.setMaximumSize(new Dimension(600, 70));
		
		
		final JButton usuario= new JButton();
		usuario.setIcon(iconoPersona);
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		usuario.setBorder(compound);
		
		
		JMenuItem opcion1= new JMenuItem("Modificar Usuario");
		JMenuItem opcion2= new JMenuItem("Dar de Baja Usuario");
		JMenuItem opcion3= new JMenuItem("Cerrar Sesión");
		final JPopupMenu popUp = new JPopupMenu();
		popUp.add(opcion1);
		popUp.add(opcion2);
		popUp.add(opcion3);
		popUp.setBackground(new Color(251, 251, 251));
		opcion1.setBackground(new Color(251, 251, 251));
		opcion2.setBackground(new Color(251, 251, 251));
		opcion3.setBackground(new Color(251, 251, 251));
		
		
		usuario.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Point ubicacion = usuario.getLocation();
				ubicacion.x = ubicacion.x + 8;
				ubicacion.y = ubicacion.y + 97;
				popUp.setLocation(ubicacion);
				popUp.setVisible(true);

			}
		});
		
		
		this.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				
			}
			
			public void mousePressed(MouseEvent e) {
				
			}
			
			public void mouseExited(MouseEvent e) {
				
			}
			
			public void mouseEntered(MouseEvent e) {
				
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
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(usuario,gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(Box.createRigidArea(new Dimension(20,20)),gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(misCompetencias,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(Box.createRigidArea(new Dimension(20,20)),gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth=1;
		gbc.gridheight=1;
	
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(competencias,gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(Box.createRigidArea(new Dimension(20,20)),gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth=1;
		gbc.gridheight=1;
		
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor=GridBagConstraints.CENTER;
		this.add(lugarDeRealizacion,gbc);
		
		//Funcion botones
		competencias.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel listarCompetencias = new ListarTodasLasCompetencias(tpPanel, new ArrayList<Object>());
				tpPanel.add(listarCompetencias, "ListarTodasLasCompetencias");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "ListarTodasLasCompetencias");
			}
			
		});

		misCompetencias.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel listarCompetencias = new ListarCompetencias(tpPanel, new ArrayList<Object>());
				tpPanel.add(listarCompetencias, "ListarCompetencias");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "ListarCompetencias");
			}
			
		});

	}

}

