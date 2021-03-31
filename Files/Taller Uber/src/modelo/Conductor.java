package modelo;
import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author 
 *
 */
public class Conductor {
	//
	// ATRIBUTOS
	//
	/**
	 * Nombre del conductor
	 */
	private String nombre;
	/**
	 * Placa del carro del conductor
	 */
	private String placa;
	/**
	 * Si el conductor esta disponible para iniciar una nueva carrera
	 */
	private boolean estaDisponible;
	
	//
	//RELACIONES
	//
	/**
	 * Lista de todos los servicios que le han sido asignados a un conductor
	 */
	private List<Servicio> serviciosAsignados;
	//
	//CONSTRUCTOR
	//
	/**
	 * @param pNombre: Nombre que se le va a dar al conductor
	 * @param pPlaca: Placa del carro del conductor en especifico
	 */
	public Conductor(String pNombre, String pPlaca){
		this.nombre = pNombre;
		this.placa = pPlaca;
		this.estaDisponible = true;
		serviciosAsignados = new ArrayList<Servicio>();
	}
	//
	//MÉTODOS
	//
	/**
	 * Asigna un servicio si el conductor esta disponible.
	 * @param tarifa
	 * @return Si pudo asignar el servicio
	 */
	public boolean asignarCarrera(){
		boolean pudoAsignar = false;
		if(estaDisponible){
			pudoAsignar = true;
			estaDisponible = false;
		}
		return pudoAsignar;
	}
	/**
	 * Termina el servicio de un conductor si este no se ecnuenta disponible.
	 * Lanza una excepción a la clase Uber si el servicio está activo y el conductor está disponible.
	 */
	public void terminarCarrera() throws Exception{
		if(!estaDisponible){
			estaDisponible = true;
		}
		else{
			throw new Exception("No se pudo terminar la carrera del conductor. Se encuentra disponible.");
		}
	}
	/**
	 * Se le asigna al conductor un servicio pedido anteriormente por un usuario
	 * @param ser
	 */
	public void solicitudServicioConductor(Servicio ser){
		serviciosAsignados.add(ser);
	}
	/**
	 * Retorna a la clase Uber la lista de todos los servicios que a tomado el conductor
	 * @return
	 */
	public List<Servicio> darServiciosAsignados(){
		return serviciosAsignados;
	}
	//
	//GETTERS Y SETTERS
	//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String pNombre){
		this.nombre = pNombre;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String pPlaca){
		this.placa = pPlaca;
	}
	public boolean getEstaDisponible() {
		return estaDisponible;
	}
	public void setEstaDisponible(boolean estaDisponible) {
		this.estaDisponible = estaDisponible;
	}

}
