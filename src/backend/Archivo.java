package backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Archivo {
    
    private static String sourceCSS = "/* " +
	"	Color fondo: #632432; " + 
	"	Color header: 246355; " +
	"	Color borde: 0F362D;" +
	" 	Color iluminado: 369681;" +
	"*/" +
	"body{" +
	"	background-color: #ffffff;" +
	"	font-family: Arial;" +
"	}" +
"	" +
"	#main-container{" +
"		margin: 150px auto;" +
"		width: 600px;" +
"	}" +
"	" +
"	table{" +
"		background-color: white;" +
"		text-align: left;" +
"		border-collapse: collapse;" +
"		width: 100%;" +
"	}" +
"	" +
"	th, td{" +
"		padding: 20px;" +
"	}" +
"	" +
"	thead{" +
"		background-color: #2cb0b0;" +
"		border-bottom: solid 5px #0F362D;" +
"		color: white;" +
"	}" +
"	" +
"	tr:nth-child(even){" +
"		background-color: #ddd;" +
"	}" +
"	" +
"	tr:hover td{" +
"		background-color: #abecff;" +
"		color: white;" +
"	}" ; 
    
    public static void leerCodigos(String sourceHTML) {
	File fileHTML= new File(seleccionarDireccion());
	escribirArchivo(sourceHTML, fileHTML.getAbsolutePath());
	File fileCSS = new File(fileHTML.getParent() + "/tabla.css");
	escribirArchivo(sourceCSS, fileCSS.getAbsolutePath());
	JOptionPane.showMessageDialog(null, "Exportado con exito");
    }
   
    public static void escribirArchivo(String source, String direccion) {
	FileWriter fileWriter;
	BufferedWriter bufferWriter=null;
	PrintWriter writer =null;
	//Escritura del archivo en la direccion
	try{
	fileWriter = new FileWriter(direccion);
	bufferWriter = new BufferedWriter(fileWriter);
	writer = new PrintWriter(bufferWriter);  
	writer.write(source);//escribimos en el archivo HTML
	//cambiamos la referncia del FileWriter
	}catch(IOException e){
	    e.printStackTrace();
	} //intentamos cerrar los canales
	finally {
	    try {
		writer.close();
		bufferWriter.close();
	    } catch (Exception e2) {
		e2.printStackTrace();
	    }
	}
	 }
	
    public static String seleccionarDireccion() {
	JFileChooser selectorDireccion = new JFileChooser();
	int opcion = selectorDireccion.showSaveDialog(null);
	if(opcion== JFileChooser.APPROVE_OPTION) 
	    return selectorDireccion.getSelectedFile().getAbsolutePath()+".html";
	else 
	    return null;
    }
    
    }


