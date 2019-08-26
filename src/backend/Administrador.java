package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import frontend.PanelIndividualCliente;

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
	    	+ "AND q.idRuta=s.idRuta "
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
	    	+ "AND q.idRuta=s.idRuta "
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
		filtroDeEstado = getFiltroDeEstado(tipoDeRuta);
		ResultSet resultados = consulta.executeQuery(
			//campos a mostar con sus respectivos alias
			"SELECT s.idRuta as 'Codigo', s.nombre , d.nombre as 'Destino', s.estado, "
			//el contador de paquetes fuera de fuera de ruta 
			+contadorDePaquetesFueraDeRuta(fechaInicial, fechaFinal)+ " as 'Paquetes Fuera',"
			//el contador de paquetes en ruta acutalmente
			+ contadorDePaquetesEnRuta(fechaInicial, fechaFinal)+ " as 'Paquetes en Ruta' "
			//Las tablas doonde se consultara la informacion
			+ "FROM Ruta s, Destino d "
			//condiciones Where pera filtrar los datos 
			+ "WHERE s.idDestino=d.idDestino "
			+filtroDeEstado);
		DefaultTableModel model = new DefaultTableModel();
		Tablas.actualizarTabla(resultados, model);
		tabla.setModel(model);
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	private String getFiltroDeEstado(JComboBox<String> tipoDeRuta) {
	    String filtroDeEstado=new String();
		if (tipoDeRuta.getSelectedItem().equals("Ambas")) 
		    filtroDeEstado =" ";
		if(tipoDeRuta.getSelectedItem().equals("Activas"))
		    filtroDeEstado =" AND s.estado=TRUE";
		if(tipoDeRuta.getSelectedItem().equals("No Activas"))
		    filtroDeEstado =" AND s.estado=FALSE";
	    return filtroDeEstado;
	}

	public void reporteDeGanancias(JTable tabla, String fechaInicial, String fechaFinal, JComboBox<String> estadoDeRuta,String filtroNombre) {
	    Statement declaracion;
	    //Filtro del tipo de Ruta: Activa/Inactiva
	    String filtroDeEstado=getFiltroDeEstado(estadoDeRuta);
	   String consulta = "SELECT s.idRuta as 'Codigo de Ruta', s.nombre as 'Nombre', d.nombre as 'Destino', "
		+ getSubConsultaOperacional("SUM(p.costo)", fechaInicial, fechaFinal, "Costos", false)
	   	+getSubConsultaOperacional("SUM(p.precioCliente)", fechaInicial, fechaFinal, "Ingresos", false)
	   	+getSubConsultaOperacional("SUM(p.precioCliente) -SUM(p.costo)", fechaInicial, fechaFinal, "Ganancias", true)
	   	+" FROM Ruta s, Destino d "
	   	+" WHERE s.idDestino=d.idDestino "
	   	+" AND s.nombre LIKE '%"+filtroNombre+"%' "
	   	+filtroDeEstado;
	   try {
	    declaracion = Main.conexion.createStatement();
	    ResultSet resultados = declaracion.executeQuery(consulta);
	    DefaultTableModel model = new DefaultTableModel();
	    Tablas.actualizarTabla(resultados, model);
	    tabla.setModel(model);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	}
	
	private String getSubConsultaOperacional(String campoDeOperacion,	String fechaInicial, String fechaFinal, 
		String alias, boolean esUltimaColumna) {
	    	String subConsulta= " (SELECT "+campoDeOperacion+
		   " FROM Paquete p, PuntoDeControl q, Ruta r "+
		   " WHERE p.idPunto=q.idPunto"+
		   " AND q.idRuta= r.idRuta "+
		  "  AND q.idRuta=s.idRuta"+
		   " AND p.llegoDestino=TRUE "+
		   " AND p.fechaDeEgreso >= '"+fechaInicial+"' "+
		   " AND p.fechaDeEgreso <= '"+fechaFinal+"') as '"+alias+"' ";
	    	if(!esUltimaColumna)
	    	    subConsulta = subConsulta + ",";
	    	return subConsulta;
	}

	public void reporteDeClientes(JPanel panelPrincipal, String filtroCliente, java.util.Date fechaInicial, java.util.Date fechaFinal) {
	    panelPrincipal.removeAll();
	    Date fechaSqlInicial = new Date(fechaInicial.getTime());
	    Date fechaSqlFinal = new Date (fechaFinal.getTime());
	    //consultamos todos los codigos de los clientes  
	    ResultSet clientes = SqlConection.generarConsulta("casillero,nombres, apellidos",
		    "Cliente", 
		    "WHERE nombres LIKE '%"+filtroCliente+"%'"
		    	+ "OR apellidos LIKE '%"+filtroCliente+"%'");
	    try {
		while (clientes.next()) {
		    consultarPaquetesDeCliente(clientes.getString(1),
			    clientes.getString(2),
			    clientes.getString(3),
			    panelPrincipal,
			    fechaSqlInicial, 
			    fechaSqlFinal);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    
	}

	private void consultarPaquetesDeCliente(String casillero, String nombre, String apellidos, JPanel panelPrincipal,
		Date fechaInicial, Date fechaFinal) {
	   //consultamos los paquetes de un cliente
	    JTable tabla; 
	    JLabel lblCliente;
	    DefaultTableModel model;
	    lblCliente = new JLabel(nombre+apellidos);
	    ResultSet paquetesCliente = SqlConection.generarConsulta("p.idPaquete as 'Codigo', p.descripcion, d.nombre as 'Destino', "
	    	+ "p.idFactura as 'Codigo de Factura', p.costo, p.precioCliente  as 'Ingreso', (p.precioCliente - p.costo) as 'Ganacia Neta'",
	    	" Paquete p, Destino d", "WHERE p.idDestino= d.idDestino"+
	    	" AND p.llegoDestino=TRUE"+
	    	" AND p.casilleroCliente="+casillero+
	    	" AND p.fechaDeEgreso>='"+fechaInicial.toString()+"'"+
	    	" AND p.fechaDeEgreso<='"+fechaFinal.toString()+"'");
	    model = new DefaultTableModel();
	    Tablas.actualizarTabla(paquetesCliente, model);
	    tabla = new JTable(model);
	    JScrollPane panelScroll = new JScrollPane();
	    panelScroll.setAutoscrolls(true);
	    panelScroll.setViewportView(new PanelIndividualCliente(lblCliente, tabla));
	    //evaluamos si el cliente tiene paquetes, lo agregamos al reporte
	    if(model.getRowCount()>0)
		panelPrincipal.add(panelScroll);
	}

	public void reporteTopRutas(JTable tabla, java.util.Date fechaInicial, java.util.Date fechaFinal) {
	    Date fechaSqlInicial = new Date(fechaInicial.getTime());
	    Date fechaSqlFinal = new Date(fechaFinal.getTime());
	    ResultSet rutas = SqlConection.generarConsulta("c.idRuta, c.nombre, d.nombre AS Destino, c.estado, "+
		   " (SELECT COUNT(*) "+
			   " FROM Paquete p, PuntoDeControl q, Ruta r "+
			   " WHERE p.idPunto=q.idPunto " +
			   " AND q.idRuta= r.idRuta "
			   + " AND p.fechaDeEgreso >= '"+fechaSqlInicial.toString()+"'"
			   + " AND p.fechaDeEgreso <= '"+fechaSqlFinal.toString()+"'"
			   + " AND q.idRuta=c.idRuta "+
			   " AND p.llegoDestino=TRUE) AS Paquetes", "Ruta c, Destino d", "WHERE c.idDestino=d.idDestino "+
			   " ORDER BY Paquetes DESC "
			   + " LIMIT 3");
		DefaultTableModel model = new DefaultTableModel();
		Tablas.actualizarTabla(rutas, model);
		tabla.setModel(model);
	}
}
