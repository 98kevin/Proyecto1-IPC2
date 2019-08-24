package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import backend.SqlConection;
import backend.Tablas;

import javax.swing.JTable;

public class FormEditar extends JPanel {
	private JTextField cajaCodigo;
	private ResultSet resultados;
	private JTable table;
    /**
     * Create the panel.
     */
    public FormEditar(boolean operacion, String tabla, String columnaAComparar, String columnas, String llavePrimaria) {
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.RIGHT);
    	add(panel, BorderLayout.NORTH);
    	
    	JLabel lblCodigo = new JLabel("Codigo");
    	panel.add(lblCodigo);
    	
    	cajaCodigo = new JTextField();
    	panel.add(cajaCodigo);
    	cajaCodigo.setColumns(10);
    	
    	JButton btnHecho = new JButton("Hecho");
    	btnHecho.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    String codigo = cajaCodigo.getText();
    		    SqlConection.generarActualizacion(" "+tabla+" ", " "+columnaAComparar+" = "+operacion," WHERE "+llavePrimaria+"="+codigo);
    		    setVisible(false);
    		}
    	});
    	panel.add(btnHecho);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	//generamos consulta de las rutas acivas
    	resultados = SqlConection.generarConsulta(columnas, 
    		tabla, "WHERE "+columnaAComparar+"="+(!operacion));
    	DefaultTableModel modelo = new DefaultTableModel();
    	Tablas.actualizarTabla(resultados, modelo);
    	table.setModel(modelo);
    }

}
