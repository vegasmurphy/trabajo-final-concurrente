package Misiles;
import java.io.Serializable;


public abstract class Misil implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Vector posicion;
	private Vector velocidadInicial;
	private int ID;
	
	

	public Misil(Vector posicion, Vector velocidad,int ID) {
		super();
		this.posicion = posicion;
		this.velocidadInicial = velocidad;
		this.ID=ID;
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




	public int getID() {
		return ID;
	}




	public void setID(int iD) {
		ID = iD;
	}
	
}
