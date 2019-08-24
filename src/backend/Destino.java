package backend;

public class Destino implements Actualizable {

    public static final String TABLA = "Destino ";
    public static final String COL_CODIGO="idDestino";
    public static final String COL_PRECIO="precio";
    public static final String COL_NOMBRE="nombre";
    
    private String codigo;
    private String nombre;
    private double precio;

    /**
     * Crea un nuevo destino con codigo asiganado
     * @param codigo
     * @param nombre
     * @param precio
     */
    public Destino(String codigo, String nombre, double precio) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
	this.precio = precio;
    }


    /**
     * Crea un nuevo destino con codigo autogenerado por la BD
     * @param nombre
     * @param precio
     */
    public Destino(String nombre, double precio) {
	super();
	this.nombre = nombre;
	this.precio = precio;
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
    public String getColumnas() {
	return "("+COL_PRECIO+","+COL_NOMBRE+")";
    }

    @Override
    public String getSentence() {
	return "("+this.getPrecio()+",'"
		    +this.getNombre()+"')";
    }
}
