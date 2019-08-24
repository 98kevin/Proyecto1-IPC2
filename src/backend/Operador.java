package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Operador extends Empleado{

    ArrayList<String> sentencias;
    
	public Operador(String cui, String nombres, String apellidos, double salario, String direccion, String correoElectronico,
			boolean estado,int tipoDeEmpleado, Date fechaDeContratacion, String nombreDeUsuario, String password) {
		super(cui, nombres, apellidos, salario, direccion, correoElectronico, estado,tipoDeEmpleado, fechaDeContratacion,password);
	}
	
	public Operador() {
	    super();
	}

	public void transferirPaquete(String idPaqueteTexto, String horasTexto) {
	    ResultSet consultaPuntoActual; 
	    try {
		consultaPuntoActual = SqlConection.generarConsulta("idPunto", "Paquete","WHERE idPaquete="+idPaqueteTexto);
		consultaPuntoActual.next();
		int puntoActual = consultaPuntoActual.getInt(1);
		int idPaquete = Integer.parseInt(idPaqueteTexto);
		int horas = Integer.parseInt(horasTexto);
		sentencias = new ArrayList<String>();
		    addSentenciaCambioPunto(idPaquete,puntoActual);
		    addSentenciaDeCosto(idPaquete, horas);
		    SqlConection.transaccion(sentencias);
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Error de formato de numeros", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
	    }catch(SQLException ex) {
		ex.printStackTrace();
	    }
	}
	
	public void addSentenciaCambioPunto(int idPaquete,int puntoActual) {
	    ResultSet consulta;
	    ResultSet consultaSigPunto;
	    int idRuta;
	    //Consultamos todos los puntos de la ruta del punto Actual 
	    consulta = SqlConection.generarConsulta("p.idRuta, p.lugarEnCola", 
		    "PuntoDeControl p",
		    "WHERE idPunto="+puntoActual);
	    try {
		consulta.next();
		//leemos el codigo de ruta
		 idRuta= consulta.getInt(1);
		 //leemos el lugar que ocupa el punto de control 
		 int lugarEnCola = consulta.getInt(2);
		 lugarEnCola++;
		 //Consulta del codigo del siguiente punto de control 
		 consultaSigPunto= SqlConection.generarConsulta("idPunto", 
			 "PuntoDeControl", 
			 "WHERE idRuta="+idRuta+
			 " AND lugarEnCola="+lugarEnCola);
		 consultaSigPunto.next();
		 //leemos el siguiente punto
		 int idSigPunto = consultaSigPunto.getInt(1);
		 //Si el codigo del siguiente punto es diferente de cero, cambiamos la referencia del punto de control 
		 if (idSigPunto!=0) 
		     sentencias.add("UPDATE Paquete SET idPunto="+idSigPunto+" WHERE idPaquete="+idPaquete);

	    } catch (SQLException e) {
		//Manejo de la excepcion por su codigo de error
		//Cuando la consulta esta vacia, es porque ya no hay puntos de control,  por lo tanto ya llego al destino
		if(e.getErrorCode() ==0) {
		    //Actualizamos que el paquete ya llego a su destino
		    sentencias.add("UPDATE Paquete SET llegoDestino=true WHERE idPaquete="+idPaquete);
		}
		e.printStackTrace();
	    }
	}
	
	public void addSentenciaDeCosto(int idPaquete, int horas) {
	    ResultSet consulta; 
	    double precioDePunto=0;
	    double costoActual=0;
	    double costoCalculado=0;
	    try {
		consulta = SqlConection.generarConsulta("costo", "Paquete", "WHERE idPaquete="+idPaquete);
		consulta.next();
		costoActual = consulta.getDouble(1);
		consulta = SqlConection.generarConsulta("r.precio", //columna 
			"Paquete p, PuntoDeControl q, Precios r" ,  //tablas 
			"WHERE p.idPaquete ="+idPaquete //primera condicion 
			+ " AND p.idPunto= q.idPunto " //segunda condicion 
			+ " AND q.tarifaDeOperacion = r.codigo "); //tercera condicion 
		consulta.next();
		 precioDePunto = consulta.getDouble(1);
		 costoCalculado=precioDePunto * horas; 
		 double nuevoCosto = costoActual+ costoCalculado;
		 sentencias.add("UPDATE Paquete SET costo="+nuevoCosto+ " WHERE idPaquete="+idPaquete);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	public void paquetesDelOperador(JTable table, String cui) {
	    DefaultTableModel modelo;
	    modelo = new DefaultTableModel();
	    ResultSet resultados =SqlConection.generarConsulta("idPaquete as Codigo, descripcion, priorizado, fechaDeIngreso, q.nombre as Ubicacion", 
	    		"Paquete p, PuntoDeControl q", ""
	    		+ "WHERE p.idPunto = q.idPunto "
	    		+ "AND q.cui="+cui 
	    		+" AND p.llegoDestino = false");
		Tablas.actualizarTabla(resultados, modelo);
		table.setModel(modelo);
	}
}
