package backend;

import java.sql.Date;

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

	//falta detalles de cabecera
	public void registrarCliente() {}

	public void ingresarPaquete(Paquete paquete) {}
	
	public void entregarPaquete(Paquete paquete) {}
	
}
