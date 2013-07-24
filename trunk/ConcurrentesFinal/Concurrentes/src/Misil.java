import java.io.Serializable;


public abstract class Misil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Vector posicion;
	private Vector velocidadInicial;
	
	
	

	public Misil(Vector posicion, Vector velocidad) {
		super();
		this.posicion = posicion;
		this.velocidadInicial = velocidad;
	}




	public Vector getPosicion() {
		return posicion;
	}




	public void setPosicion(Vector posicion) {
		this.posicion = posicion;
	}




	public Vector getVelocidad() {
		return velocidadInicial;
	}




	public void setVelocidad(Vector velocidad) {
		this.velocidadInicial = velocidad;
	}




	public abstract void avanzar();
	
}
