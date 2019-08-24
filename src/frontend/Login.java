package frontend;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.Empleado;
import backend.Main;

public class Login extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1493261112321207773L;
	private JTextField cajaNombre;
	private JPasswordField cajaContrasenia;

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Inicio de sesion");
		setBorder(new EmptyBorder(5, 0, 5, 5));
		getContentPane().setBackground(new Color(95, 158, 160));
		setBounds(100, 100, 788, 479);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 205, 170));
		panel.setBounds(30, 62, 314, 330);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel etiquetaCui = new JLabel("CUI");
		etiquetaCui.setFont(new Font("Arial", Font.PLAIN, 14));
		etiquetaCui.setBounds(22, 53, 74, 14);
		panel.add(etiquetaCui);
		
		JLabel etiquetaPassword = new JLabel("Password");
		etiquetaPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		etiquetaPassword.setBounds(22, 119, 74, 14);
		panel.add(etiquetaPassword);
		
		cajaNombre = new JTextField();
		cajaNombre.setFont(new Font("Arial", Font.PLAIN, 12));
		cajaNombre.setBounds(105, 50, 188, 20);
		panel.add(cajaNombre);
		cajaNombre.setColumns(10);
		
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creamos un empleado para acceder al metodo
				Empleado emp = new Empleado();
				Empleado empleado  = emp.leerDatos(cajaNombre.getText());
				@SuppressWarnings("deprecation")
				boolean result=empleado.evaluarIngreso(empleado.getPassword(), cajaContrasenia.getText());
				if(result)
					Main.ventanaPrincipal.abrirVentana(empleado);
			}
		});
		botonAceptar.setForeground(new Color(255, 255, 255));
		botonAceptar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		botonAceptar.setBackground(new Color(60, 179, 113));
		botonAceptar.setBounds(30, 167, 263, 34);
		panel.add(botonAceptar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setForeground(new Color(255, 255, 255));
		botonCancelar.setFont(new Font("Arial Black", Font.PLAIN, 14));
		botonCancelar.setBackground(new Color(240, 128, 128));
		botonCancelar.setBounds(30, 225, 261, 34);
		panel.add(botonCancelar);
		
		cajaContrasenia = new JPasswordField();
		cajaContrasenia.setFont(new Font("Arial", Font.PLAIN, 12));
		cajaContrasenia.setBounds(106, 117, 187, 20);
		panel.add(cajaContrasenia);
		
		JLabel etiquetaInicioDeSesion = new JLabel("Inicio de Sesion");
		etiquetaInicioDeSesion.setBounds(75, 11, 131, 29);
		panel.add(etiquetaInicioDeSesion);
		etiquetaInicioDeSesion.setForeground(new Color(255, 255, 255));
		etiquetaInicioDeSesion.setFont(new Font("Arial Black", Font.PLAIN, 14));
		
	}
}
