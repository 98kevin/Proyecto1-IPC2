package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import backend.Administrador;

public class FormNuevoEmpleado extends JPanel {
	/**
     * Serializacion del panel de ingreso de empleados
     */
    private static final long serialVersionUID = 3363566998280936019L;
	private JTextField cajaCui;
	private JTextField cajaNombres;
	private JTextField cajaApellidos;
	private JTextField cajaDireccion;
	private JTextField cajaCorreo;
	private JPasswordField cajaPass1;
	private JPasswordField cajaPass2;
	private JTextField cajaSalario;

    /**
     * Create the panel.
     */
    public FormNuevoEmpleado() {
    	setBorder(new LineBorder(Color.LIGHT_GRAY));
    	setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblRegistroDeNuevos = new JLabel("Registro de Nuevos Empleados");
    	lblRegistroDeNuevos.setFont(new Font("Liberation Sans", Font.BOLD, 20));
    	lblRegistroDeNuevos.setHorizontalAlignment(SwingConstants.CENTER);
    	add(lblRegistroDeNuevos, BorderLayout.NORTH);
    	
    	JPanel panelDeCampos = new JPanel();
    	add(panelDeCampos, BorderLayout.CENTER);
    	panelDeCampos.setLayout(new GridLayout(10, 2, 10, 10));
    	
    	JLabel lblCui = new JLabel("CUI");
    	lblCui.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblCui);
    	
    	cajaCui = new JTextField();
    	cajaCui.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaCui);
    	cajaCui.setColumns(10);
    	
    	JLabel lblNombres = new JLabel("Nombres");
    	lblNombres.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblNombres);
    	
    	cajaNombres = new JTextField();
    	cajaNombres.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaNombres);
    	cajaNombres.setColumns(10);
    	
    	JLabel lblApellidos = new JLabel("Apellidos");
    	lblApellidos.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblApellidos);
    	
    	cajaApellidos = new JTextField();
    	cajaApellidos.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaApellidos);
    	cajaApellidos.setColumns(10);
    	
    	JLabel lblSalario = new JLabel("Salario");
    	lblSalario.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblSalario);
    	
    	cajaSalario = new JTextField();
    	cajaSalario.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaSalario);
    	cajaSalario.setColumns(10);
    	
    	JLabel lblDireccion = new JLabel("Direccion");
    	lblDireccion.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblDireccion);
    	
    	cajaDireccion = new JTextField();
    	cajaDireccion.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaDireccion);
    	cajaDireccion.setColumns(10);
    	
    	JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
    	lblCorreoElectronico.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblCorreoElectronico);
    	
    	cajaCorreo = new JTextField();
    	cajaCorreo.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaCorreo);
    	cajaCorreo.setColumns(10);
    	
    	JLabel lblTipo = new JLabel("Tipo");
    	lblTipo.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblTipo);
    	
    	JComboBox<String> comboTipo = new JComboBox<String>();
    	comboTipo.addItem("1. Administrador");
    	comboTipo.addItem("2. Operador");
    	comboTipo.addItem("3. Recepcionista");
    	comboTipo.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(comboTipo);
    	
    	JLabel lblFechaDeContratacion = new JLabel("Fecha De Contratacion");
    	lblFechaDeContratacion.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblFechaDeContratacion);
    	
    	JDateChooser seleccionDeFecha = new JDateChooser();
    	seleccionDeFecha.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(seleccionDeFecha);
    	
    	JLabel lblPassword = new JLabel("Password");
    	lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblPassword);
    	
    	cajaPass1 = new JPasswordField();
    	cajaPass1.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaPass1);
    	
    	JLabel lblRepetirPassword = new JLabel("Repetir Password");
    	lblRepetirPassword.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeCampos.add(lblRepetirPassword);
    	
    	cajaPass2 = new JPasswordField();
    	cajaPass2.setFont(new Font("Dialog", Font.PLAIN, 16));
    	panelDeCampos.add(cajaPass2);
    	
    	JPanel panelDeBotones = new JPanel();
    	add(panelDeBotones, BorderLayout.SOUTH);
    	
    	JButton botonCancelar = new JButton("Cancelar");
    	botonCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    setVisible(false);
    		}
    	});
    	botonCancelar.setFont(new Font("Dialog", Font.BOLD, 16));
    	panelDeBotones.add(botonCancelar);
    	
    	JButton botonAceptar = new JButton("Aceptar");
    	botonAceptar.setFont(new Font("Dialog", Font.BOLD, 16));
    	botonAceptar.addActionListener(new ActionListener() {
    		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {  
    		    Administrador empleado = new Administrador();
    		    empleado.registrarNuevoEmpleado(cajaCui.getText(),
    			    cajaNombres.getText(),
    			    cajaApellidos.getText(), 
    			    cajaSalario.getText(),
    			    cajaDireccion.getText(),
    			    cajaCorreo.getText(),
    			    comboTipo.getSelectedIndex() +1,
    			    new Date(seleccionDeFecha.getDate().getTime()),
    			    cajaPass1.getText(),
    			    cajaPass2.getText()
    			    );
    		    setVisible(false);
    		}
    	});
    	panelDeBotones.add(botonAceptar);
    }

}
