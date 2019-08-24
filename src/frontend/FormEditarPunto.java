package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.PuntoDeControl;
import backend.SqlConection;

public class FormEditarPunto extends JPanel {
	/**
     * Serializacion del Formulario de edicion de un punto de control 
     */
    private static final long serialVersionUID = 1176489512636553095L;
	private JTextField cajaFiltro;
	private JTable tablaPuntos;
	private JTextField cajaCodigoBuscar;
	private JTextField cajaCodigo;
	private JTextField cajaNombre;
	private JTextField cajaCapacidad;
	private JTextField cajaPosicion;
	private PuntoDeControl punto; 
	private JTextField cajaNuevaTarifa;

    /**
     * Create the panel.
     */
    public FormEditarPunto() {
	
    	setLayout(new BorderLayout(0, 0));
    	punto = new PuntoDeControl();
    	JLabel lblFormularioEditarPunto = new JLabel("Formulario Editar Punto de Control");
    	add(lblFormularioEditarPunto, BorderLayout.NORTH);
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(new GridLayout(2, 1, 0, 0));
    	
    	JPanel panel1 = new JPanel();
    	panel.add(panel1);
    	panel1.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelNorte1 = new JPanel();
    	FlowLayout fl_panelNorte1 = (FlowLayout) panelNorte1.getLayout();
    	fl_panelNorte1.setAlignment(FlowLayout.LEFT);
    	panel1.add(panelNorte1, BorderLayout.NORTH);
    	
    	JLabel lblFiltrar = new JLabel("Filtrar");
    	lblFiltrar.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNorte1.add(lblFiltrar);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	panel1.add(scrollPane, BorderLayout.CENTER);
    	
    	tablaPuntos = new JTable();
    	scrollPane.setViewportView(tablaPuntos);
	punto.mostrarPuntos(tablaPuntos, "");
	    
    	cajaFiltro = new JTextField();
    	cajaFiltro.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if(e.getKeyCode() == KeyEvent.VK_ENTER)
    			punto.mostrarPuntos(tablaPuntos, cajaFiltro.getText());
    		}
    	});
    	panelNorte1.add(cajaFiltro);
    	cajaFiltro.setColumns(10);
    	
    	JPanel panel2 = new JPanel();
    	panel.add(panel2);
    	panel2.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelNorte2 = new JPanel();
    	FlowLayout fl_panelNorte2 = (FlowLayout) panelNorte2.getLayout();
    	fl_panelNorte2.setAlignment(FlowLayout.LEFT);
    	panel2.add(panelNorte2, BorderLayout.NORTH);
    	
    	JLabel lblCodigo = new JLabel("Codigo");
    	lblCodigo.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNorte2.add(lblCodigo);
    	
    	JPanel panelCentro2 = new JPanel();
    	panel2.add(panelCentro2, BorderLayout.CENTER);
    	panelCentro2.setLayout(new GridLayout(8, 2, 10, 5));
    	
    	JLabel lblCodigo_1 = new JLabel("Codigo");
    	lblCodigo_1.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblCodigo_1);
    	
    	cajaCodigo = new JTextField();
    	cajaCodigo.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelCentro2.add(cajaCodigo);
    	cajaCodigo.setColumns(10);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblNombre);
    	
    	cajaNombre = new JTextField();
    	cajaNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelCentro2.add(cajaNombre);
    	cajaNombre.setColumns(10);
    	
    	JLabel lblCodigoRuta = new JLabel("Codigo Ruta");
    	lblCodigoRuta.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblCodigoRuta);
    	
    	JComboBox<String> comboRuta = new JComboBox<String>();
    	comboRuta.setFont(new Font("Dialog", Font.BOLD, 14));
    	punto.agregarItemsAlCombo(comboRuta, "idRuta","Ruta", " ");
    	panelCentro2.add(comboRuta);
    	
    	JLabel lblCuiOperador = new JLabel("Cui Operador");
    	lblCuiOperador.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblCuiOperador);
    	
    	JComboBox<String> comboOperador = new JComboBox<String>();
    	comboOperador.setFont(new Font("Dialog", Font.BOLD, 14));
    	punto.agregarItemsAlCombo(comboOperador,"cui", "Empleado","WHERE tipo=2");
    	panelCentro2.add(comboOperador);
    	
    	JLabel lblCapacidad = new JLabel("Capacidad");
    	lblCapacidad.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblCapacidad);
    	
    	cajaCapacidad = new JTextField();
    	cajaCapacidad.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelCentro2.add(cajaCapacidad);
    	cajaCapacidad.setColumns(10);
    	
    	JLabel lblTarifaDeOperacion = new JLabel("Tarifa");
    	lblTarifaDeOperacion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblTarifaDeOperacion);
    	
    	JCheckBox checkTarifaGlobal = new JCheckBox("Global");
    	checkTarifaGlobal.addChangeListener(new ChangeListener() {
    		public void stateChanged(ChangeEvent e) {
    		    boolean editable = (checkTarifaGlobal.isSelected())? false: true;
    		    cajaNuevaTarifa.setEditable(editable);
    		}
    	});
    	panelCentro2.add(checkTarifaGlobal);
    	
    	cajaCodigoBuscar = new JTextField();
    	cajaCodigoBuscar.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        		    punto.actualizarCajas(cajaCodigoBuscar.getText(), cajaCodigo, cajaNombre, comboRuta, comboOperador,
            			    cajaCapacidad, checkTarifaGlobal, cajaNuevaTarifa, cajaPosicion);
		    }
    		}
    	});
    	panelNorte2.add(cajaCodigoBuscar);
    	cajaCodigoBuscar.setColumns(10);
    	
    	JLabel lblDefinir = new JLabel("Tarifa Propia");
    	lblDefinir.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblDefinir);
    	
    	cajaNuevaTarifa = new JTextField();
    	cajaNuevaTarifa.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelCentro2.add(cajaNuevaTarifa);
    	cajaNuevaTarifa.setColumns(10);
    	
    	JLabel lblPosicionEnLa = new JLabel("Posicion en la cola");
    	lblPosicionEnLa.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelCentro2.add(lblPosicionEnLa);
    	
    	cajaPosicion = new JTextField();
    	cajaPosicion.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelCentro2.add(cajaPosicion);
    	cajaPosicion.setColumns(10);
    	
    	JPanel panelSur2 = new JPanel();
    	panel2.add(panelSur2, BorderLayout.SOUTH);
    	
    	JButton btnActualizar = new JButton("Actualizar");
    	btnActualizar.setFont(new Font("Dialog", Font.BOLD, 14));
    	btnActualizar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    // Creamos una nueva instancia de un punto de control (Editado)
    		    PuntoDeControl puntoEditado = new PuntoDeControl(cajaNombre.getText(),
    			    Integer.parseInt(String.valueOf(comboRuta.getSelectedItem())),
    				   String.valueOf(comboOperador.getSelectedItem()),
    				   cajaCapacidad.getText(), 
    				   checkTarifaGlobal.isSelected(), cajaNuevaTarifa.getText(), Integer.parseInt(cajaPosicion.getText()));
    		    String modificaciones = puntoEditado.getSentenceActualizacion();
    		    SqlConection.generarActualizacion("PuntoDeControl", modificaciones, "WHERE idPunto="+cajaCodigo.getText());
    		}
    	});
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setFont(new Font("Dialog", Font.BOLD, 14));
    	btnCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    //TODO cerrar el jpanel
    		}
    	});
    	panelSur2.add(btnCancelar);
    	panelSur2.add(btnActualizar);

    }

}
