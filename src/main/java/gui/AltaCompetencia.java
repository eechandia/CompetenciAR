package gui;

import java.awt.BorderLayout;
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
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dominio.FormaPuntuacion;
import dominio.SistemaDeCompetencia;
import dto.CompetenciaDTO;
import gestor.GestorCompetencia;
import gestor.GestorDeporte;
import gestor.GestorLugarDeRealizacion;
import utils.Pair;
import utils.Triplet;

@SuppressWarnings("serial")
public class AltaCompetencia extends JPanel {

	//Clases auxiliares    
	class ButtonRenderer extends JButton implements TableCellRenderer {

		public ButtonRenderer() {
				setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			setText((value == null) ? "X" : value.toString());
			return this;
			}
		}

	class ButtonEditor extends DefaultCellEditor {
		private String label;
		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
			}
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
			label = (value == null) ? "X" : value.toString();
			eliminar.setText(label);
			return eliminar;
			}
		public Object getCellEditorValue() {
			return new String(label);
			}
		}
	
	class LugarDeRealizacionComboRenderer extends DefaultListCellRenderer {

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public Component getListCellRendererComponent(
	                                   JList list,
	                                   Object value,
	                                   int index,
	                                   boolean isSelected,
	                                   boolean cellHasFocus) {
	        if (value instanceof Triplet<?, ?, ?>) {
	            if(((Triplet<Integer, String, Integer>) value).getThird() >= 0){
	        	value ="D: "+ (((Triplet<Integer, String, Integer>) value).getThird())+ " - " + ((Triplet<Integer, String, Integer>)value).getSecond();
	            }
	            else value = " ";
	        }
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        return this;
	    }
	}
	
	class DeportesComboRenderer extends DefaultListCellRenderer {

	    @SuppressWarnings({ "rawtypes", "unchecked" })
		public Component getListCellRendererComponent(
	                                   JList list,
	                                   Object value,
	                                   int index,
	                                   boolean isSelected,
	                                   boolean cellHasFocus) {
	        if (value instanceof Pair<?, ?>) {
	            if(((Pair<Integer, String>) value).getFirst() >= 0){
	            	value = ((Pair) value).getSecond();
	            }
	            else value = " ";
	        }
	        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	        return this;
	    }
	}
	
	
	private JPanel tituloPanel;
	private JPanel camposPanel;
	private JPanel lugarPanel;
	private JPanel infoPanel;
	private String[] arregloM = {" ", "Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"};
	private String[] arregloP = {" ", "Resultado Final", "Puntuación", "Sets"};
	private JTable tablaLugares;
	private DefaultTableModel modeloLugar;
	public JButton eliminar;
	private GestorDeporte gestorDeporte;
	private GestorCompetencia gestorCompetencia;
	private JPanel tpPanel;
	
	public AltaCompetencia(JPanel tp, List<Object> filtros) {
		this.gestorDeporte = new GestorDeporte();
		this.gestorCompetencia = new GestorCompetencia();
		this.tpPanel = tp;
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.armarPantalla(filtros);
	}
	
	private void armarPantalla(List<Object> filtros) {		

		//Armar Pantalla
		//Etiqueta
		JLabel titulo = new JLabel("Nueva Competencia");
		JLabel nombre = new JLabel("Nombre de la Competencia");
		JLabel deporte = new JLabel("Deporte Asociado");
		JLabel modalidad = new JLabel("Modalidad de la Competencia");
		JLabel formaP = new JLabel("Forma de Puntuación");
		JLabel reglamentoL = new JLabel("Reglamento");
		JLabel cantidadMaxSets = new JLabel("Cantidad Máxima Sets");
		JLabel puntosAusente = new JLabel("Puntos si Rival Ausente");
		JLabel puntosGanado = new JLabel("Puntos por Partido Ganado");
		JLabel puntosPresentarse = new JLabel("Puntos por Presentarse");
		JLabel buscarLabel = new JLabel("Lugar");
		JLabel cantidad = new JLabel("Cantidad de Encuentros");
		JLabel puntosEmpate = new JLabel("Puntos Empate");
		puntosEmpate.setEnabled(false);
		
		nombre.setHorizontalAlignment(SwingConstants.LEFT);
		deporte.setHorizontalAlignment(SwingConstants.LEFT);
		modalidad.setHorizontalAlignment(SwingConstants.LEFT);
		formaP.setHorizontalAlignment(JLabel.LEFT);
		reglamentoL.setHorizontalAlignment(SwingConstants.LEFT);
		cantidadMaxSets.setHorizontalAlignment(SwingConstants.LEFT);
		puntosAusente.setHorizontalAlignment(SwingConstants.LEFT);
		puntosGanado.setHorizontalAlignment(SwingConstants.LEFT);
		puntosPresentarse.setHorizontalAlignment(SwingConstants.LEFT);
		buscarLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cantidad.setHorizontalAlignment(SwingConstants.LEFT);
		puntosEmpate.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel asterisco1 = new JLabel("*");
		asterisco1.setForeground(Color.red);
		JLabel asterisco2 = new JLabel("*");
		asterisco2.setForeground(Color.red);
		JLabel asterisco3 = new JLabel("*");
		asterisco3.setForeground(Color.red);
		JLabel asterisco4 = new JLabel("*");
		asterisco4.setForeground(Color.red);
		JLabel asterisco5 = new JLabel("*");
		asterisco5.setForeground(Color.red);
		JLabel asterisco6 = new JLabel("*");
		asterisco6.setForeground(Color.red);
		JLabel asterisco7 = new JLabel("*");
		asterisco7.setForeground(Color.red);
		JLabel asterisco8 = new JLabel("*");
		asterisco8.setForeground(Color.red);
		JLabel asterisco9 = new JLabel("*");
		asterisco9.setForeground(Color.red);
		JLabel asterisco10 = new JLabel("*");
		asterisco10.setForeground(Color.red);
		
		asterisco1.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco2.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco3.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco4.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco5.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco6.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco7.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco8.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco9.setHorizontalAlignment(SwingConstants.RIGHT);
		asterisco10.setHorizontalAlignment(SwingConstants.RIGHT);
		
		//Campos de Texto
		final JTextField nombreTexto = new JTextField(150);
		
		//ComboBox
		final JComboBox<Pair<Integer, String>> deporteBox = new JComboBox<Pair<Integer, String>>(); 
		
		final JComboBox<String> modalidadBox = new JComboBox<String>(arregloM);
		final JComboBox<String> puntuacionBox = new JComboBox<String>(arregloP);
		final JComboBox<Triplet<Integer, String, Integer>> comboLugares = new JComboBox<Triplet<Integer, String, Integer>>();
		comboLugares.setRenderer(new LugarDeRealizacionComboRenderer());
		
		//Rellenar deporteBOx
		final List<Pair<Integer,String>> deportes = new ArrayList<Pair<Integer, String>>();
		deportes.add(new Pair<Integer, String>(-1, " "));
		deportes.addAll(gestorDeporte.getDeportesInterfaz());
		deporteBox.setRenderer(new DeportesComboRenderer());
		for(Pair<Integer, String> elemento : deportes) {
			deporteBox.addItem(elemento);
		}
		
		
		
		//Spinners
		final JSpinner puntosAu = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		final JSpinner cantMaxSets = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		final JSpinner puntosG = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		final JSpinner puntosPorPresentarse = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		final JSpinner puntosEmp = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		final JSpinner encuentros = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		encuentros.setBounds(100, 202, 30, 20);
		puntosEmp.setEnabled(false);
		
		//CheckBox
		final JCheckBox empate = new JCheckBox("Empate Permitido");
		empate.setBackground(Color.WHITE);
		empate.setBounds(16, 23, 97, 23);
		
		//TextArea
		final JTextArea reglamento = new JTextArea();
		reglamento.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(4, 4, 4, 4)));
		reglamento.setLineWrap(true);
		reglamento.setFont(new Font("Plain", Font.PLAIN, 12));
		
		//Botones
		JButton volver = new JButton();
		JButton agregar = new JButton("+");
		eliminar = new JButton("X");
		JButton aceptar = new JButton("Aceptar");
		
		//Fuentes
		Font fuenteTitulo = new Font("Tahoma", Font.PLAIN, 30);
		Font botones = new Font("Tahoma", Font.PLAIN, 14);
		Font textos = new Font("Tahoma", Font.PLAIN, 14);
		Font etiquetasSecundarias = new Font("Tahoma", Font.PLAIN, 14);
		
		titulo.setFont(fuenteTitulo);
		
		reglamento.setFont(textos);
		nombreTexto.setFont(textos);
		
		nombre.setFont(etiquetasSecundarias);
		deporte.setFont(etiquetasSecundarias);
		modalidad.setFont(etiquetasSecundarias);
		formaP.setFont(etiquetasSecundarias);
		reglamentoL.setFont(etiquetasSecundarias);
		cantidadMaxSets.setFont(etiquetasSecundarias);
		puntosAusente.setFont(etiquetasSecundarias);
		puntosGanado.setFont(etiquetasSecundarias);
		puntosPresentarse.setFont(etiquetasSecundarias);
		buscarLabel.setFont(etiquetasSecundarias);
		cantidad.setFont(etiquetasSecundarias);
		puntosEmpate.setFont(etiquetasSecundarias);
		
		asterisco1.setFont(etiquetasSecundarias);
		asterisco2.setFont(etiquetasSecundarias);
		asterisco3.setFont(etiquetasSecundarias);
		asterisco4.setFont(etiquetasSecundarias);
		asterisco5.setFont(etiquetasSecundarias);
		asterisco6.setFont(etiquetasSecundarias);
		asterisco7.setFont(etiquetasSecundarias);
		asterisco8.setFont(etiquetasSecundarias);
		asterisco9.setFont(etiquetasSecundarias);
		asterisco10.setFont(etiquetasSecundarias);
		
		deporteBox.setFont(textos);
		modalidadBox.setFont(textos);
		puntuacionBox.setFont(textos);
		comboLugares.setFont(textos);
		
		puntosAu.setFont(textos);
		cantMaxSets.setFont(textos);
		puntosG.setFont(textos);
		puntosPorPresentarse.setFont(textos);
		puntosEmp.setFont(textos);
		encuentros.setFont(textos);
		
		agregar.setFont(botones);
		eliminar.setFont(botones);
		aceptar.setFont(botones);
		
		//Dimension
		nombreTexto.setMinimumSize(new Dimension(200, 30));
		nombreTexto.setMaximumSize(new Dimension(200, 30));
		nombreTexto.setPreferredSize(new Dimension(200, 30));
		
		deporteBox.setMinimumSize(new Dimension(200, 30));
		deporteBox.setMaximumSize(new Dimension(200, 30));
		deporteBox.setPreferredSize(new Dimension(200, 30));
		
		modalidadBox.setMinimumSize(new Dimension(200, 30));
		modalidadBox.setMaximumSize(new Dimension(200, 30));
		modalidadBox.setPreferredSize(new Dimension(200, 30));
		
		puntuacionBox.setMinimumSize(new Dimension(200, 30));
		puntuacionBox.setMaximumSize(new Dimension(200, 30));
		puntuacionBox.setPreferredSize(new Dimension(200, 30));
		
		comboLugares.setMinimumSize(new Dimension(200, 30));
		comboLugares.setMaximumSize(new Dimension(200, 30));
		comboLugares.setPreferredSize(new Dimension(200, 30));
		
		puntosAu.setMinimumSize(new Dimension (70, 30));
		cantMaxSets.setMinimumSize(new Dimension (70, 30));
		puntosG.setMinimumSize(new Dimension (70, 30));
		puntosPorPresentarse.setMinimumSize(new Dimension (70, 30));
		puntosEmp.setMinimumSize(new Dimension (70, 30));
		
		reglamento.setMinimumSize(new Dimension(460, 212));
		reglamento.setMaximumSize(new Dimension(460, 212));
		reglamento.setPreferredSize(new Dimension(460, 212));
		
		comboLugares.setMinimumSize(new Dimension(250, 20));
		comboLugares.setMaximumSize(new Dimension(250, 20));
		comboLugares.setPreferredSize(new Dimension(250, 20));
	    
	    aceptar.setMinimumSize(new Dimension(500, 50));
	    aceptar.setPreferredSize(new Dimension(500,50));
	    aceptar.setMaximumSize(new Dimension(500, 50));
		
		//Imagen volver
		ImageIcon iconoVolver= new ImageIcon("src/main/resources/IconoVolver.JPG");
		volver.setPreferredSize(new Dimension(33,33));
		volver.setIcon(iconoVolver);
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		//Constraints//
		//Volver
		GridBagConstraints volverConstraints = new GridBagConstraints();
		volverConstraints.insets = new Insets(10, 10, 10, 10);
		volverConstraints.fill = GridBagConstraints.WEST;
		volverConstraints.gridx = 1;
		volverConstraints.gridy = 0;
		
		//Info
		GridBagConstraints infoConstraints = new GridBagConstraints();
		infoConstraints.insets = new Insets(3, 10, 3, 3);
		infoConstraints.fill = GridBagConstraints.WEST;
		
		//Aux
		GridBagConstraints auxConstraints = new GridBagConstraints();
		auxConstraints.insets = new Insets(3, 3, 3, 3);
		auxConstraints.anchor = GridBagConstraints.WEST;
		auxConstraints.fill = GridBagConstraints.BOTH;
		auxConstraints.gridwidth = 1;
	    auxConstraints.gridheight = 1;
		auxConstraints.weightx = 1.0;  
	    auxConstraints.weighty = 1.0;
		
		//Lugar
		GridBagConstraints lugarConstraints = new GridBagConstraints();
		lugarConstraints.insets = new Insets(10, 3, 10, 3);
		lugarConstraints.fill = GridBagConstraints.WEST;
		
		//--------------//
		
		//Panel del titulo 		
		tituloPanel = new JPanel(new GridBagLayout());
		tituloPanel.setMinimumSize(new Dimension(1200, 75));
		tituloPanel.setBackground(Color.WHITE);
		
		JPanel rellenoPanel = new JPanel();
		rellenoPanel.setMinimumSize(new Dimension(400, 30));
		rellenoPanel.setMaximumSize(new Dimension(400, 30));
		rellenoPanel.setPreferredSize(new Dimension(400, 30));
		rellenoPanel.setBackground(Color.WHITE);
		
		JPanel rellenoPanel2 = new JPanel();
		rellenoPanel2.setMinimumSize(new Dimension(400, 30));
		rellenoPanel2.setMaximumSize(new Dimension(400, 30));
		rellenoPanel2.setPreferredSize(new Dimension(400, 30));
		rellenoPanel2.setBackground(Color.WHITE);
	
		tituloPanel.add(volver, volverConstraints);		

		volverConstraints.gridx = 2;
		volverConstraints.gridy = 0;
		tituloPanel.add(rellenoPanel, volverConstraints);
		
		volverConstraints.gridx = 5;
		tituloPanel.add(titulo, volverConstraints);
		
		volverConstraints.gridx = 6;
		volverConstraints.gridy = 0;
		tituloPanel.add(rellenoPanel2, volverConstraints);
		
		//Panel de la info que ingresa el usuario
		infoPanel = new JPanel(new GridBagLayout());
		infoPanel.setBackground(Color.WHITE);
		
		//Panel de los campos a rellenar 
		camposPanel = new JPanel(new GridBagLayout());
		camposPanel.setBackground(Color.WHITE);
		
		
		//parte de arriba: nombre, deporte, forma de puntuacion y pantalla con CardLayout
		JPanel auxArriba = new JPanel(new GridBagLayout());
		auxArriba.setBackground(Color.WHITE);

		//Prueba//
		JPanel ab = new JPanel();
		ab.setMaximumSize(new Dimension(30, 30));
		ab.setMinimumSize(new Dimension(30, 30));
		ab.setPreferredSize(new Dimension(30, 30));
		ab.setBackground(Color.white);
		
	    auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		auxArriba.add(asterisco1, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxArriba.add(nombre, auxConstraints);
		
		auxConstraints.gridx = 2;
		auxArriba.add(ab, auxConstraints);
		
		auxConstraints.gridx = 3;
		auxArriba.add(asterisco3, auxConstraints);
		
		auxConstraints.gridx = 4;
		auxArriba.add(formaP, auxConstraints);
		
		auxConstraints.gridy = 1;
		auxConstraints.gridx = 1;
		auxArriba.add(nombreTexto, auxConstraints);
		
		auxConstraints.gridx = 4;
		auxArriba.add(puntuacionBox, auxConstraints);
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 3;
		auxArriba.add(asterisco2, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxArriba.add(deporte, auxConstraints);
		
		auxConstraints.gridx = 3;
		auxArriba.add(asterisco4, auxConstraints);
		asterisco4.setVisible(false);
		
		//Dependen de Forma de Puntuacion//
		//Puntuacion
		auxConstraints.gridx = 4;
		auxArriba.add(puntosAusente, auxConstraints);

		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridy = 4;
		auxArriba.add(puntosAu, auxConstraints);
		
		puntosAusente.setVisible(false);
		puntosAu.setVisible(false);
		
		//Sets
		auxConstraints.fill = GridBagConstraints.BOTH;
		
		auxConstraints.gridy = 3;
		auxArriba.add(cantidadMaxSets, auxConstraints);

		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridy = 4;
		auxArriba.add(cantMaxSets, auxConstraints);
		
		cantidadMaxSets.setVisible(false);
		cantMaxSets.setVisible(false);
		//-------------------------------//
		
		auxConstraints.fill = GridBagConstraints.BOTH;
		
		auxConstraints.gridx = 1;		
		auxConstraints.gridy = 4;
		auxArriba.add(deporteBox, auxConstraints);
		
		auxConstraints.gridy = 5;
		auxConstraints.gridx = 0;
		auxArriba.add(asterisco5, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxArriba.add(modalidad, auxConstraints);

		auxConstraints.gridx = 3;
		auxArriba.add(asterisco6, auxConstraints);
		asterisco6.setVisible(false);
		
		auxConstraints.gridx = 4;
		auxArriba.add(puntosGanado, auxConstraints);
		puntosGanado.setVisible(false);
		
		auxConstraints.gridy = 6;
		auxConstraints.gridx = 1;
		auxArriba.add(modalidadBox, auxConstraints);

		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridx = 4;
		auxArriba.add(puntosG, auxConstraints);
		puntosG.setVisible(false);

		auxConstraints.fill = GridBagConstraints.BOTH;
		
		//Nuevo Panel
		JPanel panelAux = new JPanel(new GridBagLayout());
		panelAux.setBackground(Color.white);
		
		JPanel aa = new JPanel();
		aa.setMaximumSize(new Dimension(310, 30));
		aa.setMinimumSize(new Dimension(310, 30));
		aa.setPreferredSize(new Dimension(310, 30));
		aa.setBackground(Color.white);
		
		auxConstraints.gridy = 0;
		auxConstraints.gridx = 0;
		panelAux.add(aa, auxConstraints);
		
		auxConstraints.gridx = 1;
		panelAux.add(asterisco7, auxConstraints);
		
		auxConstraints.gridx = 2;
		panelAux.add(puntosPresentarse, auxConstraints);
		
		auxConstraints.gridx = 3;
		panelAux.add(asterisco8, auxConstraints);
		
		auxConstraints.gridx = 4;
		panelAux.add(puntosEmpate, auxConstraints);
		
		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridx = 2;
		auxConstraints.gridy = 1;
		panelAux.add(puntosPorPresentarse, auxConstraints);
		
		auxConstraints.gridx = 4;
		panelAux.add(puntosEmp, auxConstraints);
		
		auxConstraints.fill = GridBagConstraints.BOTH;
		
		auxConstraints.gridy = 2;
		auxConstraints.gridx = 1;
		panelAux.add(asterisco9, auxConstraints);
		
		auxConstraints.gridx = 2;
		panelAux.add(empate, auxConstraints);
		
		panelAux.setVisible(false);
		
		JPanel ac = new JPanel();
		ac.setBackground(Color.white);
		ac.setMinimumSize(panelAux.getMinimumSize());
		
		//---------------//
		//Panel de abajo: reglamento
		JPanel abajo = new JPanel(new GridBagLayout());
		abajo.setBackground(Color.WHITE);
		
		JPanel ad = new JPanel();
		ad.setMaximumSize(new Dimension(10, 10));
		ad.setMinimumSize(new Dimension(10, 10));
		ad.setPreferredSize(new Dimension(10, 10));
		ad.setBackground(Color.white);
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		abajo.add(ad, auxConstraints);
		
		auxConstraints.gridx = 1;
		abajo.add(reglamentoL, auxConstraints);
		
		auxConstraints.gridy = 1;
		abajo.add(reglamento, auxConstraints);
		
		infoConstraints.gridx = 0;
		infoConstraints.gridy = 1;		
		camposPanel.add(auxArriba, infoConstraints);
		
		infoConstraints.gridy = 2;		
		camposPanel.add(panelAux, infoConstraints);
		
		infoConstraints.gridy = 2;		
		camposPanel.add(ac, infoConstraints);
		
		infoConstraints.gridy = 3;		
		camposPanel.add(abajo, infoConstraints);
		
		//Panel lugar de realizacion y boton aceptar
		lugarPanel = new JPanel();
		lugarPanel.setBackground(Color.WHITE);
		lugarPanel.setLayout(new GridBagLayout());
		
		//Panel buscar Lugar de realizacion
		JPanel lugarDeRealizacion = new JPanel(new GridBagLayout());
		lugarDeRealizacion.setBackground(Color.GRAY);
		lugarDeRealizacion.setMinimumSize(new Dimension(500, 150));
		lugarDeRealizacion.setMaximumSize(new Dimension(500, 150));
		lugarDeRealizacion.setPreferredSize(new Dimension(500, 150));
		
		

		//final List<Triplet<Integer, String, Integer>> lugares = new ArrayList<Triplet<Integer, String, Integer>>();
		List<Triplet<Integer, String, Integer>> reservas = new ArrayList<Triplet<Integer,String,Integer>>();

		List<Triplet<Integer, String, Integer>> lugaresSeleccionados = new ArrayList<Triplet<Integer, String, Integer>>();		
		
		deporteBox.addActionListener( e-> {
			Pair<Integer, String> deporteSeleccionado = (Pair<Integer, String>) deporteBox.getSelectedItem();
			List<Triplet<Integer, String, Integer>> nuevaListaLugares = new ArrayList<Triplet<Integer, String, Integer>>();
			
			if(deporteSeleccionado.getFirst()!= null &&  deporteSeleccionado.getFirst() >=0) {	
				try {
					nuevaListaLugares = GestorLugarDeRealizacion.recuperarLugares(deporteSeleccionado.getFirst());
				}
				catch(Exception ex){
					JOptionPane.showMessageDialog(new JPanel(), "Aviso: No se encontraron lugares de realización para el deporte seleccionado", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
				
			}
			
			comboLugares.removeAllItems();
			comboLugares.addItem(new Triplet<Integer, String, Integer>(-1, " ", -1));
			
			for(Triplet<Integer, String, Integer> element : nuevaListaLugares) {
				
				comboLugares.addItem(element);
			}
		});
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		lugarDeRealizacion.add(asterisco10, auxConstraints);
		lugarDeRealizacion.setBackground(new Color(22, 138, 114));
		
		Dimension d = new Dimension(40, 10);
		JPanel ae = new JPanel();
		ae.setMaximumSize(d);
		ae.setMinimumSize(d);
		ae.setPreferredSize(d);
		ae.setBackground(new Color(22, 138, 114));
		
		JPanel af = new JPanel();
		af.setMaximumSize(d);
		af.setMinimumSize(d);
		af.setPreferredSize(d);
		af.setBackground(new Color(22, 138, 114));
		
		JPanel ag = new JPanel();
		ag.setMaximumSize(d);
		ag.setMinimumSize(d);
		ag.setPreferredSize(d);
		ag.setBackground(new Color(22, 138, 114));
		
		JPanel ah = new JPanel();
		ah.setMaximumSize(d);
		ah.setMinimumSize(d);
		ah.setPreferredSize(d);
		ah.setBackground(new Color(22, 138, 114));		
		
		auxConstraints.gridx = 1;
		auxConstraints.gridy = 1;
		lugarDeRealizacion.add(ae, auxConstraints);
		
		auxConstraints.gridx = 2;
		lugarDeRealizacion.add(buscarLabel, auxConstraints);
		
		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridy = 2;
		lugarDeRealizacion.add(comboLugares, auxConstraints);
		
		auxConstraints.fill = GridBagConstraints.BOTH;
		
		auxConstraints.gridy = 4;
		lugarDeRealizacion.add(cantidad, auxConstraints);
		
		auxConstraints.fill = GridBagConstraints.WEST;
		auxConstraints.gridy = 5;
		lugarDeRealizacion.add(encuentros, auxConstraints);
		
		auxConstraints.gridy = 6;
		lugarDeRealizacion.add(af, auxConstraints);
		
		auxConstraints.gridy = 2;
		auxConstraints.gridx = 3;
		lugarDeRealizacion.add(ag, auxConstraints);
		
		auxConstraints.gridx = 4;
		lugarDeRealizacion.add(agregar, auxConstraints);
		
		auxConstraints.gridx = 5;
		lugarDeRealizacion.add(ah, auxConstraints);
		
		
		lugarConstraints.gridy = 0;
		lugarConstraints.gridx = 1;
		
		lugarPanel.add(lugarDeRealizacion, lugarConstraints);

		//Crear tabla
		tablaLugares = new JTable(modeloLugar){
		    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, extend, extend);
		    }
		};
		
		
		//Constraints Tabla
	    GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    
		//Panel tabla con lugares de realizacion seleccionados
		JPanel panelTabla = new JPanel(new GridBagLayout());
		panelTabla.setBackground(Color.WHITE);
		lugarPanel.add(tablaLugares);
		actualizarTablaLugar(reservas);
		tablaLugares.setDefaultEditor(Object.class, null);
		
		//Dimension Tabla 
		Dimension tamTabla = new Dimension(500, 300);
						
		//Header
		tablaLugares.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaLugares.getTableHeader().setEnabled(false);
		tablaLugares.getTableHeader().setBackground(new Color(42, 224, 187));		
		tablaLugares.getTableHeader().setOpaque(false);
		tablaLugares.getTableHeader().setMinimumSize(new Dimension(500, 50));
		tablaLugares.getTableHeader().setMaximumSize(new Dimension(500, 50));
		tablaLugares.getTableHeader().setPreferredSize(new Dimension(500, 50));
						
		((DefaultTableCellRenderer)tablaLugares.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
								
		//Table Renderer
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer(){
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
				final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				c.setBackground(row % 2 == 0 ? new Color(187, 255, 241) : Color.WHITE);
				if(isSelected) c.setBackground(Color.BLUE);
				return c;
			}
		};
		cr.setHorizontalAlignment(JLabel.CENTER);
								
		//Configuracion tabla
		tablaLugares.setDefaultRenderer(Object.class, cr);
		tablaLugares.setMinimumSize(tamTabla);
		tablaLugares.setMaximumSize(tamTabla);
		tablaLugares.setPreferredSize(tamTabla);
		tablaLugares.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaLugares.setRowHeight(40);
				
		//ScrollPane
		JScrollPane tableSP = new JScrollPane(tablaLugares);
		tableSP.setMinimumSize(tamTabla);
		tableSP.setMaximumSize(tamTabla);
		tableSP.setPreferredSize(tamTabla);
		tableSP.setBackground(new Color(42, 224, 187));
	    panelTabla.add(tableSP, constraintsTabla);
		
		lugarConstraints.gridy = 1;
	    lugarPanel.add(panelTabla, lugarConstraints);
	    
	    //boton aceptar
		lugarConstraints.gridy = 2;
	    lugarPanel.add(aceptar, lugarConstraints);
	    
	    //Agregar paneles al Panel de AltaCompetencia
		JPanel aux3 = new JPanel();
		aux3.setBackground(Color.WHITE);
		aux3.setMinimumSize(new Dimension(1200, 15));
		aux3.setMaximumSize(new Dimension(1200, 15));
		aux3.setPreferredSize(new Dimension(1200, 15));
	 
		infoConstraints.gridx = 0;
		infoConstraints.gridy = 0;
		infoPanel.add(camposPanel, infoConstraints);
		
		this.add(aux3, BorderLayout.NORTH);
		this.add(tituloPanel);
	    
		infoConstraints.gridx = 1;
		infoPanel.add(lugarPanel, infoConstraints);
		
		this.add(infoPanel, BorderLayout.CENTER);
		
		JPanel aux = new JPanel();
		aux.setBackground(Color.WHITE);
		aux.setMinimumSize(new Dimension(1200, 50));
		aux.setMaximumSize(new Dimension(1200, 50));
		aux.setPreferredSize(new Dimension(1200, 50));
		
		JPanel aux1 = new JPanel();
		aux1.setBackground(Color.WHITE);
		aux1.setMinimumSize(new Dimension(25, 600));
		aux1.setMaximumSize(new Dimension(25, 600));
		aux1.setPreferredSize(new Dimension(25, 600));
		
		JPanel aux2 = new JPanel();
		aux2.setBackground(Color.WHITE);
		aux2.setMinimumSize(new Dimension(25, 600));
		aux2.setMaximumSize(new Dimension(25, 600));
		aux2.setPreferredSize(new Dimension(25, 600));
		
		this.add(aux, BorderLayout.SOUTH);
		this.add(aux1, BorderLayout.EAST);
		this.add(aux2, BorderLayout.WEST);
		
		//Funcion de botones
	    aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//crear DTO
				if(		nombreTexto.getText() == null ||
						deporteBox.getSelectedIndex() == 0 ||
						modalidadBox.getSelectedIndex() == 0||
						puntuacionBox.getSelectedIndex() == 0 ||
						//reservas.size() == 0 ||
						puntuacionBox.getSelectedIndex() == 2 && (Integer) puntosAu.getValue() == 0 ||
						puntuacionBox.getSelectedIndex() == 3 && (Integer) cantMaxSets.getValue() == 0 ||
						modalidadBox.getSelectedItem().toString().equals("Sistema de Liga") && (Integer) puntosG.getValue() == 0
						) {
						
					JOptionPane.showMessageDialog(new JPanel(), "Error: Uno o más de los campos se encuentra vacío", "Error", JOptionPane.ERROR_MESSAGE);
					
					if(!(nombreTexto.getText().length() > 0)) {
						nombreTexto.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						nombreTexto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(deporteBox.getSelectedIndex() == 0) {
						deporteBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						deporteBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(modalidadBox.getSelectedIndex() == 0){
						modalidadBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						modalidadBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(puntuacionBox.getSelectedIndex() == 0){
						puntuacionBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						puntuacionBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(modalidadBox.getSelectedIndex() == 1 && (Integer) puntosG.getValue() == 0){
						puntosG.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						puntosG.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(puntuacionBox.getSelectedIndex() == 3 && (Integer) cantMaxSets.getValue() == 0){
						cantMaxSets.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						cantMaxSets.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if(puntuacionBox.getSelectedIndex() == 2 && (Integer) puntosAu.getValue() == 0){
						puntosAu.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						puntosAu.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
				}
				else {
					//mandar al gestor
					SistemaDeCompetencia.Tipo tipoCompetencia = null;
					switch(modalidadBox.getSelectedIndex()) { //"Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"
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
					FormaPuntuacion.Tipo tipoPuntuacion = null;
					switch(puntuacionBox.getSelectedIndex()) { //"Resultado Final", "Puntuación", "Sets"
					case 1:
						tipoPuntuacion = FormaPuntuacion.Tipo.RESULTADO_FINAL;
						break;
					case 2: 
						tipoPuntuacion = FormaPuntuacion.Tipo.PUNTUACION;
						break;
					case 3:
						tipoPuntuacion = FormaPuntuacion.Tipo.SETS;
						break;
					default:
						break;
					}
					
					List<Pair<Integer, Integer>> reservasDTO = new ArrayList<Pair<Integer, Integer>>();
					for (Triplet<Integer, String, Integer> reserva: reservas) {
						reservasDTO.add(new Pair<Integer, Integer>(reserva.getFirst(), reserva.getThird()));
					}
					CompetenciaDTO competenciaDTO = new CompetenciaDTO( 
							nombreTexto.getText(), tipoCompetencia, tipoPuntuacion, reglamento.getText(), reservasDTO, 
							deportes.get(deporteBox.getSelectedIndex()), (Integer) puntosAu.getValue(), (Integer) cantMaxSets.getValue(), (Integer) puntosG.getValue(),
							empate.isSelected(), (Integer) puntosEmp.getValue(), (Integer) puntosPorPresentarse.getValue());
					try {
						gestorCompetencia.darDeAltaCompetencia(competenciaDTO);
						JOptionPane.showMessageDialog(new JPanel(), "La competencia se ha guardado correctamente" , "Información", JOptionPane.INFORMATION_MESSAGE);
						competenciaCreada(filtros);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(new JPanel(), "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}				
			}
	    	
	    });
	    
		eliminar.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int renglonSeleccionado = tablaLugares.getSelectedRow();
				//borrarlo de la lista y llamar a actualizar tabla
				comboLugares.addItem(lugaresSeleccionados.stream().filter(f-> reservas.get(renglonSeleccionado).getFirst() == f.getFirst()).collect(Collectors.toList()).get(0));
				lugaresSeleccionados.remove(lugaresSeleccionados.stream().filter(f-> reservas.get(renglonSeleccionado).getFirst() == f.getFirst()).collect(Collectors.toList()).get(0));
				reservas.remove(renglonSeleccionado);
				actualizarTablaLugar(reservas);
			}
			
		});
		
	    agregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Triplet<Integer, String, Integer> lugarSeleccionado = comboLugares.getItemAt(comboLugares.getSelectedIndex());
				if(lugarSeleccionado.getFirst() >= 0 && (Integer) encuentros.getValue() > 0) {
						reservas.add(new Triplet<Integer, String, Integer>(lugarSeleccionado.getFirst(), lugarSeleccionado.getSecond(), (Integer) encuentros.getValue()));
						actualizarTablaLugar(reservas);
						lugaresSeleccionados.add(lugarSeleccionado);
						comboLugares.removeItemAt(comboLugares.getSelectedIndex());
						encuentros.setValue(0);
						comboLugares.setSelectedIndex(0);	
				}
				else {
					JOptionPane.showMessageDialog(new JPanel(), "Uno o más de los campos se encuentran vacíos", "Advertencia", JOptionPane.WARNING_MESSAGE);
					if(comboLugares.getSelectedIndex() == 0) {
						nombreTexto.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						nombreTexto.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
					if((Integer) encuentros.getValue() == 0) {
						deporteBox.setBorder(BorderFactory.createLineBorder(Color.red, 2));
					}
					else {
						deporteBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
					}
				}
			}
	    	
	    });
	    
	    
	    
		volver.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				volver();
			}
			
		});
		
		empate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(empate.isSelected()) {
					puntosEmp.setEnabled(true);
					puntosEmpate.setEnabled(true);
				}
				else {
					puntosEmp.setEnabled(false);
					puntosEmpate.setEnabled(false);
				}
			}
		});
		
		nombreTexto.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (nombreTexto.getText().length() >= 150 ) e.consume(); 
		    }  
		});
		
		reglamento.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (reglamento.getText().length() >= 1000 ) e.consume(); 
		    }  
		});
		
		this.addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
                tablaLugares.clearSelection();
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
		
		
		puntuacionBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(puntuacionBox.getSelectedIndex() == 2) {
					asterisco4.setVisible(true);
					puntosAusente.setVisible(true);
					puntosAu.setVisible(true);
					cantidadMaxSets.setVisible(false);
					cantMaxSets.setVisible(false);
				} else {
					if(puntuacionBox.getSelectedIndex() == 3) {
						asterisco4.setVisible(true);
						puntosAusente.setVisible(false);
						puntosAu.setVisible(false);
						cantidadMaxSets.setVisible(true);
						cantMaxSets.setVisible(true);
					}
					else {
						asterisco4.setVisible(false);
						puntosAusente.setVisible(false);
						puntosAu.setVisible(false);
						cantidadMaxSets.setVisible(false);
						cantMaxSets.setVisible(false);
					}
				}
			}
			
		});
		
		modalidadBox.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(modalidadBox.getSelectedIndex() == 1) {
					panelAux.setVisible(true);
					asterisco6.setVisible(true);
					puntosGanado.setVisible(true);
					puntosG.setVisible(true);
					ac.setVisible(false);
				}
				else {
					asterisco6.setVisible(false);
					ac.setVisible(true);
					panelAux.setVisible(false);
					puntosGanado.setVisible(false);
					puntosG.setVisible(false);
				}
			}
			
		});
		
	}
	
	public void actualizarTablaLugar(List<Triplet<Integer, String, Integer>> lugares) {
		//crear modelo tabla
				String[] columnas = { "Establecimientos", "Encuentros", "Eliminar"};
				modeloLugar = new DefaultTableModel(columnas, 0);		

				for(Triplet<Integer, String, Integer> lug: lugares) {
					Object[] renglon = { lug.getSecond(), lug.getThird()};
					modeloLugar.addRow(renglon);
				}
				
				//actualizar modelo
				tablaLugares.setModel(modeloLugar);
				tablaLugares.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
				tablaLugares.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox()));
	}
	
	private void volver() {
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.previous(tpPanel);
		tpPanel.remove(this);
	}
	
	private void competenciaCreada(List<Object> filtros) {
		tpPanel.remove(tpPanel.getComponentCount() - 2);
		JPanel listarCompetenciasPanel = new ListarCompetencias(tpPanel, filtros);
		tpPanel.add(listarCompetenciasPanel, "ListarCompetencias");
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.show(tpPanel, "ListarCompetencias");
		tpPanel.remove(tpPanel.getComponentCount() - 2);
		tpPanel.remove(this);
	}
	

	
	
}