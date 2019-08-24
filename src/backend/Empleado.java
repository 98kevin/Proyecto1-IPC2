package backend;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Empleado extends Persona {
	public static final String TABLA = "Empleado ";
	public static final String COL_CUI="cui";
	public static final String COL_NOMBRES="nombres";
	public static final String COL_APELLIDOS="apellidos";
	public static final String COL_SALARIO = "salario";
	public static final String COL_DIRECCION="direccion";
	public static final String COL_CORREO="correoElectronico";
	public static final String COL_ESTADO="estado";
	public static final String COL_TIPO="tipo";
	public static final String COL_FECHA_CONTRATACION="fechaDeContratacion";
	public static final String COL_PASSWORD="password";

	private String cui;
	private double salario;
	private int tipo;
	private boolean activo;
	private Date fechaDeContratacion;
	private String password;
	
	
	/**
	 * Crea un nuevo Empleado en la base de datos.
	 * @param cui  El codigo unico de identificacion
	 * @param nombres Los dos nombres del empleado 
	 * @param apellidos Los apellidos del empleado
	 * @param direccion La direccion del empleado
	 * @param correoElectronico El correo electronico del empleado
	 * @param tipoDeEmpleado El codigo del tipo de empleado
	 * @param password La nueva contrasenia del empleado.
	 */
	public Empleado(String cui,  String nombres, String apellidos, double salario,String direccion, String correoElectronico, boolean estado,
			int tipoDeEmpleado, Date fechaDeContratacion, String password) {
		super( nombres, apellidos, direccion, correoElectronico);
		this.cui=cui;
		this.salario=salario;
		this.activo=estado; 
		this.tipo=tipoDeEmpleado;
		this.fechaDeContratacion=fechaDeContratacion;
		this.password=password;
	}

	public Empleado() {
	    super();
	}
	

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean estaActivo) {
		this.activo = estaActivo;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaDeContratacion() {
		return fechaDeContratacion;
	}

	public void setFechaDeContratacion(Date fechaDeContratacion) {
		this.fechaDeContratacion = fechaDeContratacion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the cui
	 */
	public String getCui() {
	    return cui;
	}

	/**
	 * @param cui the cui to set
	 */
	public void setCui(String cui) {
	    this.cui = cui;
	}

	/**
	 * @return the salario
	 */
	public double getSalario() {
	    return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(double salario) {
	    this.salario = salario;
	}

	public boolean evaluarIngreso(String passUsuario, String passIngresada) {
		boolean result=false;
		if(passUsuario.equals(passIngresada) & (this.isActivo() == true))
			result= true;
		if(! (passUsuario.equals(passIngresada))) {
			JOptionPane.showMessageDialog(null, "Password incorrecto", "Error de credenciales", JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		if (!(this.isActivo() == true)) {
			JOptionPane.showMessageDialog(null, "No es un empleado activo", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
			result = false;
		}
		return result;
	}
	
	public Empleado leerDatos(String cuiEmpleado) {
	    	String columnas =" * ";
	    	String tabla= " Empleado ";
	    	String where = " WHERE cui="+cuiEmpleado+" ";
		ResultSet resultado = SqlConection.generarConsulta(columnas, tabla, where);
		Empleado empleado= null;
		try {
		    	resultado.next();
			String cui =resultado.getString(COL_CUI);
			String nombres = resultado.getString(COL_NOMBRES);
			String apellidos = resultado.getString(COL_APELLIDOS);
			double salario = resultado.getDouble(COL_SALARIO);
			String direccion = resultado.getString(COL_DIRECCION);
			String correo = resultado.getString(COL_CORREO);
			boolean estado = resultado.getBoolean(COL_ESTADO);
			int tipoDeEmpleado = resultado.getInt(COL_TIPO);
			Date fechaDeContratacion = resultado.getDate(COL_FECHA_CONTRATACION);
			String password = resultado.getString(COL_PASSWORD);
			empleado =new Empleado(cui,nombres,apellidos,salario,direccion,correo,estado,tipoDeEmpleado,fechaDeContratacion, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empleado;
	}
	
	
	@Override
	public String getSentence() {
	    String fecha = this.getFechaDeContratacion().toString();
	    return "('"+this.getCui()+"','"
		    +this.getNombres()+"','"
		    +this.getApellidos()+"','"
		    +this.getDireccion()+"','"
		    +this.getCorreoElectronico()+"',"
		    +this.isActivo()+","
		    +this.getTipo()+",'"
		    +fecha+"','"
		    +this.getPassword()+"')";
	}

	@Override
	public String getColumnas() {
	    return "("+COL_CUI+","
		    +COL_NOMBRES+","
		    +COL_APELLIDOS+","
		    +COL_DIRECCION+","
		    +COL_CORREO+","
		    +COL_ESTADO+","
		    +COL_TIPO+","
		    +COL_FECHA_CONTRATACION+","
		    +COL_PASSWORD+")";
	}
	
}
