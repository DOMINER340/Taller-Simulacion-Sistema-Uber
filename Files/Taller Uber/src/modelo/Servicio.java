package modelo;
import java.time.LocalDate;

public class Servicio {
	//
	//ATRIBUTOS
	//
	/**
	 * Tipo de servicio Uber BLACK
	 */
	private static final int TIPO_BLACK = 0;
	/**
	 * Tipo de servicio Uber X
	 */
	private static final int TIPO_X = 1;
	/**
	 * Tipo de servicio Uber POOL
	 */
	private static final int TIPO_POOL = 2;
	/**
	 * Fecha en la que se tomo un determinado servicio
	 */
	private LocalDate fecha;
	/**
	 * Valor que se tiene que pagar por parte del usuario (Depende de la distancia a viajar y del tipo de servicio que se pidio)
	 */
	private int valor;
	/**
	 * Distancia que se va a viajar en el servicio
	 */
	private int distancia;
	/**
	 * Tipo de servicio
	 */
	private int tipo;
	//
	//RELACIONES
	//
	/**
	 * Cada servicio tiene asignado un pasajero
	 */
	private Usuario pasajero;
	/**
	 * Cada servicio tiene asignado un conductor
	 */
	private Conductor conductor;
	//
	//CONSTRUCTOR
	//
	public Servicio(LocalDate pFecha, int pDistancia, int pTipo){
		this.fecha = pFecha;
		this.distancia = pDistancia;
		this.tipo = pTipo;
		this.valor = darTarifaBase();
	}
	//
	//MÉTODOS
	//
	/**
	 * Calcula la tarifa base del servicio pedido dependiendo del tipo que se pidio y la distancia a recorrer
	 * @return
	 */
	public int darTarifaBase(){
		if(this.tipo == 0){
			this.valor = Uber.TARIFA_BASE_BLACK + (this.distancia*40);
		}
		if(this.tipo == 1){
			this.valor = Uber.TARIFA_BASE_X  + (this.distancia*40);
		}
		if(this.tipo == 2){
			this.valor = Uber.TARIFA_BASE_POOL + (this.distancia*40);
		}
		return this.valor;
	}
	//
	//GETTERS & SETTERS
	//
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getDistancia() {
		return distancia;
	}
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Usuario getPasajero() {
		return pasajero;
	}
	public void setPasajero(Usuario pasajero) {
		this.pasajero = pasajero;
	}
	public Conductor getConductor() {
		return conductor;
	}
	public void setConductor(Conductor conductor) {
		this.conductor = conductor;
	}
	
	
}

