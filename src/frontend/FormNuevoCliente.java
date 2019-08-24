package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Recepcionista;

public class FormNuevoCliente extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -3196226211800175495L;
    private JPanel contentPane;
    private JTextField cajaNit;
    private JTextField cajaNombres;
    private JTextField cajaApellidos;
    private JTextField cajaDireccion;
    private JTextField cajaCorreo;
    
    private Recepcionista recepcionista;
    
    public FormNuevoCliente() {
	super();
	setTitle("Registro de Clientes");
    }
    
    /**
     * Create the frame.
     */
    public FormNuevoCliente(String casillero) {
	recepcionista = new Recepcionista();
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 669, 416);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	
	JLabel lblFormularioDeNuevos = new JLabel("Formulario de Nuevos Clientes");
	lblFormularioDeNuevos.setFont(new Font("Dialog", Font.BOLD, 16));
	contentPane.add(lblFormularioDeNuevos, BorderLayout.NORTH);
	
	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.CENTER);
	panel.setLayout(new GridLayout(0, 1, 0, 0));
	
	JPanel panel_1 = new JPanel();
	panel.add(panel_1);
	panel_1.setLayout(new GridLayout(6, 2, 0, 0));
	
	JLabel lblCasillero = new JLabel("Casillero");
	lblCasillero.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblCasillero);
	
	JLabel lblSalidaCasillero = new JLabel();
	lblSalidaCasillero.setText(casillero);
	lblSalidaCasillero.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblSalidaCasillero);
	
	JLabel lblNit = new JLabel("nit");
	lblNit.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblNit);
	
	cajaNit = new JTextField();
	cajaNit.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel_1.add(cajaNit);
	cajaNit.setColumns(10);
	
	JLabel lblNombres = new JLabel("Nombres");
	lblNombres.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblNombres);
	
	cajaNombres = new JTextField();
	cajaNombres.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel_1.add(cajaNombres);
	cajaNombres.setColumns(10);
	
	JLabel lblApellidos = new JLabel("Apellidos");
	lblApellidos.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblApellidos);
	
	cajaApellidos = new JTextField();
	cajaApellidos.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel_1.add(cajaApellidos);
	cajaApellidos.setColumns(10);
	
	JLabel lblDireccion = new JLabel("Direccion");
	lblDireccion.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblDireccion);
	
	cajaDireccion = new JTextField();
	cajaDireccion.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel_1.add(cajaDireccion);
	cajaDireccion.setColumns(10);
	
	JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
	lblCorreoElectronico.setFont(new Font("Dialog", Font.BOLD, 14));
	panel_1.add(lblCorreoElectronico);
	
	cajaCorreo = new JTextField();
	cajaCorreo.setFont(new Font("Dialog", Font.PLAIN, 14));
	panel_1.add(cajaCorreo);
	cajaCorreo.setColumns(10);
	
	JPanel panel_2 = new JPanel();
	panel.add(panel_2);
	panel_2.setLayout(null);
	
	JButton btnAceptar = new JButton("Aceptar");
	btnAceptar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		 recepcionista.registrarNuevoCliente(Integer.parseInt(lblSalidaCasillero.getText()), 
			    cajaNit.getText(),
			    cajaNombres.getText(),
			    cajaApellidos.getText(),
			    cajaDireccion.getText(), 
			    cajaCorreo.getText());
		    setVisible(false);
		}
	});
	btnAceptar.setFont(new Font("Dialog", Font.BOLD, 15));
	btnAceptar.setBounds(515, 12, 124, 38);
	panel_2.add(btnAceptar);
    }

    
}
