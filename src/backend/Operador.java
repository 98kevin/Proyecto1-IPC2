package backend;

import java.sql.Date;
import java.util.ArrayList;

public class Operador extends Empleado{


	public Operador(String cui, String nombres, String apellidos, double salario, String direccion, String correoElectronico,
			boolean estado,int tipoDeEmpleado, Date fechaDeContratacion, String nombreDeUsuario, String password) {
		super(cui, nombres, apellidos, salario, direccion, correoElectronico, estado,tipoDeEmpleado, fechaDeContratacion,password);
		
	}

	private ArrayList<PuntoDeControl> puntosDeControlAsignados;

	public ArrayList<PuntoDeControl> getPuntosDeControlAsignados() {
		return puntosDeControlAsignados;
	}

	public void setPuntosDeControlAsignados(ArrayList<PuntoDeControl> puntosDeControlAsignados) {
		this.puntosDeControlAsignados = puntosDeControlAsignados;
	}


}
