package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import backend.Recepcionista;

public class FormConsultaEstadoPaquete extends JPanel {
	private JTextField cajaCodigoPaquete;
	private JTextField cajaNombreRuta;
	private JTextField cajaPuntoDeControl;
	private JTextField cajaPuntosFaltantes;
	private JTextField cajaFechaDeIngreso;
	private Recepcionista recepcionista;
	
    /**
     * Create the panel.
     */
    public FormConsultaEstadoPaquete() {
	recepcionista = new Recepcionista();
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) panel.getLayout();
    	flowLayout.setAlignment(FlowLayout.LEFT);
    	add(panel, BorderLayout.NORTH);
    	
    	JLabel lblPaquete = new JLabel("Paquete: ");
    	panel.add(lblPaquete);
    	
    	cajaNombreRuta = new JTextField();
    	cajaNombreRuta.setEditable(false);
    	cajaNombreRuta.setColumns(10); 
    	
    	cajaPuntoDeControl = new JTextField();
    	cajaPuntoDeControl.setEditable(false);
    	cajaPuntoDeControl.setColumns(10);
    	
    	cajaPuntosFaltantes = new JTextField();
    	cajaPuntosFaltantes.setEditable(false);
    	cajaPuntosFaltantes.setColumns(10);
    	
    	cajaFechaDeIngreso = new JTextField();
    	cajaFechaDeIngreso.setEditable(false);
    	cajaFechaDeIngreso.setColumns(10);
    	
    	JProgressBar barraProgreso = new JProgressBar();
    	barraProgreso.setValue(3);
    	barraProgreso.setForeground(new Color(30, 144, 255));
    	
    	cajaCodigoPaquete = new JTextField();
    	cajaCodigoPaquete.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    recepcionista.estadoDePaquete(cajaCodigoPaquete.getText(), 
    			    cajaNombreRuta, cajaPuntoDeControl, 
    			    cajaPuntosFaltantes, cajaFechaDeIngreso, 
    			    barraProgreso);
    		}
    	});
    	cajaCodigoPaquete.setToolTipText("Presione enter para buscar");
    	panel.add(cajaCodigoPaquete);
    	cajaCodigoPaquete.setColumns(10);
    	
    	JPanel panel_1 = new JPanel();
    	add(panel_1, BorderLayout.CENTER);
    	panel_1.setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel_3 = new JPanel();
    	panel_1.add(panel_3, BorderLayout.CENTER);
    	
    	JLabel lblRuta = new JLabel("Ruta");
    	
    	JLabel lblPuntoDeControl = new JLabel("Punto de Control");
    	
    	JLabel lblPuntosDeControl = new JLabel("Puntos de Control Faltantes");
    	
    	JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso");
    	
    	JLabel lblEstadoEnRuta = new JLabel("Estado en Ruta");
    	GroupLayout gl_panel_3 = new GroupLayout(panel_3);
    	gl_panel_3.setHorizontalGroup(
    		gl_panel_3.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_panel_3.createSequentialGroup()
    				.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
    					.addGroup(gl_panel_3.createSequentialGroup()
    						.addContainerGap()
    						.addComponent(barraProgreso, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
    					.addGroup(gl_panel_3.createSequentialGroup()
    						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
    							.addGroup(gl_panel_3.createSequentialGroup()
    								.addGap(12)
    								.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
    									.addComponent(lblRuta, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
    									.addComponent(lblPuntoDeControl, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
    							.addGroup(gl_panel_3.createSequentialGroup()
    								.addContainerGap()
    								.addComponent(lblPuntosDeControl, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
    							.addGroup(gl_panel_3.createSequentialGroup()
    								.addContainerGap()
    								.addComponent(lblFechaDeIngreso, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
    						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
    							.addComponent(cajaPuntoDeControl)
    							.addComponent(cajaPuntosFaltantes)
    							.addComponent(cajaNombreRuta, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
    							.addComponent(cajaFechaDeIngreso))
    						.addGap(95)))
    				.addGap(37))
    			.addGroup(gl_panel_3.createSequentialGroup()
    				.addContainerGap()
    				.addComponent(lblEstadoEnRuta, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
    				.addContainerGap(400, Short.MAX_VALUE))
    	);
    	gl_panel_3.setVerticalGroup(
    		gl_panel_3.createParallelGroup(Alignment.LEADING)
    			.addGroup(gl_panel_3.createSequentialGroup()
    				.addGap(12)
    				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblRuta)
    					.addComponent(cajaNombreRuta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addGap(12)
    				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblPuntoDeControl)
    					.addComponent(cajaPuntoDeControl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblPuntosDeControl)
    					.addComponent(cajaPuntosFaltantes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblFechaDeIngreso)
    					.addComponent(cajaFechaDeIngreso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addComponent(lblEstadoEnRuta)
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addComponent(barraProgreso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				.addGap(88))
    	);
    	panel_3.setLayout(gl_panel_3);
	
    }
}
