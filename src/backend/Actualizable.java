package backend;

/**
 * Implementa los metodos necesarios para la escritura en la base de datos. 
 * @author kevin
 *
 */
public interface Actualizable {
    /**
     * Crea una instruccion SQL para ingresar datos al servidor
     * @return Una instruccion SQL para ingresar a la base de datos
     */
    public String getSentence();
    
    public String getColumnas();
}
