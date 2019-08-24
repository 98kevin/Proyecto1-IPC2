package frontend;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import backend.Recepcionista;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormConsultaDePaquetes extends JPanel {
	private JTextField cajaCodigoPaquete;
	private JTable table;
	private Recepcionista recepcionista;

    /**
     * Create the panel.
     */
    public FormConsultaDePaquetes() {
	recepcionista = new Recepcionista();
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.NORTH);
    	panel.setLayout(new GridLayout(2, 1, 0, 0));
    	
    	JPanel panel_1 = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
    	panel.add(panel_1);
    	
    	JLabel lblPaquetesListosA = new JLabel("Paquetes listos a Entregar");
    	panel_1.add(lblPaquetesListosA);
    	
    	JPanel panel_2 = new JPanel();
    	panel.add(panel_2);
    	panel_2.setLayout(new GridLayout(2, 2, 5, 5));
    	
    	JLabel lblCodigo = new JLabel("Codigo");
    	panel_2.add(lblCodigo);
    	
    	JLabel lblDescripcionPaquete = new JLabel("Descripcion del paquete");
    	
    	cajaCodigoPaquete = new JTextField();
    	cajaCodigoPaquete.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		    if (e.getKeyCode() == KeyEvent.VK_ENTER) 
			recepcionista.consultarPaquete(cajaCodigoPaquete.getText(), lblDescripcionPaquete);
    		} 
    	});
    	panel_2.add(cajaCodigoPaquete);
    	cajaCodigoPaquete.setColumns(10);
    	
    	JLabel lblDescripcion = new JLabel("Descripcion");
    	panel_2.add(lblDescripcion);
    	
    	
    	panel_2.add(lblDescripcionPaquete);
    	
    	JPanel panel_3 = new JPanel();
    	add(panel_3, BorderLayout.CENTER);
    	panel_3.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel_4 = new JPanel();
    	panel_3.add(panel_4, BorderLayout.NORTH);
    	
    	JButton btnEntregar = new JButton("Entregar");
    	btnEntregar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    recepcionista.entregarPaquete(cajaCodigoPaquete.getText(), table);
    		    lblDescripcionPaquete.setText("");
    		    cajaCodigoPaquete.setText("");
    		}
    	});
    	panel_4.add(btnEntregar);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	panel_3.add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	recepcionista.consultarPaquetesListos(table);
    }

}
