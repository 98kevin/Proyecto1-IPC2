package backend;

public abstract class Persona implements Actualizable{
	private String nombres;
	private String apellidos;
	private String direccion;
	private String correoElectronico;

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public Persona(String nombres, String apellidos, String direccion, String correoElectronico ) {
		super();
		this.nombres = nombres;
		this.apellidos=apellidos;
		this.direccion=direccion;
		this.correoElectronico=correoElectronico;
	}
	
	public Persona() {
	    super();
	}

}
