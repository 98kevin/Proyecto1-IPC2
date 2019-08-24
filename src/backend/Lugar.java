/**
 * 
 */
package backend;

/**
 * @author kevin
 *
 */
public class Lugar {
    private int codigo;
    private String nombre; 
    /**
     * @param codigo
     * @param nombre
     * @param tipo
     */
    public Lugar(int codigo, String nombre) {
	super();
	this.codigo = codigo;
	this.nombre = nombre;
    }
    
    public Lugar() {
	super();
    }
    /**
     * @param nombre
     * @param tipo
     */
    public Lugar(String nombre) {
	super();
	this.nombre = nombre;
    }
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
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
    
    
}
