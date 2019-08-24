package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Destino extends Lugar  implements Actualizable {

    public static final String TABLA = "Destino ";
    public static final String COL_CODIGO="idDestino";
    public static final String COL_PRECIO="precio";
    public static final String COL_NOMBRE="nombre";
    
    private double precio;


    /**
     * @param codigo
     * @param nombre
     * @param tipo
     * @param precio
     */
    public Destino(int codigo, String nombre,double precio) {
	super(codigo, nombre);
	this.precio = precio;
    }

    public Destino() {
	super();
    }
    /**
     * Crea un nuevo destino con codigo autogenerado por la BD
     * @param nombre
     * @param precio
     */
    public Destino(String nombre, double precio) {
	super(nombre);
	this.precio = precio;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String getColumnas() {
	return "("+COL_PRECIO+","+COL_NOMBRE+")";
    }

    @Override
    public String getSentence() {
	return "("+this.getPrecio()+",'"
		    +this.getNombre()+"')";
    }

    public void agregarDestinosATabla(JTable table) {
	ResultSet consulta = SqlConection.generarConsulta("idDestino as Codigo, nombre, precio as Monto", "Destino", "");
	DefaultTableModel model = new DefaultTableModel();
	Tablas.actualizarTabla(consulta, model);
	table.setModel(model);
    }

    public void actualizarDestino(String idDestino, String nuevoPrecio) {
	SqlConection.generarActualizacion("Destino", "precio="+
		Double.parseDouble(nuevoPrecio), 
		"WHERE idDestino="+idDestino);
    }

    public void consultarDestino(String idDestino, JTextField cajaNombre, JTextField cajaPrecio) {
	ResultSet consulta = SqlConection.generarConsulta("nombre, precio", "Destino", "WHERE idDestino="+idDestino);
	try {
	    consulta.next();
	    cajaNombre.setText(consulta.getString(1));
	    cajaPrecio.setText(consulta.getString(2));
	} catch (SQLException e) {
	    if(e.getErrorCode()==0)
		JOptionPane.showMessageDialog(null, "No existe un destino con ese codigo, vefique su ingreso");
	    e.printStackTrace();
	}
	
    }
}
