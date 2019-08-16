package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import backend.Recepcionista;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ventanaRecepcionista extends JInternalFrame {

	/**
	 * Serializacion de la clase <code>VentanaRecepcionista</code>
	 */
	private static final long serialVersionUID = -7284277872685317365L;
	private JPanel panelInterno ;
	/**
	 * Create the frame.
	 */
	public ventanaRecepcionista(Recepcionista recepcionista) {
		setTitle("Recepcionista");
		setBorder(null);
		setBounds(100, 100, 683, 434);
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 6, 0, 0));
		
		JButton btnNuevoPaquete = new JButton("Nuevo");
		btnNuevoPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevoPaquete());
			}
		});
		panelNorte.add(btnNuevoPaquete);
		
		 panelInterno = new JPanel();
		 getContentPane().add(panelInterno, BorderLayout.CENTER);
		
	}
	
	
	private void cambiarPanel(JPanel nuevoPanel) {
	    setVisible(false);
	    remove(panelInterno);
	    panelInterno= nuevoPanel;
	    getContentPane().add(panelInterno);
	    setVisible(true);
	}

}
