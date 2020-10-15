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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class AltaCompetencia extends JPanel {

	//Clases auxiliares
	public class CardLayoutPersonalizado extends JPanel implements ItemListener {
	    private JPanel cards; 
	    private JPanel primero;
	    
	    public CardLayoutPersonalizado(JLabel asterisco,JLabel etiquetaBox, List<JPanel> paneles, String[] nombres, JComboBox<String> box, boolean axis) {
	        if(axis) {
	        	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	        	this.setBackground(Color.WHITE);
	        }
	        else {
	        	this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	        	this.setBackground(Color.WHITE);
	        }
	        
	        //creo el panel con el JComboBox
	        
	        primero = new JPanel(new GridBagLayout());
	        primero.setBackground(Color.WHITE); 
			GridBagConstraints auxConstraints = new GridBagConstraints();
			auxConstraints.insets = new Insets(3, 10, 3, 3);
			auxConstraints.fill = GridBagConstraints.WEST;
			
			auxConstraints.gridx = 0;
			auxConstraints.gridy = 0;
			primero.add(asterisco, auxConstraints);
			
			auxConstraints.gridx = 1;
			auxConstraints.gridy = 0;
			auxConstraints.anchor=GridBagConstraints.WEST;
			primero.add(etiquetaBox, auxConstraints);
			
	    	box.addItemListener(this);
	    	
			auxConstraints.gridy = 1;
			primero.add(box, auxConstraints);

			//Creo el panel con CardLayout
	        cards = new JPanel(new CardLayout(0,0));
	        cards.setBackground(Color.WHITE);
	        cards.add(new JPanel(), " ");
	        
	        for(int i = 0; i < paneles.size(); i++) {
	        	cards.add(paneles.get(i), nombres[i]);
	        }
	       
	        this.add(primero);
	        this.add(cards);
	        this.setBackground(Color.WHITE);
	    }
	    
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
	    }
	}
    
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
	
	private JFrame altaCompetenciaFrame = new JFrame();
	private JPanel tituloPanel;
	private JPanel camposPanel;
	private JPanel lugarPanel;
	private JPanel infoPanel;
	private String[] arregloM = {" ", "Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"};
	private String[] arregloP = {" ", "Resultado Final", "Puntuación", "Sets"};
	private JTable tablaLugares;
	private  DefaultTableModel modeloLugar;
	public JButton eliminar;
	
	public AltaCompetencia() {
		this.armarPantalla();
	}
	
	private void armarPantalla() {
		//testeo, despues cardLayout
		// Frame properties
		altaCompetenciaFrame.setResizable(false);
		altaCompetenciaFrame.setBackground(Color.WHITE);
		altaCompetenciaFrame.setTitle("Trabajo Práctico 2020 - DIED");
		altaCompetenciaFrame.setMinimumSize(new Dimension(1280, 720));
		altaCompetenciaFrame.setBounds(100, 100, 450, 300);
		altaCompetenciaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		//Panel del titulo y boton volver		
		tituloPanel = new JPanel(new GridBagLayout());
		tituloPanel.setMinimumSize(new Dimension(1200, 75));
		tituloPanel.setBackground(Color.WHITE);
		GridBagConstraints volverConstraints = new GridBagConstraints();
		volverConstraints.insets = new Insets(10, 10, 10, 10);
		volverConstraints.fill = GridBagConstraints.NONE;
		volverConstraints.gridx = 1;
		volverConstraints.gridy = 0;
		volverConstraints.anchor = GridBagConstraints.WEST;
		ImageIcon iconoVolver= new ImageIcon("IconoVolver.JPG");
		JButton volver = new JButton();
		volver.setPreferredSize(new Dimension(33,33));
		volver.setIcon(iconoVolver);
		
		Border line = new LineBorder(Color.WHITE);
		Border margin = new EmptyBorder(0, 0, 0, 0);
		Border compound = new CompoundBorder(line, margin);
		volver.setBorder(compound);
		
		volver.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//cl.show(frameTrabajoPractico.getContentPane(), "card__MainMenu");
			}
			
		});
		
		tituloPanel.add(volver, volverConstraints);
		
		volverConstraints.gridx = 2;
		tituloPanel.add(Box.createRigidArea(new Dimension(300,0)),volverConstraints);
		
		JLabel titulo = new JLabel("Nueva Competencia");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		volverConstraints.gridx = 3;
		tituloPanel.add(titulo, volverConstraints);
		
		volverConstraints.gridx = 4;
		tituloPanel.add(Box.createRigidArea(new Dimension(600,0)), volverConstraints);
		
		
		volverConstraints.gridx = 0;
		tituloPanel.add(Box.createRigidArea(new Dimension(234,0)),volverConstraints);
		
		//Panel de la info que ingresa el usuario
		infoPanel = new JPanel(new GridBagLayout());
		
		//Panel de los campos a rellenar 
		camposPanel = new JPanel(new GridBagLayout());
		camposPanel.setBackground(Color.WHITE);
		GridBagConstraints infoConstraints = new GridBagConstraints();
		infoConstraints.insets = new Insets(3, 10, 3, 3);
		infoConstraints.fill = GridBagConstraints.WEST;
		
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
		JLabel nombre = new JLabel();
		
		nombre.setText("Nombre de la Competencia");
		JLabel deporte = new JLabel("Deporte Asociado");
		JLabel modalidad = new JLabel("Modalidad de la Competencia");
		JLabel formaP = new JLabel("Forma de Puntuación");
		JLabel reglamentoL = new JLabel("Reglamento");
		JLabel cantidadMaxSets = new JLabel("Cantidad Máxima Sets");
		JLabel puntosAusente = new JLabel("Puntos si Rival Ausente");
		JLabel puntosGanado = new JLabel("<html><font color='red'>*</font> Puntos por Partido Ganado</html>");
		JLabel puntosPresentarse = new JLabel("<html><font color='red'>*</font> Puntos por Presentarse</html>");
		JLabel puntosEmpate = new JLabel("Puntos Empate");
		puntosEmpate.setEnabled(false);
		
		final JTextField nombreTexto = new JTextField(150);
		nombreTexto.setMinimumSize(new Dimension(200, 30));
		nombreTexto.setMaximumSize(new Dimension(200, 30));
		nombreTexto.setPreferredSize(new Dimension(200, 30));
		final JComboBox<String> deporteBox = new JComboBox<String>(); 
		
		//Pedir al gestor de deporte
		
		deporteBox.addItem(" ");
		deporteBox.setMinimumSize(new Dimension(200, 30));
		deporteBox.setMaximumSize(new Dimension(200, 30));
		deporteBox.setPreferredSize(new Dimension(200, 30));
		deporteBox.setBackground(Color.WHITE);
		
		final JComboBox<String> modalidadBox = new JComboBox<String>(arregloM);
		modalidadBox.setMinimumSize(new Dimension(200, 30));
		modalidadBox.setMaximumSize(new Dimension(200, 30));
		modalidadBox.setPreferredSize(new Dimension(200, 30));
		modalidadBox.setBackground(Color.WHITE);
		
		
		final JComboBox<String> puntuacionBox = new JComboBox<String>(arregloP);
		puntuacionBox.setMinimumSize(new Dimension(200, 30));
		puntuacionBox.setMaximumSize(new Dimension(200, 30));
		puntuacionBox.setPreferredSize(new Dimension(200, 30));
		puntuacionBox.setBackground(Color.WHITE);
		
		final JSpinner puntosAu = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		puntosAu.setMinimumSize(new Dimension (30, 35));
		final JSpinner cantMaxSets = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		cantMaxSets.setMinimumSize(new Dimension (30, 35));
		final JSpinner puntosG = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		puntosG.setMinimumSize(new Dimension (30, 20));
		final JSpinner puntosPorPresentarse = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		puntosPorPresentarse.setMinimumSize(new Dimension (30, 20));
		final JSpinner puntosEmp = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		puntosEmp.setMinimumSize(new Dimension (30, 20));
		puntosEmp.setEnabled(false);
		
		final JCheckBox empate = new JCheckBox("Empate Permitido");
		empate.setBackground(Color.WHITE);
		empate.setBounds(16, 23, 97, 23);
		empate.addActionListener(e -> {
			if(empate.isSelected()) {
				puntosEmp.setEnabled(true);
				puntosEmpate.setEnabled(true);
			}
			else {
				puntosEmp.setEnabled(false);
				puntosEmpate.setEnabled(false);
			}
		});
		
		JTextArea reglamento = new JTextArea();
		line = new LineBorder(Color.BLACK);
		margin = new EmptyBorder(4, 4, 4, 4);
		compound = new CompoundBorder(line, margin);
		reglamento.setBorder(compound);
		reglamento.setMinimumSize(new Dimension(415, 270));
		reglamento.setMaximumSize(new Dimension(415, 270));
		reglamento.setPreferredSize(new Dimension(415, 270));
		reglamento.setLineWrap(true);
		reglamento.setFont(new Font("Plain", Font.PLAIN, 12));
		//parte de arriba: nombre, deporte, forma de puntuacion y pantalla con CardLayout
		JPanel arriba = new JPanel(new GridBagLayout());
		arriba.setBackground(Color.WHITE);
		
		//panel con nombre y deporte
		JPanel auxArriba = new JPanel(new GridBagLayout());
		auxArriba.setBackground(Color.WHITE);
		GridBagConstraints auxConstraints = new GridBagConstraints();
		auxConstraints.insets = new Insets(3, 3, 3, 3);
		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		auxArriba.add(asterisco1, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxConstraints.gridy = 0;
		auxConstraints.anchor = GridBagConstraints.WEST;
		auxArriba.add(nombre, auxConstraints);
		
		auxConstraints.gridy = 1;
		auxArriba.add(nombreTexto, auxConstraints);
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 3;
		auxArriba.add(asterisco2, auxConstraints);
		
		auxConstraints.gridx = 1;
		auxArriba.add(deporte, auxConstraints);
		
		auxConstraints.gridy = 4;
		auxArriba.add(deporteBox, auxConstraints);
		
		infoConstraints.gridx = 0;
		infoConstraints.gridy = 0;
		arriba.add(auxArriba, infoConstraints);
		
		//parte de la forma de puntuacion	
	    JPanel sets = new JPanel(new GridBagLayout());
	    JPanel puntuacion = new JPanel(new GridBagLayout());
	    JPanel resultadofinal = new JPanel();
	    
	    sets.setBackground(Color.WHITE);
	    puntuacion.setBackground(Color.WHITE);
	    resultadofinal.setBackground(Color.WHITE);

	    auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
	    sets.add(asterisco5, auxConstraints);
	    
		auxConstraints.gridx = 1;
		auxConstraints.gridy = 0;
	    sets.add(cantidadMaxSets, auxConstraints);
	        
		auxConstraints.gridy = 1;
	    sets.add(cantMaxSets, auxConstraints);
	    
	    auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		puntuacion.add(asterisco6, auxConstraints);
	    
		auxConstraints.gridx = 1;
		auxConstraints.gridy = 0;
	    puntuacion.add(puntosAusente, auxConstraints);
	        
		auxConstraints.gridy = 1;
	    puntuacion.add(puntosAu, auxConstraints);
	        
	    List<JPanel> panelesPun = new ArrayList<JPanel>();
	    panelesPun.add(sets);
	    panelesPun.add(puntuacion);
	    panelesPun.add(resultadofinal);
	    String[] nombresPun = {"Sets", "Puntuación", "Resultado Final"};
	    
	    JPanel auxPunt = new CardLayoutPersonalizado(asterisco3,formaP, panelesPun, nombresPun, puntuacionBox, true);
	    auxPunt.setBackground(Color.WHITE);
	    infoConstraints.gridx = 1;
		infoConstraints.gridy = 0;
		arriba.add(auxPunt, infoConstraints);
		
		//Panel del medio: modalidad y puntos		
	    
		JPanel sistemadeliga = new JPanel(new GridBagLayout());
	    JPanel sistemadeeliminatoriasimple = new JPanel();
	    JPanel sistemadeeliminatoriadoble = new JPanel();
	    
	    sistemadeliga.setBackground(Color.WHITE);
	    sistemadeeliminatoriasimple.setBackground(Color.WHITE);
	    sistemadeeliminatoriadoble.setBackground(Color.WHITE);
		
		auxConstraints.gridx = 1;
		auxConstraints.gridwidth=1;
		auxConstraints.gridy = 0;
		puntosGanado.setPreferredSize(new Dimension(140,10));
		puntosGanado.setMinimumSize(new Dimension(140,30));
		sistemadeliga.add(puntosGanado, auxConstraints);
	        
		auxConstraints.gridwidth=1;
		auxConstraints.gridy = 1;
		sistemadeliga.add(puntosG, auxConstraints);
	    
		auxConstraints.gridy = 2;
		puntosPresentarse.setPreferredSize(new Dimension(140,10));
		puntosPresentarse.setMinimumSize(new Dimension(140,10));
		sistemadeliga.add(puntosPresentarse, auxConstraints);
	        
		auxConstraints.gridy = 3;
		auxConstraints.gridwidth=1;
		sistemadeliga.add(puntosPorPresentarse, auxConstraints);

		auxConstraints.gridy = 4;
		sistemadeliga.add(empate, auxConstraints);
	    
		auxConstraints.gridx = 2;
		auxConstraints.gridy = 3;
		auxConstraints.gridwidth=1;
		puntosPresentarse.setPreferredSize(new Dimension(140,10));
		puntosPresentarse.setMinimumSize(new Dimension(140,10));
		sistemadeliga.add(puntosEmpate, auxConstraints);
	        
		auxConstraints.gridy = 4;
		auxConstraints.gridwidth=1;
		sistemadeliga.add(puntosEmp, auxConstraints);
		
		List<JPanel> panelesMod = new ArrayList<JPanel>();
		panelesMod.add(sistemadeliga);
		panelesMod.add(sistemadeeliminatoriasimple);
		panelesMod.add(sistemadeeliminatoriadoble);
		
		String[] nombresMod = {"Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"};
		
		JPanel medio = new CardLayoutPersonalizado(asterisco4, modalidad, panelesMod, nombresMod, modalidadBox, false);
		medio.setBackground(Color.WHITE);
		
		
		//Panel de abajo: reglamento
		JPanel abajo = new JPanel(new GridBagLayout());
		abajo.setBackground(Color.WHITE);
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		abajo.add(reglamentoL, auxConstraints);
		
		auxConstraints.gridy = 1;
		abajo.add(reglamento, auxConstraints);
		
		infoConstraints.gridx = 0;
		infoConstraints.gridy = 1;
		
		camposPanel.add(arriba, infoConstraints);
		
		infoConstraints.gridy = 2;
		infoConstraints.anchor= GridBagConstraints.WEST;
		camposPanel.add(medio, infoConstraints);
		
		infoConstraints.anchor= GridBagConstraints.CENTER;
		infoConstraints.gridy = 3;
		
		camposPanel.add(abajo, infoConstraints);
		
		//Panel lugar de realizacion y boton aceptar
		lugarPanel = new JPanel();
		lugarPanel.setLayout(new GridBagLayout());
		lugarPanel.setBackground(Color.WHITE);
		final List<List<String>> lugares = new ArrayList<List<String>>();
		GridBagConstraints lugarConstraints = new GridBagConstraints();
		lugarConstraints.insets = new Insets(10, 3, 10, 3);
		lugarConstraints.fill = GridBagConstraints.WEST;
		
		//Panel buscar Lugar de realizacion
		JPanel lugarDeRealizacion = new JPanel(new GridBagLayout());
		lugarDeRealizacion.setBackground(Color.GRAY);
		lugarDeRealizacion.setMinimumSize(new Dimension(500, 150));
		lugarDeRealizacion.setMaximumSize(new Dimension(500, 150));
		lugarDeRealizacion.setPreferredSize(new Dimension(500, 150));
		
		JLabel buscarLabel = new JLabel("Lugar");
		JLabel cantidad = new JLabel("Cantidad de Encuentros");
		
		final JTextField buscar = new JTextField(150);
		buscar.setMinimumSize(new Dimension(150, 20));
		
		final JSpinner encuentros = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		encuentros.setBounds(100, 202, 30, 20);
		
	    JButton agregar = new JButton("+");
	    agregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(buscar.getText() != null && (Integer) encuentros.getValue() > 0) {
					List<String> aux = new ArrayList<String>();
					aux.add(buscar.getText().toString());
					aux.add( encuentros.getValue().toString());
					lugares.add(aux);			
					buscar.setText("");
					encuentros.setValue(0);
					actualizarTablaLugar(lugares);
				}

			}
	    	
	    });
		
		auxConstraints.gridx = 1;
		auxConstraints.gridy = 0;
		lugarDeRealizacion.add(buscarLabel, auxConstraints);
		
		auxConstraints.gridy = 1;
		lugarDeRealizacion.add(buscar, auxConstraints);
		
		auxConstraints.gridy = 3;
		lugarDeRealizacion.add(cantidad, auxConstraints);
		
		auxConstraints.gridy = 4;
		lugarDeRealizacion.add(encuentros, auxConstraints);
		
		auxConstraints.gridy = 2;
		auxConstraints.gridx = 3;
		lugarDeRealizacion.add(agregar, auxConstraints);
		
		lugarConstraints.gridy = 0;
		lugarConstraints.gridx = 1;
		
		lugarPanel.add(lugarDeRealizacion, lugarConstraints);
		
		//Panel tabla con lugares de realizacion seleccionados
		//Crear tabla
		tablaLugares = new JTable(modeloLugar);
		tablaLugares.setBackground(Color.WHITE);
		lugarPanel.add(tablaLugares);
		actualizarTablaLugar(lugares);
		tablaLugares.setDefaultEditor(Object.class, null);
		
		JScrollPane tableSP = new JScrollPane(tablaLugares);
		tableSP.setBackground(Color.WHITE);
		tableSP.setMinimumSize(new Dimension(500, 300));
		
		
		eliminar = new JButton("X");
		eliminar.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int renglonSeleccionado = tablaLugares.getSelectedRow();
				String lugar = tablaLugares.getValueAt(renglonSeleccionado, 0).toString();
				String encuentro = tablaLugares.getValueAt(renglonSeleccionado, 1).toString();
				//borrarlo de la lista y llamar a actualizar tabla
				boolean borrado = false;
				int i = 0;
				while(!borrado) {
					if( lugares.get(i).get(0).equals(lugar) && lugares.get(i).get(1).equals(encuentro)) {
						lugares.remove(i);
						borrado = true;
					}
					i++;
				}
				actualizarTablaLugar(lugares);
			}
			
		});
		
		JPanel panelTabla = new JPanel(new GridBagLayout());
		panelTabla.setBackground(Color.WHITE);
	    GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    panelTabla.add(tableSP, constraintsTabla);
		
		lugarConstraints.gridy = 1;
		
	    lugarPanel.add(panelTabla, lugarConstraints);
	    
	    //boton aceptar
	    JButton aceptar = new JButton("Aceptar");
	    aceptar.setMinimumSize(new Dimension(500, 50));
	    aceptar.setPreferredSize(new Dimension(500,50));
	    aceptar.setMaximumSize(new Dimension(500, 50));
	    
	    aceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//crear DTO
				if(		nombreTexto.getText().toString() == null ||
						deporteBox.getSelectedItem().toString().equals(" ") ||
						modalidadBox.getSelectedItem().toString().equals(" ") ||
						puntuacionBox.getSelectedItem().toString().equals(" ") ||
						lugares.size() == 0 ||
						puntuacionBox.getSelectedItem().toString().equals("Puntuación") && (Integer) puntosAu.getValue() == 0 ||
						puntuacionBox.getSelectedItem().toString().equals("Sets") && (Integer) cantMaxSets.getValue() == 0 ||
						modalidadBox.getSelectedItem().toString().equals("Sistema de Liga") && ((Integer) puntosG.getValue() == 0 || (Integer) puntosPorPresentarse.getValue() == 0 || (empate.isSelected() && (Integer) puntosEmp.getValue() == 0))) {
					
					JOptionPane.showMessageDialog(new JPanel(), "Error: Uno o más de los campos se encuentra vacío", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else {
					//mandar al gestor
				}					
			}
	    	
	    });
		
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
	    
	    infoPanel.setForeground(Color.WHITE);
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
		
		this.setBackground(Color.WHITE);
		altaCompetenciaFrame.add(this);
		altaCompetenciaFrame.setBackground(Color.WHITE);
	}
	
	public void actualizarTablaLugar(final List<List<String>> lugares) {
		//crear modelo tabla
				String[] columnas = { "Establecimientos", "Encuentros", "Eliminar"};
				modeloLugar = new DefaultTableModel(columnas, 0);		

				for(List<String> lug: lugares) {
					Object[] renglon = { lug.get(0), lug.get(1)};
					modeloLugar.addRow(renglon);
				}
				
				//actualizar modelo
				tablaLugares.setModel(modeloLugar);
				tablaLugares.getColumn("Eliminar").setCellRenderer(new ButtonRenderer());
				tablaLugares.getColumn("Eliminar").setCellEditor(new ButtonEditor(new JCheckBox()));
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				  try {
					  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					  
				  }  
			    catch (UnsupportedLookAndFeelException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (ClassNotFoundException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (InstantiationException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
			    catch (IllegalAccessException e) {
			    	e.printStackTrace();
			       // handle exception
			    }
				  
				  EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								AltaCompetencia ventana = new AltaCompetencia();
								ventana.altaCompetenciaFrame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				System.out.println("app creada");
			}
		});
}
}
