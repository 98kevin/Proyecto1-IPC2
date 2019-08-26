package frontend;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
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
		panelInterno.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(5, 5, 5, 5));
		getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mntmDesactivarRuta = new JMenu("Rutas");
		mntmDesactivarRuta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			    mntmDesactivarRuta.setSelected(true);
			}
		});
		menuBar.add(mntmDesactivarRuta);
		
		JMenuItem crearRuta = new JMenuItem("Crear");
		crearRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevaRuta(admin));
			}
		});
		mntmDesactivarRuta.add(crearRuta);
		
		JMenuItem mntmActivar = new JMenuItem("Activar");
		mntmActivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditar(true, "Ruta", "estado", "idRuta as Codigo, nombre, idDestino as CodigoDestino, estado", "idRuta"));
			}
		});
		mntmDesactivarRuta.add(mntmActivar);
		
		JMenuItem editarRuta = new JMenuItem("Desactivar");
		editarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditar(false, "Ruta", "estado", "idRuta as Codigo, nombre, idDestino as CodigoDestino, estado", "idRuta"));
			}
		});
		mntmDesactivarRuta.add(editarRuta);
		
		JMenu mnPuntosDeControl = new JMenu("Puntos de Control");
		menuBar.add(mnPuntosDeControl);
		
		JMenuItem mntmAgregar_1 = new JMenuItem("Agregar");
		mnPuntosDeControl.add(mntmAgregar_1);
		
		JMenuItem mntmEditar = new JMenuItem("Editar");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditarPunto());
			}
		});
		mnPuntosDeControl.add(mntmEditar);
		
		JMenuItem mntmEliminarPunto = new JMenuItem("Eliminar");
		mnPuntosDeControl.add(mntmEliminarPunto);
		
		JMenu menuReportes = new JMenu("Reportes");
		menuBar.add(menuReportes);
		
		JMenuItem mntmRutas = new JMenuItem("Rutas");
		mntmRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new ReporteDeRutas());
			}
		});
		menuReportes.add(mntmRutas);
		
		JMenuItem mntmGanancias = new JMenuItem("Ganancias");
		menuReportes.add(mntmGanancias);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		menuReportes.add(mntmClientes);
		
		JMenuItem mntmTopRutas = new JMenuItem("Top 3 Rutas");
		menuReportes.add(mntmTopRutas);
		
		JMenu menuEmpleados = new JMenu("Empleados");
		menuBar.add(menuEmpleados);
		
		JMenuItem mntmRegistarEmpleado = new JMenuItem("Registar");
		mntmRegistarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevoEmpleado());
			}
		});
		menuEmpleados.add(mntmRegistarEmpleado);
		
		JMenuItem mntmActivar_1 = new JMenuItem("Activar");
		mntmActivar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditar(true, "Empleado ", "estado", "cui, nombres, apellidos, salario ", "cui"));
			}
		});
		menuEmpleados.add(mntmActivar_1);
		
		JMenuItem mntmDesactivarEmp = new JMenuItem("Desactivar");
		mntmDesactivarEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditar(false, "Empleado ", "estado", "cui, nombres, apellidos, salario ", "cui"));
			}
		});
		menuEmpleados.add(mntmDesactivarEmp);
		
		JMenu mnPrecios = new JMenu("Precios");
		menuBar.add(mnPrecios);
		
		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditarPrecios());
			}
		});
		mnPrecios.add(mntmModificar);
		
		JMenu mnDestinos = new JMenu("Destinos");
		menuBar.add(mnDestinos);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormNuevosDestinos());
			}
		});
		mnDestinos.add(mntmAgregar);
		
		JMenuItem mntmEditar_1 = new JMenuItem("Editar");
		mntmEditar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    cambiarPanel(new FormEditarDestino());
			}
		});
		mnDestinos.add(mntmEditar_1);
		
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mnDestinos.add(mntmEliminar);
		
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
