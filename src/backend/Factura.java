package backend;

import java.sql.Date;

public class Factura implements Actualizable{
    private int idFactura;
    private int casilleroCliente;
    private Date fecha;
    private double total;
    
    @SuppressWarnings("unused")
    private final String COL_ID_FACTURA="idFactura";
    private final String COL_CASILLERO_CLIENTE="casilleroCliente";
    private final String COL_FECHA="fecha";
    private final String COL_TOTAL="total";
    
    /**
     * @param idFactura
     * @param casilleroCliente
     * @param fecha
     * @param total
     */
    public Factura(int idFactura, int casilleroCliente, Date fecha, double total) {
	super();
	this.idFactura = idFactura;
	this.casilleroCliente = casilleroCliente;
	this.fecha = fecha;
	this.total = total;
    }
    
    public Factura(int casilleroCliente, Date fecha, double total) {
	super();
	this.casilleroCliente = casilleroCliente;
	this.fecha = fecha;
	this.total = total;
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
     * @return the casilleroCliente
     */
    public int getCasilleroCliente() {
        return casilleroCliente;
    }
    /**
     * @param casilleroCliente the casilleroCliente to set
     */
    public void setCasilleroCliente(int casilleroCliente) {
        this.casilleroCliente = casilleroCliente;
    }
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }
    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }
    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
    @Override
    public String getSentence() {
	return "("+this.getCasilleroCliente()+",'"
		+this.getFecha().toString()+"',"
		+this.getTotal()+")";
    }
    @Override
    public String getColumnas() {
	return "("+COL_CASILLERO_CLIENTE+", "
		+COL_FECHA+", "
		+COL_TOTAL+")";
    }
    
}
