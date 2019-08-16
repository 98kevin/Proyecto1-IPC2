package backend;

import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PuntoDeControl implements Actualizable {
    
    	public static final String TABLA="PuntoDeControl";
    	public static final String COL_ID="idPunto";
    	public static final String COL_NOMBRE="nombre";
    	public static final String COL_ID_RUTA="idRuta";
    	public static final String COL_CUI_OPERADOR="cui";
    	public static final String COL_CAPACIDAD="capacidad";
    	public static final String COL_TARIFA="tarifaDeOperacion";
    	public static final String COL_LUGAR_EN_COLA="lugarEnCola";
    	    	
	private int codigo; 
	private String nombre; 
	private int codigoRuta;
	private String cuiOperador;
	private int capacidad;
	private int tarifaDeOperacion;
	private int lugarEnCola;
	private boolean tarifaGlobal;
	
	public static LinkedList<PuntoDeControl> puntos;
	
	
	public PuntoDeControl () {
	    super();
	}
	
	/**
	 * @param nombre
	 * @param cui
	 * @param capacidad
	 * @param tarifa
	 */
	public PuntoDeControl(String nombre, int codigoRuta, String cui, String capacidad, 
		boolean usaTarifaGlobal, String tarifa, int lugarEnCola) {
	    super();
	    this.nombre = nombre;
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

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
	    return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
	    this.codigo = codigo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
	    return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
	    this.nombre = nombre;
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
	
	@Override
	public String getSentence() {
	    String codigoTarifa;
	    if(isTarifaGlobal()) {
		codigoTarifa= Precio.PRECIO_GLOBAL;
	    }else{
		String codigoPrecio = this.getNombre().toLowerCase().replace(" ", "_");  //pasamos el nombre a snak_case
		Precio nuevoPrecio = new Precio(codigoPrecio, this.getNombre(),this.getTarifaDeOperacion());  //generamos un nuevo precio en la memoria principal
		SqlConection.escribirRegistro(Precio.TABLA, nuevoPrecio.getColumnas(), nuevoPrecio.getSentence());
		codigoTarifa= nuevoPrecio.getCodigo();
	    }
	    return "('"+this.getNombre()+"', "
		    +this.getCodigoRuta()+", '"
		    +this.getCuiOperador()+"',  "
		    +this.capacidad+", '"
		    +codigoTarifa+"', "
		    +this.getLugarEnCola()+")";
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

	/**
	public void registrarNuevoPunto(String nombreDelPunto, 
		String cuiOperador, 
		String capacidadDelPunto, 
		boolean tarifaGlobal,
		String tarifaPropia) {
	    String precioTarifa;
	    try {
		    int capacidad = Integer.parseInt(capacidadDelPunto);
		    if (tarifaGlobal)
			precioTarifa= PREFIJO+PRECIO_GLOBAL;
		    else {
			precioTarifa=getCodigoPrecio(nombreDelPunto);
		    }
		} catch (NumberFormatException e) {
		    JOptionPane.showMessageDialog(null, "Error de lectura de la capacidad, ingrese un numero valido",
			    "Erro de formato", JOptionPane.ERROR_MESSAGE);
		}
	}
	*/

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
	
}
