package backend;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PuntoDeControl extends Lugar implements Actualizable {
    
    	public static final String TABLA="PuntoDeControl";
    	public static final String COL_ID="idPunto";
    	public static final String COL_NOMBRE="nombre";
    	public static final String COL_ID_RUTA="idRuta";
    	public static final String COL_CUI_OPERADOR="cui";
    	public static final String COL_CAPACIDAD="capacidad";
    	public static final String COL_TARIFA="tarifaDeOperacion";
    	public static final String COL_LUGAR_EN_COLA="lugarEnCola";
    	    	
	private int codigoRuta;
	private String cuiOperador;
	private int capacidad;
	private int tarifaDeOperacion;
	private int lugarEnCola;
	private boolean tarifaGlobal;
	
	private ResultSet resultados;
	
	public static LinkedList<PuntoDeControl> puntos;
	
	
	public PuntoDeControl (String nombre) {
	    super(nombre);
	}
	
/**
 * 
 * @param codigo
 * @param nombre
 * @param codigoRuta
 * @param cui
 * @param capacidad
 * @param usaTarifaGlobal
 * @param tarifa
 * @param lugarEnCola
 */
	public PuntoDeControl(String nombre, int codigoRuta, String cui, String capacidad, 
		boolean usaTarifaGlobal, String tarifa, int lugarEnCola) {
	    super( nombre);
	    this.cuiOperador = cui;
	    this.lugarEnCola=lugarEnCola;
	    try {
		this.codigoRuta=codigoRuta;
		 this.capacidad = Integer.parseInt(capacidad);
		 this.tarifaGlobal= usaTarifaGlobal;
		 if (!usaTarifaGlobal) {
		     this.tarifaDeOperacion = Integer.parseInt(tarifa);
		}
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null, "Ingrese numeros validos en tarifa o capacidad");
	    }
	}

	public PuntoDeControl() {
	    super();
	}
	
	/**
	 * @return the codigoRuta
	 */
	public int getCodigoRuta() {
	    return codigoRuta;
	}
	/**
	 * @param codigoRuta the codigoRuta to set
	 */
	public void setCodigoRuta(int codigoRuta) {
	    this.codigoRuta = codigoRuta;
	}
	/**
	 * @return the cuiOperador
	 */
	public String getCuiOperador() {
	    return cuiOperador;
	}
	/**
	 * @param cuiOperador the cuiOperador to set
	 */
	public void setCuiOperador(String cuiOperador) {
	    this.cuiOperador = cuiOperador;
	}
	/**
	 * @return the capacidad
	 */
	public int getCapacidad() {
	    return capacidad;
	}
	/**
	 * @param capacidad the capacidad to set
	 */
	public void setCapacidad(int capacidad) {
	    this.capacidad = capacidad;
	}
	/**
	 * @return the tarifaDeOperacion
	 */
	public int getTarifaDeOperacion() {
	    return tarifaDeOperacion;
	}
	/**
	 * @param tarifaDeOperacion the tarifaDeOperacion to set
	 */
	public void setTarifaDeOperacion(int tarifaDeOperacion) {
	    this.tarifaDeOperacion = tarifaDeOperacion;
	}
	/**
	 * @return the lugarEnCola
	 */
	public int getLugarEnCola() {
	    return lugarEnCola;
	}
	/**
	 * @param lugarEnCola the lugarEnCola to set
	 */
	public void setLugarEnCola(int lugarEnCola) {
	    this.lugarEnCola = lugarEnCola;
	}
	

	/**
	 * @return the tarifaGlobal
	 */
	public boolean isTarifaGlobal() {
	    return tarifaGlobal;
	}

	/**
	 * @param tarifaGlobal the tarifaGlobal to set
	 */
	public void setTarifaGlobal(boolean tarifaGlobal) {
	    this.tarifaGlobal = tarifaGlobal;
	}
	
	@Override
	public String getSentence() {
	    String codigoTarifa= getCodigoDeTarifa(isTarifaGlobal());
	    return "('"+this.getNombre()+"', "
		    +this.getCodigoRuta()+", '"
		    +this.getCuiOperador()+"',  "
		    +this.capacidad+", '"
		    +codigoTarifa+"', "
		    +this.getLugarEnCola()+")";
	}
	
	private String getCodigoDeTarifa(boolean tarifaGlobal) {
	    String tarifa=null;
	    if(isTarifaGlobal()) {
		tarifa= Precio.PRECIO_GLOBAL;
	    }else{
		String codigoPrecio = this.getNombre().toLowerCase().replace(" ", "_");  //pasamos el nombre a snak_case
		Precio nuevoPrecio = new Precio(codigoPrecio, this.getNombre(),this.getTarifaDeOperacion());  //generamos un nuevo precio en la memoria principal
		SqlConection.escribirRegistro(Precio.TABLA, nuevoPrecio.getColumnas(), nuevoPrecio.getSentence());
		tarifa= nuevoPrecio.getCodigo();
	    }
	    return tarifa;
	}

	@Override
	public String getColumnas() {
	    return "("+COL_NOMBRE+", "+
		    	COL_ID_RUTA+", "+
		    	COL_CUI_OPERADOR+", "+
		    	COL_CAPACIDAD+", "+
		    	COL_TARIFA+", "+
		    	COL_LUGAR_EN_COLA+")";
	}

	public void agregarPunto(JTable table) {
	    if(puntos==null)
		puntos = new LinkedList<PuntoDeControl>();
	    puntos.addLast(this);
	    actualizarTabla(table);
	}

	private void actualizarTabla( JTable tabla) {
	    String columnas []= {"Nombre","CUI Operador ", "Lugar en la cola", " Capacidad", "Tarifa de Operacion"};
	    Object datos [][]= new Object[puntos.size()][5];
	    for (int i=0; i<puntos.size(); i++) {
		datos[i][0]=puntos.get(i).getNombre();
		datos[i][1]=puntos.get(i).getCuiOperador();
		datos[i][2]=String.valueOf(i+1);
		datos[i][3]=puntos.get(i).getCapacidad();
		datos[i][4]=(puntos.get(i).tarifaGlobal) ?  "Tarifa Global": "Tarifa Propia: "+puntos.get(i).getTarifaDeOperacion();
	    }
	    tabla.setModel(new DefaultTableModel(datos,columnas));
	}

	public boolean isIntegro() {
	   return (this.getCodigoRuta()>0 &
		   this.getCapacidad() >0 & 
		   //si su propia tarifa es mayor a cero o usa tarifa global
		   (this.getTarifaDeOperacion()>0 || this.tarifaGlobal ));
	}

	public void mostrarPuntos(JTable tablaPuntos, String texto) {
	    DefaultTableModel model = new DefaultTableModel();
	    resultados= SqlConection.generarConsulta("idPunto as Codigo, nombre, idRuta as 'Codigo de Ruta', "
		    	+ "cui as Operador, capacidad, "
		    	+ "tarifaDeOperacion as Tarifa, lugarEnCola as Posicion", "PuntoDeControl", " WHERE nombre LIKE '%"+texto+"%' ");
		    Tablas.actualizarTabla(resultados, model);
		    tablaPuntos.setModel(model);
	}

	public void agregarItemsAlCombo(JComboBox<String>combo, String campo, String Tabla, String where) {
	    resultados= SqlConection.generarConsulta(campo, Tabla, where); 
	    try {
		while (resultados.next()) {
		    combo.addItem(resultados.getString(1));
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	public void actualizarCajas(String punto, JTextField cajaCodigo, JTextField cajaNombre, JComboBox<String> comboRuta,
		JComboBox<String> comboOperador, JTextField cajaCapacidad, JCheckBox checkTarifaGlobal,
		JTextField cajaNuevaTarifa, JTextField cajaPosicion) {
	    resultados = SqlConection.generarConsulta("*", "PuntoDeControl", "WHERE idPunto="+punto);
	    try {
		resultados.next();
		cajaCodigo.setEditable(false);
		cajaCodigo.setText(resultados.getString(1));
		cajaNombre.setText(resultados.getString(2));
		comboRuta.setEditable(false);
		comboRuta.setSelectedItem(resultados.getString(3));
		comboOperador.setEditable(false);
		comboOperador.setSelectedItem(resultados.getString(4));
		cajaCapacidad.setText(resultados.getString(5));
		leerTarifa(punto, resultados.getString(6), checkTarifaGlobal, cajaNuevaTarifa);
		cajaPosicion.setText(resultados.getString(7));
	    } catch (SQLException e) {
		if(e.getErrorCode()==0) {
		    JOptionPane.showMessageDialog(null, "No existe el punto de control con ese codigo");
		}
	    }
	    
	}

	private void leerTarifa(String idPunto, String codigoTarifa, JCheckBox checkTarifaGlobal, JTextField cajaNuevaTarifa) {
	    ResultSet consulta = SqlConection.generarConsulta("tarifaDeOperacion", "PuntoDeControl ","WHERE idPunto="+idPunto);
	    try {
		consulta.next();
		String tarifa = consulta.getString(1);
		if(tarifa.equals("global"))
		    checkTarifaGlobal.setSelected(true);
		else {
		    consulta =SqlConection.generarConsulta("precio", "Precios","WHERE codigo='"+tarifa+"'");
		    consulta.next();
		    cajaNuevaTarifa.setText(consulta.getString(1));
		    checkTarifaGlobal.setSelected(false);
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}

	public String getSentenceActualizacion() {
	    String tarifa = getCodigoDeTarifa(this.tarifaGlobal);
	    return new String("nombre='"+this.getNombre()+"', "
	    	+ "idRuta="+this.getCodigoRuta()+", "
	    	+ "cui="+this.getCuiOperador()+", "
	    	+ "capacidad="+this.getCapacidad()+", "
	    	+ "tarifaDeOperacion='"+tarifa+"', "
	    	+ "lugarEnCola="+this.getLugarEnCola());
	} 
	
}
