package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.Cliente;
import backend.Paquete;

public class FormNuevoPaquete extends JPanel {
	/**
     * 
     */
    private static final long serialVersionUID = -1720339697338514591L;
	private JTextField cajaCasillero;
	private JTextField cajaDescripcion;
	private JTextField cajaLibras;
	private JTextField cajaCodigoDestino;
	private JLabel etqSalidaDireccion;
	private JLabel etqSalidaNombre;
	private JLabel etqSalidaNit;
	private LinkedList <Paquete> listaPaquetes ;
	Cliente cliente;

    /**
     * Create the panel.
     */
    public FormNuevoPaquete() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblRegistroDePaquetes = new JLabel("Registro de Paquetes");
    	lblRegistroDePaquetes.setFont(new Font("Dialog", Font.BOLD, 16));
    	lblRegistroDePaquetes.setHorizontalAlignment(SwingConstants.CENTER);
    	add(lblRegistroDePaquetes, BorderLayout.NORTH);
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(new GridLayout(2, 1, 0, 0));
    	
    	JPanel panel_1 = new JPanel();
    	panel.add(panel_1);
    	panel_1.setLayout(new GridLayout(6, 2, 0, 0));
    	
    	JLabel etqCasillero = new JLabel("Casillero");
    	etqCasillero.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(etqCasillero);
    	
    	cajaCasillero = new JTextField();
    	cajaCasillero.setToolTipText("Presione enter para buscar");
    	cajaCasillero.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if(e.getKeyCode()==KeyEvent.VK_ENTER) {
    			cliente = new Cliente();
    			Cliente clienteNuevo = cliente.consultarCliente(cajaCasillero.getText());
    			actualizarEtiquetas(clienteNuevo);
    		    }
    		}
    	});
    	cajaCasillero.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panel_1.add(cajaCasillero);
    	cajaCasillero.setColumns(10);
    	
    	JLabel etqDescripcion = new JLabel("Descripcion");
    	etqDescripcion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(etqDescripcion);
    	
    	cajaDescripcion = new JTextField();
    	cajaDescripcion.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panel_1.add(cajaDescripcion);
    	cajaDescripcion.setColumns(10);
    	
    	JLabel etqLibras = new JLabel("Libras");
    	etqLibras.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(etqLibras);
    	
    	cajaLibras = new JTextField();
    	cajaLibras.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panel_1.add(cajaLibras);
    	cajaLibras.setColumns(10);
    	
    	JLabel etqPriorizado = new JLabel("Priorizado");
    	etqPriorizado.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(etqPriorizado);
    	
    	JCheckBox checkPriorizado = new JCheckBox("");
    	checkPriorizado.setToolTipText("Indica si el paquete esta priorizado");
    	checkPriorizado.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(checkPriorizado);
    	
    	JLabel etqCodigoDestino = new JLabel("Codigo Destino");
    	etqCodigoDestino.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_1.add(etqCodigoDestino);
    	
    	cajaCodigoDestino = new JTextField();
    	cajaCodigoDestino.setFont(new Font("Dialog", Font.PLAIN, 14));
    	panel_1.add(cajaCodigoDestino);
    	cajaCodigoDestino.setColumns(10);
    	
    	JPanel panel_2 = new JPanel();
    	panel.add(panel_2);
    	panel_2.setLayout(null);
    	
    	JButton botonBuscarCasillero = new JButton("Buscar Casillero");
    	botonBuscarCasillero.setFont(new Font("Dialog", Font.BOLD, 11));
    	botonBuscarCasillero.setBounds(12, 170, 135, 18);
    	panel_2.add(botonBuscarCasillero);
    	
    	JButton botonBuscarDestino = new JButton("Buscar Destino");
    	botonBuscarDestino.setFont(new Font("Dialog", Font.BOLD, 11));
    	botonBuscarDestino.setBounds(12, 148, 135, 18);
    	panel_2.add(botonBuscarDestino);
    	
    	JLabel etqSalidaCantidad = new JLabel("");
    	etqSalidaCantidad.setFont(new Font("Dialog", Font.BOLD, 14));
    	etqSalidaCantidad.setBounds(353, 149, 60, 27);
    	panel_2.add(etqSalidaCantidad);
    	
    	JButton botonAgregar = new JButton("Agregar");
    	botonAgregar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    listaPaquetes = (listaPaquetes==null)? new LinkedList<Paquete>(): listaPaquetes;
    		    listaPaquetes.add(new Paquete(cajaCasillero.getText(), 
    			   cajaDescripcion.getText(), 
    			   cajaLibras.getText(), checkPriorizado.isSelected(), 
    			   cajaCodigoDestino.getText()));
    		    etqSalidaCantidad.setText(String.valueOf(listaPaquetes.size()));
    		}
    	});
    	botonAgregar.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonAgregar.setBounds(636, 12, 190, 27);
    	panel_2.add(botonAgregar);
    	
    	JButton botonFacturar = new JButton("Facturar");
    	botonFacturar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    new Facturacion(cliente, listaPaquetes);
    		}
    	});
    	botonFacturar.setFont(new Font("Dialog", Font.BOLD, 14));
    	botonFacturar.setBounds(636, 51, 190, 48);
    	panel_2.add(botonFacturar);
    	
    	JPanel panel_3 = new JPanel();
    	panel_3.setBounds(12, 12, 554, 127);
    	panel_2.add(panel_3);
    	panel_3.setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblDatosDelCliente = new JLabel("Datos del Cliente");
    	lblDatosDelCliente.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_3.add(lblDatosDelCliente, BorderLayout.NORTH);
    	
    	JPanel panel_4 = new JPanel();
    	panel_3.add(panel_4, BorderLayout.CENTER);
    	panel_4.setLayout(new GridLayout(3, 2, 0, 0));
    	
    	JLabel etqNombreCliente = new JLabel("Nombre");
    	etqNombreCliente.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqNombreCliente);
    	
    	etqSalidaNombre = new JLabel("");
    	etqSalidaNombre.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqSalidaNombre);
    	
    	JLabel etqNit = new JLabel("Nit");
    	etqNit.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqNit);
    	
    	etqSalidaNit = new JLabel("");
    	etqSalidaNit.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqSalidaNit);
    	
    	JLabel etqDireccion = new JLabel("Direccion");
    	etqDireccion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqDireccion);
    	
    	etqSalidaDireccion = new JLabel("");
    	etqSalidaDireccion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel_4.add(etqSalidaDireccion);
    	
    	JLabel lblCantidadDePaquetes = new JLabel("Cantidad de Paquetes");
    	lblCantidadDePaquetes.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblCantidadDePaquetes.setBounds(166, 149, 190, 27);
    	panel_2.add(lblCantidadDePaquetes);
    	
    }
    
	private void actualizarEtiquetas(Cliente cliente) {
	    if (cliente!=null) {
		etqSalidaNombre.setText(cliente.getApellidos() +", "+ cliente.getNombres());
		etqSalidaNit.setText(cliente.getNit());
		etqSalidaDireccion.setText(cliente.getDireccion());
	    }
	}
	
	/**
	 * @param etqSalidaDireccion the etqSalidaDireccion to set
	 */
	public void setEtqSalidaDireccion(JLabel etqSalidaDireccion) {
	    this.etqSalidaDireccion = etqSalidaDireccion;
	}

	/**
	 * @param etqSalidaNombre the etqSalidaNombre to set
	 */
	public void setEtqSalidaNombre(JLabel etqSalidaNombre) {
	    this.etqSalidaNombre = etqSalidaNombre;
	}

	/**
	 * @param etqSalidaNit the etqSalidaNit to set
	 */
	public void setEtqSalidaNit(JLabel etqSalidaNit) {
	    this.etqSalidaNit = etqSalidaNit;
	}
}
