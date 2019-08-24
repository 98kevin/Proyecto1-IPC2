package backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SqlConection {

	static Statement sentencias;
	
	/**
	 * Escribe nuevos registros en la base de datos 
	 * @param table
	 * @param columnas
	 * @param registro
	 */
	public static void escribirRegistro(String table, String columnas, String registro) {
		try {
			sentencias = Main.conexion.createStatement();
			if(Main.tm)
			    System.out.println("INSERT INTO "+ table+columnas + "VALUES" + registro);
			sentencias.executeUpdate("INSERT INTO "+ table+columnas + "VALUES" + registro);
			JOptionPane.showMessageDialog(null, "Registro agregado con exito", table+" agregado a la BD", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para generar consultas basicas
	 * @param columnas Las columnas a consultar 
	 * @param tablas Las tablas donde se va a consultar
	 * @param condiciones Las condiciones para generar la nueva consulta
	 * @return
	 */
	public static ResultSet generarConsulta(String columnas, String tablas, String condiciones) {
	    ResultSet consulta = null;
	    try {
		sentencias= Main.conexion.createStatement();
		if(Main.tm)
		    System.out.println("SELECT "+columnas+" FROM "+tablas+" "+condiciones);
		consulta = sentencias.executeQuery("SELECT "+columnas+" FROM "+tablas+" "+condiciones);
	    } catch (SQLException e) {
		if(e.getErrorCode()==1054)
		    JOptionPane.showMessageDialog(null, "Error de sintaxis");
		e.printStackTrace();
	    }
	    return consulta;
	}
	
	
	/**
	 * Consulta preparada para consultar un cliente
	 * @param casillero
	 * @return
	 */
	public static ResultSet consultarCliente(int casillero) {
	    ResultSet consulta = null;
	    try {
		 PreparedStatement sentencia = Main.conexion.prepareStatement("SELECT * FROM Cliente WHERE casillero= ? ");
		 sentencia.setInt(1, casillero);
		 consulta=sentencia.executeQuery();
	    } catch (SQLException e) {
		e.printStackTrace();
		System.out.println(e.getErrorCode());
	    }
	    return consulta;
	}
	
	/**
	 * Obtiene el maximo valor de una tabla
	 * @param tabla La tabla a consultar
	 * @param columna La columna a consultar
	 * @return
	 */
	public static int getUltimo(String tabla, String columna) {
	    int ultimo = 0 ;
	    try {
		sentencias = Main.conexion.createStatement();
		if(Main.tm)
		    System.out.println("SELECT MAX("+columna+") FROM "+tabla);
		ResultSet consulta = sentencias.executeQuery("SELECT MAX("+columna+") FROM "+tabla);
		consulta.next();
		ultimo = consulta.getInt(1);
	    } catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "Ocurrio un error de lectura del codigo");
	    }
	    return ultimo;
	}
	
	
	/**
	 * Metodo para ejecutar transacciones en la base de datos
	 * @param sentencias El conjunto de intrucciones que se tiene que ejecutar
	 */
	public static void transaccion(ArrayList<String> sentencias) {
	    try {
		Main.conexion.setAutoCommit(false);
		for (int i = 0; i < sentencias.size(); i++) {
		    Statement sentencia = Main.conexion.createStatement();
		    if(Main.tm)
			System.out.println(sentencias.get(i));
		    	sentencia.executeUpdate(sentencias.get(i));
		}
		JOptionPane.showMessageDialog(null, "Ingreso con exito");
	    } catch (SQLException e) {
		e.printStackTrace();
		try {
		    Main.conexion.rollback();
		} catch (SQLException e1) {
		    e1.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Error al intentar reestabler la integridad de la base de datos");
		}
		JOptionPane.showMessageDialog(null, "No se completo la transaccion, intente de nuevo");
	    } finally {
		try {
		    Main.conexion.setAutoCommit(true);
		} catch (SQLException e2) {
		    e2.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Ocurrio un error durante el cirre de la conexion");
		}
	    }
	}
	
	
	/**
	 * Metodo para generar actualizaciones en la base de datos 
	 * @param tabla La tabla donde se va a modificar
	 * @param modificaciones Las modificaciones a la columnas
	 * @param where Las condiciones de llaves primarias. 
	 */
	public static void generarActualizacion(String tabla, String modificaciones, String where) {
	    try {
		sentencias = Main.conexion.createStatement();
		if(Main.tm)
		    System.out.println("UPDATE "+tabla+" SET "+modificaciones+" "+where+" ");
		sentencias.executeUpdate("UPDATE "+tabla+" SET "+modificaciones+" "+where+" ");
		JOptionPane.showMessageDialog(null, "Actualizacion realizada con exito");
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
}
