package backend;

public class Ruta implements Actualizable{
    
    public static final String COL_ID="idRuta";
    private static final String COL_NOMBRE="nombre";
    private static final String COL_PAQUETES_REGISTRADOS="cantidadDePaquetesRegistrados";
    public static final String TABLA = "Ruta";
    
    private int idRuta;
    private String nombre;
    private int idDestino;
    private int paquetesRegistrados;
    /**
     * @param idRuta
     * @param nombre
     * @param idDestino
     * @param paquetesRegistrados
     */
    public Ruta(String nombre, int idDestino, int paquetesRegistrados) {
	super();
	this.nombre = nombre;
	this.idDestino = idDestino;
	this.paquetesRegistrados = paquetesRegistrados;
	
    }
    
    public Ruta() {
	super();
    }
    
    
    /**
     * @return the idRuta
     */
    public int getIdRuta() {
        return idRuta;
    }
    /**
     * @param idRuta the idRuta to set
     */
    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
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
     * @return the paquetesRegistrados
     */
    public int getPaquetesRegistrados() {
        return paquetesRegistrados;
    }
    /**
     * @param paquetesRegistrados the paquetesRegistrados to set
     */
    public void setPaquetesRegistrados(int paquetesRegistrados) {
        this.paquetesRegistrados = paquetesRegistrados;
    }

    @Override
    public String getSentence() {
	return "('"+this.nombre+"',"
		+ this.idDestino+ ", "
		+ this.paquetesRegistrados+")";
    }

    @Override
    public String getColumnas() {
	return "("+COL_NOMBRE+", "+COL_ID+", "+COL_PAQUETES_REGISTRADOS+")";
    }
    
    
}
