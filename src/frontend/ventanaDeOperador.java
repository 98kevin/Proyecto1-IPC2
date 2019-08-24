package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import backend.Operador;
import backend.SqlConection;
import backend.Tablas;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ventanaDeOperador extends JInternalFrame {

	private JInternalFrame frameInterno;
	private JPanel panelInterno;
	private JPanel panelCentral;
	private DefaultTableModel modelo;
	private ResultSet resultados;

	/**
	 * Serializacion de la clase
	 */
	private static final long serialVersionUID = 3488964472195880088L;
	private JTextField cajaCodigo;
	private JTable table;
	private JTextField cajaHoras;
	private Operador operador;

	/**
	 * Create the frame.
	 */
	public ventanaDeOperador(String cui) {
	    operador = new Operador();
	    
		setTitle("Operador");
		setBorder(null);
		setBounds(100, 100, 740, 427);
		panelInterno = new JPanel();
		getContentPane().add(panelInterno, BorderLayout.CENTER);
		panelInterno.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panelInterno.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JLabel lblCodigo = new JLabel("Codigo");
		panel.add(lblCodigo);
		
		cajaCodigo = new JTextField();
		panel.add(cajaCodigo);
		cajaCodigo.setColumns(10);
		
		JLabel lblHoras = new JLabel("Horas");
		panel.add(lblHoras);
		
		cajaHoras = new JTextField();
		panel.add(cajaHoras);
		cajaHoras.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    operador.transferirPaquete(cajaCodigo.getText(), cajaHoras.getText());
			    cajaCodigo.setText("");
			    cajaHoras.setText("");
			    operador.paquetesDelOperador(table, cui);
			}
		});
		panel.add(btnEnviar);
		
		panelCentral = new JPanel();
		panelInterno.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		panelCentral.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		operador.paquetesDelOperador(table,cui);
		
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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

	

	
	public void setFrameInterno(JInternalFrame frameInterno) {
		this.frameInterno = frameInterno;
		panelInterno.removeAll();
		panelInterno.add(frameInterno, BorderLayout.CENTER);
		frameInterno.setVisible(true);
	}

}
