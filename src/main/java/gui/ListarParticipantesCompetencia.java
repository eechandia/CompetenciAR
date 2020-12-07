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
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import dto.CompetenciaDTO;
import dto.ParticipanteDTO;
import gestor.GestorParticipante;

@SuppressWarnings("serial")
public class ListarParticipantesCompetencia extends JPanel {

	//Clases auxiliares
	class JTextStateController implements DocumentListener {
		  List<ParticipanteDTO> participantes;
		  JTextField txt;
		  
		  JTextStateController( List<ParticipanteDTO> participantes, JTextField texto) {
		     this.participantes = participantes;
		     this.txt = texto;
		  }

		  public void changedUpdate(DocumentEvent e) {
		    filtrar(e);
		  }

		  public void insertUpdate(DocumentEvent e) {
		    filtrar(e);
		  }

		  public void removeUpdate(DocumentEvent e) {
		    filtrar(e);
		  }

		  public void filtrar(DocumentEvent e) {
		    if(e.getDocument().getLength() > 0) {
		    	actualizarTablaParticipantes(participantes.stream().filter(p -> p.getNombre().contains(txt.getText())).collect(Collectors.toList()));
		    }
		    else {
		    	actualizarTablaParticipantes(participantes);
		    }
		  }
	}
	
	private JPanel filtrosPanel;
	private JPanel tablaPanel;
	private JTable tablaParticipantes;
	private DefaultTableModel modeloParticipantes;
	private JPanel tpPanel;
	private GestorParticipante gestorParticipante;
	
	public ListarParticipantesCompetencia(JPanel tp, CompetenciaDTO competencia) {
		this.tpPanel = tp;
		this.gestorParticipante = new GestorParticipante();
		this.setBackground(Color.WHITE);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		armarPantalla(competencia);
	}
	
	private void armarPantalla(CompetenciaDTO competenciaDTO) {
		
		//Armar Pantalla
		//Etiquetas
		JLabel competencia = new JLabel(competenciaDTO.getNombre());
		JLabel participantes = new JLabel("Participantes");
		
		//Campos de Texto
		JTextField participanteTexto = new JTextField(150);
		
		//Botones
		JButton volver = new JButton();
		JButton agregar = new JButton("Agregar");
		JButton modificar = new JButton("Modificar");
		JButton eliminar = new JButton("Eliminar");
		
		//Fuentes
		Font titulo = new Font("Tahoma", Font.PLAIN, 30);
		Font etiquetasSecundarias = new Font("Tahoma", Font.PLAIN, 20);
		Font botones = new Font("Tahoma", Font.PLAIN, 14);
		Font textos = new Font("Tahoma", Font.PLAIN, 16);
				
		competencia.setFont(titulo);
		participantes.setFont(etiquetasSecundarias);
		participanteTexto.setFont(textos);		
		modificar.setFont(botones);
		agregar.setFont(botones);
		eliminar.setFont(botones);
		
		//Dimensiones
		Dimension tituloDimension = new Dimension(400, 50);
		Dimension etiquetasDimension = new Dimension(400, 30);
		Dimension botonDimension = new Dimension(100, 40);
		Dimension textosDimension = new Dimension(400, 30);
		
		competencia.setMinimumSize(tituloDimension);
		competencia.setMaximumSize(tituloDimension);
		competencia.setPreferredSize(tituloDimension);
		
		participantes.setMinimumSize(etiquetasDimension);
		participantes.setMaximumSize(etiquetasDimension);
		participantes.setPreferredSize(etiquetasDimension);
		
		participanteTexto.setMinimumSize(textosDimension);
		participanteTexto.setMaximumSize(textosDimension);
		participanteTexto.setPreferredSize(textosDimension);
		
		agregar.setMinimumSize(botonDimension);
		agregar.setMaximumSize(botonDimension);
		agregar.setPreferredSize(botonDimension);
		
		modificar.setMinimumSize(botonDimension);
		modificar.setMaximumSize(botonDimension);
		modificar.setPreferredSize(botonDimension);
		
		eliminar.setMinimumSize(botonDimension);
		eliminar.setMaximumSize(botonDimension);
		eliminar.setPreferredSize(botonDimension);
		
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
		
		//panel datos
		filtrosPanel = new JPanel();
		filtrosPanel.setLayout(new GridBagLayout());
		filtrosPanel.setMinimumSize(new Dimension(1200, 75));
		filtrosPanel.setBackground(Color.WHITE);
				
		filtrosPanel.add(volver, constraints);
		
		constraints.gridx = 1;
		filtrosPanel.add(competencia, constraints);
		
		constraints.gridy = 1;
		filtrosPanel.add(participantes, constraints);
		
		constraints.gridy = 2;
		filtrosPanel.add(participanteTexto, constraints);
		
		constraints.gridx = 2;
		filtrosPanel.add(auxRelleno1, constraints);
		
		constraints.gridx = 3;
		filtrosPanel.add(agregar, constraints);
		
		constraints.gridx = 4;
		filtrosPanel.add(auxRelleno2, constraints);
		
		constraints.gridx = 5;
		filtrosPanel.add(modificar, constraints);
		
		constraints.gridx = 6;
		filtrosPanel.add(auxRelleno3, constraints);
		
		constraints.gridx = 7;
		filtrosPanel.add(eliminar, constraints);
		
		//Panel tabla
		tablaPanel = new JPanel();
		tablaPanel.setLayout(new BoxLayout(tablaPanel, BoxLayout.LINE_AXIS));
		tablaPanel.setBackground(Color.WHITE);
		
		JPanel auxTablaP = new JPanel();
		auxTablaP.setBackground(Color.WHITE);	
		
		//Tamaño tablas
		Dimension tamTabla = new Dimension(885, 400);
		
		//Constraints tablas
		GridBagConstraints constraintsTabla = new GridBagConstraints();
	    constraintsTabla.anchor = GridBagConstraints.BOTH;
	    constraintsTabla.insets = new Insets(0, 0, 0, 5);
	    constraintsTabla.gridy = 2;
	    constraintsTabla.gridx = 1;
	    constraintsTabla.gridwidth = 8;
	    constraintsTabla.anchor = GridBagConstraints.CENTER;
		
		//Tabla Participantes
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
		tablaParticipantes.setMinimumSize(tamTabla);
		tablaParticipantes.setMaximumSize(tamTabla);
		tablaParticipantes.setPreferredSize(tamTabla);
		tablaParticipantes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablaParticipantes.setRowHeight(40);
		
		actualizarTablaParticipantes(competenciaDTO.getParticipantes());
		auxTablaP.add(tablaParticipantes);
		tablaParticipantes.setDefaultEditor(Object.class, null);
		
		//Scroll Pane
		JScrollPane tableSP = new JScrollPane(tablaParticipantes);
		tableSP.setMinimumSize(tamTabla);
		tableSP.setMaximumSize(tamTabla);
		tableSP.setPreferredSize(tamTabla);
		tableSP.setBackground(new Color(42, 224, 187));
	   
	    auxTablaP.add(tableSP, constraintsTabla);
		
		JPanel auxRelleno0 = new JPanel();
		
		auxRelleno0.setMinimumSize(new Dimension(50, 40));
		auxRelleno0.setMaximumSize(new Dimension(50, 40));
		auxRelleno0.setPreferredSize(new Dimension(50, 40));
		auxRelleno0.setBackground(Color.WHITE);
		
		tablaPanel.add(auxRelleno0);
		tablaPanel.add(auxTablaP);
						
		//Panel de relleno 
		JPanel auxRelleno = new JPanel();
		JPanel auxRellenoA = new JPanel();
		
		auxRelleno.setMinimumSize(new Dimension(100, 60));
		auxRelleno.setMaximumSize(new Dimension(100, 60));
		auxRelleno.setPreferredSize(new Dimension(100, 60));
		auxRelleno.setBackground(Color.WHITE);
		
		auxRellenoA.setMinimumSize(new Dimension(100, 20));
		auxRellenoA.setMaximumSize(new Dimension(100, 20));
		auxRellenoA.setPreferredSize(new Dimension(100, 20));
		auxRellenoA.setBackground(Color.WHITE);
		
		this.add(auxRellenoA);
		this.add(filtrosPanel);
		this.add(tablaPanel);
		this.add(auxRelleno);
		
		//Funcion de Botones
		volver.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				volver();
			}
			
		});
		
		agregar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AltaParticipante altaParticipante = new AltaParticipante(tpPanel, competenciaDTO);
				tpPanel.add(altaParticipante, "AltaParticipante");
				CardLayout layout = (CardLayout)tpPanel.getLayout();
				layout.show(tpPanel, "AltaParticipante");
			}
			
		});
		
		eliminar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int ok = JOptionPane.showConfirmDialog(new JPanel(), "El participante se eliminará permanentemente", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
				if(ok == JOptionPane.OK_OPTION) {
					if(gestorParticipante.eliminarParticipante(competenciaDTO.getParticipantes().get(tablaParticipantes.getSelectedRow()), competenciaDTO)) {
						competenciaDTO.getParticipantes().remove(tablaParticipantes.getSelectedRow());
						JOptionPane.showMessageDialog(new JPanel(), "El participante se ha eliminado exitosamente", " ", JOptionPane.INFORMATION_MESSAGE);
						actualizarTablaParticipantes(competenciaDTO.getParticipantes());
						((VerCompetencia) tpPanel.getComponent(tpPanel.getComponentCount() - 2)).competenciaModificada(competenciaDTO, false);
					}
					else {
						JOptionPane.showMessageDialog(new JPanel(), "No se pudo eliminar al participante", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
		});
		
		modificar.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), "Modalidad aún no implementada", " ", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		this.addMouseListener( new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
                tablaParticipantes.clearSelection();
                modificar.setEnabled(tablaParticipantes.getSelectedRow() != -1);
                eliminar.setEnabled(tablaParticipantes.getSelectedRow() != -1);
                System.out.printf(String.valueOf(tablaParticipantes.getSelectedRow()));
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
		
		Document documento = participanteTexto.getDocument();
		documento.addDocumentListener(new JTextStateController(competenciaDTO.getParticipantes(), participanteTexto));
		
	}
	
	public void actualizarTablaParticipantes( List<ParticipanteDTO> participantes ) {
		String[] columnas = { "Nombre", "Email"};
		modeloParticipantes = new DefaultTableModel(columnas, 0);		

		for(ParticipanteDTO participante: participantes) {
			Object[] renglon = { participante.getNombre(), participante.getEmail()};
			modeloParticipantes.addRow(renglon);
		}
		
		//actualizar modelo
		tablaParticipantes.setModel(modeloParticipantes);
	}
	
	private void volver() {
		CardLayout layout = (CardLayout)tpPanel.getLayout();
		layout.previous(tpPanel);
		tpPanel.remove(this);
	}
	
}

