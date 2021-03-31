package vista;
import java.util.Scanner;
/**
 * @author
 * 
 */
public class InterfazConsola{
	//
	//ATRIBUTOS
	//
	/**
	 * Lee todos los datos que ingresa el usuario
	 */
	private Scanner sc;
	//
	//CONSTRUCTOR
	//
	public InterfazConsola() 
	{
		sc = new Scanner(System.in);
	}
	//
	//MÉTODOS
	//
	/**
	 * Lee un dato de tipo Int que digita el usuario
	 * @param enunciado
	 * @return Un entero que el usuario digita
	 */
	public int leerInt(String enunciado){
		imprimir(enunciado);
		int respuesta = sc.nextInt();
		return respuesta;
	}
	/**
	 * Lee un dato de tipo Double que digita el usuario
	 * @param enunciado
	 * @return Un double que el usuario digita
	 */
	public double leerDouble(String enunciado){
		imprimir(enunciado);
		double respuesta = sc.nextDouble();
		return respuesta;
	}
	/**
	 * Imprime un mensaje al usuario cuando se realiza una determinada acción
	 * @param enunciado
	 * @return Imprime el mensaje que recibe
	 */
	public void imprimir(String mensaje){
		System.out.println(mensaje);
	}
	/**
	 * Lee un dato de tipo String que digita el usuario
	 * @param enunciado
	 * @return Un string que el usuario digita
	 */
	public String leerString(String enunciado){
		imprimir(enunciado);
		String respuesta = sc.next();
		//respuesta = sc.nextLine();
		return respuesta;
	}
	public void imprimirEnter(){
		System.out.println();
	}
	/**
	 * Es el menu principal que ve el usuario al entrar al sistema. El usuario luego digita una opción para realizar una actividad
	 * @return
	 */
	public int imprimirMenu() {
		imprimirEnter();
		imprimir("Menú de Uber");
		imprimir("1. Leer Conductores");
		imprimir("2. Agregar Servicio");
		imprimir("3. Mostrar Conductores");
		imprimir("4. Mostrar Usuarios");
		imprimir("5. Dar total dinero Usuario");
		imprimir("6. Dar total dinero Conductor");
		imprimir("7. Dar Cantidad servicios por tipo");
		imprimir("8. Terminar Servicio");
		imprimir("9. Generar reporte conductores disponibles");
		imprimir("0. Salir");
		imprimirEnter();
		return leerInt("Ingrese una opción");
	}
	

}
