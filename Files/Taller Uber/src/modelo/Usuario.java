package modelo;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class Usuario {
	//
	//ATRIBUTOS
	//
	/**
	 * Nombre del usuario
	 */
	private String nombre;
	/**
	 * ID del usuario
	 */
	private int id;
	//
	//RELACIONES
	//
	/**
	 * Cada usuario tiene una lista de servicios que ha tomado
	 */
	private List<Servicio> serviciosTomados;
	//
	//CONSTRUCTOR
	//
	public Usuario(String pNombre, int pId){
		this.nombre = pNombre;
		this.id = pId;
		serviciosTomados = new ArrayList<Servicio>();
	}
	//
	//MÉTODOS
	//
	/**
	 * El usuario que pide un servicio crea una instancia de este donde se le asigna un conductor.
	 * @param fecha
	 * @param distancia
	 * @param tipo
	 * @param asignado
	 * @return
	 */
	public Servicio solicitarServicio(LocalDate fecha, int distancia, int tipo, Conductor asignado){
		Servicio ser = new Servicio(fecha, distancia, tipo);
		ser.setPasajero(this);
		ser.setConductor(asignado);
		return ser;
		
	}
	/**
	 * Se le asigna al usuario un servicio pedido por este anteriormente
	 * @param ser
	 */
	public void solicitudServicioUsuario(Servicio ser){
		serviciosTomados.add(ser);
	}
	/**
	 * Lista de todos los servicios que le han sido tomados por un usuario
	 * @return
	 */
	public List<Servicio> darServiciosTomados(){
		return serviciosTomados;
	}
	//
	//GETTERS Y SETTERS
	//
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
