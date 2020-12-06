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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dominio.Competencia.Estado;
import dto.CompetenciaDTO;
import dto.EncuentroDTO;
import dto.ParticipanteDTO;
import exceptions.EstadoCompetenciaException;
import exceptions.ReservasInsuficientesException;
import gestor.GestorCompetencia;

@SuppressWarnings("serial")
public class VerCompetencia extends JPanel {

	private JPanel datosPanel;
	private JPanel botonesPanel;
	private JPanel tablasPanel;
	private JTable tablaParticipantes;
	private JTable tablaEncuentros;
	private DefaultTableModel modeloParticipantes;
	private DefaultTableModel modeloEncuentros;
	private JPanel tpPanel;
	private GestorCompetencia gestorCompetencia;
	private List<Object> filtros;
	
	public VerCompetencia(CompetenciaDTO competencia, JPanel tp, List<Object> filtros) {
		this.tpPanel = tp;
		this.gestorCompetencia = new GestorCompetencia();
		this.filtros = filtros;
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		armarPantalla(competencia);
	}
	
	private void armarPantalla( CompetenciaDTO competencia ) {
		 
		//Armar Pantalla
		
		//Etiquetas
		JLabel nombreCompetencia = new JLabel( competencia.getNombre());
		JLabel modalidadCompetencia = new JLabel("Modalidad: " + competencia.getTipoSistemaDeCompetencia().toString().replace("_", " "));
		JLabel deporteCompetencia = new JLabel("Deporte: " + competencia.getDeporteDeCompetencia());
		JLabel estadoCompetencia = new JLabel("Estado: " + competencia.getEstadoCompetencia().toString().replace("_", " "));
		
		//Botones
		JButton modificar = new JButton("Modificar");
		JButton eliminar = new JButton("Eliminar");
		JButton generarFixture = new JButton("Generar Fixture");
		JButton verFixture = new JButton("Ver Fixture");
		JButton verTablaPosiciones = new JButton("Ver Tabla de Posiciones");
		JButton verParticipantes = new JButton("Ver Participantes");
		JButton volver = new JButton();
		
		if(competencia.getEstadoCompetencia() == Estado.EN_DISPUTA || competencia.getEstadoCompetencia() == Estado.FINALIZADA ) {
			generarFixture.setEnabled(false);
			modificar.setEnabled(false);
		}
		
		//Fuentes
		Font titulo = new Font("Tahoma", Font.PLAIN, 30);
		Font etiquetasSecundarias = new Font("Tahoma", Font.PLAIN, 20);
		Font botones = new Font("Tahoma", Font.PLAIN, 14);
		
		nombreCompetencia.setFont(titulo);
		modalidadCompetencia.setFont(etiquetasSecundarias);
		deporteCompetencia.setFont(etiquetasSecundarias);
		estadoCompetencia.setFont(etiquetasSecundarias);
		
		modificar.setFont(botones);
		eliminar.setFont(botones);
		generarFixture.setFont(botones);
		verFixture.setFont(botones);
		verTablaPosiciones.setFont(botones);
		verParticipantes.setFont(botones);
		
		//Dimensiones
		Dimension etiquetas = new Dimension(200, 30);
		Dimension botonesArriba = new Dimension(100, 40);
		Dimension botonesAbajo = new Dimension(200, 40);
		
		nombreCompetencia.setMinimumSize(etiquetas);
		nombreCompetencia.setMaximumSize(etiquetas);
		nombreCompetencia.setPreferredSize(etiquetas);
		
		modalidadCompetencia.setMinimumSize(etiquetas);
		modalidadCompetencia.setMaximumSize(etiquetas);
		modalidadCompetencia.setPreferredSize(etiquetas);
		
		deporteCompetencia.setMinimumSize(etiquetas);
		deporteCompetencia.setMaximumSize(etiquetas);
		deporteCompetencia.setPreferredSize(etiquetas);
		
		estadoCompetencia.setMinimumSize(etiquetas);
		estadoCompetencia.setMaximumSize(etiquetas);
		estadoCompetencia.setPreferredSize(etiquetas);
		
		modificar.setMinimumSize(botonesArriba);
		modificar.setMaximumSize(botonesArriba);
		modificar.setPreferredSize(botonesArriba);
		
		eliminar.setMinimumSize(botonesArriba);
		eliminar.setMaximumSize(botonesArriba);
		eliminar.setPreferredSize(botonesArriba);
		
		generarFixture.setMinimumSize(botonesAbajo);
		generarFixture.setMaximumSize(botonesAbajo);
		generarFixture.setPreferredSize(botonesAbajo);
		
		verFixture.setMinimumSize(botonesAbajo);
		verFixture.setMaximumSize(botonesAbajo);
		verFixture.setPreferredSize(botonesAbajo);
		
		verTablaPosiciones.setMinimumSize(botonesAbajo);
		verTablaPosiciones.setMaximumSize(botonesAbajo);
		verTablaPosiciones.setPreferredSize(botonesAbajo);
		
		verParticipantes.setMinimumSize(botonesAbajo);
		verParticipantes.setMaximumSize(botonesAbajo);
		verParticipantes.setPreferredSize(botonesAbajo);

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
		
		//panel datos
		datosPanel = new JPanel();
		datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.LINE_AXIS));
		datosPanel.setMinimumSize(new Dimension(1200, 75));
		datosPanel.setBackground(Color.WHITE);
		
		JPanel auxNombres = new JPanel(new GridBagLayout());
		JPanel auxRelleno = new JPanel();
		JPanel auxRelleno0 = new JPanel();
		JPanel auxBotones = new JPanel(new GridBagLayout());
		
		auxNombres.setBackground(Color.WHITE);
		
		auxRelleno.setMinimumSize(new Dimension(400, 40));
		auxRelleno.setMaximumSize(new Dimension(400, 40));
		auxRelleno.setPreferredSize(new Dimension(400, 40));
		auxRelleno.setBackground(Color.WHITE);
		
		auxRelleno0.setMinimumSize(new Dimension(40, 40));
		auxRelleno0.setMaximumSize(new Dimension(40, 40));
		auxRelleno0.setPreferredSize(new Dimension(40, 40));
		auxRelleno0.setBackground(Color.WHITE);
		
		auxBotones.setBackground(Color.WHITE);
		
		//auxNombres
		auxNombres.add(volver, constraints);
		
		constraints.gridx = 1;
		auxNombres.add(nombreCompetencia, constraints);
		
		constraints.gridy = 1;
		auxNombres.add(modalidadCompetencia, constraints);
		
		constraints.gridy = 2;
		auxNombres.add(deporteCompetencia, constraints);
		
		constraints.gridy = 3;
		auxNombres.add(estadoCompetencia, constraints);
		
		//auxBotones
		constraints.gridy = 2;
		constraints.gridx = 0;
		
		auxBotones.add(modificar, constraints);
		
		constraints.gridy = 3;
		auxBotones.add(eliminar, constraints);
		
		datosPanel.add(auxNombres);
		datosPanel.add(auxRelleno);
		datosPanel.add(auxBotones);
		datosPanel.add(auxRelleno0);
		
		//Panel tablas
		tablasPanel = new JPanel();
		tablasPanel.setLayout(new BoxLayout(tablasPanel, BoxLayout.LINE_AXIS));
		tablasPanel.setBackground(Color.WHITE);
		
		//Tamaño tablas
		Dimension tamTablaP = new Dimension(250, 400);
		Dimension tamTablaE = new Dimension(700, 400);
		
		//Constraints tablas
		GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
		
		//Tabla Participantes
		JPanel auxTablaP = new JPanel();
		auxTablaP.setBackground(Color.WHITE);	
		tablaParticipantes = new JTable(modeloParticipantes){
		    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, extend, extend);
		    }
		};
		
		
		//Header
		tablaParticipantes.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaParticipantes.getTableHeader().setEnabled(false);
		tablaParticipantes.getTableHeader().setBackground(new Color(42, 224, 187));
		tablaParticipantes.getTableHeader().setOpaque(false);
		tablaParticipantes.getTableHeader().setMinimumSize(new Dimension(250, 50));
		tablaParticipantes.getTableHeader().setMaximumSize(new Dimension(250, 50));
		tablaParticipantes.getTableHeader().setPreferredSize(new Dimension(250, 50));
		((DefaultTableCellRenderer)tablaParticipantes.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		
		//Table Renderer
		DefaultTableCellRenderer cr = new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        c.setBackground(row % 2 == 0 ? new Color(187, 255, 241) : Color.WHITE);
		        if(isSelected) c.setBackground(Color.BLUE);
		        return c;
		    }
		};
		cr.setHorizontalAlignment(JLabel.CENTER);
		
		//Configuracion tabla
		tablaParticipantes.setDefaultRenderer(Object.class, cr);
		tablaParticipantes.setMinimumSize(tamTablaP);
		tablaParticipantes.setMaximumSize(tamTablaP);
		tablaParticipantes.setPreferredSize(tamTablaP);
		tablaParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaParticipantes.setRowHeight(40);
		
		actualizarTablaParticipantes(competencia.getParticipantes());
		auxTablaP.add(tablaParticipantes);
		tablaParticipantes.setDefaultEditor(Object.class, null);
		
		//Scroll Pane
		JScrollPane tableSP = new JScrollPane(tablaParticipantes);
		tableSP.setMinimumSize(tamTablaP);
		tableSP.setMaximumSize(tamTablaP);
		tableSP.setPreferredSize(tamTablaP);
		tableSP.setBackground(new Color(42, 224, 187));
	   
	    auxTablaP.add(tableSP, constraintsTabla);
	    
	    //Tabla Proximos Encuentros
		JPanel auxTablaE = new JPanel();
		auxTablaE.setBackground(Color.WHITE);	
		tablaEncuentros = new JTable(modeloEncuentros){
		    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
		    {
		        super.changeSelection(rowIndex, columnIndex, extend, extend);
		    }
		};
		
		
		//Header
		tablaEncuentros.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaEncuentros.getTableHeader().setEnabled(false);
		tablaEncuentros.getTableHeader().setBackground(new Color(42, 224, 187));
		tablaEncuentros.getTableHeader().setOpaque(false);
		tablaEncuentros.getTableHeader().setMinimumSize(new Dimension(700, 50));
		tablaEncuentros.getTableHeader().setMaximumSize(new Dimension(700, 50));
		tablaEncuentros.getTableHeader().setPreferredSize(new Dimension(700, 50));
		
		//Configuracion Tabla
		tablaEncuentros.setDefaultRenderer(Object.class, cr);
		((DefaultTableCellRenderer)tablaEncuentros.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
		tablaEncuentros.setMinimumSize(tamTablaE);
		tablaEncuentros.setMaximumSize(tamTablaE);
		tablaEncuentros.setPreferredSize(tamTablaE);
		tablaEncuentros.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaEncuentros.setRowHeight(40);
		
		List<EncuentroDTO> proximosEncuentros = gestorCompetencia.obtenerProximosEncuentros(competencia); 
		
		actualizarTablaEncuentros(proximosEncuentros);
		auxTablaE.add(tablaEncuentros);
		tablaEncuentros.setDefaultEditor(Object.class, null);		
	    
		//Scroll Pane
		JScrollPane tableSPE = new JScrollPane(tablaEncuentros);
		tableSPE.setMinimumSize(tamTablaE);
		tableSPE.setMaximumSize(tamTablaE);
		tableSPE.setPreferredSize(tamTablaE);		
		tableSPE.setBackground(new Color(42, 224, 187));
		
		auxTablaE.add(tableSPE, constraintsTabla);
	    
	    //-----------------------------//
	    JPanel relleno1 = new JPanel();
	    JPanel relleno2 = new JPanel();
	    relleno1.setBackground(Color.WHITE);
	    relleno2.setBackground(Color.WHITE);
	    
	    tablasPanel.add(relleno1);
	    tablasPanel.add(auxTablaP);
	    tablasPanel.add(auxTablaE);
	    tablasPanel.add(relleno2);
	    //-----------------------------//
	    
	    //Panel Botones
	    botonesPanel = new JPanel(new GridBagLayout());
	    botonesPanel.setBackground(Color.WHITE);
	    
	    //Paneles de relleno
	    JPanel auxRelleno1 = new JPanel();
	    JPanel auxRelleno2 = new JPanel();
	    JPanel auxRelleno3 = new JPanel();
	    
	    Dimension rellenos = new Dimension(36, 30);
	    
		auxRelleno1.setMinimumSize(rellenos);
		auxRelleno1.setMaximumSize(rellenos);
		auxRelleno1.setPreferredSize(rellenos);
		auxRelleno1.setBackground(Color.WHITE);
		
		auxRelleno2.setMinimumSize(rellenos);
		auxRelleno2.setMaximumSize(rellenos);
		auxRelleno2.setPreferredSize(rellenos);
		auxRelleno2.setBackground(Color.WHITE);
		
		auxRelleno3.setMinimumSize(rellenos);
		auxRelleno3.setMaximumSize(rellenos);
		auxRelleno3.setPreferredSize(rellenos);
		auxRelleno3.setBackground(Color.WHITE);
		
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.fill = GridBagConstraints.WEST;
		
		constraints.gridy = 0;
		constraints.gridx = 0;
		botonesPanel.add(generarFixture, constraints);
		
		constraints.gridx = 1;
		botonesPanel.add(auxRelleno1, constraints);
		
		constraints.gridx = 2;
		botonesPanel.add(verFixture, constraints);
		
		constraints.gridx = 3;
		botonesPanel.add(auxRelleno2, constraints);
		
		constraints.gridx = 4;
		botonesPanel.add(verTablaPosiciones, constraints);
		
		constraints.gridx = 5;
		botonesPanel.add(auxRelleno3, constraints);

		constraints.gridx = 6;
		botonesPanel.add(verParticipantes, constraints);
		
		this.add(datosPanel);
		this.add(tablasPanel);
		this.add(botonesPanel);
		
		//Funcion de botones
		volver.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
			
		});
		
		modificar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), "Modalidad aún no implementada", " ", JOptionPane.INFORMATION_MESSAGE);
			}
		
		});
		
		eliminar.addActionListener( new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(new JPanel(), "La competencia se eliminará permanentemente", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(ok == JOptionPane.OK_OPTION && gestorCompetencia.eliminarCompetencia(competencia)) {
					JOptionPane.showMessageDialog(new JPanel(), "La competencia se ha eliminado exitosamente", " ", JOptionPane.INFORMATION_MESSAGE);
					competenciaEliminada(filtros);
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
				if(ok == JOptionPane.OK_OPTION) {
					try {
						gestorCompetencia.generarFixture(competencia);
						JOptionPane.showMessageDialog(new JPanel(), "El Fixture se ha creado exitosamente", " ", JOptionPane.INFORMATION_MESSAGE);
						competenciaModificada(competencia);
					}
					catch(EstadoCompetenciaException e1) {
						JOptionPane.showMessageDialog(new JPanel(), "La Competencia se encuentra en Estado: " + competencia.getEstadoCompetencia() + "\nNo se puede generar un nuevo Fixture", "Error", JOptionPane.ERROR_MESSAGE);
					}
					catch(ReservasInsuficientesException e2) {
						JOptionPane.showMessageDialog(new JPanel(), "Los lugares de realización se encuentran llenos\nPruebe más tarde", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
	
		verFixture.addActionListener( new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), "Modalidad aún no implementada", " ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
	
		verTablaPosiciones.addActionListener( new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), "Modalidad aún no implementada", " ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
	
		verParticipantes.addActionListener( new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel verParticipantesPanel = new ListarParticipantesCompetencia(tpPanel, competencia);
				tpPanel.add(verParticipantesPanel, "ListarParticipantesCompetencia");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "ListarParticipantesCompetencia");
			}
			
		});		
		
		this.addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
                tablaParticipantes.clearSelection();
                tablaEncuentros.clearSelection();
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
		
	}
	
	private void actualizarTablaParticipantes( List<ParticipanteDTO> participantes ) {
		String[] columnas = {"Participante"};
		modeloParticipantes = new DefaultTableModel(columnas, 0);
		
		for(ParticipanteDTO participante: participantes) {
			Object[] renglon = { participante.getNombre()};
			modeloParticipantes.addRow(renglon);
		}
		
		//actualizar modelo
		tablaParticipantes.setModel(modeloParticipantes);
	}
	
	private void actualizarTablaEncuentros( List<EncuentroDTO> proximosEncuentros) {
		String[] columnas = {"Etapa", "Local", "Fecha y Hora", "Visitante"};
		modeloEncuentros = new DefaultTableModel(columnas, 0);	
		 
		for(EncuentroDTO encuentro: proximosEncuentros) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			String fecha = formato.format( encuentro.getFecha() );
			Object[] renglon = { encuentro.getEtapa(), encuentro.getParticipante1(), fecha, encuentro.getParticipante2()};
			modeloEncuentros.addRow(renglon);
			
		}
		
		//actualizar modelo
		tablaEncuentros.setModel(modeloEncuentros);
	}
	
	private void volver() {
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.previous(tpPanel);
		tpPanel.remove(this);
	}
	
	private void competenciaEliminada(List<Object> filtros) {
		tpPanel.remove(tpPanel.getComponentCount() - 2);
		JPanel listarCompetenciasPanel = new ListarCompetencias(tpPanel, filtros);
		tpPanel.add(listarCompetenciasPanel, "ListarCompetencias");		
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.show(tpPanel, "ListarCompetencias");
		tpPanel.remove(this);
	}
	
	public void competenciaModificada(CompetenciaDTO competenciaDTO) {
		tpPanel.remove(tpPanel.getComponentCount() - 2);
		JPanel listarCompetencias = new ListarCompetencias(tpPanel, filtros);
		tpPanel.add(listarCompetencias, "ListarCompetencias");
		JPanel verCompetencia = new VerCompetencia(competenciaDTO, tpPanel, filtros);
		tpPanel.add(verCompetencia, "VerCompetencia");
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		//ver tema usuario
        layout.show(tpPanel, "VerCompetencia");
        tpPanel.remove(this);
	}
	
}
