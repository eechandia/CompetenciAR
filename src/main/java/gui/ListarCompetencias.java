package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;

import dominio.Competencia.Estado;
import dominio.SistemaDeCompetencia;
import dominio.Usuario;
import dto.CompetenciaDTO;
import gestor.GestorCompetencia;
import gestor.GestorDeporte;
import utils.Filtro;
import utils.Pair;

@SuppressWarnings("serial")
public class ListarCompetencias extends JPanel {

	//Clases auxiliares
	class JButtonStateController implements DocumentListener {
		 JButton button;
		  
		  JButtonStateController(JButton button) {
		     this.button = button ;
		  }

		  public void changedUpdate(DocumentEvent e) {
		    disableIfEmpty(e);
		  }

		  public void insertUpdate(DocumentEvent e) {
		    disableIfEmpty(e);
		  }

		  public void removeUpdate(DocumentEvent e) {
		    disableIfEmpty(e);
		  }

		  public void disableIfEmpty(DocumentEvent e) {
		    button.setEnabled(e.getDocument().getLength() > 0);
		  }
	}
	
	private JPanel tituloPanel;
	private JPanel filtrosPanel;
	private JPanel tablaPanel;
	private String[] arregloM = {"Modalidad", "Liga", "Eliminatoria Simple", "Eliminatoria Doble"};
	private String[] arregloE = {"Estado", "Creada", "Planificada", "En Disputa", "Finalizada"};
	private JTable tablaCompetencias;
	private DefaultTableModel modeloCompetencia;
	private GestorDeporte gestorDeporte;
	private GestorCompetencia gestorCompetencia;
	private JPanel tpPanel;
	private List<CompetenciaDTO> competenciasFiltradas;
	
	public ListarCompetencias(JPanel tp, List<Object> filtros) {
		this.tpPanel = tp;
		this.gestorDeporte = new GestorDeporte();
		this.gestorCompetencia = new GestorCompetencia();
		this.competenciasFiltradas = new ArrayList<CompetenciaDTO>();
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		armarPantalla(filtros);
	}
	
	private void armarPantalla(List<Object> filtrosAnteriores) {

		//Armar Pantalla
		//Etiquetas
		JLabel titulo = new JLabel("Mis Competencias");
		
		//Campos de texto
		JTextField nombreCompetencia = new JTextField(150);
		
		//ComboBox
		final JComboBox<String> deporteBox = new JComboBox<String>(); 
		final JComboBox<String> modalidad = new JComboBox<String>(arregloM);
		final JComboBox<String> estado = new JComboBox<String>(arregloE);
		
		//-------------------------------------------------------------//
		/*final JComboBox<Pair<Integer,String>> deporteBox0 = new JComboBox<Pair<Integer,String>>(); 
		//Pedir al gestor de deporte
		Pair<Integer,String> vacio = new Pair<Integer, String>();
		vacio.setFirst(0);
		vacio.setSecond("Deporte");
		deporteBox0.addItem(vacio);*/
		Pair<Integer,String> vacio = new Pair<Integer, String>();
		vacio.setFirst(0);
		vacio.setSecond("Deporte");
		//deporteBox0.addItem(vacio);
		final List<Pair<Integer,String>> deportes = new ArrayList<Pair<Integer, String>>();
		deportes.add(vacio);
		deportes.addAll(gestorDeporte.getDeportesInterfaz());
		for(Pair<Integer,String> dep: deportes) {
			deporteBox.addItem(dep.getSecond());
		}
		//Agrego contenido a ComboBox Deporte
		/*deporteBox.addItem("Deporte");
		final List<String> deportes = gestorDeporte.getDeportesInterfaz();
		for(String dep: deportes) {
			deporteBox.addItem(dep);
		}*/
		//--------------------------------------------------------------//
		
		//Botones
		JButton volver = new JButton();
		JButton agregar = new JButton("Agregar");
		JButton verDetalles = new JButton("Ver Detalles");	
		JButton agregar2 = new JButton("Agregar");
		JButton buscar = new JButton("Buscar");
		buscar.setEnabled(false);
		verDetalles.setEnabled(false);
		
		//Fuentes
		Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 30);
		Font botones = new Font("Tahoma", Font.PLAIN, 14);
		Font textos = new Font("Tahoma", Font.PLAIN, 16);
		
		titulo.setFont(fuenteTitulo);
		
		nombreCompetencia.setFont(textos);
		deporteBox.setFont(textos);
		modalidad.setFont(textos);
		estado.setFont(textos);
		
		agregar.setFont(botones);
		agregar2.setFont(botones);
		verDetalles.setFont(botones);
		buscar.setFont(botones);
		
		//Dimensiones
		Dimension tituloDimension = new Dimension(400, 50);
		Dimension botonDimension = new Dimension(150, 40);
		Dimension textoDimension = new Dimension(300, 30);
		Dimension filtrosDimension = new Dimension(170, 30);
		
		titulo.setMinimumSize(tituloDimension);
		titulo.setMaximumSize(tituloDimension);
		titulo.setPreferredSize(tituloDimension);
		
		nombreCompetencia.setMinimumSize(textoDimension);
		nombreCompetencia.setMaximumSize(textoDimension);
		nombreCompetencia.setPreferredSize(textoDimension);
		
		deporteBox.setMinimumSize(filtrosDimension);
		deporteBox.setMaximumSize(filtrosDimension);
		deporteBox.setPreferredSize(filtrosDimension);

		modalidad.setMinimumSize(filtrosDimension);
		modalidad.setMaximumSize(filtrosDimension);
		modalidad.setPreferredSize(filtrosDimension);
		
		estado.setMinimumSize(filtrosDimension);
		estado.setMaximumSize(filtrosDimension);
		estado.setPreferredSize(filtrosDimension);

		agregar.setMinimumSize(botonDimension);
		agregar.setMaximumSize(botonDimension);
		agregar.setPreferredSize(botonDimension);
		
		buscar.setMinimumSize(botonDimension);
		buscar.setMaximumSize(botonDimension);
		buscar.setPreferredSize(botonDimension);
		
		agregar2.setMinimumSize(botonDimension);
		agregar2.setMaximumSize(botonDimension);
		agregar2.setPreferredSize(botonDimension);
		
		verDetalles.setMinimumSize(botonDimension);
		verDetalles.setMaximumSize(botonDimension);
		verDetalles.setPreferredSize(botonDimension);
		
		//Constraints
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.WEST;
		constraints.gridx = 1;
		constraints.gridy = 0;
		
		//Boton Volver imagen
		ImageIcon iconoVolver= new ImageIcon("src/main/resources/IconoVolver.JPG");
		volver.setPreferredSize(new Dimension(33,33));
		volver.setIcon(iconoVolver);
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		//Panel del titulo y boton volver		
		tituloPanel = new JPanel(new GridBagLayout());
		tituloPanel.setMinimumSize(new Dimension(1200, 75));
		tituloPanel.setBackground(Color.WHITE);
		
		JPanel rellenoPanel2 = new JPanel();
		rellenoPanel2.setMinimumSize(new Dimension(500, 30));
		rellenoPanel2.setMaximumSize(new Dimension(500, 30));
		rellenoPanel2.setPreferredSize(new Dimension(500, 30));
		rellenoPanel2.setBackground(Color.WHITE);

		constraints.gridx = 0;
		constraints.gridy = 0;
		tituloPanel.add(volver, constraints);
			
		constraints.gridx = 1;
		tituloPanel.add(titulo, constraints);
	
		constraints.gridx = 2;
		tituloPanel.add(rellenoPanel2, constraints);
		
		//panel filtros
		filtrosPanel = new JPanel();
		filtrosPanel.setLayout(new BoxLayout(filtrosPanel, BoxLayout.PAGE_AXIS));
		filtrosPanel.setMinimumSize(new Dimension(1200, 75));
		filtrosPanel.setBackground(Color.WHITE);
		
		JPanel auxFiltros = new JPanel(new GridBagLayout());
		auxFiltros.setBackground(Color.WHITE);
		
		JPanel auxBotonesFiltros = new JPanel(new GridBagLayout());
		auxBotonesFiltros.setBackground(Color.WHITE);
		
		JPanel auxEspacio = new JPanel();		
		auxEspacio.setMinimumSize(new Dimension(533, 30));
		auxEspacio.setMaximumSize(new Dimension(533, 30));
		auxEspacio.setPreferredSize(new Dimension(533, 30));
		auxEspacio.setBackground(Color.WHITE);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		auxFiltros.add(nombreCompetencia, constraints);
		
		constraints.gridx = 1;
		auxFiltros.add(deporteBox, constraints);
		
		constraints.gridx = 2;
		auxFiltros.add(modalidad, constraints);
		
		constraints.gridx = 3;
		auxFiltros.add(estado, constraints);
		
		constraints.gridy = 0;
		constraints.gridx = 0;
		auxBotonesFiltros.add(auxEspacio, constraints);
		
		constraints.gridx = 1;
		auxBotonesFiltros.add(agregar, constraints);
		
		constraints.gridx = 3;
		auxBotonesFiltros.add(buscar, constraints);
		
		filtrosPanel.add(auxFiltros);
		filtrosPanel.add(auxBotonesFiltros);
		
		//Panel Tabla
		tablaPanel = new JPanel();
		CardLayout cl = new CardLayout(0,0);
		tablaPanel.setLayout(cl);
		JPanel sinBuscar = new JPanel();
		sinBuscar.setBackground(Color.WHITE);
		tablaPanel.add(sinBuscar, "Card__SinBuscar");
	
		//Dimension Tabla 
		Dimension tamTabla = new Dimension(870, 330);
		
		JPanel aux = new JPanel();
		aux.setLayout(new BoxLayout(aux, BoxLayout.PAGE_AXIS));
		JPanel auxTabla = new JPanel();
		auxTabla.setBackground(Color.WHITE);
		tablaCompetencias = new JTable(modeloCompetencia){
		    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, extend, extend);
		    }
		};
		
		auxTabla.add(tablaCompetencias);
		tablaCompetencias.setDefaultEditor(Object.class, null);
		
		//Header
		tablaCompetencias.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaCompetencias.getTableHeader().setEnabled(false);
		tablaCompetencias.getTableHeader().setBackground(new Color(42, 224, 187));
		tablaCompetencias.getTableHeader().setOpaque(false);
		tablaCompetencias.getTableHeader().setMinimumSize(new Dimension(870, 50));
		tablaCompetencias.getTableHeader().setMaximumSize(new Dimension(870, 50));
		tablaCompetencias.getTableHeader().setPreferredSize(new Dimension(870, 50));
		((DefaultTableCellRenderer)tablaCompetencias.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
				
		//Table Renderer
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer(){
			
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				        c.setBackground(row % 2 == 0 ? new Color(187, 255, 241) : Color.WHITE);
				        if(isSelected) {
				        	c.setBackground(Color.BLUE);
				        	verDetalles.setEnabled(true);
				        }
				        return c;
		    }
		};
		cr.setHorizontalAlignment(JLabel.CENTER);
				
		//Configuracion tabla
		tablaCompetencias.setDefaultRenderer(Object.class, cr);
		tablaCompetencias.setMinimumSize(tamTabla);
		tablaCompetencias.setMaximumSize(tamTabla);
		tablaCompetencias.setPreferredSize(tamTabla);
		tablaCompetencias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaCompetencias.setRowHeight(40);
		
		//Constraints Tabla
		GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    
		//Scroll Pane
		JScrollPane tableSP = new JScrollPane(tablaCompetencias);

		tableSP.setMinimumSize(tamTabla);
		tableSP.setMaximumSize(tamTabla);
		tableSP.setPreferredSize(tamTabla);
		tableSP.setBackground(new Color(42, 224, 187));
	    auxTabla.add(tableSP, constraintsTabla);
	    
		JPanel auxBotones = new JPanel(new GridBagLayout());
		auxBotones.setBackground(Color.WHITE);
		
	    JPanel auxEspacio2 = new JPanel();
		auxEspacio2.setMinimumSize(new Dimension(560, 30));
		auxEspacio2.setMaximumSize(new Dimension(560, 30));
		auxEspacio2.setPreferredSize(new Dimension(560, 30));
		auxEspacio2.setBackground(Color.WHITE);
		
		constraints.insets = new Insets(3, 3, 3, 3);
		constraints.fill = GridBagConstraints.WEST;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		auxBotones.add(auxEspacio2, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.EAST;
		auxBotones.add(agregar2, constraints);
		
		constraints.gridx = 2;
		auxBotones.add(verDetalles, constraints);
		
		aux.add(auxTabla);
		aux.add(auxBotones);
		tablaPanel.add(aux, "Card__Buscar");
		tablaPanel.setBackground(Color.WHITE);
		
		this.add(tituloPanel);
		this.add(filtrosPanel);
		this.add(tablaPanel);
		
		//Funcion de Botones

		volver.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
			
		});
		
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
				competenciasFiltradas = gestorCompetencia.obtenerCompetencias(new Usuario(), new Filtro(nombreCompetencia.getText(), deportes.get(deporteBox.getSelectedIndex()).getFirst(), tipoCompetencia, tipoEstado));
				actualizarTablaCompetencias(competenciasFiltradas);
				agregar.setVisible(false);
				auxEspacio.setMinimumSize(new Dimension(700, 30));
				auxEspacio.setMaximumSize(new Dimension(700, 30));
				auxEspacio.setPreferredSize(new Dimension(700, 30));
				cl.show(tablaPanel, "Card__Buscar");
			}
			
		});
				
		agregar.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<Object> filtros = new ArrayList<Object>();
				filtros.add(nombreCompetencia.getText());
				filtros.add(deporteBox.getSelectedIndex());
				filtros.add(modalidad.getSelectedIndex());
				filtros.add(estado.getSelectedIndex());
				JPanel altaCompetencia = new AltaCompetencia(tpPanel, filtros);
				tpPanel.add(altaCompetencia, "AltaCompetencia");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//ver tema usuario
		        layout.show(tpPanel, "AltaCompetencia");
			}
			
		});
		
		agregar2.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				List<Object> filtros = new ArrayList<Object>();
				filtros.add(nombreCompetencia.getText());
				filtros.add(deporteBox.getSelectedIndex());
				filtros.add(modalidad.getSelectedIndex());
				filtros.add(estado.getSelectedIndex());
				JPanel altaCompetencia = new AltaCompetencia(tpPanel, filtros);
				tpPanel.add(altaCompetencia, "AltaCompetencia");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//ver tema usuario
		        layout.show(tpPanel, "AltaCompetencia");
			}
			
		});
		
		verDetalles.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<Object> filtros = new ArrayList<Object>();
				filtros.add(nombreCompetencia.getText());
				filtros.add(deporteBox.getSelectedIndex());
				filtros.add(modalidad.getSelectedIndex());
				filtros.add(estado.getSelectedIndex());
				JPanel verCompetencia = new VerCompetencia(competenciasFiltradas.get(tablaCompetencias.getSelectedRow()), tpPanel, filtros);
				tpPanel.add(verCompetencia, "VerCompetencia");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				//ver tema usuario
		        layout.show(tpPanel, "VerCompetencia");
			}
			
		});
		
		//Enable/Disable Buscar
		Document document = nombreCompetencia.getDocument();
	    document.addDocumentListener(new JButtonStateController(buscar));
	    
		nombreCompetencia.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (nombreCompetencia.getText().length() >= 150 ) e.consume(); 
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
		
		this.addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
                tablaCompetencias.clearSelection();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//----------------------------//
		if(!filtrosAnteriores.isEmpty()) {
			if(!filtrosAnteriores.get(0).equals(null)) {
				nombreCompetencia.setText((String)filtrosAnteriores.get(0));
			}
			if(!filtrosAnteriores.get(1).equals(null)) {
				deporteBox.setSelectedIndex((Integer)filtrosAnteriores.get(1));
			}
			if(!filtrosAnteriores.get(2).equals(null)) {
				modalidad.setSelectedIndex((Integer)filtrosAnteriores.get(2));
			}
			if(!filtrosAnteriores.get(3).equals(null)) {
				estado.setSelectedIndex((Integer)filtrosAnteriores.get(3));
			}
			buscar.setEnabled(true);
			buscar.doClick();
		}
		
	}
	
	public void actualizarTablaCompetencias( List<CompetenciaDTO> competencias) {
		String[] columnas = { "Nombre de la Competencia", "Deporte", "Modalidad", "Estado"};
		modeloCompetencia = new DefaultTableModel(columnas, 0);		

		for(CompetenciaDTO competencia: competencias) {
			String modalidad = competencia.getTipoSistemaDeCompetencia().toString().replace("_", " ");
			String estado = competencia.getEstadoCompetencia().toString().replace("_", " ");
			Object[] renglon = { competencia.getNombre(), competencia.getDeporteDeCompetencia(), modalidad, estado};
			modeloCompetencia.addRow(renglon);
		}
		//actualizar modelo
		tablaCompetencias.setModel(modeloCompetencia);
	}
	
	private void volver() {
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.previous(tpPanel);
		tpPanel.remove(this);
	}
}

