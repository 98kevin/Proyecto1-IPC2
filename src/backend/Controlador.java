package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;


public class Controlador implements Runnable{
    
	private static final String SQL_USER="admin";
	private static final String PASSWORD="password";
	private static final String SQL_PORT="jdbc:mysql://localhost:3306/";
	private static final String DATABASE_NAME="ControlPaquetes";
	
	private static final int MINUTOS_ESPERA=0;
	private static final int SEGUNDOS_ESPERA=60;
	
	
	public static Connection conexion;
	
	public Controlador() {
	    super();
	}
	
	@Override
	public void run() {
	    long miliSegundos;
	    Time hora ;
	    LinkedList<Paquete> paquetes;
	    try {
		//Generamos la conexion con la base de datos
		conexion = DriverManager.getConnection(SQL_PORT+DATABASE_NAME, SQL_USER, PASSWORD);
		//calculo del tiempo en milisegundos
		miliSegundos=(MINUTOS_ESPERA*60 + SEGUNDOS_ESPERA)*1000 ;
		while (true) {
		    paquetes =leerPaquetes();
		    enviarPaquetes(paquetes);
		    hora = new Time(Calendar.getInstance().getTimeInMillis());
		    System.out.println("Revision de rutina realizada "+hora.toString());
		    try {
			//detenemos el hilo por el tiempo determinado
			Thread.sleep(miliSegundos);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	    } catch (SQLException e ) {
		e.printStackTrace();
	    }
	}

	public static void enviarPaquetes(LinkedList <Paquete> paquetes) {
		ordenarPaquetes(paquetes);
		for (int i = 0; i < paquetes.size(); i++) {
		    enviarAlPrimerPunto((paquetes.get(i)));
		}
	}
	
	/**
	 * Lee todos los paquetes que se encuentran en la bodega 
	 */
	private static LinkedList<Paquete> leerPaquetes() {
	    LinkedList<Paquete> paquetesEnCola=null;
	    try {
		Statement sentencia = conexion.createStatement();
		paquetesEnCola = new LinkedList<Paquete>();
		//lee todos los paquetes de bodega segun su fecha de ingreso
		ResultSet paquetes = sentencia.executeQuery("SELECT * "
			+ "FROM Paquete "
			+ "WHERE estaBodega=1 "
			+ "AND retirado=0 "
			+ " ORDER BY fechaDeIngreso");
		while (paquetes.next()) {
		    Paquete paquete = new Paquete(paquetes);
		    paquetesEnCola.add(paquete);
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return paquetesEnCola;
	}
	
	/**
	 * Ordena los paquetes por orden de priorizacion. 
	 * @param paquetesEnCola
	 */
	private static void ordenarPaquetes(LinkedList<Paquete> paquetes) {
		for (int i = 0; i < paquetes.size(); i++) {
		    //si el paquete esta priorizado lo analiza para cambiarlo de lugar
			if (paquetes.get(i).isPriorizado()) {
			    for (int j = i; j > 0; j--) {
				if (getIdRuta(paquetes.get(j))  == getIdRuta(paquetes.get(j-1)) || ! paquetes.get(j-1).isPriorizado()) {
				    //creamos una referencia temporal del objeto
				    Paquete temp = paquetes.get(j-1);
				    //cambiamos los indices en la lista enlazada
				    paquetes.set((j-1), paquetes.get(j));
				    paquetes.set(j, temp);
				}
			    }
			}
		}
	    }
	    
	
	/**
	 * Lee la base de datos en busca de la ruta del paquete
	 * @param paquete El paquete que contiene la informacion
	 * @return
	 */
	private static int getIdRuta(Paquete paquete) {
	    int idRuta=0;
	    try {
		Statement sentencia =conexion.createStatement();
		ResultSet resultado = sentencia.executeQuery("Select idRuta "
			+ "from Paquete p, PuntoDeControl q  "
			+ "where p.idPaquete=" +paquete.getIdPaquete()+" "
				+ "and q.idPunto="+paquete.getIdPunto());
		if (resultado.next()) {
		    idRuta= resultado.getInt(1);
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return idRuta;
	}
	
	
	/**
	 * Envia el paquete al primer punto de la ruta 
	 * @param paquete
	 */
	private static void enviarAlPrimerPunto(Paquete paquete) {
	    //Se leen todas las rutas del destino
	   ArrayList<Ruta> rutas = leerRutas(paquete.getIdDestino());
	   Ruta ruta;
	   Statement sentencia;
	   int paquetesEnPunto=0;
	   int capacidad=0;
	   int idPuntoControl=0;
	   //Se encuentra la ruta con menos saturacion
	   ruta = rutaMenosSaturada(rutas);
	   try {
	     //Se consulta cuantos paquetes hay en el primer punto de control
		   sentencia=conexion.createStatement();
		   ResultSet consultaPaquetes =sentencia.executeQuery("SELECT COUNT(*) "
		   	+ "FROM Paquete p, PuntoDeControl q "
		   	+ "WHERE q.idRuta="+ruta.getIdRuta() +" "
		   		+ "AND p.idPunto=q.idPunto");
		 //se consulta la capacidad del primer punto de control en la ruta
		   sentencia=conexion.createStatement();
		   ResultSet consultaCapacidad = sentencia.executeQuery("SELECT capacidad, idPunto "
		   	+ "FROM PuntoDeControl "
		   	+ "WHERE idRuta="+ruta.getIdRuta() +" "
		   		+ "AND lugarEnCola=1");
	       //Se leen los valores de la consulta
	       if(consultaPaquetes.next())
		       paquetesEnPunto= consultaPaquetes.getInt(1);
	       if(consultaCapacidad.next())
		   	capacidad=consultaCapacidad.getInt(1);
	       		idPuntoControl=consultaCapacidad.getInt(2);
	   } catch (SQLException e) {
	       e.printStackTrace();
	   } catch (NullPointerException ex) {
	       ex.printStackTrace();
	   }
	   //Evaluamos si el punto de control aun tiene capacidad
	   if(capacidad>paquetesEnPunto) {
	       generarActualizacion(" Paquete ", " idPunto ="+idPuntoControl
		       +",  estaBodega =false "," WHERE idPaquete  ="+paquete.getIdPaquete());
	   }
	}
	
	/**
	 * Genera una actualizacion con la base de datos
	 * @param tabla
	 * @param modificaciones
	 * @param where
	 */
	private static void generarActualizacion(String tabla, String modificaciones, String where) {
	    Statement sentencias;
	    try {
		sentencias = conexion.createStatement();
		sentencias.executeUpdate("UPDATE "+tabla+" SET "+modificaciones+" "+where+" ");
	    } catch (SQLException e) {
		e.printStackTrace();
	    }	    
	}

	/**
	 * Busca la ruta que contiene menos paquetes. 
	 * @param rutas El arreglo de rutas en donde va a buscar
	 * @return La ruta con menos paquetes 
	 */
	private static Ruta rutaMenosSaturada(ArrayList<Ruta> rutas) {
	    Ruta rutaDelPaquete=null;
	    if (rutas.size()==1) 
		rutaDelPaquete=rutas.get(0);
	    if(rutas.size()>1) {
		rutaDelPaquete= rutas.get(0);
		for (int i = 1; i < rutas.size(); i++) {
		    if(rutas.get(i).getPaquetesEnRuta(rutas.get(i).getIdRuta()) < 
			    rutaDelPaquete.getPaquetesEnRuta(rutaDelPaquete.getIdRuta()))
			rutaDelPaquete=rutas.get(i);
	    }
	    }
	    return rutaDelPaquete;
	}
	
	/**
	 * Lee todas las rutas del destino
	 * @param idDestino El codigo del destino a leer
	 * @return Un arreglo de rutas
	 */
	private static ArrayList<Ruta> leerRutas(int idDestino) {
	    ArrayList<Ruta>  rutas =null;
	    try {
	    Statement sentencia = conexion.createStatement();
	    ResultSet resultados = sentencia.executeQuery("SELECT * FROM Ruta WHERE idDestino ="+idDestino+" AND estado=true");
	   rutas = new ArrayList<Ruta>();
	    while (resultados.next()){
		rutas.add(new Ruta(resultados));
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	    return rutas;
	}

}
