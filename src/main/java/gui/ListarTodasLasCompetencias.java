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
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dominio.SistemaDeCompetencia;
import dominio.Usuario;
import dominio.Competencia.Estado;
import dto.CompetenciaDTO;
import gestor.GestorCompetencia;
import gestor.GestorDeporte;

@SuppressWarnings("serial")
public class ListarTodasLasCompetencias extends JPanel {
	
	private JPanel tituloPanel;
	private JPanel filtrosPanel;
	private JPanel tablaPanel;
	private String[] arregloM = {"Modalidad", "Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"};
	private String[] arregloE = {"Estado", "Creada", "Planificada", "En Disputa", "Finalizada"};
	private JTable tablaCompetencias;
	private DefaultTableModel modeloCompetencia;
	private GestorDeporte gestorDeporte;
	private GestorCompetencia gestorCompetencia;
	private JPanel tpPanel;
	
	public ListarTodasLasCompetencias(JPanel tp, GestorDeporte gDeporte, GestorCompetencia gCompetencia) {
		this.tpPanel = tp;
		this.gestorDeporte = gDeporte;
		this.gestorCompetencia = gCompetencia;
		armarPantalla();
	}
	
	private void armarPantalla() {
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		tablaPanel = new JPanel();
		CardLayout cl = new CardLayout(0,0);
		tablaPanel.setLayout(cl);
		JPanel sinBuscar = new JPanel();
		sinBuscar.setBackground(Color.WHITE);
		tablaPanel.add(sinBuscar, "Card__SinBuscar");
		

		//Panel del titulo y boton volver		
		tituloPanel = new JPanel(new GridBagLayout());
		tituloPanel.setMinimumSize(new Dimension(1200, 75));
		tituloPanel.setBackground(Color.WHITE);
		GridBagConstraints volverConstraints = new GridBagConstraints();
		volverConstraints.insets = new Insets(10, 10, 10, 10);
		volverConstraints.fill = GridBagConstraints.WEST;
		volverConstraints.gridx = 1;
		volverConstraints.gridy = 0;
		
		JPanel rellenoPanel2 = new JPanel();
		rellenoPanel2.setMinimumSize(new Dimension(875, 30));
		rellenoPanel2.setMaximumSize(new Dimension(875, 30));
		rellenoPanel2.setPreferredSize(new Dimension(875, 30));
		rellenoPanel2.setBackground(Color.WHITE);
		
		ImageIcon iconoVolver= new ImageIcon("src/main/resources/IconoVolver.JPG");
		JButton volver = new JButton();
		volver.setPreferredSize(new Dimension(33,33));
		volver.setIcon(iconoVolver);
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		volver.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//if con el usuario autenticado 
		        layout.show(tpPanel, "Card__");
			}
			
		});
		volverConstraints.gridx = 0;
		volverConstraints.gridy = 0;
		tituloPanel.add(volver, volverConstraints);
		
		JLabel titulo = new JLabel("Competencias");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		volverConstraints.gridx = 1;
		tituloPanel.add(titulo, volverConstraints);
	
		volverConstraints.gridx = 2;

		tituloPanel.add(rellenoPanel2, volverConstraints);
		
		
		//panel filtros
		filtrosPanel = new JPanel();
		filtrosPanel.setLayout(new BoxLayout(filtrosPanel, BoxLayout.PAGE_AXIS));
		filtrosPanel.setMinimumSize(new Dimension(1200, 75));
		filtrosPanel.setBackground(Color.WHITE);
		
		final JComboBox<String> deporteBox = new JComboBox<String>(); 
		//Pedir al gestor de deporte
		deporteBox.addItem("Deporte");
		deporteBox.addItem("Hello");
		deporteBox.addItem("AHello");
		final List<String> deportes = gestorDeporte.getDeportesInterfaz();
		for(String dep: deportes) {
			deporteBox.addItem(dep);
		}
		deporteBox.setMinimumSize(new Dimension(200, 30));
		deporteBox.setMaximumSize(new Dimension(200, 30));
		deporteBox.setPreferredSize(new Dimension(200, 30));
		
		JTextField nombreCompetencia = new JTextField(150);
		nombreCompetencia.setMinimumSize(new Dimension(200, 30));
		nombreCompetencia.setMaximumSize(new Dimension(200, 30));
		nombreCompetencia.setPreferredSize(new Dimension(200, 30));
		
		final JComboBox<String> modalidad = new JComboBox<String>(arregloM);
		modalidad.setMinimumSize(new Dimension(200, 30));
		modalidad.setMaximumSize(new Dimension(200, 30));
		modalidad.setPreferredSize(new Dimension(200, 30));
		
		final JComboBox<String> estado = new JComboBox<String>(arregloE);
		estado.setMinimumSize(new Dimension(200, 30));
		estado.setMaximumSize(new Dimension(200, 30));
		estado.setPreferredSize(new Dimension(200, 30));
		
		JTextField usuario = new JTextField(150);
		usuario.setMinimumSize(new Dimension(200, 30));
		usuario.setMaximumSize(new Dimension(200, 30));
		usuario.setPreferredSize(new Dimension(200, 30));
		
		JButton agregar = new JButton("Agregar");
		JButton buscar = new JButton("Buscar");
		
		buscar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SistemaDeCompetencia.Tipo tipoCompetencia = null;
				switch(modalidad.getSelectedIndex()) { //"Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"
				case 1:
					tipoCompetencia = SistemaDeCompetencia.Tipo.LIGA;
					break;
				case 2: 
					tipoCompetencia = SistemaDeCompetencia.Tipo.ELIMIN_SIMPLE;
					break;
				case 3:
					tipoCompetencia = SistemaDeCompetencia.Tipo.ELIMIN_DOBLE;
					break;
				default:
					break;
				}
				Estado tipoEstado = null;
				switch(estado.getSelectedIndex()) { //"Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"
				case 1:
					tipoEstado = Estado.CREADA;
					break;
				case 2: 
					tipoEstado = Estado.PLANIFICADA;
					break;
				case 3:
					tipoEstado = Estado.EN_DISPUTA;
					break;
				case 4:
					tipoEstado = Estado.FINALIZADA;
					break;
				default:
					break;
				}
				
				// ver usuario
				List<CompetenciaDTO> competenciasFiltradas =  gestorCompetencia.obtenerCompetencias(nombreCompetencia.getText(), deporteBox.getSelectedItem().toString(), tipoCompetencia, tipoEstado, usuario.getText());
				actualizarTablaCompetencias(competenciasFiltradas);
				agregar.setVisible(false);
				cl.show(tablaPanel, "Card__Buscar");
			}
			
		});
		
		//ver para que sea mientras escribe
		nombreCompetencia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					buscar.setEnabled(nombreCompetencia.getText().length() > 0);
				
			}
			
		});
		
		usuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					buscar.setEnabled(nombreCompetencia.getText().length() > 0);
				
			}
			
		});
		
		modalidad.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscar.setEnabled(modalidad.getSelectedIndex() != 0);
			}
			
		});

		deporteBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscar.setEnabled(deporteBox.getSelectedIndex() != 0);
			}
			
		});
		
		estado.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buscar.setEnabled(estado.getSelectedIndex() != 0);
			}
			
		});
				
		agregar.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//ver tema usauario
		        layout.show(tpPanel, "Card__AltaCompetencia");
			}
			
		});
		
		buscar.setEnabled(false);
		JPanel auxFiltros = new JPanel(new GridBagLayout());
		auxFiltros.setBackground(Color.WHITE);
		JPanel auxBotonesFiltros = new JPanel(new GridBagLayout());
		auxBotonesFiltros.setBackground(Color.WHITE);
		JPanel auxEspacio = new JPanel();
		
		auxEspacio.setMinimumSize(new Dimension(905, 30));
		auxEspacio.setMaximumSize(new Dimension(905, 30));
		auxEspacio.setPreferredSize(new Dimension(905, 30));
		auxEspacio.setBackground(Color.WHITE);
		
		volverConstraints.gridx = 0;
		volverConstraints.gridy = 0;
		auxFiltros.add(nombreCompetencia, volverConstraints);
		
		volverConstraints.gridx = 1;
		auxFiltros.add(deporteBox, volverConstraints);
		
		volverConstraints.gridx = 2;
		auxFiltros.add(modalidad, volverConstraints);
		
		volverConstraints.gridx = 3;
		auxFiltros.add(estado, volverConstraints);
		
		volverConstraints.gridx = 4;
		auxFiltros.add(usuario, volverConstraints);
		
		volverConstraints.gridy = 0;
		volverConstraints.gridx = 0;
		auxBotonesFiltros.add(auxEspacio, volverConstraints);
		
		volverConstraints.gridx = 1;
		auxBotonesFiltros.add(agregar, volverConstraints);
		
		volverConstraints.gridx = 3;
		auxBotonesFiltros.add(buscar, volverConstraints);
		
		filtrosPanel.add(auxFiltros);
		filtrosPanel.add(auxBotonesFiltros);
		//Panel Tabla
		JButton agregar2 = new JButton("Agregar");
		agregar2.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//ver tema usuario
		        layout.show(tpPanel, "Card__AltaCompetencia");
			}
			
		});
	
		JPanel aux = new JPanel();
		aux.setLayout(new BoxLayout(aux, BoxLayout.PAGE_AXIS));
		JPanel auxTabla = new JPanel();
		auxTabla.setBackground(Color.WHITE);
		tablaCompetencias = new JTable(modeloCompetencia);
		auxTabla.add(tablaCompetencias);
		tablaCompetencias.setDefaultEditor(Object.class, null);
		
		tablaCompetencias.setMinimumSize(new Dimension(1080, 400));
		tablaCompetencias.setMaximumSize(new Dimension(1080, 400));
		tablaCompetencias.setPreferredSize(new Dimension(1080, 400));
		
		JScrollPane tableSP = new JScrollPane(tablaCompetencias);

		tableSP.setMinimumSize(new Dimension(1080, 400));
		tableSP.setMaximumSize(new Dimension(1080, 400));
		tableSP.setPreferredSize(new Dimension(1080, 400));
		
		GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    auxTabla.add(tableSP, constraintsTabla);
	    
		JPanel auxBotones = new JPanel(new GridBagLayout());
		auxBotones.setBackground(Color.WHITE);
		GridBagConstraints auxConstraints = new GridBagConstraints();
		auxConstraints.insets = new Insets(3, 3, 3, 3);
		auxConstraints.fill = GridBagConstraints.WEST;
		
		JButton verDetalles = new JButton("Ver Detalles");	
		verDetalles.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//crear una pantalla ver detalles
				CompetenciaDTO competencia = gestorCompetencia.obtenerCompetencia(tablaCompetencias.getValueAt(tablaCompetencias.getSelectedRow(), tablaCompetencias.getSelectedColumn()).toString());
				JPanel verDetalles = new VerCompetencia(competencia, null, null, gestorCompetencia);
				tpPanel.add(verDetalles, "Card__VerDetalles");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "Card__VerDetalles");
				
			}
			
		});
		verDetalles.setEnabled(false);
		
	    tablaCompetencias.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(tablaCompetencias.isFocusOwner()) {
					verDetalles.setEnabled(true);
				}
				else {
					verDetalles.setEnabled(false);
				}
			}
	    	
	    });
		
	    JPanel auxEspacio2 = new JPanel();
		auxEspacio2.setMinimumSize(new Dimension(905, 30));
		auxEspacio2.setMaximumSize(new Dimension(905, 30));
		auxEspacio2.setPreferredSize(new Dimension(905, 30));
		auxEspacio2.setBackground(Color.WHITE);
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		auxBotones.add(auxEspacio2, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxConstraints.anchor = GridBagConstraints.EAST;
		auxBotones.add(agregar2, auxConstraints);
		
		auxConstraints.gridx = 2;
		auxBotones.add(verDetalles, auxConstraints);
		
		aux.add(auxTabla);
		aux.add(auxBotones);
		tablaPanel.add(aux, "Card__Buscar");
		tablaPanel.setBackground(Color.WHITE);
		
		this.add(tituloPanel);
		this.add(filtrosPanel);
		this.add(tablaPanel);
	}
	
	public void actualizarTablaCompetencias(List<CompetenciaDTO> competencias) {
		String[] columnas = { "Nombre de la Competencia", "Deporte", "Modalidad", "Estado", "Usuario"};
		modeloCompetencia = new DefaultTableModel(columnas, 0);		

		for(CompetenciaDTO competencia: competencias) {
			String modalidad = competencia.getTipoSistemaDeCompetencia().toString().replace("_", " ");
			String estado = competencia.getEstadoCompetencia().toString().replace("_", " ");
			Object[] renglon = { competencia.getNombre(), competencia.getDeporteDeCompetencia(), modalidad, estado, competencia.getUsuarioAsociado()};
			modeloCompetencia.addRow(renglon);
		}
		
		//actualizar modelo
		tablaCompetencias.setModel(modeloCompetencia);
	}

}
