package test;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
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
import java.util.stream.Collectors;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class AltaCompetencia {//extends JPanel


	public class CardLayoutDemo extends JPanel implements ItemListener {
	    private JPanel cards; //a panel that uses CardLayout
	    JPanel primero;
	    
	    public CardLayoutDemo(JLabel etiquetaBox, List<JPanel> paneles, String[] nombres, JComboBox<String> box, boolean axis) {
	        if(axis) {
	        	this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	        }
	        else {
	        	this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
	        }
	        
	        primero = new JPanel(new GridBagLayout());
	        
			GridBagConstraints auxConstraints = new GridBagConstraints();
			auxConstraints.insets = new Insets(3, 3, 3, 3);
			auxConstraints.fill = GridBagConstraints.WEST;
			
			auxConstraints.gridx = 1;
			auxConstraints.gridy = 0;
			primero.add(etiquetaBox, auxConstraints);
			
	    	box.addItemListener(this);
	    	
			auxConstraints.gridy = 1;
			primero.add(box, auxConstraints);

	        //Create the panel that contains the "cards".
	        cards = new JPanel(new CardLayout(0,0));
	        cards.add(new JPanel(), " ");
	        
	        for(int i = 0; i < paneles.size(); i++) {
	        	cards.add(paneles.get(i), nombres[i]);
	        }
	       
	        this.add(primero);
	        this.add(cards);
	    }
	    
	    public void itemStateChanged(ItemEvent evt) {
	        CardLayout cl = (CardLayout)(cards.getLayout());
	        cl.show(cards, (String)evt.getItem());
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
	
	//temporal
	private JPanel alta;
	
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
			
		alta = new JPanel();
		alta.setLayout(new BoxLayout(alta, BoxLayout.PAGE_AXIS));

		//Panel del titulo
		
		tituloPanel = new JPanel(new GridBagLayout());
		tituloPanel.setMinimumSize(new Dimension(1200, 100));
		GridBagConstraints volverConstraints = new GridBagConstraints();
		volverConstraints.insets = new Insets(10, 10, 10, 10);
		volverConstraints.fill = GridBagConstraints.WEST;
		volverConstraints.gridx = 1;
		volverConstraints.gridy = 0;
		JButton volver = new JButton("Volver");
		volver.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				//cl.show(frameTrabajoPractico.getContentPane(), "card__MainMenu");
			}
			
		});
		tituloPanel.add(volver, volverConstraints);
		
		JLabel titulo = new JLabel("Nueva Competencia");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		volverConstraints.gridx = 5;
		tituloPanel.add(titulo, volverConstraints);
		
		//Panel de la info
		infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));

		
		//Panel de los campos
		camposPanel = new JPanel(new GridBagLayout());
		GridBagConstraints infoConstraints = new GridBagConstraints();
		infoConstraints.insets = new Insets(3, 10, 3, 3);
		infoConstraints.fill = GridBagConstraints.WEST;
		
		JLabel nombre = new JLabel("Nombre de la Competencia");
		JLabel deporte = new JLabel("Deporte Asociado");
		JLabel modalidad = new JLabel("Modalidad de la Competencia");
		JLabel formaP = new JLabel("Forma de Puntuación");
		JLabel reglamentoL = new JLabel("Reglamento");
		JLabel cantidadMaxSets = new JLabel("Cantidad Máxima Sets");
		JLabel puntosAusente = new JLabel("Puntos si Rival Ausente");
		JLabel puntosGanado = new JLabel("Puntos por Partido Ganado");
		JLabel puntosPresentarse = new JLabel("Puntos por Presentarse");
		JLabel puntosEmpate = new JLabel("Puntos Empate");
		
		JTextField nombreTexto = new JTextField(150);
		nombreTexto.setMinimumSize(new Dimension(150, 20));
		nombreTexto.setMaximumSize(new Dimension(150, 20));
		nombreTexto.setPreferredSize(new Dimension(150, 20));
		JComboBox<String> deporteBox = new JComboBox<String>(); //Pedir al gestor de deporte
		deporteBox.addItem(" ");
		deporteBox.setMinimumSize(new Dimension(150, 20));
		deporteBox.setMaximumSize(new Dimension(150, 20));
		deporteBox.setPreferredSize(new Dimension(150, 20));
		JComboBox<String> modalidadBox = new JComboBox<String>(arregloM);
		modalidadBox.setMinimumSize(new Dimension(150, 20));
		modalidadBox.setMaximumSize(new Dimension(150, 20));
		modalidadBox.setPreferredSize(new Dimension(150, 20));
		JComboBox<String> puntuacionBox = new JComboBox<String>(arregloP);
		puntuacionBox.setMinimumSize(new Dimension(150, 20));
		puntuacionBox.setMaximumSize(new Dimension(150, 20));
		puntuacionBox.setPreferredSize(new Dimension(150, 20));
		
		JSpinner puntosAu = new JSpinner();
		puntosAu.setMinimumSize(new Dimension (30, 20));
		JSpinner cantMaxSets = new JSpinner();
		puntosAu.setMinimumSize(new Dimension (30, 20));
		JSpinner puntosG = new JSpinner();
		puntosAu.setMinimumSize(new Dimension (30, 20));
		JSpinner puntosPorPresentarse = new JSpinner();
		puntosAu.setMinimumSize(new Dimension (30, 20));
		JSpinner puntosEmp = new JSpinner();
		puntosAu.setMinimumSize(new Dimension (30, 20));
		
		JCheckBox empate = new JCheckBox("Empate Permitido");
		empate.setBounds(16, 23, 97, 23);
		
		JTextPane reglamento = new JTextPane();
		reglamento.setMinimumSize(new Dimension(150, 350));
		
		//parte de arriba
		JPanel arriba = new JPanel();
		arriba.setLayout(new BoxLayout(arriba, BoxLayout.LINE_AXIS));
		
		JPanel auxArriba = new JPanel(new GridBagLayout());
		GridBagConstraints auxConstraints = new GridBagConstraints();
		auxConstraints.insets = new Insets(3, 3, 3, 3);
		auxConstraints.fill = GridBagConstraints.WEST;
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		auxArriba.add(nombre, auxConstraints);
		
		auxConstraints.gridy = 1;
		auxArriba.add(nombreTexto, auxConstraints);
		
		auxConstraints.gridy = 3;
		auxArriba.add(deporte, auxConstraints);
		
		auxConstraints.gridy = 4;
		auxArriba.add(deporteBox, auxConstraints);
		
		arriba.add(auxArriba);
		
		//parte de la puntuacion	
	    JPanel sets = new JPanel(new GridBagLayout());
	    JPanel puntuacion = new JPanel(new GridBagLayout());
	    JPanel resultadofinal = new JPanel();

		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
	    sets.add(cantidadMaxSets, auxConstraints);
	        
		auxConstraints.gridy = 1;
	    sets.add(cantMaxSets, auxConstraints);
	    
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
	    puntuacion.add(puntosAusente, auxConstraints);
	        
		auxConstraints.gridy = 1;
	    puntuacion.add(puntosAu, auxConstraints);
	        
	    List<JPanel> panelesPun = new ArrayList<JPanel>();
	    panelesPun.add(sets);
	    panelesPun.add(puntuacion);
	    panelesPun.add(resultadofinal);
	    String[] nombresPun = {"Sets", "Puntuación", "Resultado Final"};
	    
	    CardLayoutDemo auxPunt = new CardLayoutDemo(formaP, panelesPun, nombresPun, puntuacionBox, true);
		arriba.add(auxPunt);
		
		//parte de abajo		
	    JPanel sistemadeliga = new JPanel(new GridBagLayout());
	    JPanel sistemadeeliminatoriasimple = new JPanel();
	    JPanel sistemadeeliminatoriadoble = new JPanel();
	      
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		sistemadeliga.add(puntosGanado, auxConstraints);
	        
		auxConstraints.gridy = 1;
		sistemadeliga.add(puntosG, auxConstraints);
	    
		auxConstraints.gridy = 2;
		sistemadeliga.add(puntosPresentarse, auxConstraints);
	        
		auxConstraints.gridy = 3;
		sistemadeliga.add(puntosPorPresentarse, auxConstraints);

		auxConstraints.gridy = 4;
		sistemadeliga.add(empate, auxConstraints);
	    
		auxConstraints.gridx = 2;
		auxConstraints.gridy = 3;
		sistemadeliga.add(puntosEmpate, auxConstraints);
	        
		auxConstraints.gridy = 4;
		sistemadeliga.add(puntosEmp, auxConstraints);
		
		List<JPanel> panelesMod = new ArrayList<JPanel>();
		panelesMod.add(sistemadeliga);
		panelesMod.add(sistemadeeliminatoriasimple);
		panelesMod.add(sistemadeeliminatoriadoble);
		
		String[] nombresMod = {"Sistema de Liga", "Sistema de Eliminatoria Simple", "Sistema de Eliminatoria Doble"};
		
		JPanel medio = new CardLayoutDemo(modalidad, panelesMod, nombresMod, modalidadBox, false);
		
		JPanel abajo = new JPanel(new GridBagLayout());
		
		auxConstraints.gridx = 0;
		auxConstraints.gridy = 0;
		abajo.add(reglamentoL, auxConstraints);
		
		auxConstraints.gridy = 1;
		abajo.add(reglamento, auxConstraints);
		
		infoConstraints.gridx = 0;
		infoConstraints.gridy = 1;
		
		camposPanel.add(auxArriba, infoConstraints);
		
		infoConstraints.gridx = 2;
		
		camposPanel.add(auxPunt, infoConstraints);
		
		infoConstraints.gridx = 1;
		infoConstraints.gridy = 2;
		
		camposPanel.add(medio, infoConstraints);
		
		infoConstraints.gridy = 3;
		
		camposPanel.add(abajo, infoConstraints);
		
		//lugar de realizacion y boton aceptar
		lugarPanel = new JPanel();
		lugarPanel.setLayout(new GridBagLayout());
		List<String[]> lugares = new ArrayList<String[]>();
		GridBagConstraints lugarConstraints = new GridBagConstraints();
		lugarConstraints.insets = new Insets(10, 10, 10, 50);
		lugarConstraints.fill = GridBagConstraints.WEST;
		
		//Lugar de realizacion
		JPanel lugarDeRealizacion = new JPanel(new GridBagLayout());
		lugarDeRealizacion.setBackground(Color.GRAY);
		lugarDeRealizacion.setMinimumSize(new Dimension(400, 150));
		lugarDeRealizacion.setMaximumSize(new Dimension(400, 150));
		lugarDeRealizacion.setPreferredSize(new Dimension(400, 150));
		
		JLabel buscarLabel = new JLabel("Lugar");
		JLabel cantidad = new JLabel("Cantidad de Encuentros");
		
		JTextField buscar = new JTextField(150);
		buscar.setMinimumSize(new Dimension(150, 20));
		
		JSpinner encuentros = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1));
		encuentros.setBounds(100, 202, 30, 20);
		
	    JButton agregar = new JButton("+");
	    agregar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(buscar.getText() != null && (Integer) encuentros.getValue() > 0) {
					String[] aux = {buscar.getText().toString(), encuentros.getValue().toString()};
					lugares.add(aux);
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
		
		//Crear tabla
		tablaLugares = new JTable(modeloLugar);
		lugarPanel.add(tablaLugares);
		actualizarTablaLugar(lugares);
		tablaLugares.setDefaultEditor(Object.class, null);
		
		JScrollPane tableSP = new JScrollPane(tablaLugares);
		tableSP.setMinimumSize(new Dimension(400, 300));
		
		JPanel panelTabla = new JPanel(new GridBagLayout());
	    GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
	    panelTabla.add(tableSP, constraintsTabla);
		
		lugarConstraints.gridy = 2;
		
	    lugarPanel.add(panelTabla, lugarConstraints);
	    
	    //boton aceptar
	    JButton aceptar = new JButton("Aceptar");
	    aceptar.setMinimumSize(new Dimension(400, 50));
	    aceptar.setPreferredSize(new Dimension(400,50));
	    aceptar.setMaximumSize(new Dimension(400, 50));
	    
	    aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//crear DTO
				if(		nombreTexto.getText().toString() == null ||
						deporteBox.getSelectedItem().toString().equals(" ") ||
						modalidadBox.getSelectedItem().toString().equals(" ") ||
						puntuacionBox.getSelectedItem().toString().equals(" ") ||
						lugares.size() == 0 ||
						puntuacionBox.getSelectedItem().toString().equals("Puntuación") && (Integer) puntosAu.getValue() == 0 ||
						puntuacionBox.getSelectedItem().toString().equals("Sets") && (Integer) cantMaxSets.getValue() == 0 ||
						modalidadBox.getSelectedItem().toString().equals("Sistema de Liga") && ((Integer) puntosG.getValue() == 0 || (Integer) puntosPorPresentarse.getValue() == 0 || (Integer) puntosEmp.getValue() == 0)) {
					
					JOptionPane.showMessageDialog(alta, "Error: Uno o más de los campos se encuentra vacío", "Error", JOptionPane.ERROR_MESSAGE);					
				}
				else {
					//mandar al gestor
				}					
			}
	    	
	    });
		
		lugarConstraints.gridy = 5;
		
	    lugarPanel.add(aceptar, lugarConstraints);
	    
		alta.add(tituloPanel);
		infoPanel.add(camposPanel, BorderLayout.WEST);
		infoPanel.add(lugarPanel, BorderLayout.EAST);
		alta.add(infoPanel, BorderLayout.CENTER);
		JPanel aux = new JPanel();
		aux.setMinimumSize(new Dimension(1200, 50));
		aux.setMaximumSize(new Dimension(1200, 50));
		aux.setPreferredSize(new Dimension(1200, 50));
		JPanel aux1 = new JPanel();
		aux1.setMinimumSize(new Dimension(50, 600));
		aux1.setMaximumSize(new Dimension(50, 600));
		aux1.setPreferredSize(new Dimension(50, 600));
		JPanel aux2 = new JPanel();
		aux2.setMinimumSize(new Dimension(50, 600));
		aux2.setMaximumSize(new Dimension(50, 600));
		aux2.setPreferredSize(new Dimension(50, 600));
		alta.add(aux, BorderLayout.SOUTH);
		alta.add(aux1, BorderLayout.EAST);
		alta.add(aux2, BorderLayout.WEST);
		altaCompetenciaFrame.add(alta);
	}
	
	public void actualizarTablaLugar(List<String[]> lugares) {
		//crear modelo tabla
				String[] columnas = {"Eliminar", "Establecimientos", "Encuentros"};
				modeloLugar = new DefaultTableModel(columnas, 0);		

				JButton eliminar = new JButton("X");
				eliminar.addActionListener( new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int renglonSeleccionado = tablaLugares.getSelectedRow();
						String lugar = tablaLugares.getComponentAt(renglonSeleccionado, 1).toString();
						String encuentro = tablaLugares.getComponentAt(renglonSeleccionado, 2).toString();
						//borrarlo de la lista y llamar a actualizar tabla
						lugares.remove(lugares.stream().filter(z -> z[0].equals(lugar) && z[1].equals(encuentro)).collect(Collectors.toList()).get(0));
						actualizarTablaLugar(lugares);
					}
					
				});
				
				for(int i = 0; i < lugares.size(); i++) {
					Object[] renglon = {eliminar, lugares.get(i)[0], lugares.get(i)[1]};
					modeloLugar.addRow(renglon);
				}


				//actualizar modelo
				tablaLugares.setModel(modeloLugar);
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
