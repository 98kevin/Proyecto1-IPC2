package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.Administrador;
import backend.Main;
import backend.PuntoDeControl;
import backend.Ruta;
import backend.SqlConection;

public class FormNuevaRuta extends JPanel {
        /**
         * 
         */
    	private static final long serialVersionUID = -2480081645214884888L;
	private JTextField cajaNombre;
	private JTextField cajaNombreDestino;
	private JTable table;
	private JTextField cajaNombrePdC;
	private JTextField cajaCapacidad;
	private JTextField cajaTarifa;

	private JPanel panelNuevoPdC ;
	private JButton botonHecho;
	private JButton botonCerrar;
	private JTextField cajaNombreEmpleado;
	
    /**
     * Create the panel.
     */
    public FormNuevaRuta(Administrador admin) {
	PuntoDeControl.puntos=new LinkedList<PuntoDeControl>();
    	setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblCreacionDeNuevas = new JLabel("Creacion De Nuevas Rutas");
    	lblCreacionDeNuevas.setFont(new Font("Dialog", Font.BOLD, 16));
    	lblCreacionDeNuevas.setHorizontalAlignment(SwingConstants.CENTER);
    	add(lblCreacionDeNuevas, BorderLayout.NORTH);
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(null);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblNombre.setBounds(12, 14, 60, 17);
    	panel.add(lblNombre);
    	
    	cajaNombre = new JTextField();
    	cajaNombre.setFont(new Font("Dialog", Font.PLAIN, 14));
    	cajaNombre.setBounds(90, 12, 239, 21);
    	panel.add(cajaNombre);
    	cajaNombre.setColumns(10);
    	
    	JLabel lblDestino = new JLabel("Destino");
    	lblDestino.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblDestino.setBounds(12, 50, 60, 17);
    	panel.add(lblDestino);
    	
    	cajaNombreDestino = new JTextField();
    	cajaNombreDestino.setFont(new Font("Dialog", Font.PLAIN, 14));
    	cajaNombreDestino.setEditable(false);
    	cajaNombreDestino.setBounds(220, 45, 345, 21);
    	panel.add(cajaNombreDestino);
    	cajaNombreDestino.setColumns(10);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setBounds(577, 45, 605, 447);
    	panel.add(scrollPane);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	
    	 panelNuevoPdC = new JPanel();
    	panelNuevoPdC.setBounds(12, 121, 553, 211);
    	panel.add(panelNuevoPdC);
    	panelNuevoPdC.setLayout(new GridLayout(6, 2, 5, 5));
    	
	botonCerrar = new JButton("Cerrar");
	botonCerrar.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonCerrar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    ocultarElementos();
    		}
    	});
    	botonCerrar.setBounds(341, 344, 105, 27);
    	botonCerrar.setVisible(false);
    	panel.add(botonCerrar);
    	 
    	JComboBox<String> cajaOperador = new JComboBox<String>();
    	cajaOperador.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    		    actualizarTextoCaja(cajaOperador, 
    			    cajaNombreEmpleado,
    			    "nombres", 
    			    " Empleado ", 
    			    " cui ");
    		}
    	});
    	
	JLabel lblNombreDelPdc = new JLabel("Nombre del PdC");
	lblNombreDelPdc.setFont(new Font("Dialog", Font.BOLD, 14));
	panelNuevoPdC.add(lblNombreDelPdc);
    	
    	cajaNombrePdC = new JTextField();
    	cajaNombrePdC.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelNuevoPdC.add(cajaNombrePdC);
    	cajaNombrePdC.setColumns(10);
    	
    	JLabel lblCui = new JLabel("Operador");
    	lblCui.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNuevoPdC.add(lblCui);
    	cajaOperador.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNuevoPdC.add(cajaOperador);
    	
    	JCheckBox checkBoxTarifaGlobal = new JCheckBox("");
    	checkBoxTarifaGlobal.setSelected(true);
    	checkBoxTarifaGlobal.setToolTipText("Utiliza el precio global de punto de control");
    	checkBoxTarifaGlobal.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    if(checkBoxTarifaGlobal.isSelected())
    			cajaTarifa.setEditable(false);
    		    else 
    			cajaTarifa.setEditable(true);
    		}
    	});
    	
    	JLabel lblNombreDelEmpleado = new JLabel("Nombre Del Empleado");
    	panelNuevoPdC.add(lblNombreDelEmpleado);
    	
    	cajaNombreEmpleado = new JTextField();
    	cajaNombreEmpleado.setEditable(false);
    	panelNuevoPdC.add(cajaNombreEmpleado);
    	cajaNombreEmpleado.setColumns(10);
    	
    	JLabel lblCapacidad = new JLabel("Capacidad");
    	lblCapacidad.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNuevoPdC.add(lblCapacidad);
    	
    	cajaCapacidad = new JTextField();
    	cajaCapacidad.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelNuevoPdC.add(cajaCapacidad);
    	cajaCapacidad.setColumns(10);
    	
    	JLabel lblTarifaGlobal = new JLabel("Tarifa Global");
    	panelNuevoPdC.add(lblTarifaGlobal);
    	panelNuevoPdC.add(checkBoxTarifaGlobal);
    	
    	botonHecho = new JButton("Hecho");
    	botonHecho.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonHecho.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    int codigoRuta= SqlConection.getUltimo(Ruta.TABLA, Ruta.COL_ID);
    		    //sumamos un valor al ultimo codigo de la ruta
    		    codigoRuta++; 
    		    if(Main.tm)
    			System.out.println("Ultima Ruta: "+ codigoRuta);
    		    //Si los puntos de control son nulos, entonces se insatancia la lista
    		    if(PuntoDeControl.puntos==null)
    			PuntoDeControl.puntos =new LinkedList<PuntoDeControl>();
    		    //Creamos un nuevo punto de control en base a los datos ingresados
    		    PuntoDeControl pt = new PuntoDeControl(cajaNombrePdC.getText(),
    			    codigoRuta,
    			    (String)cajaOperador.getSelectedItem(), 
    			    cajaCapacidad.getText(), 
    			    checkBoxTarifaGlobal.isSelected(), 
    			    cajaTarifa.getText(),
    			    PuntoDeControl.puntos.size()+1);
    		    //Verificamos si tiene integridad
    		    if(pt.isIntegro())
    		    //agregamos el nuevo de control a la lista
    			pt.agregarPunto(table);
    		    ocultarElementos();
    		}
    	});
    	botonHecho.setBounds(458, 344, 105, 27);
    	botonHecho.setVisible(false);
    	panel.add(botonHecho);
    	    	
    	JButton botonAgregar = new JButton("Agregar Nuevo Punto");
    	botonAgregar.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonAgregar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    panelNuevoPdC.setVisible(true);
    		    botonHecho.setVisible(true);
    		    botonCerrar.setVisible(true);
    		    agregarItems(cajaOperador,
    			    " cui ",
    			    " Empleado ",
    			    "where tipo=2"
    			    );
    		}
    	});
    	botonAgregar.setBounds(380, 78, 185, 27);
    	panel.add(botonAgregar);
    	
    	
    	JLabel lblTarifaDeOperacion = new JLabel("Tarifa de Operacion");
    	lblTarifaDeOperacion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panelNuevoPdC.add(lblTarifaDeOperacion);
    	
    	cajaTarifa = new JTextField();
    	cajaTarifa.setToolTipText("Su propia tarifa de operacion");
    	cajaTarifa.setEditable(false);
    	cajaTarifa.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panelNuevoPdC.add(cajaTarifa);
    	cajaTarifa.setColumns(10);
    	
    	JComboBox<String> cajaDestinos = new JComboBox<String>();
    	cajaDestinos.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    		    actualizarTextoCaja(cajaDestinos, cajaNombreDestino, "nombre", "Destino", " idDestino ");
    		}
    	});
    	cajaDestinos.setFont(new Font("Dialog", Font.BOLD, 14));
    	cajaDestinos.setBounds(90, 45, 113, 26);
    	agregarItems(cajaDestinos, " idDestino ", "Destino" , " ");
    	panel.add(cajaDestinos);
    	
    	JPanel panel_1 = new JPanel();
    	add(panel_1, BorderLayout.SOUTH);
    	
    	JButton botonCancelarIngreso = new JButton("Cancelar");
    	botonCancelarIngreso.setHorizontalAlignment(SwingConstants.RIGHT);
    	botonCancelarIngreso.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonCancelarIngreso.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    setVisible(false);
    		}
    	});
    	panel_1.add(botonCancelarIngreso);
    	
    	JButton botonAceptar = new JButton("Aceptar");
    	botonAceptar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    ArrayList<String> sentencias = new ArrayList<String>();
    		    //Creamos una nueva ruta en base a los datos ingresados
    		    admin.crearRuta( cajaNombre.getText(), cajaDestinos.getSelectedIndex()+1);
    		    //Se escriben los puntos de control en la base de datos
    		    admin.obtenerSentencias(sentencias,PuntoDeControl.puntos);
    		    //Ejecutamos la transaccion de los puntos de control
    		    SqlConection.transaccion(sentencias);
    		    setVisible(false);
    		}
    	});
    	botonAceptar.setHorizontalAlignment(SwingConstants.RIGHT);
    	botonAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(botonAceptar);
    	
    	panelNuevoPdC.setVisible(false);
    }
    
	public void ocultarElementos() {
	    panelNuevoPdC.setVisible(false);
	    botonHecho.setVisible(false);
	    botonCerrar.setVisible(false);
	}
	
	public void actualizarTextoCaja(JComboBox<String> cajaSeleccionadora,
		JTextField cajaDeTexto,
		String columnas,
		String tabla, 
		String campoComparacion) {
	    	String condiciones = " WHERE "+campoComparacion+" = "+cajaSeleccionadora.getSelectedItem();
	    	ResultSet consulta = SqlConection.generarConsulta(columnas, tabla, condiciones);
	    	try {
		    consulta.next();
	    	    cajaDeTexto.setText(consulta.getString(1));
	    	}	 catch (SQLException ex) {
	    	    //ex.printStackTrace();
	    	}    		    
	}
	
	public void agregarItems(JComboBox<String> cajaDeItems,
		String columna, 
		String tabla,
		String where) {
	    	cajaDeItems.removeAllItems();
	    	ResultSet resultado = SqlConection.generarConsulta(columna, tabla,where);
		   try {
		       	while (resultado.next()) {
			cajaDeItems.addItem(resultado.getString(1));
		       	}
		   } catch (Exception e) {
		       e.printStackTrace();
		   }
	}
	
}
