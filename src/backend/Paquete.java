package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Paquete {
    
    String idPaquete;
    private String descripcion;
    String idPunto;
    Date fechaDeIngreso;
    int casillero;
    boolean priorizado;
    int libras;
    int idFactura;
    double precioCliente;
    double costoEmpresa;
    boolean entregado;
    private String idDestino;

    /**
     * @param idPaquete
     * @param idPunto
     * @param fechaDeIngreso
     * @param nitDuenio
     * @param priorizado
     * @param libras
     * @param idDestino
     * @param idFactura
     * @param tarifaDeOperacion
     * @param precioDestino
     */
    public Paquete(String idPaquete, 
	    String descripcion, 
	    String idPunto, 
	    Date fechaDeIngreso, 
	    int casillero, 
	    boolean priorizado,
	    int libras,
	    int idFactura) {
	super();
	this.idPaquete = idPaquete;
	this.descripcion = descripcion;
	this.idPunto = idPunto;
	this.fechaDeIngreso = fechaDeIngreso;
	this.casillero = casillero;
	this.priorizado = priorizado;
	this.libras = libras;
	this.idFactura = idFactura;
	this.costoEmpresa=0;
    }
    /**
     * Crea un nuevo paquete en memoria pero no lo ingresa a la BD
     * @param casillero
     * @param descripcion
     * @param libras
     * @param priorizado
     * @param idDestino
     * @param precioDestino
     */
    public Paquete(String casillero,String descripcion,  String libras,  boolean priorizado,String idDestino) {
	super();
	try {
		this.casillero = Integer.parseInt(casillero);
		this.descripcion = descripcion;
		this.libras = Integer.parseInt(libras);
		this.priorizado = priorizado;
		this.idDestino = idDestino;
	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "Error en los formatos de ingreso. Ingrese valores validos. ");
	}
    }
    
    public Paquete() {
	super();
    }
    
    /**
     * @return the idDestino
     */
    public String getIdDestino() {
        return idDestino;
    }

    /**
     * @param idDestino the idDestino to set
     */
    public void setIdDestino(String idDestino) {
        this.idDestino = idDestino;
    }

    /**
     * @return the idPaquete
     */
    public String getIdPaquete() {
        return idPaquete;
    }
    /**
     * @param idPaquete the idPaquete to set
     */
    public void setIdPaquete(String idPaquete) {
        this.idPaquete = idPaquete;
    }
    /**
     * @return the idPunto
     */
    public String getIdPunto() {
        return idPunto;
    }
    /**
     * @param idPunto the idPunto to set
     */
    public void setIdPunto(String idPunto) {
        this.idPunto = idPunto;
    }
    /**
     * @return the fechaDeIngreso
     */
    public Date getFechaDeIngreso() {
        return fechaDeIngreso;
    }
    /**
     * @param fechaDeIngreso the fechaDeIngreso to set
     */
    public void setFechaDeIngreso(Date fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }
    /**
     * @return the nitDuenio
     */
    public int getCasillero() {
        return casillero;
    }
    /**
     * @param nitDuenio the nitDuenio to set
     */
    public void setNitDuenio(int casillero) {
        this.casillero = casillero;
    }
    /**
     * @return the priorizado
     */
    public boolean isPriorizado() {
        return priorizado;
    }
    /**
     * @param priorizado the priorizado to set
     */
    public void setPriorizado(boolean priorizado) {
        this.priorizado = priorizado;
    }
    /**
     * @return the libras
     */
    public int getLibras() {
        return libras;
    }
    /**
     * @param libras the libras to set
     */
    public void setLibras(int libras) {
        this.libras = libras;
    }

    /**
     * @return the idFactura
     */
    public int getIdFactura() {
        return idFactura;
    }
    /**
     * @param idFactura the idFactura to set
     */
    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    /**
     * @return the costo
     */
    public double getCosto() {
        return costoEmpresa;
    }
    /**
     * @param costo the costo to set
     */
    public void setCosto(double costo) {
        this.costoEmpresa = costo;
    }
    /**
     * @return the precioDestino
     */
    public double getPrecio() {
        return precioCliente;
    }
    /**
     * @param precioDestino the precioDestino to set
     */
    public void setPrecio(double precio) {
        this.precioCliente = precio;
    }
    /**
     * @return the retiradoPorDuenio
     */
    public boolean isRetiradoPorDuenio() {
        return entregado;
    }
    /**
     * @param retiradoPorDuenio the retiradoPorDuenio to set
     */
    public void setRetiradoPorDuenio(boolean entregado) {
        this.entregado = entregado;
    }
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * @return the entregado
     */
    public boolean isEntregado() {
        return entregado;
    }
    /**
     * @param entregado the entregado to set
     */
    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }
    /**
     * @param casillero the casillero to set
     */
    public void setCasillero(int casillero) {
        this.casillero = casillero;
    }
    
    public void llenarTablaDePaquetes(JTable table, LinkedList <Paquete> paquetes) {
	String columnas [] = {"Codigo","Descripcion", "Destino", "Precio"};
	Object datos [][]= new Object[paquetes.size()][4];
	int ultimo = SqlConection.getUltimo("Paquete", "idPaquete");
	String nombreDestino=null;
	//lee todos los paquetes en la lista enlzada y los los agrega a la tabla
	for (int i = 0; i < paquetes.size(); i++) {
	    datos[i][0]=(ultimo+1+i);
	    try {
		 ResultSet destino =SqlConection.generarConsulta("nombre", "Destino", "WHERE idDestino="+paquetes.get(i).getIdDestino());
		 destino.next();
		 nombreDestino= destino.getString(1);
	    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar la base de datos");
	    }
	    datos[i][1]=paquetes.get(i).getDescripcion()+", "+paquetes.get(i).getLibras()+" libras";
	    datos[i][2]= nombreDestino;
	    Precio calculador= new Precio();
	    double precio =calculador.calcularPrecio(paquetes.get(i).getLibras(),
		    paquetes.get(i).getIdDestino(), 
		    paquetes.get(i).isPriorizado());
	    paquetes.get(i).setPrecio(precio);
	    datos[i][3]=precio;
	}
	table = new JTable(new DefaultTableModel(datos, columnas));
    }
    
}
