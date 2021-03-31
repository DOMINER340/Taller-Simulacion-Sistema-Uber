package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import modelo.Uber;

public class ManejoArchivos {
	//
	//ATRIBUTOS
	//
	
	//
	//CONSTRUCTOR
	//
	public ManejoArchivos(){
		
	}
	//
	//MÉTODOS
	//
	/**
	 * Guarda a los conductores que se leen desde un archivo en el sistema
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void leerConductores(Uber modelo, String ruta) throws Exception{
		String []datos = new String[2];
		BufferedReader br = new BufferedReader(new FileReader(ruta));
		String linea = br.readLine();
		while(linea != null){
			datos = linea.split("#");
			String nombre = datos[0];
			String placa = datos[1];
			modelo.agregarConductor(nombre, placa);
			linea = br.readLine();
		}
		br.close();
	}
	/**
	 * Lee toda la lista de conductores y escribe en un archivo de texto una lista con los conductores que se encuentran disponibles en el momento
	 * @param modelo
	 * @param ruta
	 * @throws Exception
	 */
	public static void escribirReporte(Uber modelo, String ruta) throws Exception{
		PrintWriter pw = new PrintWriter (new FileWriter(ruta));
		int i = 1;
		pw.println("Reporte de Conductores disponibles");
		pw.println();
		List<modelo.Conductor> con = modelo.darConductores();
		for(modelo.Conductor conductores: con){
			if(conductores.getEstaDisponible() == true){
				pw.println("Conductor Disponible # " + i + ":");
				pw.println("Nombre: " + conductores.getNombre());
				pw.println("Placa: " + conductores.getPlaca());
				pw.println();
				i++;
			}
		}
		pw.close();
		System.out.println("Archivo creado exitosamente");
	}
}
