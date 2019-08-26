package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import backend.Administrador;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class ReporteClientes extends JPanel {

    private Administrador admin;
    /**
     * 
     */
    private static final long serialVersionUID = -5502638884264436356L;
    private JTextField cajaCliente;

    /**
     * Create the panel.
     */
    public ReporteClientes() {
	admin = new Administrador();
    	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panelCentral = new JPanel();
    	add(panelCentral, BorderLayout.CENTER);
    	
    	panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
    	
    	JPanel panelOeste = new JPanel();
    	add(panelOeste, BorderLayout.WEST);
    	panelOeste.setLayout(new GridLayout(13, 1, 0, 0));
    	
    	JLabel lblFiltrar = new JLabel("Filtrar");
    	panelOeste.add(lblFiltrar);
    	
    	cajaCliente = new JTextField();
    	
    	panelOeste.add(cajaCliente);
    	cajaCliente.setColumns(10);
    	
    	JLabel lblDesde = new JLabel("Desde");
    	panelOeste.add(lblDesde);
    	
    	JDateChooser fechaInicial = new JDateChooser();
    	//Establecemos la fecha inicial al 01 de enero de 2018 
    	fechaInicial.setDate( new Date(1514851200000L));
    	panelOeste.add(fechaInicial);
    	
    	JLabel lblHasta = new JLabel("Hasta");
    	panelOeste.add(lblHasta);
    	
    	JDateChooser fechaFinal = new JDateChooser();
    	fechaFinal.addPropertyChangeListener(new PropertyChangeListener() {
    		public void propertyChange(PropertyChangeEvent evt) {
    		admin.reporteDeClientes(panelCentral, cajaCliente.getText(), fechaInicial.getDate(), fechaFinal.getDate());
    		}
    	});
    	fechaFinal.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
    	panelOeste.add(fechaFinal);
    	
    	fechaInicial.addPropertyChangeListener(new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
		admin.reporteDeClientes(panelCentral, cajaCliente.getText(), fechaInicial.getDate(), fechaFinal.getDate());
		}
	});
    	
    	cajaCliente.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
		    admin.reporteDeClientes(panelCentral, cajaCliente.getText(), fechaInicial.getDate(), fechaFinal.getDate());
		}
	});
    	
    	JButton btnExportarHtml = new JButton("Exportar HTML");
    	panelOeste.add(btnExportarHtml);
    	
    	admin.reporteDeClientes(panelCentral, cajaCliente.getText(), fechaInicial.getDate(), fechaFinal.getDate());
    }

}
