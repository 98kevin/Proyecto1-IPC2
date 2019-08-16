package frontend;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import backend.Administrador;

public class VentanaAdministrador extends JInternalFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6201627369893668279L;
	JInternalFrame frameInterno;
	JPanel panelInterno;

	/**
	 * Create the frame.
	 */
	public VentanaAdministrador(Administrador admin) {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(20);
		setTitle("Administrador ");
		setBorder(null);
		setBounds(100, 100, 901, 391);
		
		panelInterno = new JPanel();
		getContentPane().add(panelInterno, BorderLayout.CENTER);
		
		JLabel lblPanelInterno = new JLabel("Panel Interno");
		panelInterno.add(lblPanelInterno);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu menuRutas = new JMenu("Rutas");
		menuRutas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			    menuRutas.setSelected(true);
			}
		});
		menuBar.add(menuRutas);
		
		JMenuItem crearRuta = new JMenuItem("Crear");
		crearRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevaRuta(admin));
			}
		});
		menuRutas.add(crearRuta);
		
		JMenuItem editarRuta = new JMenuItem("Editar");
		menuRutas.add(editarRuta);
		
		JMenuItem eliminarRuta = new JMenuItem("Eliminar");
		menuRutas.add(eliminarRuta);
		
		JMenu menuReportes = new JMenu("Reportes");
		menuBar.add(menuReportes);
		
		JMenu menuEmpleados = new JMenu("Empleados");
		menuBar.add(menuEmpleados);
		
		JMenuItem mntmRegistarEmpleado = new JMenuItem("Registar Empleado");
		mntmRegistarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevoEmpleado());
			}
		});
		menuEmpleados.add(mntmRegistarEmpleado);
		
		JMenu mnPrecios = new JMenu("Precios");
		menuBar.add(mnPrecios);
		
		JMenu mnDestinos = new JMenu("Destinos");
		menuBar.add(mnDestinos);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevosDestinos());
			}
		});
		mnDestinos.add(mntmAgregar);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mnDestinos.add(mntmEliminar);
		
	}
	
	public JInternalFrame getFrameInterno() {
		return frameInterno;
	}
	
	private void cambiarPanel(JPanel nuevoPanel) {
	    setVisible(false);
	    remove(panelInterno);
	    panelInterno= nuevoPanel;
	    getContentPane().add(panelInterno);
	    setVisible(true);
	}

}
