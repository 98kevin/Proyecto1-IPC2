package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Administrador extends Empleado{
    
	public Administrador(String cui, String nombres, String apellidos, double salario, String direccion, String correoElectronico,
			boolean estado,int tipoDeEmpleado, Date fechaDeContratacion, String password) {
		super(cui, nombres, apellidos, salario,direccion, correoElectronico, estado,tipoDeEmpleado, fechaDeContratacion,password);
	}

	public Administrador() {
	    super();
	}
	
	public Administrador(Empleado empleado) {
	    super();
		   this.setCui(empleado.getCui());
		   this.setNombres(empleado.getNombres());
		   this.setApellidos(empleado.getApellidos());
		   this.setDireccion(empleado.getDireccion());
		   this.setFechaDeContratacion(empleado.getFechaDeContratacion());
		   this.setTipo(empleado.getTipo());
		   this.setPassword(empleado.getPassword());
		   this.setActivo(empleado.isActivo());
	}
	
	public void crearRuta(String nombre, int idDestino) {
	    try {
		//creamos una nueva ruta con los parametros dados, con cero paquetes registrados
	 		Ruta nuevaRuta =new Ruta(nombre,idDestino, 0);
	 		//nos aseguramos que el autoCommit este activado
	 		Main.conexion.setAutoCommit(true);
	 		//Escribimos el registro en la base de datos
	 		SqlConection.escribirRegistro("Ruta", 
	 			nuevaRuta.getColumnas(), 
	 			nuevaRuta.getSentence());
	 	    } catch (NumberFormatException e) {
	 		JOptionPane.showMessageDialog(null, "Formato de numero incorrecto");
	 	    } catch(SQLException ex) {
	 		ex.printStackTrace();
	 	    }
	}
	
	public void setCapacidadDePuntoDeContro(PuntoDeControl punto, int nuevaCapacidad) {
	    
	    
	}
	
	public void setCuotaDeDestino(Destino destino) {}
	
	public void desactivarRuta(Ruta ruta) {}
	
	/**
	 * Asigna las sentencias al arraylist para la transaccion
	 * @param sentencias El arrego de sentencias
	 * @param listaDePuntos Los puntos de control a agregar
	 */
	public void obtenerSentencias(ArrayList<String> sentencias, LinkedList<PuntoDeControl> listaDePuntos) {
	    //obtenemos una sentencia para cada registro
	    for (int i = 0; i < listaDePuntos.size(); i++) {
		sentencias.add("INSERT INTO "+ 
			PuntoDeControl.TABLA+
			listaDePuntos.get(i).getColumnas() +
			"VALUES" + listaDePuntos.get(i).getSentence());
	    }
	}
	
	public void quitarPuntosDeControl(Ruta ruta) {}
	
	public void setPrecioPorLibra(double nuevoPrecio) {}
	
	public void setPrecioDePriorizacion(double nuevoPrecio) {}

	public void registrarNuevoEmpleado(String cui, String nombres, String apellidos, String salarioTexto, String direccion, String correo, int tipo,
		Date fecha, String pass1, String pass2) {
	    double salario= Double.parseDouble(salarioTexto);
	    try {
		if(pass1.equals(pass2)) {
	    	    Empleado nuevoEmpleado =new Empleado(cui,nombres,apellidos,salario,direccion,correo,true,tipo,fecha,pass1);
	    	    SqlConection.escribirRegistro(Empleado.TABLA, 
	    		    nuevoEmpleado.getColumnas(), 
	    		    nuevoEmpleado.getSentence()); 
	    	}
	    	else
	    	    JOptionPane.showMessageDialog(null, "Contrasenias no coinciden", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Salario no valido", "Error de Formato", JOptionPane.ERROR_MESSAGE);
	    }
	    
	}

	public void crearDestino(String textoNombreDestino, String textoPrecio) {
	   try {
		if(textoNombreDestino.length()<=20) {
		    double precio = Double.parseDouble(textoPrecio);
		    Destino nuevoDestino = new Destino(textoNombreDestino, precio);
		    SqlConection.escribirRegistro(Destino.TABLA,
			    nuevoDestino.getColumnas(), 
			    nuevoDestino.getSentence());
		}
	} catch (NumberFormatException e) {
	    JOptionPane.showMessageDialog(null, "El formato del precio es incorrecto, ingrese un valor numerico", "Error de precio", JOptionPane.ERROR_MESSAGE);
	}
	}
	
	
	public String contadorDePaquetesEnRuta(String fechaInicial, String fechaFinal) {
	    String statement="(";
	    statement= "(SELECT COUNT(*)   "
	    	+ "FROM Paquete p, PuntoDeControl q, Ruta r     "
	    	+ "WHERE p.idPunto=q.idPunto   "
	    	+ "AND q.idRuta= r.idRuta "
	    	+ "AND q.idRuta=c.idRuta "
	    	+ "AND p.llegoDestino=FALSE";
	    if(!fechaInicial.equals(""))
		statement = statement +(" AND p.fechaDeIngreso >= '" +fechaInicial+"'");
	    if(!fechaFinal.equals("")) 
		statement = statement +(" AND p.fechaDeIngreso <= '" +fechaFinal+"'");
	    statement = statement +(")");
	    return statement;
	}
	
	public String contadorDePaquetesFueraDeRuta(String fechaInicial, String fechaFinal) {
	    String statement="(";
	    statement= "(SELECT COUNT(*)   "
	    	+ "FROM Paquete p, PuntoDeControl q, Ruta r     "
	    	+ "WHERE p.idPunto=q.idPunto   "
	    	+ "AND q.idRuta= r.idRuta "
	    	+ "AND q.idRuta=c.idRuta "
	    	+ "AND p.llegoDestino=TRUE";
	    if(!fechaInicial.equals("")) {
		statement = statement + (" AND p.fechaDeIngreso >= '" +fechaInicial+"'");
	    }
	    if(!fechaFinal.equals("")) {
		statement = statement +(" AND p.fechaDeIngreso <= '" +fechaFinal+"'");
	    }
	    statement = statement +(")");
	    return statement;
	}
	
	/**
	 * 
	 * @param tabla
	 * @param fechaInicial
	 * @param fechaFinal
	 * @param rutasActivas
	 * @param rutasInactivas
	 */
	public void reporteDeRutas(JTable tabla, String fechaInicial, String fechaFinal, JComboBox<String> tipoDeRuta) {
	    Statement consulta;
	    try {
		consulta = Main.conexion.createStatement();
		//generamos la consulta con la base de datos 
		String filtroDeEstado="";
		if (tipoDeRuta.getSelectedItem().equals("Ambas")) 
		    filtroDeEstado =" ";
		if(tipoDeRuta.getSelectedItem().equals("Activas"))
		    filtroDeEstado =" AND c.estado=TRUE";
		if(tipoDeRuta.getSelectedItem().equals("No Activas"))
		    filtroDeEstado =" AND c.estado=FALSE";
		ResultSet resultados = consulta.executeQuery(
			//campos a mostar con sus respectivos alias
			"SELECT c.idRuta as 'Codigo', c.nombre , d.nombre as 'Destino', c.estado, "
			//el contador de paquetes fuera de fuera de ruta 
			+contadorDePaquetesFueraDeRuta(fechaInicial, fechaFinal)+ " as 'Paquetes Fuera',"
			//el contador de paquetes en ruta acutalmente
			+ contadorDePaquetesEnRuta(fechaInicial, fechaFinal)+ " as 'Paquetes en Ruta' "
			//Las tablas doonde se consultara la informacion
			+ "FROM Ruta c, Destino d "
			//condiciones Where pera filtrar los datos 
			+ "WHERE c.idDestino=d.idDestino "
			+filtroDeEstado);
		DefaultTableModel model = new DefaultTableModel();
		Tablas.actualizarTabla(resultados, model);
		tabla.setModel(model);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
}
