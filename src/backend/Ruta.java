package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ruta implements Actualizable{
    
    public static final String COL_ID="idRuta";
    private static final String COL_NOMBRE="nombre";
    private static final String COL_ID_DESTINO="idDestino";
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
    
    
    public Ruta(ResultSet resultados) {
	try {
	    	this.idRuta=resultados.getInt(COL_ID);
	    	this.nombre = resultados.getString(COL_NOMBRE);
		this.idDestino = resultados.getInt(COL_ID_DESTINO);
		this.paquetesRegistrados = resultados.getInt(COL_PAQUETES_REGISTRADOS);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
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
	return "("+COL_NOMBRE+", "+COL_ID_DESTINO+", "+COL_PAQUETES_REGISTRADOS+")";
    }

    public int getPaquetesEnRuta(int idRuta) {
	int cant=0;
	try {
		Statement statemente = Main.conexion.createStatement();
		ResultSet  resultado = statemente.executeQuery("SELECT COUNT(*) "
			+ "FROM Paquete p, PuntoDeControl q "
			+ "WHERE q.idRuta ="+idRuta+" and p.idPunto=q.idPunto");
		resultado.next();
		cant= resultado.getInt(1);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return cant;
    }

    /**
    public String agregarPaqueteARuta(int idRuta) {
	    ResultSet consulta;
	    try {
		//consulta de cantidad de paquetes acutales 
		consulta=SqlConection.generarConsulta("cantidadDePaquetesRegistrados", "Ruta", "WHERE idRuta="+idRuta);
		consulta.next();
		int paquetesActuales= consulta.getInt(1);
		//sumamos una cantidad
		int sigCantidad = paquetesActuales+1;
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	   return  ("UPDATE Ruta SET=");
    }
    */
}
