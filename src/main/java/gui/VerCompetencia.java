package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dominio.Competencia.Estado;
import dominio.Participante;
import dto.CompetenciaDTO;
import gestor.GestorCompetencia;

@SuppressWarnings("serial")
public class VerCompetencia extends JPanel{

	private JPanel datosPanel;
	private JPanel botonesPanel;
	private JPanel tablasPanel;
	private JTable tablaParticipantes;
	private JTable tablaFixture;
	private DefaultTableModel modeloParticipantes;
	private DefaultTableModel modeloFixture;
	private JPanel tpPanel;
	private String cardAnterior;
	private GestorCompetencia gestorCompetencia;
	
	public VerCompetencia(CompetenciaDTO competencia, String card, JPanel tp, GestorCompetencia gCompetencia) {
		this.tpPanel = tp;
		this.cardAnterior = card;
		this.gestorCompetencia = gCompetencia;
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		armarPantalla(competencia);
	}
	
	private void armarPantalla(CompetenciaDTO competencia) {
		
		JLabel nombreCompetencia = new JLabel(competencia.getNombre());
		JLabel modalidadCompetencia = new JLabel("Modalidad: " + competencia.getTipoSistemaDeCompetencia().toString().toLowerCase().replace("_", " "));
		JLabel deporteCompetencia = new JLabel("Deporte: " + competencia.getDeporteDeCompetencia());
		JLabel estadoCompetencia = new JLabel("Estado: " + competencia.getEstadoCompetencia().toString().toLowerCase().replace("_", " "));
		
		JButton modificar = new JButton("Modificar");
		JButton eliminar = new JButton("Eliminar");
		JButton generarFixture = new JButton("Generar Fixture");
		JButton verFixture = new JButton("Ver Fixture");
		JButton verTablaPosiciones = new JButton("Ver Tabla de Posiciones");
		JButton verParticipantes = new JButton("Ver Participantes");
		JButton volver = new JButton("");
		
		modificar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel modificarCompetencia = new JPanel();
				tpPanel.add(modificarCompetencia, "Card__ModificarCompetencia");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "Card__ModificarCompetencia");
				volver(); //ver si hacer en modificar competencia directamente
			}
			
		});
		
		eliminar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(new JPanel(), "La competencia se eliminará permanentemente", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(ok == JOptionPane.OK_OPTION && gestorCompetencia.eliminarCompetencia(competencia)) {
					JOptionPane.showMessageDialog(new JPanel(), "La competencia se ha eliminado exitosamente", " ", JOptionPane.INFORMATION_MESSAGE);
					CardLayout layout = (CardLayout)tpPanel.getLayout();
					layout.show(tpPanel, cardAnterior);
					volver();
				}
				else {
					JOptionPane.showMessageDialog(new JPanel(), "No se pudo eliminar la competencia", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		generarFixture.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(new JPanel(), "¿Desea crear un nuevo Fixture?\nSi ya existe un Fixture este será eliminado", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(ok == JOptionPane.OK_OPTION /*&& gestorCompetencia.generarFixture(competencia)*/) {
					JOptionPane.showMessageDialog(new JPanel(), "El Fixture se ha creado exitosamente", " ", JOptionPane.INFORMATION_MESSAGE);
					CardLayout layout = (CardLayout)tpPanel.getLayout();
					layout.show(tpPanel, cardAnterior);
					actualizarTablaFixture();
				}
				else {
					JOptionPane.showMessageDialog(new JPanel(), "No se pudo crear un nuevo Fixture", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		if(competencia.getEstadoCompetencia() == Estado.EN_DISPUTA || competencia.getEstadoCompetencia() == Estado.FINALIZADA ) generarFixture.setEnabled(false);

		verFixture.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel verFixturePanel = new JPanel();
				tpPanel.add(verFixturePanel, "Card__VerFixture");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "Card__VerFixture");
			}
			
		});

		verTablaPosiciones.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel verPosiciones = new JPanel();
				tpPanel.add(verPosiciones, "Card__TablaPosiciones");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "Card__TablaPosiciones");
			}
			
		});

		verParticipantes.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel verParticipantesPanel = new JPanel();
				tpPanel.add(verParticipantesPanel, "Card__VerParticipantes");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "Card__VerParticipantes");
				actualizarTablaParticipantes(competencia.getParticipantes());
			}
			
		});

		volver.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
			
		});

		//panel datos
		datosPanel = new JPanel(new BoxLayout(this, BoxLayout.LINE_AXIS));
		datosPanel.setMinimumSize(new Dimension(1200, 75));
		datosPanel.setBackground(Color.WHITE);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(3, 10, 3, 3);
		constraints.fill = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 0;
		
		JPanel auxNombres = new JPanel(new GridBagLayout());
		auxNombres.add(volver, constraints);
		
		constraints.gridx = 1;
		auxNombres.add(nombreCompetencia, constraints);
		
		constraints.gridy = 1;
		auxNombres.add(modalidadCompetencia, constraints);
		
		constraints.gridy = 2;
		auxNombres.add(deporteCompetencia, constraints);
		
		constraints.gridy = 3;
		auxNombres.add(estadoCompetencia, constraints);
		
		JPanel auxRelleno = new JPanel();
		
		JPanel auxBotones = new JPanel(new GridBagLayout());
		constraints.gridy = 2;
		constraints.gridx = 0;
		
		auxBotones.add(modificar, constraints);
		
		constraints.gridy = 3;
		auxBotones.add(eliminar, constraints);
		
		datosPanel.add(auxNombres);
		datosPanel.add(auxRelleno);
		datosPanel.add(auxBotones);
		
		//panel tablas
		//tabla participantes
		JPanel tablaPanel = new JPanel(new BoxLayout(this, BoxLayout.LINE_AXIS));
		JPanel auxTablaP = new JPanel();
		tablaParticipantes = new JTable(modeloParticipantes);
		auxTablaP.add(tablaParticipantes);
		tablaParticipantes.setDefaultEditor(Object.class, null);
		
		JScrollPane tableSP = new JScrollPane(tablaParticipantes);
		tableSP.setMinimumSize(new Dimension(300, 300));
		
		GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    auxTablaP.add(tableSP, constraintsTabla);
	    
	    //tabla fixture
		JPanel auxTablaF = new JPanel();
		tablaFixture = new JTable(modeloFixture);
		auxTablaF.add(tablaFixture);
		tablaFixture.setDefaultEditor(Object.class, null);
		
		JScrollPane tableSPF = new JScrollPane(tablaParticipantes);
		tableSP.setMinimumSize(new Dimension(800, 300));
		
	    auxTablaP.add(tableSPF, constraintsTabla);
	    
	    tablaPanel.add(auxTablaP);
	    tablaPanel.add(auxTablaF);
	    
	    //panel botones
	    botonesPanel = new JPanel(new GridBagLayout());
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.WEST;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		botonesPanel.add(generarFixture, constraints);
		
		constraints.gridx = 1;
		botonesPanel.add(verFixture, constraints);
		
		constraints.gridx = 2;
		botonesPanel.add(verTablaPosiciones, constraints);

		constraints.gridx = 1;
		botonesPanel.add(verParticipantes, constraints);
		
		this.add(datosPanel);
		this.add(tablasPanel);
		this.add(botonesPanel);
	}
	
	private void volver() {
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.show(tpPanel, cardAnterior);
		tpPanel.remove(this);
	}
	
	private void actualizarTablaParticipantes(List<Participante> participantes) {
		String[] columnas = {"Participante"};
		modeloParticipantes = new DefaultTableModel(columnas, 0);
		for(Participante participante: participantes) {
			Object[] renglon = { participante.getNombre()};
			modeloParticipantes.addRow(renglon);
		}
		
		//actualizar modelo
		tablaParticipantes.setModel(modeloParticipantes);
	}
	
	private void actualizarTablaFixture() {
		String[] columnas = {"Etapa", "Local", "Fecha y Hora", "Visitante"};
		modeloFixture = new DefaultTableModel(columnas, 0);
		/*for(Participante participante: participantes) {
			Object[] renglon = { participante.getNombre()};
			modeloParticipantes.addRow(renglon);
		}*/
		
		//actualizar modelo
		tablaFixture.setModel(modeloFixture);
	}
	
}
