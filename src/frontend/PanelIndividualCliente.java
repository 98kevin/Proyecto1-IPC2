package frontend;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelIndividualCliente extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -7905308198661347733L;
    private JTable table;

    /**
     * Create the panel.
     */
    public PanelIndividualCliente(JLabel cliente, JTable table) {
    	setAutoscrolls(true);
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	add(panel, BorderLayout.NORTH);
    	
    	JLabel lblCliente = new JLabel("Cliente:");
    	panel.add(lblCliente);
    	
    	JLabel clienteSalida = cliente;
    	panel.add(clienteSalida);
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setAutoscrolls(true);
    	add(scrollPane, BorderLayout.CENTER);
    	
    	scrollPane.setViewportView(table);
	
    	setVisible(true);
    }

}
