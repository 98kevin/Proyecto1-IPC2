package backend;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import frontend.FormNuevoCliente;

public class Cliente extends Persona{
	
	private final String TABLA = "Cliente";
	private final String COL_CASILLERO = "casillero";
	private final String COL_NIT= "nit";
	private final String COL_APELLIDOS= "apellidos";
	private final String COL_NOMBRES= "nombres";
	private final String COL_DIRECCION= "direccion";
	private final String COL_CORREO= "correoElectronico";
    	
    
	private String nit;
	private int casillero;
	
	/**
	 * @return the casillero
	 */
	public int getCasillero() {
	    return casillero;
	}

	/**
	 * @param casillero the casillero to set
	 */
	public void setCasillero(int casillero) {
	    this.casillero = casillero;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public Cliente(int casillero,String nit,String nombres, String apellidos, String direccion, String correoElectronico) {
		super(nombres, apellidos, direccion, correoElectronico);
		this.casillero= casillero;
		this.nit=nit;
	}

	public Cliente () {
	    super();
	}
	
	public Cliente consultarCliente(String casilleroTexto) {
	    Cliente cliente = null;
	    try {
		int casillero = Integer.parseInt(casilleroTexto);
		ResultSet resultado = SqlConection.consultarCliente(casillero);
		if(resultado.next()) {
		    	cliente = new Cliente(resultado.getInt("casillero"),
			resultado.getString("nit"),
			resultado.getString("nombres"),
			resultado.getString("apellidos"),
			resultado.getString("direccion"),  
			resultado.getString("correoElectronico"));
		}else {
		   int respuesta =  JOptionPane.showConfirmDialog(null, "Cliente No Registrado, desea registrarlo...");
		   if (respuesta == JOptionPane.OK_OPTION) {
		       FormNuevoCliente formulario = new FormNuevoCliente(casilleroTexto);
		       formulario.setVisible(true);
		   }
		}		
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Formato de casillero incorrecto, ingrese un numero");
		e.printStackTrace();
	    }
	    catch(SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error de lectura de datos");
		ex.printStackTrace();
	    }
	    return cliente;
	}

	public Cliente registrarNuevoCliente(int casilleroTexto, String nit, String nombres, String apellidos, String direccion, String correoElectronico) {
	    Cliente nuevoCliente=null;
	    try {
			int casillero = casilleroTexto;
		    	nuevoCliente = new Cliente(casillero,nit, nombres, apellidos, direccion, correoElectronico);
		    	SqlConection.escribirRegistro(TABLA,
			nuevoCliente.getColumnas() , 
			nuevoCliente.getSentence());
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Erro de codigo de casillero");
	    }
	    return nuevoCliente;
	}

	@Override
	public String getSentence() {
	    return "("+this.getCasillero()+",'"+this.getNit()+"', '"+this.getNombres()+"', ' "+this.getApellidos()+"', '"+this.getDireccion()+"', '"+this.getCorreoElectronico()+"')";
	}

	@Override
	public String getColumnas() {
	    return "("+COL_CASILLERO+","+COL_NIT+","+COL_NOMBRES+", "+COL_APELLIDOS+", "+COL_DIRECCION+", "+COL_CORREO+")";
	}

}
