package backend;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.table.DefaultTableModel;

public class Tablas{

    /**
     * Rellena el DefaultTableModel con los datos del ResultSet.
     * Vacía el DefaultTableModel completamente y le mete los datos que hay
     * en el ResultSet.
     * @param rs El resultado de la consula a la base de datos.
     * @param modelo El DefaultTableModel que queremos rellenar
     */
    public static void actualizarTabla(ResultSet rs, DefaultTableModel modelo)
    {
        configuraColumnas(rs, modelo);
        vaciaFilasModelo(modelo);
        anhadeFilasDeDatos(rs, modelo);
    }

    /**
     * Añade al DefaultTableModel las filas correspondientes al ResultSet.
     * @param rs El resultado de la consulta a base de datos
     * @param modelo El DefaultTableModel que queremos rellenar.
     */
    private static void anhadeFilasDeDatos(ResultSet rs,
            DefaultTableModel modelo){
        try{
            while (rs.next()){
                // Se crea y rellena la fila para el modelo de la tabla.
                Object[] datosFila = new Object[modelo.getColumnCount()];
                for (int i = 0; i < modelo.getColumnCount(); i++) {
                    datosFila[i] = rs.getObject(i + 1);
                    if(rs.getObject(i+1) instanceof Boolean)
                	datosFila[i] =(rs.getBoolean(i+1)) ? "Si":"No";
                }
                modelo.addRow(datosFila);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Borra todas las filas del modelo.
     * @param modelo El modelo para la tabla.
     */
    private static void vaciaFilasModelo(final DefaultTableModel modelo)
    {
        // La llamada se hace in un invokeAndWait para que se ejecute en el
        // hilo de refresco de ventanas y evitar que salten excepciones
        // durante dicho refresco.
        try{ 
            while (modelo.getRowCount() > 0)
            modelo.removeRow(0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Pone en el modelo para la tabla tantas columnas como tiene el resultado
     * de la consulta a base de datos.
     * @param rs Resultado de consulta a base de datos.
     * @param modelo Modelo de la tabla.
     */
    public static void configuraColumnas(ResultSet rs,
            DefaultTableModel modelo){
                    try{
                        //Un ResultSetMetaData sirve para saber el numero de columnas
                	//y propiedades de las mismas de un objeto ResultSet
                        ResultSetMetaData metaDatos = rs.getMetaData();
                        // Se obtiene el numero de columnas.
                        int numeroColumnas = metaDatos.getColumnCount();
                        // Se obtienen las etiquetas para cada columna
                        String[] etiquetas = new String[numeroColumnas];
                        for (int i = 0; i < numeroColumnas; i++)
                            etiquetas[i] = metaDatos.getColumnLabel(i + 1);
                        // Se meten las etiquetas en el modelo. El numero
                        // de columnas se ajusta automáticamente.
                        modelo.setColumnIdentifiers(etiquetas);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
    }
    
}
