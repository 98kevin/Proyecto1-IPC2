package frontend;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import backend.Recepcionista;

public class ConsultaPaquetesRetirados extends JPanel {
	private JTable table;
	private Recepcionista recepcionista;
    /**
     * Create the panel.
     */
    public ConsultaPaquetesRetirados() {
	recepcionista = new Recepcionista();
    	setLayout(new BorderLayout(0, 0));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	recepcionista.consultarPaquetesRetirados(table);
    	scrollPane.setViewportView(table);

    }

}
