package controlador;

import modelo.Uber;
import modelo.Usuario;
import modelo.Servicio;
import vista.InterfazConsola;
import modelo.Conductor;
import persistencia.ManejoArchivos;
import java.time.LocalDate;
import java.util.List;
/**
 * @author Juan Camilo Chafloque
 * @author Miguel Uribe
 */
public class Controlador {
	//
	//RELACIONES
	//
	/**
	 * El controlador tiene una relación con todo el modelo
	 */
	private Uber modelo;
	/**
	 * El controlador tiene una relación con toda la vista
	 */
	private InterfazConsola vista;
	/**
	 * El controlador tiene una relación con todo el manejo de archivos
	 */
	private ManejoArchivos archivo;
	//
	//MAIN
	//
	public static void main(String[] args) {
		Controlador c = new Controlador();
	}
	//
	//CONSTRUCTOR
	//
	public Controlador(){
		modelo = new Uber();
		vista = new InterfazConsola();
		archivo = new ManejoArchivos();
		int opcion = vista.imprimirMenu();
		while(opcion != 10){
			seleccionarOpcion(opcion);
			opcion = vista.imprimirMenu();
		}
	}
	//
	//MÉTODOS
	//
	/**
	 * Este método es llamado por el constructor del controlador para poder generar las opciones que pide el usuario mediante un switch
	 * @param opcion
	 */
	public void seleccionarOpcion(int opcion) {
		switch (opcion){
		case 1:
			agregarConductor();
			break;
		case 2:
			solicitarServicio();
			break;
		case 3:
			mostrarConductores();
			break;
		case 4:
			mostrarUsuarios();
			break;
		case 5:
			darDineroUsuario();
			break;
		case 6:
			darDineroConductor();
			break;
		case 7:
			darCantidadServicios();
			break;
		case 8: 
			terminarServicio();
			break;
		case 9: 
			generarReporteConductores();
			break;
		case 0: 
			vista.imprimir("Muchas gracias por utilizar nuestro servicio. Vuelva pronto");
			break;
		default: break;
		}
	}
	/**
	 * El método llama a la clase de ManejoArchivos para que genere el reporte de los conductores que se encuentran disponible
	 */
	public void generarReporteConductores() {
		try {
			archivo.escribirReporte(modelo, "Reporte.txt");
		} catch (Exception e) {
			vista.imprimir("Error al generar el reporte d conductores");
		}
		
	}
	/**
	 * El sistema pide el nombre de un conductor para que lo busque y verifique que se encuentra registrado en el sistema e imprimir su información
	 */
	public void buscarConductor() {
		String nombreP = vista.leerString("Ingrese el nombre del conductor a buscar");
		Conductor conductorBuscado = modelo.buscarConductor(nombreP);
		if(conductorBuscado!=null){
			mostrarInformaciónConductor(conductorBuscado);
		}
		else{
			vista.imprimir("No existe el conductor "+nombreP);
		}
	}
	/**
	 * Muestra la información de un conductor que se ha buscado
	 * @param conductorBuscado
	 */
	public void mostrarInformaciónConductor(Conductor conductorBuscado){
		vista.imprimir("Nombre: " + conductorBuscado.getNombre());
		vista.imprimir("Placa: " + conductorBuscado.getPlaca());
		if(conductorBuscado.getEstaDisponible() == true){
			vista.imprimir("Disponibilidad: Disponible");
		}else{
			vista.imprimir("Disponibilidad: No Disponible");
		}
		vista.imprimirEnter();
		vista.imprimirEnter();
	}
	/**
	 * Muestra la información de un usuario que se ha buscado
	 * @param usuarioBuscado
	 */
	public void mostrarInformaciónUsuario(Usuario usuarioBuscado){
		vista.imprimir("Nombre: " + usuarioBuscado.getNombre());
		vista.imprimir("ID: " + usuarioBuscado.getId());
		
		vista.imprimirEnter();
		vista.imprimirEnter();
	}
	/**)
	 * Recibe los datos del conductor que se quiere agregar y llama al modelo para que
	 * efectue la acción.
	 */
	public void agregarConductor(){
		try {
			archivo.leerConductores(modelo, "Conductores.txt");
			vista.imprimir("Los conductores fueron agregados exitosamente");
		} catch (Exception e) {
			vista.imprimir("Error al abrir el archivo de conductores");
		}
	}
	/**
	 * Llama la lista de conductores para que pueda imprimir por pantalla la información
	 * de cada uno.
	 */
	public void mostrarConductores(){
		List<Conductor> conductores = modelo.darConductores();
		for (int i = 0;i<conductores.size();i++){
			Conductor conductorImprimir = conductores.get(i);
			mostrarInformaciónConductor(conductorImprimir);
		}
	}
	/**
	 * Llama la lista de usuarios para que pueda imprimir por pantalla la información
	 * de cada uno.
	 */
	public void mostrarUsuarios(){
		List<Usuario> usuarios = modelo.darUsuarios();
		for (int i = 0;i<usuarios.size();i++){
			Usuario usuarioImprimir = usuarios.get(i);
			mostrarInformaciónUsuario(usuarioImprimir);
		}
	}
	/**
	 * Intenta llamar al método de la clase Uber para efectuar la solicitud de un servicio,
	 * pero atrapa la excepción en dado caso que se presente algún error.
	 * @param  double tarifa
	 */
	public void solicitarServicio( ){
		
		String nombre = vista.leerString("Digite su nombre");
		Conductor asignado;
		int id = vista.leerInt("Digite su ID personal");
		int tipo = vista.leerInt("Digite el tipo de servicio 0: BLACK, 1: X, 2: POOL");
		LocalDate fecha = LocalDate.now();
		int distancia = vista.leerInt("Digite la distancia a viajar");
		try{
			asignado = modelo.solicitarServicio(nombre, id, tipo, fecha, distancia);
			vista.imprimir("Se encontro un servicio.");
			vista.imprimir("Conductor: " + asignado.getNombre());
			vista.imprimir("Placa: " + asignado.getPlaca());
			vista.imprimir("Su Uber llegara pronto. Gracias por esperar");
		}
		catch (Exception e){
			vista.imprimir("Error en Solicitar Servicio");
		}
	}
	/**
	 * Intenta terminar el servicio de un conductor llamando al método de la clae Uber, pero
	 * atrapa la expecíon en dado caso que se presente algún error.
	 * @param  String nombreP
	 * @return
	 */
	public void terminarServicio(){	
		String pNombre = vista.leerString("Ingrese el nombre del conductor para terminar carrera");
		try{
			modelo.terminarCarrera(pNombre);
			vista.imprimir("Servicio terminado");
		}
		catch (Exception e){
			vista.imprimir("Error en Terminar Servicio: " + e.getMessage());
		}
	}
	/**
	 * Le pide al modelo la lista de usuarios existentes para luego uno por uno ver todos los servicios que han pedido para poder calcular el 
	 * dinero gastado de cada uno de ellos y ademas un valor total 
	 */
	public void darDineroUsuario(){
		int valorInd = 0;
		int valorTotal = 0;
		int cont = 0;
		int i = 1;
		List<Usuario> us;
		List<Servicio> ser;
		us = modelo.darUsuarios();
		for(Usuario u: us){
			vista.imprimir("Usuario #" + i + ":");
			ser = u.darServiciosTomados();
			for(Servicio s: ser){
				valorInd += s.getValor();
				cont++;
				i++;
			}
			valorTotal += valorInd;
			vista.imprimir("El total de dinero gastado en servicios del usuario fue de: " + valorInd);
			valorInd = 0;
		}
		vista.imprimir("El total gastado por todos los usuarios que tomaron servicios en el sistema de UBER fue de: " + valorTotal);
		vista.imprimir("La cantidad de viajes que se hicieron en total fue de: " + cont);
	}
	/**
	 * Le pide al modelo la lista de conductores existentes para luego uno por uno ver todos los servicios que han tomado para poder calcular el 
	 * dinero recuadado de cada uno de ellos y ademas un valor total 
	 */
	public void darDineroConductor(){
		int valorInd = 0;
		int valorTotal = 0;
		int cont = 0;
		int i = 1;
		List<Conductor> con;
		List<Servicio> ser;
		con = modelo.darConductores();
		for(Conductor cond: con){
			vista.imprimir("Conductor #" + i + ":");
			ser = cond.darServiciosAsignados();
			for(Servicio s: ser){
				valorInd += s.getValor();
				cont++;
				i++;
			}
			valorTotal += valorInd;
			vista.imprimir("El total de dinero recaudado en servicios del conductor fue de: " + valorInd);
		}
		vista.imprimir("El total recuadado por todos los conductores que realizaron servicios en el sistema de UBER fue de: " + valorTotal);
		vista.imprimir("La cantidad de viajes que se hicieron en total fue de: " + cont);	
	}
	/**
	 * Le pide al modelo la lista de usuarios existentes para luego uno por uno ver todos los servicios que han pedido para poder calcular la
	 * cantidad pedida de un determinado tipo de servicio
	 */
	public void darCantidadServicios(){
		int contPool = 0;
		int contBlack = 0;
		int contX = 0;
		List<Usuario> us;
		List<Servicio> ser;
		us = modelo.darUsuarios();
		for(Usuario u: us){
			ser = u.darServiciosTomados();
			for(Servicio s: ser){
				if(s.getTipo() == 0){
					contBlack++;
				}else if(s.getTipo() == 1){
					contX++;
				}else{
					contPool++;
				}
			}
		}
		vista.imprimir("La cantidad de servicios de UBER BLACK que se realizaron fue de: " + contBlack);
		vista.imprimir("La cantidad de servicios de UBER X que se realizaron fue de: " + contX);
		vista.imprimir("La cantidad de servicios de UBER POOL que se realizaron fue de: " + contPool);
	}
}