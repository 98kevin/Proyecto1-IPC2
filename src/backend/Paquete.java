package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Paquete implements Actualizable{
    
    private int idPaquete;
    private String descripcion;
    private String idPunto;
    private int idDestino;
    private Date fechaDeIngreso;
    private int casillero;
    private boolean priorizado;
    private int libras;
    private int idFactura;
    private double precioCliente;
    private double costoEmpresa;
    private boolean retirado;
    private boolean estaBodega;
    private boolean llegoDestino;

    private final String COL_DESCR="descripcion";
    private final String COL_ID_PUNTO="idPunto";
    private final String COL_ID_DESTINO = "idDestino";
    private final String COL_FECHA_INGRESO="fechaDeIngreso";
    private final String COL_CASILLERO="casilleroCliente";
    private final String COL_PRIORIZADO="priorizado";
    private final String COL_LIBRAS="libras";
    private final String COL_ID_FACTURA="idFactura";
    private final String COL_COSTO="costo";
    private final String COL_PRECIO="precioCliente";
    private final String COL_RETIRADO="retirado";
    private final String COL_ESTA_BODEGA="estaBodega";
    private final String COL_LLEGO_DESTINO="llegoDestino";
    
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
    public Paquete(int idPaquete, 
	    String descripcion, 
	    String idPunto, 
	    int idDestino,
	    Date fechaDeIngreso, 
	    int casillero, 
	    boolean priorizado,
	    int libras,
	    int idFactura) {
	super();
	this.idPaquete = idPaquete;
	this.descripcion = descripcion;
	this.idPunto = idPunto;
	this.idDestino=idDestino;
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
    public Paquete(String casillero,String descripcion,  String libras,  boolean priorizado,String idDestino, int idFactura) {
	super();
	try {
		this.casillero = Integer.parseInt(casillero);
		this.descripcion = descripcion;
		this.libras = Integer.parseInt(libras);
		this.priorizado = priorizado;
		this.idDestino = Integer.parseInt(idDestino);
		this.idFactura=idFactura;
		this.estaBodega=true;
		this.llegoDestino=false;
	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "Error en los formatos de ingreso. Ingrese valores validos. ");
	}
    }
    
    public Paquete() {
	super();
    }
    
    /**
     * Crea un nuevo paquete en base a un ResulSet de una consulta
     * @param paquete
     */
    public Paquete(ResultSet paquete) {
	super();
	try {
	    	this.idPaquete = paquete.getInt(1);
	    	this.descripcion = paquete.getString(2);
    		this.idPunto =paquete.getString(3);
    		this.idDestino=paquete.getInt(4);
    		this.fechaDeIngreso =paquete.getDate(5);
    		this.casillero =paquete.getInt(6);
    		this.priorizado = paquete.getBoolean(7);
    		this.libras = paquete.getInt(8);
    		this.idFactura = paquete.getInt(9);
    		this.costoEmpresa=paquete.getDouble(10);
    		this.precioCliente =paquete.getDouble(11);
    		this.retirado =paquete.getBoolean(12);
    		this.estaBodega=paquete.getBoolean(13);
    		this.llegoDestino=paquete.getBoolean(14);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	
    }
    /**
     * @return the idDestino
     */
    public int getIdDestino() {
        return idDestino;
    }

    /**
     * @param idDestino the idDestino to set
     */
    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    /**
     * @return the idPaquete
     */
    public int getIdPaquete() {
        return idPaquete;
    }
    /**
     * @param idPaquete the idPaquete to set
     */
    public void setIdPaquete(int idPaquete) {
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
        return retirado;
    }
    /**
     * @param retiradoPorDuenio the retiradoPorDuenio to set
     */
    public void setRetiradoPorDuenio(boolean entregado) {
        this.retirado = entregado;
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
        return retirado;
    }
    /**
     * @return the bodega
     */
    public boolean isBodega() {
        return estaBodega;
    }
    /**
     * @param bodega the bodega to set
     */
    public void setBodega(boolean bodega) {
        this.estaBodega = bodega;
    }
    /**
     * @return the llego
     */
    public boolean isLlego() {
        return llegoDestino;
    }
    /**
     * @param llego the llego to set
     */
    public void setLlego(boolean llego) {
        this.llegoDestino = llego;
    }
    /**
     * @param entregado the entregado to set
     */
    public void setEntregado(boolean entregado) {
        this.retirado = entregado;
    }
    /**
     * @param casillero the casillero to set
     */
    public void setCasillero(int casillero) {
        this.casillero = casillero;
    }
    
    public JTable llenarTablaDePaquetes(JTable table, LinkedList <Paquete> paquetes) {
	String columnas [] = {"Codigo","Descripcion", "Destino", "Precio"};
	Object datos [][]= new Object[paquetes.size()][4];
	int ultimo = SqlConection.getUltimo("Paquete", "idPaquete");
	String nombreDestino=null;
	ResultSet destino=null;
	//lee todos los paquetes en la lista enlzada y los los agrega a la tabla
	for (int i = 0; i < paquetes.size(); i++) {
	    datos[i][0]=(ultimo+1+i);
	    try {
		 destino =SqlConection.generarConsulta("nombre", "Destino", "WHERE idDestino="+paquetes.get(i).getIdDestino());
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
	return new JTable(new DefaultTableModel(datos, columnas));
    }
    
    @Override
    public String getColumnas() {
	return "("+COL_DESCR+", "
		+COL_ID_PUNTO+", "
		+COL_ID_DESTINO+", "
		+COL_FECHA_INGRESO+", "
		+COL_CASILLERO+", "
		+COL_PRIORIZADO+", "
		+COL_LIBRAS+", "
		+COL_ID_FACTURA+", "
		+COL_COSTO+", "
		+COL_PRECIO+", "
		+COL_RETIRADO+", "
		+COL_ESTA_BODEGA +", "
		+COL_LLEGO_DESTINO+")";
    }
    @Override
    public String getSentence() {
	/*
	 * El parametro de idRuta es null, porque no se ha ingresado a ninguna ruta, 
	 * El costo de operacion al inicio es de 0
	 * Aun no ha sido retirado por lo que el parametro es 'false'
	 */
	return "('"+this.getDescripcion()
	+"', "+this.getIdPunto()
	+", "+this.getIdDestino()
	+", '"+getFechaHoraSQL()
	+"', "+this.getCasillero()
	+", "+this.isPriorizado()
	+", "+this.getLibras()
	+", "+this.getIdFactura()
	+", 0"
	+ ", "+this.getPrecio()
	+",false"
	+ ",true"
	+ ",false)" ; 
    }

    private String getFechaHoraSQL() {
	long timeInMillis= Calendar.getInstance().getTimeInMillis();
	Date f = new Date(timeInMillis);
	Time h= new Time(timeInMillis);
	return f.toString()+" "+h.toString();
    }
    
    public boolean integro() {
	return (this.getCasillero()>=0 & this.idDestino>=0 & this.getLibras()>0) ? true : false;
    }
    
    public String getIdRuta(String codigo) {
	String ruta =null;
	ResultSet consulta=SqlConection.generarConsulta("r.idRuta","Paquete p, PuntoDeControl q, Ruta r", 
		"WHERE p.idPaquete = "+codigo
		+ " AND p.idPunto=q.idPunto "
		+ " AND q.idRuta = r.idRuta");
	try {
	    consulta.next();
	    ruta = consulta.getString(1);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return ruta;
    }
}
