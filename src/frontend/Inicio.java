package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Administrador;
import backend.Empleado;
import backend.Recepcionista;

public class Inicio extends JFrame {
	/**
	 * Serialiazacion de la clase Inicio
	 */
	private static final long serialVersionUID = 2401811622519448998L;
	private JPanel contentPane;
	private JInternalFrame frameInterno;
	private JPanel panelFrameInterno;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelEncabezado = new JPanel();
		panelEncabezado.setBackground(new Color(0, 206, 209));
		contentPane.add(panelEncabezado, BorderLayout.NORTH);
		
		JLabel etiqueteTitulo = new JLabel("Code \u2018n Bugs");
		etiqueteTitulo.setForeground(new Color(255, 255, 255));
		etiqueteTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
		panelEncabezado.add(etiqueteTitulo);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(153, 204, 204));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panelFrameInterno = new JPanel();
		panelCentral.add(panelFrameInterno, BorderLayout.CENTER);
		panelFrameInterno.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPieDeFormulario = new JPanel();
		panelPieDeFormulario.setBackground(new Color(0, 206, 209));
		contentPane.add(panelPieDeFormulario, BorderLayout.SOUTH);
		panelPieDeFormulario.setLayout(new GridLayout(4, 1, 5, 5));
		
		JLabel etiqueteDerechosReservados = new JLabel("Copyright 2019. Todos los derechos reservados.");
		etiqueteDerechosReservados.setForeground(new Color(255, 255, 255));
		panelPieDeFormulario.add(etiqueteDerechosReservados);
	}

	
	public JInternalFrame getFrameInterno() {
		return frameInterno;
	}
	
	public void setFrameInterno(JInternalFrame frameInterno) {
		this.frameInterno = frameInterno;
		panelFrameInterno.removeAll();
		panelFrameInterno.add(frameInterno,BorderLayout.CENTER);
		frameInterno.setVisible(true);
	}
	

	public void abrirVentana(Empleado empleado) {
		int tipo = empleado.getTipo();
		switch (tipo) {
		case 1:
			this.setFrameInterno(new VentanaAdministrador( 
				new Administrador(empleado)));
			break;
		default:
		    	this.setFrameInterno(new ventanaRecepcionista(
		    		new Recepcionista( empleado)));
			break;
		}
		
	}
	
}
