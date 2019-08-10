package frontend;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.Administrador;

public class FormNuevosDestinos extends JPanel {
	/**
     * 
     */
    private static final long serialVersionUID = -3490858344839752515L;
	private JTextField cajaNombre;
	private JTextField cajaPrecio;

    /**
     * Create the panel.
     */
    public FormNuevosDestinos() {
    	setLayout(new BorderLayout(0, 0));
    	
    	JLabel lblFormularioDeNuevos = new JLabel("Formulario de Nuevos Destinos");
    	lblFormularioDeNuevos.setFont(new Font("Dialog", Font.BOLD, 16));
    	lblFormularioDeNuevos.setHorizontalAlignment(SwingConstants.CENTER);
    	add(lblFormularioDeNuevos, BorderLayout.NORTH);
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(null);
    	
    	JLabel lblNombre = new JLabel("Nombre");
    	lblNombre.setBounds(12, 53, 60, 17);
    	panel.add(lblNombre);
    	
    	cajaNombre = new JTextField();
    	cajaNombre.setBounds(131, 51, 239, 21);
    	panel.add(cajaNombre);
    	cajaNombre.setColumns(10);
    	
    	JLabel lblPrecio = new JLabel("Precio");
    	lblPrecio.setBounds(12, 101, 60, 17);
    	panel.add(lblPrecio);
    	
    	cajaPrecio = new JTextField();
    	cajaPrecio.setBounds(131, 99, 114, 21);
    	panel.add(cajaPrecio);
    	cajaPrecio.setColumns(10);
    	
    	JPanel panel_1 = new JPanel();
    	add(panel_1, BorderLayout.SOUTH);
    	
    	JButton btnCancelar = new JButton("Cancelar");
    	btnCancelar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    setVisible(false);
    		}
    	});
    	panel_1.add(btnCancelar);
    	
    	JButton btnAceptar = new JButton("Aceptar");
    	btnAceptar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    Administrador em = new Administrador();
    		    em.crearDestino(cajaNombre.getText(), cajaPrecio.getText());
    		    setVisible(false);
    		}
    	});
    	panel_1.add(btnAceptar);

    }
}
