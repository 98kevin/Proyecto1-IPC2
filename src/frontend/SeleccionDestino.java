package frontend;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import backend.SqlConection;
import backend.Tablas;

public class SeleccionDestino extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6492119180669712209L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private ResultSet resultados;
    /**
     * Create the frame.
     */
    public SeleccionDestino() {
	setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(contentPane);
	
	JPanel panel = new JPanel();
	contentPane.add(panel, BorderLayout.NORTH);
	
	JLabel lblFiltrar = new JLabel("Filtrar");
	panel.add(lblFiltrar);
	
	textField = new JTextField();
	panel.add(textField);
	textField.setColumns(10);
	
	JScrollPane scrollPane = new JScrollPane();
	contentPane.add(scrollPane, BorderLayout.CENTER);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	llenarTabla("");
	
	textField.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
		    if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			llenarTabla(textField.getText());
		    }
		}
	});
    }
    
    protected void llenarTabla(String text) {
	//consulta solo por palabras parciales
	resultados= SqlConection.generarConsulta("idDestino , nombre, precio", "Destino", " WHERE nombre LIKE '%"+text+"%' "
		+ "ORDER BY idDestino"); 
	DefaultTableModel model =new DefaultTableModel();
	if(table==null)
	    table = new JTable();
	Tablas.actualizarTabla(resultados,model);
	table.setModel(model);
    }

}
