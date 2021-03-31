package modelo;

import modelo.Conductor;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Uber {
	//
	//ATRIBUTOS
	//
	/**
	 * Tarifa base del tipo de servicio BLACK
	 */
	public static final int TARIFA_BASE_BLACK = 8000;
	/**
	 * Tarifa base del tipo de servicio X
	 */
	public static final int TARIFA_BASE_X = 5000;
	/**
	 * Tarifa base del tipo de servicio POOL
	 */
	public static final int TARIFA_BASE_POOL = 3000;

	//
	//RELACIONES
	//
	/**
	 * El sistema tiene una lista de conductores que hacen parte de Uber
	 */
	private List <Conductor> conductores;
	/**
	 * El sistema tiene una lista de usuarios que hacen parte de Uber
	 */
	private List <Usuario> usuarios;
	//
	//CONSTRUCTOR
	//
	public Uber(){
		conductores = new ArrayList<Conductor>();
		usuarios = new ArrayList<Usuario>();
	}
	//
	//MÉTODOS
	//
	/**
	 * Agrega un nuevo conductor a la lista de conductores.
	 * @param String pNombre, String pPlaca
	 * @return Si este no existía al ser buscado
	 */
	public Conductor agregarConductor(String pNombre, String pPlaca){

		Conductor buscado = buscarConductor(pNombre);
		Conductor respuesta = null;
		if(buscado == null){
			Conductor Nuevo = new Conductor(pNombre, pPlaca);
			conductores.add(Nuevo);
			respuesta = Nuevo;
		}
		return respuesta;
	}
	/**
	 * Agrega un nuevo conductor a la lista de conductores.
	 * @param String pNombre, String pPlaca
	 * @return Si este no existía al ser buscado
	 */
	public Usuario agregarUsuario(String pNombre, int pId){

		Usuario buscado = buscarUsuario(pNombre);
		Usuario respuesta = null;
		if(buscado == null){
			Usuario Nuevo = new Usuario(pNombre, pId);
			usuarios.add(Nuevo);
			respuesta = Nuevo;
		}
		return respuesta;
	}
	/**
	 * Busca un conductor en la lista de conductores usando su nombre como identificador.
	 * @param String pNombre
	 * @return Si este fue encontrado despues de buscar en la lista
	 */
	public Conductor buscarConductor(String pNombre){
		Conductor encontrado = null;
		int tam = conductores.size();
		for(int i = 0;i<tam;i++){
			Conductor actual= conductores.get(i);
			if(actual.getNombre().equalsIgnoreCase(pNombre)){
				encontrado = actual;
				break;
			}
		}
		return encontrado;
	}
	/**
	 * Busca un conductor en la lista de conductores usando su nombre como identificador.
	 * @param String pNombre
	 * @return Si este fue encontrado despues de buscar en la lista
	 */
	public Usuario buscarUsuario(String pNombre){
		Usuario encontrado = null;
		int tam = usuarios.size();
		for(int i = 0;i<tam;i++){
			Usuario actual= usuarios.get(i);
			if(actual.getNombre().equalsIgnoreCase(pNombre)){
				encontrado = actual;
				break;
			}
		}
		return encontrado;
	}
	/**
	 * Retorna la lista de usuarios al Controlador.
	 * @param
	 * @return Toda la lista de usuarios
	 */
	public List<Usuario> darUsuarios(){
		return usuarios;
	}
	/**
	 * Retorna la lista de conductores al Controlador.
	 * @param
	 * @return Toda la lista de conductores
	 */
	public List<Conductor> darConductores(){
		return conductores;
	}
	/**
	 * Método que solicita el servicio de un conductor que esté disponible
	 * @param tarifa - Tarifa que se va a pagar por el servicio.
	 * @return 
	 * @throws Exception - Lanza una excepción si todos los conductores se encuentran ocupados.
	 */
	public Conductor solicitarServicio(String nombre, int id, int tipo, LocalDate fecha, int distancia) throws Exception{
		int cont = 0;
		Servicio ser;
		Usuario nuevo = agregarUsuario(nombre, id);
		for(Conductor con: conductores){
			if(con.getEstaDisponible()){
				ser = nuevo.solicitarServicio(fecha, distancia, tipo, con);
				con.asignarCarrera();
				con.solicitudServicioConductor(ser);
				nuevo.solicitudServicioUsuario(ser);
				return con;
			}
		}if(cont == 0){
			throw new Exception("No se pudo solicitar el servicio. No se encuentran conductores disponibles");
		}
		return null;
	}
	/**
	 * Finaliza el servicio de un conductor.
	 * @param String pNombre
	 * Lanza una expepción si el conductor buscado no existe
	 * Recibe y lanza hacia el Controlador la exepción de la clase Conductor si este se encuentra disponible
	 * cuando se quiere finalizar el servicio.
	 */
	public void terminarCarrera(String nombre) throws Exception{
		Conductor buscado = buscarConductor(nombre);
		if( buscado == null){
			throw new Exception("El conductor no existe");
		}
		else{
			buscado.terminarCarrera();
		}
	}
}

