package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import backend.Administrador;
import backend.GeneradorHTML;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReporteDeGanancias extends JPanel {
	/**
     * 
     */
    private static final long serialVersionUID = 7903275917333498511L;
	private JTable table;
	private Administrador admin;
	private JDateChooser fechaInicial;
	private JDateChooser fechaFinal;
	private JComboBox<String> comboTipo;
	private JTextField cajaFiltro;
	private String consulta;

    /**
     * Create the panel.
     */
	public ReporteDeGanancias() {
    	admin = new Administrador();
	setLayout(new BorderLayout(0, 0));
    	
    	JPanel panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(new BorderLayout(0, 0));
    	
    	JScrollPane scrollPane = new JScrollPane();
    	panel.add(scrollPane, BorderLayout.CENTER);
    	
    	table = new JTable();
    	scrollPane.setViewportView(table);
    	
    	JPanel panel_1 = new JPanel();
    	add(panel_1, BorderLayout.WEST);
    	panel_1.setLayout(new GridLayout(12, 1, 10, 10));
    	
    	JLabel lblFiltrar = new JLabel("Filtrar");
    	panel_1.add(lblFiltrar);
    	
    	cajaFiltro = new JTextField();
    	cajaFiltro.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyPressed(KeyEvent e) {
    		llamarConsulta(table, fechaInicial, fechaFinal, comboTipo, cajaFiltro.getText());
    		}
    	});
    	panel_1.add(cajaFiltro);
    	cajaFiltro.setColumns(10);
    	
    	JLabel lblDesde = new JLabel("Desde");
    	panel_1.add(lblDesde);
    	
    	fechaInicial = new JDateChooser();
    	panel_1.add(fechaInicial);    
    	
    	JLabel lblHasta = new JLabel("Hasta");
    	panel_1.add(lblHasta);
    	
    	fechaFinal = new JDateChooser();
    	panel_1.add(fechaFinal);
    	
    	//Fecha del inicio de los tiempos 
    	fechaInicial.setDate(new Date(0));
    	//fecha Actual 
    	fechaFinal.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
    	
    	fechaInicial.addPropertyChangeListener(new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
		  llamarConsulta(table, fechaInicial, fechaFinal, comboTipo, cajaFiltro.getText());
		}
	});
    	
    	fechaFinal.addPropertyChangeListener(new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
		    llamarConsulta(table, fechaInicial, fechaFinal, comboTipo, cajaFiltro.getText());
		}
	});
    	
    	JLabel lblTipoDeRuta = new JLabel("Tipo de Ruta");
    	panel_1.add(lblTipoDeRuta);
    	
    	comboTipo = new JComboBox<String>();
    	comboTipo.addItemListener(new ItemListener() {
    		public void itemStateChanged(ItemEvent e) {
    		llamarConsulta(table, fechaInicial, fechaFinal, comboTipo, cajaFiltro.getText());
    		}
    	});
    	comboTipo.addItem("Ambas");
    	comboTipo.addItem("Activas");
    	comboTipo.addItem("No Activas");
    	panel_1.add(comboTipo);
    	
    	JButton btnExportar = new JButton("Exportar HTML");
    	btnExportar.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    		    GeneradorHTML.generarHTML("Reporte de Ganancias", consulta);
    		}
    	});
    	panel_1.add(btnExportar);
    }

    private void llamarConsulta(JTable table, JDateChooser fechaInicial, JDateChooser fechaFinal, JComboBox<String> tipoDeRuta, String filtroNombre) {
	//Convertimos la fecha a la fecha SQL
	Date dateInitial = new Date(fechaInicial.getDate().getTime());
	Date dateFinal = new Date(fechaFinal.getDate().getTime());
	consulta = admin.reporteDeGanancias(table, dateInitial.toString(), dateFinal.toString(), tipoDeRuta, filtroNombre);
    }
    
}
