package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import backend.Cliente;
import backend.Paquete;
import backend.Precio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Facturacion extends JPanel {

    /**
     * Serializacion del panel de facturacion
     */
    private static final long serialVersionUID = 5383663894681721728L;
    private JTextField cajaTotal;
    private JTextField cajaPago;
    private JTextField cajaCambio;
    private JTable table;

    /**
     * Create the panel.
     */
    public Facturacion(Cliente cliente, LinkedList<Paquete> paquetes){
    	setLayout(new BorderLayout(0, 0));
    
    	JPanel panelNorte = new JPanel();
    	add(panelNorte, BorderLayout.NORTH);
    	panelNorte.setLayout(new GridLayout(2, 0, 0, 0));
    	
    	JPanel panel1 = new JPanel();
    	panelNorte.add(panel1);
    	
    	JLabel lblFacturacion = new JLabel("Facturacion");
    	lblFacturacion.setFont(new Font("Dialog", Font.BOLD, 18));
    	panel1.add(lblFacturacion);
    	
    	JPanel panel2 = new JPanel();
    	panelNorte.add(panel2);
    	panel2.setLayout(new GridLayout(3, 2, 0, 0));
    	
    	JLabel lblNit = new JLabel("Nit: ");
    	lblNit.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel2.add(lblNit);
    	
    	JLabel lblSalidaNit = new JLabel("");
    	lblSalidaNit.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblSalidaNit.setText(cliente.getNit());
    	panel2.add(lblSalidaNit);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel2.add(lblNombre);
    	
    	JLabel lblSalidanombre = new JLabel("");
    	lblSalidanombre.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblSalidanombre.setText(cliente.getNombres()+", "+ cliente.getApellidos());
    	panel2.add(lblSalidanombre);
    	
    	JLabel lblDireccion = new JLabel("Direccion");
    	lblDireccion.setFont(new Font("Dialog", Font.BOLD, 14));
    	panel2.add(lblDireccion);
    	
    	JLabel lblSalidadireccion = new JLabel("");
    	lblSalidadireccion.setFont(new Font("Dialog", Font.BOLD, 14));
    	lblSalidadireccion.setText(cliente.getDireccion());
    	panel2.add(lblSalidadireccion);
    	
    	JPanel panelCentral = new JPanel();
    	add(panelCentral, BorderLayout.CENTER);
    	panelCentral.setLayout(new BorderLayout(0, 0));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	panelCentral.add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	Paquete paquete = new Paquete();
    	paquete.llenarTablaDePaquetes(table,paquetes);
    	scrollPane.setViewportView(table);
    	
    	JPanel panelSur = new JPanel();
    	add(panelSur, BorderLayout.SOUTH);
    	panelSur.setLayout(new GridLayout(2, 1, 0, 0));
    	
    	JPanel panelSur1 = new JPanel();
    	panelSur.add(panelSur1);
    	panelSur1.setLayout(new GridLayout(3, 2, 0, 0));
    	
    	JLabel lblTotal = new JLabel("Total");
    	panelSur1.add(lblTotal);
    	
    	cajaTotal = new JTextField();
    	cajaTotal.setEditable(false);
    	Precio precio = new Precio();
    	cajaTotal.setText(String.valueOf(
    		precio.getTotal(paquetes)));
    	panelSur1.add(cajaTotal);
    	cajaTotal.setColumns(10);
    	
    	JLabel lblPagaCon = new JLabel("Paga con:");
    	panelSur1.add(lblPagaCon);
    	
    	cajaPago = new JTextField();
    	cajaPago.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if ( e.getKeyCode()==KeyEvent.VK_ENTER) {
			double cambio = precio.calcularCambio(cajaPago.getText(), cajaTotal.getText());
			cajaCambio.setText(String.valueOf(cambio));
		    }
    		}
    	});
    	panelSur1.add(cajaPago);
    	cajaPago.setColumns(10);
    	
    	JLabel lblCambio = new JLabel("Cambio");
    	panelSur1.add(lblCambio);
    	
    	cajaCambio = new JTextField();
    	cajaCambio.setEditable(false);
    	panelSur1.add(cajaCambio);
    	cajaCambio.setColumns(10);
    	   	
    	JPanel panelSur2 = new JPanel();
    	panelSur.add(panelSur2);
    	panelSur2.setLayout(null);
    	
    	JButton btnAceptar = new JButton("Aceptar");
    	btnAceptar.setBounds(491, 12, 145, 27);
    	panelSur2.add(btnAceptar);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.setBounds(12, 12, 105, 27);
    	panelSur2.add(btnCancelar);

    }

}
