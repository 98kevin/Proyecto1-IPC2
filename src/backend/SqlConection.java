package backend;

import java.sql.*;

import javax.swing.JOptionPane;

public class SqlConection {

	static Statement sentencias;
	
	public static void escribirRegistro(String table, String columnas, String registro) {
		try {
			sentencias = Main.conexion.createStatement();
			System.out.println("INSERT INTO "+ table+columnas + "VALUES" + registro);
			sentencias.executeUpdate("INSERT INTO "+ table+columnas + "VALUES" + registro);
			JOptionPane.showMessageDialog(null, "Registro agregado con exito", table+" agregado a la BD", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet generarConsulta(String columnas, String tablas, String condiciones) {
	    ResultSet consulta = null;
	    try {
		sentencias= Main.conexion.createStatement();
		consulta = sentencias.executeQuery("SELECT "+columnas+" FROM "+tablas+" "+condiciones);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	    return consulta;
	}
	
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
}
