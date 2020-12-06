package gui;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import gestor.GestorParticipante;

@SuppressWarnings("serial")
public class AltaParticipante extends JPanel {
		
	
		private JPanel camposPanel;
		private JPanel botonPanel;
		private JPanel tpPanel;
		private GestorParticipante gestorParticipante;
		
		public AltaParticipante(JPanel tp, CompetenciaDTO competencia) {
			this.tpPanel = tp;
			this.gestorParticipante = new GestorParticipante();
			this.setBackground(Color.WHITE);
			this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			this.armarPantalla(competencia);
		}
		
		private void armarPantalla(CompetenciaDTO competenciaDTO) {		

			//Armar Pantalla
			//Etiquetas
			JLabel agregarParticipante = new JLabel("Agregar Participante");
			JLabel nombreParticipante = new JLabel("Nombre:");
			JLabel emailParticipante = new JLabel("Email:");
			JLabel camposObligatorios = new JLabel(": Campos obligatorios");
			JLabel asterisco1 = new JLabel("*");
			JLabel asterisco2 = new JLabel("*");
			JLabel asterisco3 = new JLabel("*");
			
			//Campos de Texto
			JTextField nombreTexto = new JTextField(150);
			JTextField emailTexto = new JTextField(150);
			
			//Botones
			JButton volver = new JButton();
			JButton aceptar = new JButton("Aceptar");
			
			//Fuentes
			Font etiquetas = new Font("Tahoma", Font.PLAIN, 20);
			Font textos = new Font("Tahoma", Font.PLAIN, 16);
			
			agregarParticipante.setFont(new Font("Tahoma", Font.PLAIN, 30));
			
			nombreParticipante.setFont(etiquetas);
			emailParticipante.setFont(etiquetas);
			camposObligatorios.setFont(etiquetas);
			
			asterisco1.setFont(etiquetas);
			asterisco1.setForeground(Color.red);
			asterisco2.setFont(etiquetas);
			asterisco2.setForeground(Color.red);
			asterisco3.setFont(etiquetas);
			asterisco3.setForeground(Color.red);
			
			nombreTexto.setFont(textos);
			emailTexto.setFont(textos);
			
			aceptar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			
			//Dimensiones
			Dimension tituloDimension = new Dimension(400, 50);
			Dimension etiquetasDimension = new Dimension(400, 30);
			Dimension botonDimension = new Dimension(100, 40);
			Dimension textosDimension = new Dimension(400, 30);
			
			agregarParticipante.setMinimumSize(tituloDimension);
			agregarParticipante.setMaximumSize(tituloDimension);
			agregarParticipante.setPreferredSize(tituloDimension);
			
			nombreParticipante.setMinimumSize(etiquetasDimension);
			nombreParticipante.setMaximumSize(etiquetasDimension);
			nombreParticipante.setPreferredSize(etiquetasDimension);
		
			emailParticipante.setMinimumSize(etiquetasDimension);
			emailParticipante.setMaximumSize(etiquetasDimension);
			emailParticipante.setPreferredSize(etiquetasDimension);

			camposObligatorios.setMinimumSize(etiquetasDimension);
			camposObligatorios.setMaximumSize(etiquetasDimension);
			camposObligatorios.setPreferredSize(etiquetasDimension);
			
			nombreTexto.setMinimumSize(textosDimension);
			nombreTexto.setMaximumSize(textosDimension);
			nombreTexto.setPreferredSize(textosDimension);
			
			emailTexto.setMinimumSize(textosDimension);
			emailTexto.setMaximumSize(textosDimension);
			emailTexto.setPreferredSize(textosDimension);
			
			aceptar.setMinimumSize(botonDimension);
			aceptar.setMaximumSize(botonDimension);
			aceptar.setPreferredSize(botonDimension);
			
			//Boton volver imagen
			ImageIcon iconoVolver= new ImageIcon("src/main/resources/IconoVolver.JPG");
			volver.setPreferredSize(new Dimension(33,33));
			volver.setIcon(iconoVolver);
			Border line = new LineBorder(Color.WHITE);
			Border margin = new EmptyBorder(0, 0, 0, 0);
			Border compound = new CompoundBorder(line, margin);
			volver.setBorder(compound);
			
			//Constraints
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.insets = new Insets(3, 10, 3, 3);
			constraints.fill = GridBagConstraints.WEST;
			constraints.gridx = 0;
			constraints.gridy = 0;
			
			//Panel de datos + boton volver		
			camposPanel = new JPanel(new GridBagLayout());
			camposPanel.setMinimumSize(new Dimension(1200, 600));
			camposPanel.setBackground(Color.WHITE);
			
			Dimension rellenoDim = new Dimension(200, 30);
			
			JPanel rellenoPanel = new JPanel();
			JPanel rellenoPanel1 = new JPanel();
			JPanel rellenoPanel2 = new JPanel();
			JPanel rellenoPanel3 = new JPanel();
			
			rellenoPanel.setMinimumSize(new Dimension(600, 80));
			rellenoPanel.setMaximumSize(new Dimension(600, 80));
			rellenoPanel.setPreferredSize(new Dimension(600, 80));
			rellenoPanel.setBackground(Color.white);
						
			rellenoPanel1.setMinimumSize(rellenoDim);
			rellenoPanel1.setMaximumSize(rellenoDim);
			rellenoPanel1.setPreferredSize(rellenoDim);
			rellenoPanel1.setBackground(Color.white);
						
			rellenoPanel2.setMinimumSize(rellenoDim);
			rellenoPanel2.setMaximumSize(rellenoDim);
			rellenoPanel2.setPreferredSize(rellenoDim);
			rellenoPanel2.setBackground(Color.white);
			
			rellenoPanel3.setMinimumSize(new Dimension(600, 80));
			rellenoPanel3.setMaximumSize(new Dimension(600, 80));
			rellenoPanel3.setPreferredSize(new Dimension(600, 80));
			rellenoPanel3.setBackground(Color.white);
			
			camposPanel.add(volver, constraints);
			
			constraints.gridx = 2;
			camposPanel.add(agregarParticipante, constraints);
			
			constraints.gridx = 3;
			camposPanel.add(rellenoPanel, constraints);
			
			constraints.gridy = 1;
			camposPanel.add(rellenoPanel3, constraints);
			
			constraints.gridy = 2;
			constraints.gridx = 1;
			camposPanel.add(asterisco1, constraints);
			
			constraints.gridx = 2;
			camposPanel.add(nombreParticipante, constraints);
			
			constraints.gridy = 3;
			camposPanel.add(nombreTexto, constraints);			
			
			constraints.gridy = 4;
			camposPanel.add(rellenoPanel1, constraints);
			
			constraints.gridy = 5;
			constraints.gridx = 1;
			camposPanel.add(asterisco2, constraints);
			
			constraints.gridx = 2;
			camposPanel.add(emailParticipante, constraints);
			
			constraints.gridy = 6;
			camposPanel.add(emailTexto, constraints);
			
			constraints.gridy = 7;
			camposPanel.add(rellenoPanel2, constraints);
			
			constraints.gridy = 8;
			constraints.gridx = 1;
			camposPanel.add(asterisco3, constraints);
			
			constraints.gridx = 2;
			camposPanel.add(camposObligatorios, constraints);
			
			//Panel boton
			botonPanel = new JPanel(new GridBagLayout());
			botonPanel.setBackground(Color.white);
			
			JPanel rellenoBoton = new JPanel();
			Dimension dimensionRellenoB = new Dimension(700, 30);
			rellenoBoton.setMinimumSize(dimensionRellenoB);
			rellenoBoton.setMaximumSize(dimensionRellenoB);
			rellenoBoton.setPreferredSize(dimensionRellenoB);
			rellenoBoton.setBackground(Color.white);
			
			constraints.gridy = 0;
			constraints.gridx = 0;
			botonPanel.add(rellenoBoton, constraints);
			
			constraints.gridy = 1;
			constraints.gridx = 1;
			botonPanel.add(aceptar, constraints);
			
			this.add(camposPanel);
			this.add(botonPanel);
			
			//Funcion 
			volver.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					volver();
				}
				
			});
			
			aceptar.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if( nombreTexto.getText().length() > 0 &&
						((emailTexto.getText().endsWith("@gmail.com") && emailTexto.getText().length() > 10) ||
						(emailTexto.getText().endsWith("@hotmail.com") && emailTexto.getText().length() > 12) ||
						(emailTexto.getText().endsWith("@outlook.com") && emailTexto.getText().length() > 12))) {
						
						ParticipanteDTO participanteDTO = new ParticipanteDTO(nombreTexto.getText(), emailTexto.getText());
						try{
							gestorParticipante.crearParticipante(participanteDTO, competenciaDTO);
							JOptionPane.showMessageDialog(new JPanel(), "Se ha agregado el participante exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
							competenciaDTO.addParticipante(participanteDTO);
							participanteAgregado(competenciaDTO);
						}
						catch(Exception e1) {
							JOptionPane.showMessageDialog(new JPanel(), e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}											
					}
					else {
						
						JOptionPane.showMessageDialog(new JPanel(), "Los datos son invalidos", "Error", JOptionPane.ERROR_MESSAGE);												
						if(!(nombreTexto.getText().length() > 0)) {
							nombreTexto.setBorder(BorderFactory.createLineBorder(Color.red, 2));
						}
						else {
							nombreTexto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
						}
						if((emailTexto.getText().endsWith("@gmail.com") && emailTexto.getText().length() > 10) ||
						(emailTexto.getText().endsWith("@hotmail.com") && emailTexto.getText().length() > 12) ||
						(emailTexto.getText().endsWith("@outlook.com") && emailTexto.getText().length() > 12)) {
							emailTexto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));							
						}
						else {
							emailTexto.setBorder(BorderFactory.createLineBorder(Color.red, 2));
						}
						
					}
				}
				
			});
		}
		
		private void volver() {
			CardLayout layout = (CardLayout)tpPanel.getLayout();
			layout.previous(tpPanel);
			tpPanel.remove(this);
		}
		
		private void participanteAgregado(CompetenciaDTO competenciaDTO) {
			((VerCompetencia) tpPanel.getComponent(tpPanel.getComponentCount() - 3)).competenciaModificada(competenciaDTO);
			JPanel listarParticipantes = new ListarParticipantesCompetencia(tpPanel, competenciaDTO);
			tpPanel.add(listarParticipantes, "ListarParticipantesCompetencia");
			CardLayout layout = (CardLayout)tpPanel.getLayout();
			layout.show(tpPanel, "ListarParticipantesCompetencia");
			tpPanel.remove(this);
		}
}
