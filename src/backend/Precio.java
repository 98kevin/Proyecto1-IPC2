package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Precio implements Actualizable{
    
	public final static String PRECIO_GLOBAL ="global";
	public final static String PRECIO_PRIORIZACION ="priorizacion";
	public static final String TABLA = "Precios";
	
	private final String COL_CODIGO="codigo";
	private final String COL_NOMBRE="nombre";
	private final String COL_MONTO="precio";
	
	
	private String codigo;
	private String nombre;
	private double precio;
	/**
	 * @param codigo
	 * @param nombre
	 * @param precio
	 */
	public Precio(String codigo, String nombre, double precio) {
	    super();
	    this.codigo = codigo;
	    this.nombre = nombre;
	    this.precio = precio;
	}
	
	public Precio() {
	    super();
	}
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
	    return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
	    this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
	    return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
	    this.nombre = nombre;
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
	public String getSentence() {
	    return "('"+this.getCodigo()+"', '"+this.getNombre()+"', "+this.precio+")";
	}
	
	@Override
	public String getColumnas() {
	    return "("+COL_CODIGO+", "+COL_NOMBRE+", "+COL_MONTO+")";
	}
	
	public double calcularPrecio(int libras, int idDestino,boolean priorizado) {
	    	double precioResultante=0;
		ResultSet consultaPrecioDestino = SqlConection.generarConsulta("precio", "Destino","WHERE idDestino="+idDestino); //solo se consulta una columna
		ResultSet consultaTarifa = SqlConection.generarConsulta("precio", "Precios","WHERE codigo='tarifa_libra'");
		ResultSet consultaPriorizado = SqlConection.generarConsulta("precio", "Precios","WHERE codigo='priorizacion'");
		double precioDestino=0;
		double precioLibra=0;
		double precioPriorizado=0;
		try {
			if (consultaPrecioDestino.next())
			    precioDestino = consultaPrecioDestino.getDouble(1);  //primera columna
			if(consultaTarifa.next()) 
			    precioLibra = consultaTarifa.getDouble(1); //primera columna
			if(consultaPriorizado.next()) 
			    precioPriorizado = consultaPriorizado.getDouble(1); //primera columna
		precioResultante = (priorizado) ? (precioDestino + (libras*precioLibra)+ precioPriorizado) :
		    (precioDestino + (libras*precioLibra));
		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, "Error de lectura de informacion en la base de datos");
		    e.printStackTrace();
		}
		return precioResultante;
	}

	public double getTotal(LinkedList<Paquete> paquetes) {
	    double total=0;
	    for (int i = 0; i < paquetes.size(); i++) {
		total = total + paquetes.get(i).getPrecio();
	    }
	    return total;
	}

	public double calcularCambio(String ingreso, String totalFactura) {
	    double cambio=0;
	  try {
	    double dineroEntrante = Double.parseDouble(ingreso);
	    double total = Double.parseDouble(totalFactura);
	    if(total>dineroEntrante) {
		JOptionPane.showMessageDialog(null, "No puede pagar con esa cantidad");
		cambio = dineroEntrante-total;
	    }
	    else
		cambio = dineroEntrante-total;
	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "Ingrese una cantidad numerica");
	}
	    return cambio;
	}
}
