package backend;

import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JOptionPane;

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
	 		Ruta nuevaRuta =new Ruta(nombre,idDestino, 0);
	 		SqlConection.escribirRegistro("Ruta", 
	 			nuevaRuta.getColumnas(), 
	 			nuevaRuta.getSentence());
	 	    } catch (NumberFormatException e) {
	 		JOptionPane.showMessageDialog(null, "Formato de numero incorrecto");
	 	    }
	}
	
	public void setCapacidadDePuntoDeContro(PuntoDeControl punto, int nuevaCapacidad) {
	    
	    
	}
	
	public void setCuotaDeDestino(Destino destino) {}
	
	public void desactivarRuta(Ruta ruta) {}
	
	/**
	 * Agrega los puntos de control asignados a la ruta. 
	 * @param idRuta Codigo de la ruta que referencian los puntos de control 
	 * @param listaDePuntos Listado de puntos de control a asignar. 
	 */
	public void agragarPuntosDeControl(int idRuta, LinkedList<PuntoDeControl> listaDePuntos) {
	    for (int i = 0; i < listaDePuntos.size(); i++) {
		SqlConection.escribirRegistro(PuntoDeControl.TABLA, 
			listaDePuntos.get(i).getColumnas(), 
			listaDePuntos.get(i).getSentence());
	    }
	}
	
	public void quitarPuntosDeControl(Ruta ruta) {}
	
	public void setPrecioPorLibra(double nuevoPrecio) {}
	
	public void setPrecioDePriorizacion(double nuevoPrecio) {}

	public void registrarNuevoEmpleado(String cui, String nombres, String apellidos, double salario, String direccion, String correo, int tipo,
		Date fecha, String pass1, String pass2) {
	    	if(pass1.equals(pass2)) {
	    	    Empleado nuevoEmpleado =new Empleado(cui,nombres,apellidos,salario,direccion,correo,true,tipo,fecha,pass1);
	    	    SqlConection.escribirRegistro(Empleado.TABLA, 
	    		    nuevoEmpleado.getColumnas(), 
	    		    nuevoEmpleado.getSentence()); 
	    	}
	    	else
	    	    JOptionPane.showMessageDialog(null, "Contrasenias no coinciden", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
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
	
	
	
}
