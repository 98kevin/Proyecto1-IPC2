package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Recepcionista extends Empleado{

	public Recepcionista(String cui, String nombres, String apellidos, double salario, String direccion, String correoElectronico,
			boolean estado,int tipoDeEmpleado, Date fechaDeContratacion, String password) {
		super(cui, nombres, apellidos, salario, direccion, correoElectronico, estado,tipoDeEmpleado, fechaDeContratacion,password);
	}

	public Recepcionista(Empleado empleado) {
	    super();
	   this.setNombres(empleado.getNombres());
	   this.setApellidos(empleado.getApellidos());
	   this.setDireccion(empleado.getDireccion());
	   this.setFechaDeContratacion(empleado.getFechaDeContratacion());
	   this.setTipo(empleado.getTipo());
	   this.setPassword(empleado.getPassword());
	   this.setActivo(empleado.isActivo());
	}

	public Recepcionista() {
	    super();
	}
	
	public Cliente registrarNuevoCliente(int casilleroTexto, String nit, String nombres, 
		String apellidos, String direccion, String correoElectronico) {
	    Cliente nuevoCliente=null;
	    try {
			int casillero = casilleroTexto;
		    	nuevoCliente = new Cliente(casillero,nit, nombres, apellidos, direccion, correoElectronico);
		    	SqlConection.escribirRegistro(Cliente.TABLA,
			nuevoCliente.getColumnas() , 
			nuevoCliente.getSentence());
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Erro de codigo de casillero");
	    }
	    return nuevoCliente;
	}

	public void ingresarPaquetes(LinkedList<Paquete> paquetes, Cliente cliente, double total) {
	    //primero se ingresa la factura y luego se ingresan los demas paquetes
	    try {
		Main.conexion.setAutoCommit(true);
		Statement instruccion = Main.conexion.createStatement();
		instruccion.executeUpdate(instruccionNuevaFactura(cliente, total));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    //Aca se transfieren todos los paquetes 
	    ArrayList<String> sentencias = new ArrayList<String>();
	    for (int i = 0; i < paquetes.size(); i++) {
		sentencias.add("INSERT INTO Paquete "+paquetes.get(i).getColumnas() 
			    +" VALUES "+ paquetes.get(i).getSentence());
	    }
	    SqlConection.transaccion(sentencias);
	}
	
	private String instruccionNuevaFactura(Cliente cliente, double total) {
			Date fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
			Factura nuevaFactura = new Factura(cliente.getCasillero(), fechaActual, total);
			return ("INSERT INTO Factura "+nuevaFactura.getColumnas()
			+" VALUES "+nuevaFactura.getSentence());
	}
	

	public void consultarPaquetesListos(JTable tabla) {
	    DefaultTableModel model = new DefaultTableModel();
	    ResultSet consulta = SqlConection.generarConsulta("p.idPaquete as Codigo, p.descripcion,"
	    	+ " c.nombres, c.apellidos, p.fechaDeIngreso as 'Fecha de Ingreso' ",
		    "Paquete p, Cliente c", 
		    "WHERE p.llegoDestino=TRUE "
		    + " AND p.casilleroCliente=c.casillero"
		    + "  AND p.retirado=FALSE");
	    Tablas.actualizarTabla(consulta, model);
	    tabla.setModel(model);
	}

	public void consultarPaquete(String codigoPaquete, JLabel lblDescripcionPaquete) {
	    ResultSet consulta = SqlConection.generarConsulta("descripcion",
		    "Paquete", 
		    "WHERE idPaquete='"+codigoPaquete+"'");
	    try {
		consulta.next();
		lblDescripcionPaquete.setText(consulta.getString(1));
	    } catch (SQLException e) {
		if(e.getErrorCode() == 0)
		    JOptionPane.showMessageDialog(null, "Error, no existe el paquete en la consulta");
	    }
	    
	}

	public void entregarPaquete(String codigoPaquete, JTable tabla) {
	    long tiempoEnMillis=Calendar.getInstance().getTimeInMillis();
	    Date fechaActual = new Date(tiempoEnMillis);
	    Time horaAcutal = new Time(tiempoEnMillis);
	    SqlConection.generarActualizacion("Paquete",
		    " retirado = true, "
		    + "fechaDeEgreso = '"+fechaActual.toString()+" "+horaAcutal.toString()+"'",
		    " WHERE idPaquete="+codigoPaquete);
	    consultarPaquetesListos(tabla);
	}

	public void estadoDePaquete(String codigoPaquete, JTextField cajaNombreRuta, JTextField cajaPuntoDeControl,
		JTextField cajaPuntosFaltantes, JTextField cajaFechaDeIngreso, JProgressBar barraDeProgreso) {
	    //consulta referente al paquete
	   ResultSet consulta = SqlConection.generarConsulta("r.nombre, q.nombre, p.fechaDeIngreso, q.lugarEnCola, p.llegoDestino", 
		   "Paquete p, PuntoDeControl q, Ruta r" ,
		   "WHERE p.idPaquete= "+codigoPaquete
		   + " AND p.idPunto=q.idPunto"
		   + " AND q.idRuta= r.idRuta");
	   //instancia del paquete para saber su ruta
	   Paquete p = new Paquete();
	   //obtenemos el codigo de ruta por el codigo del paquete
	   String idRuta =p.getIdRuta(codigoPaquete);
	   //consulta para saber todos los puntos de control de la ruta del paquete
	  ResultSet consultaNumeroPaquetes = SqlConection.generarConsulta(" COUNT(*) ",
		   " PuntoDeControl ",
		   "WHERE idRuta="+idRuta);
	   try {
	    consulta.next();
	    cajaNombreRuta.setText(consulta.getString(1));
	    int indiceDelPunto = consulta.getInt(4);
	    boolean llegoDestino=consulta.getBoolean(5);
	    cajaPuntoDeControl.setText(consulta.getString(2));
	    
	    consultaNumeroPaquetes.next(); 
	    int totalPuntos= consultaNumeroPaquetes.getInt(1);
	    int puntosFaltantes = totalPuntos- indiceDelPunto;
	    //corregimos que no se pase de cero 
	    puntosFaltantes = (puntosFaltantes<0) ? 0: puntosFaltantes;
	    cajaPuntosFaltantes.setText(String.valueOf(puntosFaltantes));
	    cajaFechaDeIngreso.setText(consulta.getDate(3).toString());
	    //actualizamos la barra de progreso
	    actualizarBarra(totalPuntos, puntosFaltantes,llegoDestino,  barraDeProgreso);

	} catch (SQLException e) {
	    if (e.getErrorCode() == 0) {
		JOptionPane.showMessageDialog(null, "No existe ese paquete en la base de datos");
	    }
	    e.printStackTrace();
	}
	}
	
	private void actualizarBarra(int total, int faltantes, boolean llegoDestino, JProgressBar barraDeProgreso) {
	    //actualizamos la barra de progreso 
	    int porcentaje =(llegoDestino) ? (100*(total - faltantes))/(total) : (100*(total - faltantes))/(total) -10;
	    barraDeProgreso.setValue(porcentaje);
	}

	public void consultarPaquetesEnRuta(JTable table) {
	    ResultSet resultado =SqlConection.generarConsulta("p.idPaquete as Codigo, p.descripcion, p.fechaDeIngreso as 'Fecha de Ingreso', q.nombre as 'Punto De Control', r.nombre as 'Ruta '",
		    "Paquete p, PuntoDeControl q, Ruta r",
		    "WHERE q.idRuta = r.idRuta "
		    + "AND p.idPunto=q.idPunto "
		    + "AND estaBodega=FALSE  "
		    + "AND llegoDestino="
		    + "FALSE AND "
		    + "retirado= FALSE");
	    DefaultTableModel model = new DefaultTableModel();
	    Tablas.actualizarTabla(resultado, model);
	    table.setModel(model);
	    
	}

	public void consultarPaquetesRetirados(JTable table) {
	   ResultSet resultado = SqlConection.generarConsulta("p.idPaquete as Codigo, p.descripcion, p.fechaDeIngreso as 'Fecha De Ingreso', p.fechaDeEgreso as 'Fecha De Egreso', "
	   	+ "c.nombres as 'Nombres del cliente', c.apellidos as 'Apellidos del cliente', p.precioCliente", 
	   	"Paquete p, Cliente c",
	   	"WHERE p.casilleroCliente=c.casillero AND retirado=true");
	   DefaultTableModel model = new DefaultTableModel();
	   Tablas.actualizarTabla(resultado, model);
	   table.setModel(model);
	}
	
}
