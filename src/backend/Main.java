package backend;

import java.sql.*;

import frontend.Inicio;
import frontend.Login;

public class Main {

	private static final String SQL_USER="admin";
	private static final String PASSWORD="password";
	private static final String SQL_PORT="jdbc:mysql://localhost:3306/";
	private static final String DATABASE_NAME="CodeNBugs";
	public static Connection conexion;
	public static Inicio ventanaPrincipal;
	public static boolean tm =false;
	
	public static void main(String[] args) {
		try {
			conexion = DriverManager.getConnection(SQL_PORT+DATABASE_NAME, SQL_USER, PASSWORD);
		} catch (SQLException e ) {
			e.printStackTrace();
		}
		ventanaPrincipal = new Inicio();
		Login inicioDeSesion = new Login();
		ventanaPrincipal.setFrameInterno(inicioDeSesion);
		ventanaPrincipal.setVisible(true);
	}
	/**
	//1. Obtener una conexion con la base de datos
	Connection  conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Colegio", "root", "");
	//2. Crear una sentencia
	Statement sentencias = conexion.createStatement();
	//3. Ejecutar una consulta SQL
	ResultSet results = sentencias.executeQuery("SELECT * FROM Alumnos");
	//4. Procesar el conjunto de resultados
	while (results.next()) {
		System.out.println(results.getString(1)+", "+results.getString(2));
	}
	
	//para ejecutar insersiones en la base de datos 
	String sqlSentence="INSERT INTO Alumnos VALUES ('Marcelino',15)";
	sentencias.executeUpdate(sqlSentence);
	*/
}
