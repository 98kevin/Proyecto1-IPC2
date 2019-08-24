package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import backend.Destino;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormEditarDestino extends JPanel {
	/**
     * 
     */
    private static final long serialVersionUID = -1937873338441556300L;
	private JTextField cajaCodigo;
	private JTextField cajaPrecio;
	private JTextField cajaNombre;
	private JTextField cajaNuevoPrecio;
	private JTable table;
	private Destino destino;

    /**
     * Create the panel.
     */
    public FormEditarDestino() {
	destino = new Destino();
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	add(panel, BorderLayout.NORTH);
    	
    	JLabel lblEditarPreciosDel = new JLabel("Editar Precios del Destino");
    	panel.add(lblEditarPreciosDel);
    	
    	JPanel panel_1 = new JPanel();
    	add(panel_1, BorderLayout.CENTER);
    	panel_1.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel_2 = new JPanel();
    	panel_1.add(panel_2, BorderLayout.NORTH);
    	panel_2.setLayout(new GridLayout(4, 2, 0, 0));
    	
    	JLabel lblCodigo = new JLabel("Codigo");
    	panel_2.add(lblCodigo);
    	
    	cajaCodigo = new JTextField();
    	cajaCodigo.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if(e.getKeyCode()==KeyEvent.VK_ENTER) {
    			destino.consultarDestino(cajaCodigo.getText(), cajaNombre, cajaPrecio);
    		    }
    		}
    	});
    	panel_2.add(cajaCodigo);
    	cajaCodigo.setColumns(10);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	panel_2.add(lblNombre);
    	
    	cajaNombre = new JTextField();
    	cajaNombre.setEditable(false);
    	panel_2.add(cajaNombre);
    	cajaNombre.setColumns(10);
    	
    	JLabel lblPrecio = new JLabel("Precio");
    	panel_2.add(lblPrecio);
    	
    	cajaPrecio = new JTextField();
    	cajaPrecio.setEditable(false);
    	panel_2.add(cajaPrecio);
    	cajaPrecio.setColumns(10);
    	
    	JLabel lblNuevoPrecio = new JLabel("Nuevo Precio");
    	panel_2.add(lblNuevoPrecio);
    	
    	cajaNuevoPrecio = new JTextField();
    	panel_2.add(cajaNuevoPrecio);
    	cajaNuevoPrecio.setColumns(10);
    	
    	JPanel panel_3 = new JPanel();
    	panel_1.add(panel_3, BorderLayout.CENTER);
    	panel_3.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel_4 = new JPanel();
    	panel_3.add(panel_4, BorderLayout.NORTH);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    setVisible(false);
    		}
    	});
    	panel_4.add(btnCancelar);
    	
    	JButton btnAceptar = new JButton("Actualizar");
    	btnAceptar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    //Escribimos en la base de datos 
    		    destino.actualizarDestino(cajaCodigo.getText(), cajaNuevoPrecio.getText());
    		    //Actualizamos la tabla
    		    destino.agregarDestinosATabla(table);
    		    //borramos El texto en las cajas de texto
    		    cajaCodigo.setText("");
    		    cajaNombre.setText("");
    		    cajaPrecio.setText("");
    		    cajaNuevoPrecio.setText("");
    		}
    	});
    	panel_4.add(btnAceptar);
    	
    	JPanel panel_5 = new JPanel();
    	panel_3.add(panel_5, BorderLayout.CENTER);
    	panel_5.setLayout(new BorderLayout(0, 0));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	panel_5.add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	destino.agregarDestinosATabla(table);
    	scrollPane.setViewportView(table);

    }
}
