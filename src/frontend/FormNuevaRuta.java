package frontend;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FormNuevaRuta extends JPanel {

    /**
     * Create the panel.
     */
    public FormNuevaRuta() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblCreacionDeNuevas = new JLabel("Creacion De Nuevas Rutas");
    	lblCreacionDeNuevas.setHorizontalAlignment(SwingConstants.CENTER);
    	add(lblCreacionDeNuevas, BorderLayout.NORTH);
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(null);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setBounds(12, 23, 60, 17);
    	panel.add(lblNombre);
	
    }
}
