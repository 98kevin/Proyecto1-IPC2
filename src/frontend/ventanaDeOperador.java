package frontend;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaDeOperador extends JInternalFrame {

	private JInternalFrame frameInterno;
	private JPanel panelFrameInterno;

	/**
	 * Serializacion de la clase
	 */
	private static final long serialVersionUID = 3488964472195880088L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaDeOperador frame = new ventanaDeOperador();
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
	public ventanaDeOperador() {
		setTitle("Operador");
		setBorder(null);
		setBounds(100, 100, 740, 427);
		
		JPanel panelDeBotones = new JPanel();
		getContentPane().add(panelDeBotones, BorderLayout.WEST);
		panelDeBotones.setLayout(new GridLayout(6, 1, 5, 5));
		
		JButton botonSeleccionar = new JButton("Seleccionar Punto");
		panelDeBotones.add(botonSeleccionar);
		
		JButton botonConsultar = new JButton("Consutar Paquetes");
		panelDeBotones.add(botonConsultar);
		
		JButton botonRegistrar = new JButton("Registrar Paquete");
		panelDeBotones.add(botonRegistrar);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panelDeBotones.add(botonSalir);
		panelFrameInterno = new JPanel();
		getContentPane().add(panelFrameInterno, BorderLayout.CENTER);

	}

	public void setFrameInterno(JInternalFrame frameInterno) {
		this.frameInterno = frameInterno;
		panelFrameInterno.removeAll();
		panelFrameInterno.add(frameInterno, BorderLayout.CENTER);
		frameInterno.setVisible(true);
	}

}
