package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import backend.Recepcionista;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ventanaRecepcionista extends JInternalFrame {

	/**
	 * Serializacion de la clase <code>VentanaRecepcionista</code>
	 */
	private static final long serialVersionUID = -7284277872685317365L;
	private JPanel panelInterno;
	private ventanaRecepcionista propioFrame;
	/**
	 * Create the frame.
	 */
	public ventanaRecepcionista(Recepcionista recepcionista) {
		setTitle("Recepcionista");
		setBorder(null);
		setBounds(100, 100, 683, 434);
		
		propioFrame=this;
		
		 panelInterno = new JPanel();
		 getContentPane().add(panelInterno, BorderLayout.CENTER);
		 
		 JMenuBar menuBar = new JMenuBar();
		 setJMenuBar(menuBar);
		 
		 JMenu mnPaquete = new JMenu("Paquete");
		 menuBar.add(mnPaquete);
		 
		 JMenuItem mntmNuevoPaquete = new JMenuItem("Nuevo Paquete");
		 mntmNuevoPaquete.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	   cambiarPanel(new FormNuevoPaquete(propioFrame));
		 	}
		 });
		 mnPaquete.add(mntmNuevoPaquete);
		 
		 JMenuItem mntmVerEstado = new JMenuItem("Ver Estado");
		 mntmVerEstado.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	    cambiarPanel(new FormConsultaEstadoPaquete());
		 	}
		 });
		 mnPaquete.add(mntmVerEstado);
		 
		 JMenu mnConsultar = new JMenu("Consultar");
		 menuBar.add(mnConsultar);
		 
		 JMenuItem mntmPaquetesEnRuta = new JMenuItem("Paquetes en Ruta");
		 mntmPaquetesEnRuta.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	    cambiarPanel(new ConsultaPaquetesEnRuta());
		 	}
		 });
		 mnConsultar.add(mntmPaquetesEnRuta);
		 
		 JMenuItem mntmPaquetesListos = new JMenuItem("Paquetes Listos");
		 mntmPaquetesListos.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	    cambiarPanel(new FormConsultaDePaquetes());
		 	}
		 });
		 mnConsultar.add(mntmPaquetesListos);
		 
		 JMenuItem mntmPaquetesEntregados = new JMenuItem("Paquetes entregados");
		 mntmPaquetesEntregados.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	    cambiarPanel(new ConsultaPaquetesRetirados());
		 	}
		 });
		 mnConsultar.add(mntmPaquetesEntregados);
		 
		 JMenu mnSesion = new JMenu("Sesion");
		 menuBar.add(mnSesion);
		 
		 JMenuItem mntmSalir = new JMenuItem("Salir");
		 mntmSalir.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 	    Inicio.setFrameInterno(new Login());
		 	}
		 });
		 mnSesion.add(mntmSalir);
	}
	
	
	public void cambiarPanel(JPanel nuevoPanel) {
	    setVisible(false);
	    remove(panelInterno);
	    panelInterno= nuevoPanel;
	    getContentPane().add(panelInterno);
	    setVisible(true);
	}

}
