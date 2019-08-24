package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import backend.SqlConection;
import backend.Tablas;

public class FormEditarPrecios extends JPanel {
	private JTextField cajaCodigo;
	private JTextField cajaNuevoPrecio;
	private JTable table;

    /**
     * Create the panel.
     */
    public FormEditarPrecios() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.NORTH);
    	panel.setLayout(new GridLayout(2, 3, 10, 5));
    	
    	JLabel lblCodigo = new JLabel("Codigo");
    	panel.add(lblCodigo);
    	
    	cajaCodigo = new JTextField();
    	panel.add(cajaCodigo);
    	cajaCodigo.setColumns(10);
    	
    	JButton btnAceptar = new JButton("Aceptar");
    	btnAceptar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    SqlConection.generarActualizacion("Precios", " precio =" 
    		+Double.parseDouble(cajaNuevoPrecio.getText()),
    		" WHERE codigo='"+cajaCodigo.getText());
    		    setVisible(false);
    		}
    	});
    	panel.add(btnAceptar);
    	
    	JLabel lblNuevoPrecio = new JLabel("Nuevo precio");
    	panel.add(lblNuevoPrecio);
    	
    	cajaNuevoPrecio = new JTextField();
    	panel.add(cajaNuevoPrecio);
    	cajaNuevoPrecio.setColumns(10);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	DefaultTableModel modelo = new DefaultTableModel();
    	ResultSet resultado = SqlConection.generarConsulta("codigo, nombre, precio as monto", "Precios", "");
    	Tablas.actualizarTabla(resultado, modelo);
    	table.setModel(modelo);
    }

}
