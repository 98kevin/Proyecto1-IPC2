package frontend;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.Recepcionista;

public class ConsultaPaquetesEnRuta extends JPanel {
	private JTable table;
	private Recepcionista recepcionista;
    /**
     * Create the panel.
     */
    public ConsultaPaquetesEnRuta() {
	recepcionista = new Recepcionista();
    	setLayout(new BorderLayout(0, 0));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	recepcionista.consultarPaquetesEnRuta(table);
    	scrollPane.setViewportView(table);

    }

}
