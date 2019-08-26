package frontend;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import backend.Administrador;

public class ReporteTopRutas extends JPanel {
	private JTable table;
	private Administrador admin;
	
    /**
     * Create the panel.
     */
    public ReporteTopRutas() {
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
    	panel_1.setLayout(new GridLayout(13, 1, 0, 5));
    	
    	JLabel lblDesde = new JLabel("Desde");
    	panel_1.add(lblDesde);
    	
    	JDateChooser fechaInicial = new JDateChooser();
    	fechaInicial.setDate(new Date(1514851200000L));
    	panel_1.add(fechaInicial);
    	
    	JLabel lblHasta = new JLabel("Hasta");
    	panel_1.add(lblHasta);
    	
    	JDateChooser fechaFinal = new JDateChooser();
    	fechaFinal.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
    	panel_1.add(fechaFinal);
    	
    	JButton btnExportarHtml = new JButton("Exportar HTML");
    	panel_1.add(btnExportarHtml);

    	admin.reporteTopRutas(table, fechaInicial.getDate(), fechaFinal.getDate());
    }

}
